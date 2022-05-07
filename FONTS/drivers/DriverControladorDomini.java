package drivers;

import domini.*;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverControladorDomini {
    static ControladorDomini contr;
    static Scanner entry;

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

    public static void imprimirDoc() {
        if (contr.getDocument() == null)
            return;

        int n = contr.getDocument().getNumFulls();
        for (int i = 0; i < n; ++i) imprimir(contr.getDocument().getFull(i));
    }

    public static void main(String[] args) {
        entry  = new Scanner(System.in);
        contr = new ControladorDomini();
        while (true) {
            System.out.println("Introdueix el nombre d'strings " +
                    "necessaris per codificar l'operació que vol " +
                    "executar");

            int num = entry.nextInt();

            System.out.println("Introdueixi els strings necessaris per " +
                    "codificar l'operació de document a parsejar");
            String opSenseParsejar[] = new String[num];
            for (int i = 0; i < num; ++i) {
                opSenseParsejar[i] = entry.next();
            }

            contr.executaOperacio(opSenseParsejar);
            imprimirDoc();
            System.out.println("Introdueix 1 per continuar introduint un nou " +
                    "parsejat o 0 per abortar");
            int sortir = entry.nextInt();
            if (sortir == 0) return;
        }
    }
}
