package drivers;

import domini.*;

import java.time.LocalDate;
import java.util.Scanner;

public class DriverControladorFull {
    static Full full;
    static ResultatParserFull parsejat;
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

    public static void llegirFull() {
        System.out.println("Introdueix el nombre de files del full sobre el " +
                "que operar");

        int n = entry.nextInt();

        System.out.println("Introdueix el nombre de columnes del full sobre " +
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

        System.out.println("Introdueix un int de midaFila:");
        int midaFila = entry.nextInt();
        parsejat.setMidaFila(midaFila);

        System.out.println("Introdueix un int de midaColumna:");
        int midaCol = entry.nextInt();
        parsejat.setMidaColumna(midaCol);

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
                System.out.println("Introdueix stringCercada:");
                String stringCercada = entry.next();
                parsejat.setStringCercada(stringCercada);
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
                System.out.println("Introdueix un criteriOrdenacio:");
                String criteriOrdenacio = entry.next();
                switch (criteriOrdenacio) {
                    case "ASCENDENT":
                        parsejat.setTipusCriteriOrdenacio(CriteriOrdenacio.ASCENDENT);
                        break;
                    case "DESCENDENT":
                        parsejat.setTipusCriteriOrdenacio(CriteriOrdenacio.DESCENDENT);
                }
                parsejat.setColumnaOrdenacio(columnaOrdenacio);
                break;
            case "MODIFICA_CELA":
                parsejat.setTipusOpFull(OperacioFull.MODIFICA_CELA);
                ResultatParserCela resParsCel = new ResultatParserCela();

                System.out.println("Introdueix el tipus de la nova cela:");
                String tipus = entry.next();

                switch (tipus) {
                    case "Textual":
                        resParsCel.setTipus(Cela.TipusCela.TEXTUAL);
                        System.out.println("Introdueix el text de la Cela");
                        String input = entry.next();

                        resParsCel.setInputUsuari(input);
                        break;
                    case "Numerica":
                        resParsCel.setTipus(Cela.TipusCela.NUMERICA);
                        System.out.println("Introdueix el valor de la Cela");
                        double val = entry.nextDouble();

                        resParsCel.setValorNumeric(val);
                        resParsCel.setInputUsuari(String.valueOf(val));
                        break;
                    case "Datada":
                        resParsCel.setTipus(Cela.TipusCela.DATADA);
                        System.out.println("Introdueix l'any de la Cela");
                        int any = entry.nextInt();
                        System.out.println("Introdueix el mes de la Cela");
                        int mes = entry.nextInt();
                        System.out.println("Introdueix el dia de la Cela");
                        int dia = entry.nextInt();

                        LocalDate data = LocalDate.of(any, mes, dia);
                        resParsCel.setData(data);
                        resParsCel.setInputUsuari(data.toString());
                        break;
                    case "Referencia":
                        resParsCel.setTipus(Cela.TipusCela.REFERENCIAL);
                        System.out.println("Introdueix la fila de la Cela " +
                                "referenciada");
                        int ir = entry.nextInt();
                        System.out.println("Introdueix la columna de la Cela " +
                                "referenciada");
                        int jr = entry.nextInt();

                        resParsCel.setFilaRef(ir);
                        resParsCel.setColRef(jr);
                        resParsCel.setInputUsuari("res");
                }
                parsejat.setResultatParserCela(resParsCel);
                break;
            case "AFEGEIX_COLUMNA":
                parsejat.setTipusOpFull(OperacioFull.AFEGEIX_COLUMNA);
                break;
            case "AFEGEIX_FILA":
                parsejat.setTipusOpFull(OperacioFull.AFEGEIX_FILA);
                break;
            case "ELIMINA_COLUMNA":
                parsejat.setTipusOpFull(OperacioFull.ELIMINA_COLUMNA);
                System.out.println("Introdueix un int de colEliminar:");
                int filaColEliminar = entry.nextInt();
                parsejat.setFilaColEliminar(filaColEliminar);
                break;
            case "ELIMINA_FILA":
                parsejat.setTipusOpFull(OperacioFull.ELIMINA_FILA);
                System.out.println("Introdueix un int de filaEliminar:");
                int filaColEliminar2 = entry.nextInt();
                parsejat.setFilaColEliminar(filaColEliminar2);
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
                        "AFEGEIX_FILA");
                parsejat.setTipusOpFull(OperacioFull.AFEGEIX_FILA);
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

            System.out.println("Introdueix 1 per continuar introduint un nou " +
                    "parsejat o 0 per abortar");
            int sortir = entry.nextInt();
            if (sortir == 0) return;
        }
    }
}
