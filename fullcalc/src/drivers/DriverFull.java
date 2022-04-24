package drivers;
import domini.*;
import java.time.LocalDate;
import java.util.Scanner;

public class DriverFull {

    static Full full;

    static Scanner entry;

    public static void setUp() {
        entry = new Scanner(System.in);

        System.out.println("Introdueix el nombre de files de la matriu");

        int n = entry.nextInt();

        System.out.println("Introdueix el nombre de columnes de la matriu");

        int m = entry.nextInt();

        System.out.println("Introdueix el nombre de celes no buides a inicialitzar");

        full = new Full(n, m);

        int k = entry.nextInt();

        for (int p = 0; p < k; ++p) {
            System.out.println("Introdueix la fila en que vols inicialitzar " +
                    "la cela");
            int i = entry.nextInt();
            System.out.println("Introdueix la columna en que vols " +
                    "inicialitzar " +
                    "la cela");
            int j = entry.nextInt();

            System.out.println("Introdueix el tipus de la Cela que vols " +
                    "inicialitzar en el següent format:");
            System.out.println("Textual - Numerica - Datada - Referencia");

            String tipus = entry.next();

            switch (tipus) {
                case "Textual":
                    System.out.println("Introdueix el text de la Cela");
                    String input = entry.next();

                    CelaText ct = new CelaText(input);
                    full.setCela(ct, i, j);
                    break;
                case "Numerica":
                    System.out.println("Introdueix el valor de la Cela");
                    double val = entry.nextDouble();

                    CelaNum cn = new CelaNum("val", val);
                    full.setCela(cn, i, j);
                    break;
                case "Datada":
                    System.out.println("Introdueix l'any de la Cela");
                    int any = entry.nextInt();
                    System.out.println("Introdueix el mes de la Cela");
                    int mes = entry.nextInt();
                    System.out.println("Introdueix el dia de la Cela");
                    int dia = entry.nextInt();

                    LocalDate data = LocalDate.of(any, mes, dia);
                    CelaData cd = new CelaData(data.toString(), data);
                    full.setCela(cd, i, j);
                    break;
                case "Referencia":
                    System.out.println("Introdueix la fila de la Cela " +
                            "referenciada");
                    int ir = entry.nextInt();
                    System.out.println("Introdueix la columna de la Cela " +
                            "referenciada");
                    int jr = entry.nextInt();
                    full.getCela(ir, jr);
                    CelaRef cr = new CelaRef("ref", full.getCela(ir, jr));

                    full.setCela(cr, i, j);
                    break;
                default:
                    System.out.println("Tipus no valid, torna a començar el " +
                            "proces d'enregistrar la cela.");
                    --p;
                    break;
            }
        }
    }

    public static void TestGetCela(int fila, int col) {
        Cela c = full.getCela(fila, col);
        if (c == null) System.out.println(c);
        else System.out.println(c.getInputUsuari());
    }

    public static void TestSetCela(Cela novaCela, int fila, int col) {
        full.setCela(novaCela, fila, col);
    }

    public static void TestBuidaBloc(int filaIni, int colIni, int numFiles, int numCols) {
        full.buidaBloc(filaIni, colIni, numFiles, numCols);
    }

    public static void TestCopiaBloc(int filaIni, int colIni, int numFiles, int numCols, int filaFi, int colFi) {
        full.copiaBloc(filaIni, colIni, numFiles, numCols, filaFi, colFi);
    }

    public static void TestMouBloc(int filaIni, int colIni, int numFiles, int numCols, int filaFi, int colFi) {
        full.mouBloc(filaIni, colIni, numFiles, numCols, filaFi, colFi);
    }

    public static void TestBlocInvalid(int filaIni, int colIni, int numFiles, int numCols) {
        System.out.println(full.blocInvalid(filaIni, colIni, numFiles, numCols));
    }

    public static void imprimir() {
        for (int i = 0; i < full.getNumFiles(); ++i) {
            for (int j = 0; j < full.getNumCols(); ++j) {
                Cela c = full.getCela(i, j);
                if (c == null) System.out.print("--  ");
                else {
                    switch (c.getTipusCela()) {
                        case NUMERICA:
                            System.out.print(c.getNum() + "  ");
                            break;
                        case TEXTUAL:
                            System.out.print(c.getText() + "  ");
                            break;
                        case DATADA:
                            System.out.print(c.getData().toString() + "  ");
                            break;
                        case REFERENCIAL:
                            System.out.println(c.getInputUsuari() + "  ");
                            break;
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        setUp();
        System.out.println("Imprimint la matriu inicial");
        imprimir();

        while(true) {

            System.out.println("Introdueix dos int per obtenir l'inputUsuari de la Cela de la posicio (int, int)");
            int fil = entry.nextInt();
            int col = entry.nextInt();
            System.out.println("Executant getCela");
            TestGetCela(fil, col);

            System.out.println("Introdueix un String i dos int per crear una nova Cela a la posicio (int, int)");
            String input = entry.next();
            fil = entry.nextInt();
            col = entry.nextInt();
            System.out.println("Executant setCela i imprimint el full");
            TestSetCela(new CelaText(input), fil, col);
            imprimir();

            System.out.println("Introdueix 4 int per indicar la fila inicial, la columna inicial, el numero de files i" +
                    " el numero de columnes del bloc");
            fil = entry.nextInt();
            col = entry.nextInt();
            int numFil = entry.nextInt();
            int numCol = entry.nextInt();
            System.out.println("Executant buidaBloc i imprimint el full");
            TestBuidaBloc(fil, col, numFil, numCol);
            imprimir();

            System.out.println("Introdueix 6 int per indicar la fila inicial, la columna inicial, el numero de files," +
                    " el numero de columnes del bloc, la primera fila del bloc destí i la primera columna d'aquest" +
                    " mateix");
            fil = entry.nextInt();
            col = entry.nextInt();
            numFil = entry.nextInt();
            numCol = entry.nextInt();
            int filFi = entry.nextInt();
            int colFi = entry.nextInt();
            System.out.println("Executant copiaBloc i imprimint el full");
            TestCopiaBloc(fil, col, numFil, numCol, filFi, colFi);
            imprimir();

            System.out.println("Introdueix 6 int per indicar la fila inicial, la columna inicial, el numero de files," +
                    " el numero de columnes del bloc, la primera fila del bloc destí i la primera columna d'aquest" +
                    " mateix");
            fil = entry.nextInt();
            col = entry.nextInt();
            numFil = entry.nextInt();
            numCol = entry.nextInt();
            filFi = entry.nextInt();
            colFi = entry.nextInt();
            System.out.println("Executant mouBloc i imprimint el full");
            TestMouBloc(fil, col, numFil, numCol, filFi, colFi);
            imprimir();

            System.out.println("Introdueix 4 int per indicar la fila inicial, la columna inicial, el numero de files i" +
                    " el numero de columnes del bloc");
            fil = entry.nextInt();
            col = entry.nextInt();
            numFil = entry.nextInt();
            numCol = entry.nextInt();
            System.out.println("Executant blocInvalid");
            TestBlocInvalid(fil, col, numFil, numCol);
            System.out.println("Imprimint la matriu final");
            imprimir();
        }
    }
}