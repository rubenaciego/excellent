package drivers;

import domini.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverOperador {
    static Operador op;
    static MatriuCeles bloc;

    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);

        System.out.println("Introdueix el nombre de files del bloc sobre el " +
                "que operar");

        int n = entry.nextInt();

        System.out.println("Introdueix el nombre de columnes del bloc sobre " +
                "el que operar");

        int m = entry.nextInt();

        System.out.println("Introdueix el nombre de celes no buides a " +
                "inicialitzar");

        bloc = new MatriuCeles(n, m);

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
                    bloc.setCela(ct, i, j);
                    break;
                case "Numerica":
                    System.out.println("Introdueix el valor de la Cela");
                    double val = entry.nextDouble();

                    CelaNum cn = new CelaNum("val", val);
                    bloc.setCela(cn, i, j);
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
                    bloc.setCela(cd, i, j);
                    break;
                case "Referencia":
                    System.out.println("Introdueix la fila de la Cela " +
                            "referenciada");
                    int ir = entry.nextInt();
                    System.out.println("Introdueix la columna de la Cela " +
                            "referenciada");
                    int jr = entry.nextInt();
                    bloc.getCela(ir, jr);
                    CelaRef cr = new CelaRef("ref", bloc.getCela(ir, jr));

                    bloc.setCela(cr, i, j);
                    break;
                default:
                    System.out.println("Tipus no valid, torna a començar el " +
                            "proces d'enregistrar la cela.");
                    --p;
                    break;
            }
        }

        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
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
}
