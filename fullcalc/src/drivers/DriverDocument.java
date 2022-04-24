package drivers;

import domini.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class DriverDocument {
    static Document doc;
    static Scanner entry;

    public static void imprimir() {
        System.out.println("Document amb nom: " + doc.getNom());
        System.out.println("Ultima modificacio desada en data: " + doc.getDataModificacio().toString());
        System.out.println("Nombre de fulls: " + doc.getNumFulls());
    }

    public static void imprimir(Full full) {
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
        entry = new Scanner(System.in);

        System.out.println("Introdueix el nom del document");
        String nom = entry.next();

        doc = new Document(nom);

        imprimir();

        while (true) {
            System.out.println("Introdueix la operacio a realitzar:");
            String op = entry.next();

            switch (op) {
                case "SET_NOM":
                    System.out.println("Introdueix el nou nom del document:");
                    String nouNom = entry.next();
                    doc.setNom(nouNom);
                    imprimir();
                    break;
                case "SET_DATA":
                    System.out.println("Introdueix l'any de la nova data");
                    int any = entry.nextInt();
                    System.out.println("Introdueix el mes de la nova data");
                    int mes = entry.nextInt();
                    System.out.println("Introdueix el dia de la nova data");
                    int dia = entry.nextInt();
                    System.out.println("Introdueix l'hora de la nova data");
                    int hora = entry.nextInt();
                    System.out.println("Introdueix el minut de la nova data");
                    int min = entry.nextInt();
                    System.out.println("Introdueix el segon de la nova data");
                    int sec = entry.nextInt();

                    LocalDateTime data = LocalDateTime.of(any, mes, dia, hora,
                            min, sec);
                    doc.setDataModificacio(data);
                    imprimir();
                    break;
                case "AFEGIR_FULL":
                    doc.afegeixFull();
                    imprimir();
                    break;
                case "ELIMINAR_FULL":
                    System.out.println("Introdueix l'id del full a eliminar:");
                    int idE = entry.nextInt();
                    doc.eliminaFull(idE);
                    imprimir();
                    break;
                case "OBTE_FULL":
                    System.out.println("Introdueix l'id del full a obtenir:");
                    int idG = entry.nextInt();
                    Full f = doc.getFull(idG);
                    imprimir(f);
                    imprimir();
                    break;
                case "FI":
                    return;
            }
        }
    }
}
