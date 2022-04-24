package drivers;

import domini.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DriverParser {

    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);

        while (true) {

            //parse operacions document
            System.out.println("Introdueix el nombre d'strings " +
                    "necessaris per codificar l'operació que vol " +
                    "executar");

            int num = Integer.parseInt(entry.nextLine());

            System.out.println("Introdueixi els strings necessaris per " +
                    "codificar l'operació de document a parsejar");
            String opSenseParsejar[] = new String[num];
            for (int i = 0; i < num; ++i) {
                opSenseParsejar[i] = entry.nextLine();
            }

            System.out.println("Parsejant");

            ResultatParserDocument resultat =
                    Parser.getInstance().parseOpDocument(opSenseParsejar);

            System.out.println("L'operació retorna una instància de " +
                    "ResultatParserDocument amb els següents camps");

            System.out.println("idFull = " + resultat.getIdFull());
            System.out.println("tipusOperacioDocument = " + resultat.getTipusOpDocument());
            System.out.println("nomDocument = " + resultat.getNomDocument());


            System.out.println("Introdueix el nombre d'strings " +
                    "necessaris per codificar l'operació que vol " +
                    "executar");

            num = Integer.parseInt(entry.nextLine());

            //parse operacions full
            System.out.println("Introdueixi els strings necessaris per " +
                    "codificar l'operació de full a parsejar");
            opSenseParsejar = new String[num];
            for (int i = 0; i < num; ++i) {
                opSenseParsejar[i] = entry.nextLine();
            }

            System.out.println("Parsejant");

            ResultatParserFull resultat2 =
                    Parser.getInstance().parseOpFull(opSenseParsejar);

            System.out.println("L'operació retorna una instància de " +
                    "ResultatParserFull amb els següents camps");

            System.out.println("idFull = " + resultat2.getIdFull());
            System.out.println("filaOrigen = " + resultat2.getFilaOrigen());
            System.out.println("filaDesti = " + resultat2.getFilaDesti());
            System.out.println("columnaOrigen = " + resultat2.getColumnaOrigen());
            System.out.println("columnaDesti = " + resultat2.getColumnaDesti());
            System.out.println("midaFila = " + resultat2.getMidaFila());
            System.out.println("midaColumna = " + resultat2.getMidaColumna());
            System.out.println("digitsTruncar = " + resultat2.getDigitsTruncar());
            System.out.println("filaColEliminar = " + resultat2.getFilaColEliminar());
            System.out.println("columnaOrdenacio = " + resultat2.getColumnaOrdenacio());
            System.out.println("tipusOperacioFull = " + resultat2.getTipusOpFull());
            System.out.println("tipusOperacioAritmetica = " + resultat2.getTipusOpAritmetica());
            System.out.println("tipusOperacioEstadistica = " + resultat2.getTipusOpEstadistica());
            System.out.println("tipusConversioUnitats = " + resultat2.getTipusConversioUnitats());
            System.out.println("tipusCriteriOrdenacio = " + resultat2.getTipusCriteriOrdenacio());
            System.out.println("stringCercada = " + resultat2.getStringCercada());
            System.out.println("stringReemplacadora = " + resultat2.getStringRemplacadora());
            if (resultat2.getResultatParserCela() != null) {
                System.out.println("resultatParserCela te: ");
                System.out.println("inputUsuari = " + resultat2.getResultatParserCela().getInputUsuari());
                System.out.println("valorNumeric = " + resultat2.getResultatParserCela().getValorNumeric());
                System.out.println("data = " + resultat2.getResultatParserCela().getData());
                System.out.println("filaRef = " + resultat2.getResultatParserCela().getFilaRef());
                System.out.println("colRef = " + resultat2.getResultatParserCela().getColRef());
                System.out.println("tipus = " + resultat2.getResultatParserCela().getTipus());
            }

            System.out.println("Introdueix 1 per continuar introduint un nou " +
                    "parsejat o 0 per abortar");
            int sortir = entry.nextInt();
            if (sortir == 0) return;
    }
    }
}