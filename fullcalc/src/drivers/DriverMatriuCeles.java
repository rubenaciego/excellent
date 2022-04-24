package drivers;
import domini.*;

import java.util.ArrayList;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class DriverMatriuCeles {

    static MatriuCeles matriu;

    static Scanner entry;

    public static void setUp() {
        entry = new Scanner(System.in);

        System.out.println("Introdueix el nombre de files de la matriu");

        int n = entry.nextInt();

        System.out.println("Introdueix el nombre de columnes de la matriu");

        int m = entry.nextInt();

        System.out.println("Introdueix el nombre de celes no buides a inicialitzar");

        matriu = new MatriuCeles(n, m);

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
                    matriu.setCela(ct, i, j);
                    break;
                case "Numerica":
                    System.out.println("Introdueix el valor de la Cela");
                    double val = entry.nextDouble();

                    CelaNum cn = new CelaNum("val", val);
                    matriu.setCela(cn, i, j);
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
                    matriu.setCela(cd, i, j);
                    break;
                case "Referencia":
                    System.out.println("Introdueix la fila de la Cela " +
                            "referenciada");
                    int ir = entry.nextInt();
                    System.out.println("Introdueix la columna de la Cela " +
                            "referenciada");
                    int jr = entry.nextInt();
                    matriu.getCela(ir, jr);
                    CelaRef cr = new CelaRef("ref", matriu.getCela(ir, jr));

                    matriu.setCela(cr, i, j);
                    break;
                default:
                    System.out.println("Tipus no valid, torna a començar el " +
                            "proces d'enregistrar la cela.");
                    --p;
                    break;
            }
        }
    }

    public static void TestGetNumFiles() {
        System.out.println(matriu.getNumFiles());
    }

    public static void TestGetNumCols() {
        System.out.println(matriu.getNumCols());
    }

    public static void TestGetCela(int fila, int col) {
        Cela c = matriu.getCela(fila, col);
        if (c == null) System.out.println(c);
        else System.out.println(c.getInputUsuari());
    }

    public static void TestSetCela(Cela novaCela, int fila, int col) {
        matriu.setCela(novaCela, fila, col);
    }

    public static void TestEsborraCela(int fila, int col) {
        matriu.esborraCela(fila, col);
    }

    public static void TestAfegeixFila() {
        matriu.afegeixFila();
    }

    public static void TestAfegeixColumna() {
        matriu.afegeixColumna();
    }

    public static void TestEliminaFila(int fila) {
        matriu.eliminaFila(fila);
    }

    public static void TestEliminaColumna(int col) {
        matriu.eliminaColumna(col);
    }

    public static void TestGetBloc(int filaIni, int colIni, int numFiles, int numCols) {
        MatriuCeles matr = matriu.getBloc(filaIni, colIni, numFiles, numCols);
        imprimir(matr);
    }

    public static void TestGetEntrades() {
        ArrayList<EntradaMatriuCeles> entrades = matriu.getEntrades();
        for (EntradaMatriuCeles e : entrades) {
            System.out.println("Cela: " + e.getFila() + ", " +
                    e.getColumna() + " de tipus " + e.getCela().getTipusCela() + " amb ");
            switch (e.getCela().getTipusCela()) {
                case NUMERICA:
                    System.out.println("Valor: " + e.getCela().getNum());
                    break;
                case TEXTUAL:
                    System.out.println("Text: " + e.getCela().getText());
                    break;
                case DATADA:
                    System.out.println("Data: " + e.getCela().getData().toString());
                    break;
                case REFERENCIAL:
                    System.out.println("Referencia");
                    break;
            }
        }
    }

    public static void TestGetEntradesCol(int col) {
        ArrayList<EntradaMatriuCeles> entrades = matriu.getEntradesColumna(col);
        for (EntradaMatriuCeles e : entrades) {
            System.out.print("Cela: " + e.getFila() + ", " +
                    e.getColumna() + " de tipus " + e.getCela().getTipusCela() + " amb ");
            switch (e.getCela().getTipusCela()) {
                case NUMERICA:
                    System.out.println("Valor: " + e.getCela().getNum());
                    break;
                case TEXTUAL:
                    System.out.println("Text: " + e.getCela().getText());
                    break;
                case DATADA:
                    System.out.println("Data: " + e.getCela().getData().toString());
                    break;
                case REFERENCIAL:
                    System.out.println("Referencia");
                    break;
            }
        }
    }

    public static void TestBlocInvalid(int filaIni, int colIni, int numFiles, int numCols) {
        System.out.println(matriu.blocInvalid(filaIni, colIni, numFiles, numCols));
    }

    public static void imprimir(MatriuCeles matr) {
        for (int i = 0; i < matr.getNumFiles(); ++i) {
            for (int j = 0; j < matr.getNumCols(); ++j) {
                Cela c = matr.getCela(i, j);
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

        while(true) {
            System.out.println("Executant getNumFiles()");
            TestGetNumFiles();

            System.out.println("Executant getNumCols()");
            TestGetNumCols();

            System.out.println("Introdueix dos int per obtenir l'inputUsuari de la Cela de la posicio (int, int)");
            int fil = entry.nextInt();
            int col = entry.nextInt();
            System.out.println("Executant getCela");
            TestGetCela(fil, col);

            System.out.println("Introdueix un String i dos int per crear una nova Cela a la posicio (int, int)");
            String input = entry.next();
            fil = entry.nextInt();
            col = entry.nextInt();
            System.out.println("Executant setCela i imprimint la matriu");
            TestSetCela(new CelaText(input), fil, col);
            imprimir(matriu);

            System.out.println("Introdueix dos int per a esborrar la Cela de la posicio (int, int)");
            fil = entry.nextInt();
            col = entry.nextInt();
            System.out.println("Executant esborraCela i imprimint la matriu");
            TestEsborraCela(fil, col);
            imprimir(matriu);

            System.out.println("Executant afegeixFila i imprimint el nou numero total de files");
            TestAfegeixFila();
            TestGetNumFiles();

            System.out.println("Executant afegeixCol i imprimint el nou numero total de columnes");
            TestAfegeixColumna();
            TestGetNumCols();

            System.out.println("Imprimint la matriu");
            imprimir(matriu);

            System.out.println("Introdueix un int per a indicar la fila a esborrar");
            fil = entry.nextInt();
            System.out.println("Executant eliminaFila i imprimint la matriu");
            TestEliminaFila(fil);
            imprimir(matriu);

            System.out.println("Introdueix un int per a indicar la columna a esborrar");
            col = entry.nextInt();
            System.out.println("Executant eliminaCol i imprimint la matriu");
            TestEliminaColumna(col);
            imprimir(matriu);

            System.out.println("Introdueix 4 int per indicar la fila inicial, la columna inicial, el numero de files i" +
                    " el numero de columnes del bloc");
            fil = entry.nextInt();
            col = entry.nextInt();
            int numFil = entry.nextInt();
            int numCol = entry.nextInt();
            System.out.println("Executant getBloc i imprimint-lo");
            TestGetBloc(fil, col, numFil, numCol);

            System.out.println("Executant getEntrades i imprimint-lo");
            TestGetEntrades();

            System.out.println("Introdueix un int per a indicar la columna a obtenir");
            col = entry.nextInt();
            System.out.println("Executant getEntradesCol i imprimint-lo");
            TestGetEntradesCol(col);

            System.out.println("Introdueix 4 int per indicar la fila inicial, la columna inicial, el numero de files i" +
                    " el numero de columnes del bloc");
            fil = entry.nextInt();
            col = entry.nextInt();
            numFil = entry.nextInt();
            numCol = entry.nextInt();
            System.out.println("Executant blocInvalid");
            TestBlocInvalid(fil, col, numFil, numCol);

            System.out.println("Imprimint la matriu final");
            imprimir(matriu);

            System.out.println("Desitges sortir de l'executable? Introdueix un 0 si vols sortir, o entra qualsevol" +
                    " altre caracter si es que vols continuar");
            input = entry.next();
            if (Objects.equals(input, "0")) break;
        }
    }
}