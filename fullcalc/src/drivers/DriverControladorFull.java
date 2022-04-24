package drivers;

import domini.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverControladorFull {
    static Full full;
    static ResultatParserFull parsejat;
    static Scanner entry;

    public static void imprimir(Full full) {
        ArrayList<EntradaMatriuCeles> entrades = full.getEntrades();
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

    public static void llegirFull() {
        System.out.println("Introdueix el nombre de files del bloc sobre el " +
                "que operar");

        int n = entry.nextInt();

        System.out.println("Introdueix el nombre de columnes del bloc sobre " +
                "el que operar");

        int m = entry.nextInt();

        System.out.println("Introdueix el nombre de celes no buides a " +
                "inicialitzar");

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
                            "proces d'enregistrar la cela");
                    --p;
                    break;
            }
        }
    }

    public static void llegirParsejat() {
        parsejat = new ResultatParserFull();
        int fo = entry.nextInt();
        System.out.println("Introdueix totes les dades necessaries per al " +
                "Parsejat de la operacio a realitzar:");
        System.out.println("Introdueix un int de filaOrigen:");
        int fo = entry.nextInt();
        parsejat.setFilaOrigen(fo);

        System.out.println("Introdueix un int de columnaOrigen:");
        int co = entry.nextInt();
        parsejat.setColumnaOrigen(co);

        System.out.println("Introdueix un int de filaDesti:");
        int fd = entry.nextInt();
        parsejat.setFilaDesti(fd);

        System.out.println("Introdueix un int de columnaDesti:");
        int cd = entry.nextInt();
        parsejat.setColumnaDesti(cd);

        parsejat.setMidaColumna(Math.abs(cd - co));
        parsejat.setMidaFila(Math.abs(fd - fo));


        System.out.println("Introdueix tipusOperacioFull:");
        String tipusOperacioFull = entry.next();
        switch (tipusOperacioFull) {
            case "EXTREU_HOROSCOP":
                parsejat.setTipusOpFull(OperacioFull.EXTREU_HOROSCOP);
                break;
            case "EXTREU_ANY":
                parsejat.setTipusOpFull(OperacioFull.EXTREU_ANY);
                break;
            case "EXTREU_MES":
                parsejat.setTipusOpFull(OperacioFull.EXTREU_MES);
                break;
            case "EXTREU_DIA":
                parsejat.setTipusOpFull(OperacioFull.EXTREU_DIA);
                break;
            case "EXTREU_DIA_SETMANA":
                parsejat.setTipusOpFull(OperacioFull.EXTREU_DIA_SETMANA);
                break;
            case "EXECUTA_OPERACIO_ARITMETICA_UNARIA":
                parsejat.setTipusOpFull(OperacioFull.EXECUTA_OPERACIO_ARITMETICA_UNARIA);
                System.out.println("Introdueix tipusOperacioAritmetica:");
                String opAritm = entry.next();
                switch (opAritm) {
                    case "VALOR_ABSOLUT":
                        parsejat.setTipusOpAritmetica(OperacioAritmetica.VALOR_ABSOLUT);
                        break;
                    case "INCREMENTAR":
                        parsejat.setTipusOpAritmetica(OperacioAritmetica.INCREMENTAR);
                        break;
                    case "DECREMENTAR":
                        parsejat.setTipusOpAritmetica(OperacioAritmetica.DECREMENTAR);
                        break;
                    case "EXPONENCIAL":
                        parsejat.setTipusOpAritmetica(OperacioAritmetica.EXPONENCIAL);
                        break;
                    case "COSINUS":
                        parsejat.setTipusOpAritmetica(OperacioAritmetica.COSINUS);
                        break;
                    case "SINUS":
                        parsejat.setTipusOpAritmetica(OperacioAritmetica.SINUS);
                        break;
                    case "COSINUS_HIPERBOLIC":
                        parsejat.setTipusOpAritmetica(OperacioAritmetica.COSINUS_HIPERBOLIC);
                        break;
                    case "SINUS_HIPERBOLIC":
                        parsejat.setTipusOpAritmetica(OperacioAritmetica.SINUS_HIPERBOLIC);
                        break;
                    case "TANGENT_HIPERBOLIC":
                        parsejat.setTipusOpAritmetica(OperacioAritmetica.TANGENT_HIPERBOLIC);
                        break;
                    default:
                        System.out.println("No valida, seleccionant VALOR_ABSOLUT");
                        parsejat.setTipusOpAritmetica(OperacioAritmetica.VALOR_ABSOLUT);
                }
                break;
            case "EXECUTA_FUNCIO_ESTADISTICA":
                parsejat.setTipusOpFull(OperacioFull.EXECUTA_FUNCIO_ESTADISTICA);
                System.out.println("Introdueix tipusOperacioEstadistica:");
                String opEs = entry.next();
                switch (opEs) {
                    case "MITJANA":
                        parsejat.setTipusOpEstadistica(OperacioEstadistica.MITJANA);
                        break;
                    case "MEDIANA":
                        parsejat.setTipusOpEstadistica(OperacioEstadistica.MEDIANA);
                        break;
                    case "VARIANCIA":
                        parsejat.setTipusOpEstadistica(OperacioEstadistica.VARIANCIA);
                        break;
                    case "DESVIACIO_ESTANDARD":
                        parsejat.setTipusOpEstadistica(OperacioEstadistica.DESVIACIO_ESTANDARD);
                        break;
                    case "COVARIANCIA":
                        parsejat.setTipusOpEstadistica(OperacioEstadistica.COVARIANCIA);
                        break;
                    case "COEFICIENT_PEARSON":
                        parsejat.setTipusOpEstadistica(OperacioEstadistica.COEFICIENT_PEARSON);
                        break;
                    default:
                        System.out.println("Operacio estadistica no valida, " +
                                "seleccionant MITJANA");
                        parsejat.setTipusOpEstadistica(OperacioEstadistica.MITJANA);
                }
                break;
            case "TRUNCA_NUMERO":
                parsejat.setTipusOpFull(OperacioFull.TRUNCA_NUMERO);
                System.out.println("Introdueix un int de digitsTruncar:");
                int digitsTrunc = entry.nextInt();
                parsejat.setDigitsTruncar(digitsTrunc);
                break;
            case "CONVERTEIX_UNITATS":
                parsejat.setTipusOpFull(OperacioFull.CONVERTEIX_UNITATS);
                System.out.println("Introdueix tipusConversioUnitats:");
                String conv = entry.next();
                switch (conv) {
                    case "RAD_GRAUS":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.RAD_GRAUS);
                        break;
                    case "GRAUS_RAD":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.GRAUS_RAD);
                        break;
                    case "KM_MILLA":
                    case "KMH_MILLAH":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.KM_MILLA);
                        break;
                    case "MILLA_KM":
                    case "MILLAH_KMH":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.MILLA_KM);
                        break;
                    case "KG_LLIURA":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.KG_LLIURA);
                        break;
                    case "LLIURA_KG":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.LLIURA_KG);
                        break;
                    case "LITRE_GALO":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.LITRE_GALO);
                        break;
                    case "GALO_LITRE":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.GALO_LITRE);
                        break;
                    case "CELSIUS_KELVIN":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.CELSIUS_KELVIN);
                        break;
                    case "KELVIN_CELSIUS":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.KELVIN_CELSIUS);
                        break;
                    case "KELVIN_FAHRENHEIT":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.KELVIN_FAHRENHEIT);
                        break;
                    case "FAHRENHEIT_KELVIN":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.FAHRENHEIT_KELVIN);
                        break;
                    case "FAHRENHEIT_CELSIUS":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.FAHRENHEIT_CELSIUS);
                        break;
                    case "CELSIU_FAHRENHEIT":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.CELSIU_FAHRENHEIT);
                        break;
                    case "KM2_HECATAREA":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.KM2_HECATAREA);
                        break;
                    case "HECTAREA_KM2":
                        parsejat.setTipusConversioUnitats(ConversioUnitats.HECTAREA_KM2);
                        break;
                    default:
                        System.out.println("Conversio no valida, seleccionant " +
                                "KM_MILLA");
                        parsejat.setTipusConversioUnitats(ConversioUnitats.KM_MILLA);
                }
                break;
            case "EXTREU_LONGITUD_TEXT":
                parsejat.setTipusOpFull(OperacioFull.EXTREU_LONGITUD_TEXT);
                break;
            case "CERCA_OCURRENCIES":
                parsejat.setTipusOpFull(OperacioFull.CERCA_OCURRENCIES);
                break;
            case "CONVERTEIX_MAJUSCULES":
                parsejat.setTipusOpFull(OperacioFull.CONVERTEIX_MAJUSCULES);
                break;
            case "CONVERTEIX_MINUSCULES":
                parsejat.setTipusOpFull(OperacioFull.CONVERTEIX_MINUSCULES);
                break;
            case "TRANSPOSA":
                parsejat.setTipusOpFull(OperacioFull.TRANSPOSA);
                break;
            case "REEMPLACA":
                parsejat.setTipusOpFull(OperacioFull.REEMPLACA);
                System.out.println("Introdueix stringCercada:");
                String stringCercadaR = entry.next();
                parsejat.setStringCercada(stringCercadaR);
                System.out.println("Introdueix stringRemplacadora:");
                String stringRemplacadora = entry.next();
                parsejat.setStringRemplacadora(stringRemplacadora);
                break;
            case "ORDENA":
                parsejat.setTipusOpFull(OperacioFull.ORDENA);
                System.out.println("Introdueix un int de columnaOrdenacio:");
                int columnaOrdenacio = entry.nextInt();
                parsejat.setColumnaOrdenacio(columnaOrdenacio);
                System.out.println("Introdueix stringCercada:");
                String stringCercada = entry.next();
                parsejat.setStringCercada(stringCercada);
                break;
            case "MODIFICA_CELA":
                parsejat.setTipusOpFull(OperacioFull.MODIFICA_CELA);
                break;
            case "AFEGEIX_COLUMNA":
                parsejat.setTipusOpFull(OperacioFull.AFEGEIX_COLUMNA);
                break;
            case "AFEGEIX_FILA":
                parsejat.setTipusOpFull(OperacioFull.AFEGEIX_FILA);
                break;
            case "ELIMINA_COLUMNA":
                parsejat.setTipusOpFull(OperacioFull.ELIMINA_COLUMNA);
                parsejat.setTipusOpFull(OperacioFull.ORDENA);
                System.out.println("Introdueix un int de colEliminar:");
                int filaColEliminar = entry.nextInt();
                parsejat.setFilaColEliminar(filaColEliminar);
                break;
            case "ELIMINA_FILA":
                parsejat.setTipusOpFull(OperacioFull.ELIMINA_FILA);
                parsejat.setTipusOpFull(OperacioFull.ORDENA);
                System.out.println("Introdueix un int de filaEliminar:");
                int filaColEliminar = entry.nextInt();
                parsejat.setFilaColEliminar(filaColEliminar);
                break;
            case "MOU_BLOC":
                parsejat.setTipusOpFull(OperacioFull.MOU_BLOC);
                break;
            case "COPIA_BLOC":
                parsejat.setTipusOpFull(OperacioFull.COPIA_BLOC);
                break;
            case "BUIDA_BLOC":
                parsejat.setTipusOpFull(OperacioFull.BUIDA_BLOC);
                break;
            default:
                System.out.println("Operacio no valida, abortant, fixant " +
                        "BUIDA_BLOC");
                parsejat.setTipusOpFull(OperacioFull.BUIDA_BLOC);
        }
    }

    public static void main(String[] args) {
        entry = new Scanner(System.in);

        llegirFull();
        imprimir(full);

        while (true) {
            llegirParsejat();
            ControladorFull contr = new ControladorFull(full);
            contr.executaOperacio(parsejat);
            imprimir(full);
            System.out.println("Introdueix FI per abortar o CONTINUA per " +
                    "omplir un nou parsejat");
            int sortir = entry.nextInt();
            if (sortir == 1) return;
        }
    }
}
