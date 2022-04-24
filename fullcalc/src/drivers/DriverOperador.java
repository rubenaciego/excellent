package drivers;

import domini.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverOperador {
    static Operador op;
    static MatriuCeles bloc;
    static Scanner entry;

    public static void imprimir(MatriuCeles bloc) {

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
    public static void llegirBloc() {
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
                            "proces d'enregistrar la cela");
                    --p;
                    break;
            }
        }
    }
    public static void main(String[] args) {
        entry = new Scanner(System.in);
        op = Operador.getInstance();

        llegirBloc();
        imprimir(bloc);

        MatriuCeles res;

        while (true){
            System.out.println("Introdueix la operacio a realitzar:");
            String codiOp = entry.next();
            switch (codiOp) {
                case "EXTREU_HOROSCOP":
                    res = op.extreuHoroscop(bloc);
                    imprimir(res);
                    break;
                case "EXTREU_ANY":
                    res = op.extreuAny(bloc);
                    imprimir(res);
                    break;
                case "EXTREU_MES":
                    res = op.extreuMes(bloc);
                    imprimir(res);
                    break;
                case "EXTREU_DIA":
                    res = op.extreuDia(bloc);
                    imprimir(res);
                    break;
                case "EXTREU_DIA_SETMANA":
                    res = op.extreuDiaSetmana(bloc);
                    imprimir(res);
                    break;
                case "EXECUTA_OPERACIO_ARITMETICA_UNARIA":
                    System.out.println("Introdueix la operacio aritmetica a " +
                            "realitzar:");
                    String opAr = entry.next();
                    switch (opAr) {
                        case "VALOR_ABSOLUT":
                            res = op.executaOperacioAritmeticaUnaria(bloc, OperacioAritmetica.VALOR_ABSOLUT);
                            imprimir(res);
                            break;
                        case "INCREMENTAR":
                            res = op.executaOperacioAritmeticaUnaria(bloc,
                                    OperacioAritmetica.INCREMENTAR);
                            imprimir(res);
                            break;
                        case "DECREMENTAR":
                            res = op.executaOperacioAritmeticaUnaria(bloc,
                                    OperacioAritmetica.DECREMENTAR);
                            imprimir(res);
                            break;
                        case "EXPONENCIAL":
                            res = op.executaOperacioAritmeticaUnaria(bloc,
                                    OperacioAritmetica.EXPONENCIAL);
                            imprimir(res);
                            break;
                        case "COSINUS":
                            res = op.executaOperacioAritmeticaUnaria(bloc,
                                    OperacioAritmetica.COSINUS);
                            imprimir(res);
                            break;
                        case "SINUS":
                            res = op.executaOperacioAritmeticaUnaria(bloc,
                                    OperacioAritmetica.SINUS);
                            imprimir(res);
                            break;
                        case "COSINUS_HIPERBOLIC":
                            res = op.executaOperacioAritmeticaUnaria(bloc,
                                    OperacioAritmetica.COSINUS_HIPERBOLIC);
                            imprimir(res);
                            break;
                        case "SINUS_HIPERBOLIC":
                            res = op.executaOperacioAritmeticaUnaria(bloc,
                                    OperacioAritmetica.SINUS_HIPERBOLIC);
                            imprimir(res);
                            break;
                        case "TANGENT_HIPERBOLIC":
                            res = op.executaOperacioAritmeticaUnaria(bloc,
                                    OperacioAritmetica.TANGENT_HIPERBOLIC);
                            imprimir(res);
                            break;
                        default:
                            System.out.println("Operacio aritmetica no valida");
                            break;
                    }
                    break;
                case "EXECUTA_FUNCIO_ESTADISTICA":
                    System.out.println("Introdueix la operacio estadistica a " +
                            "realitzar:");
                    String opEs = entry.next();
                    switch (opEs) {
                        case "MITJANA":
                            res = op.executaOperacioEstadistica(bloc, OperacioEstadistica.MITJANA);
                            imprimir(res);
                            break;
                        case "MEDIANA":
                            res = op.executaOperacioEstadistica(bloc,
                                    OperacioEstadistica.MEDIANA);
                            imprimir(res);
                            break;
                        case "VARIANCIA":
                            res = op.executaOperacioEstadistica(bloc,
                                    OperacioEstadistica.VARIANCIA);
                            imprimir(res);
                            break;
                        case "DESVIACIO_ESTANDARD":
                            res = op.executaOperacioEstadistica(bloc,
                                    OperacioEstadistica.DESVIACIO_ESTANDARD);
                            imprimir(res);
                            break;
                        case "COVARIANCIA":
                            res = op.executaOperacioEstadistica(bloc,
                                    OperacioEstadistica.COVARIANCIA);
                            imprimir(res);
                            break;
                        case "COEFICIENT_PEARSON":
                            res = op.executaOperacioEstadistica(bloc,
                                    OperacioEstadistica.COEFICIENT_PEARSON);
                            imprimir(res);
                            break;
                        default:
                            System.out.println("Operacio estadistica no valida");
                    }
                    break;
                case "TRUNCA_NUMERO":
                    System.out.println("Introdueix la xifra a la que truncar");
                    int xifra = entry.nextInt();
                    res = op.truncaNumero(bloc, xifra);
                    imprimir(res);
                    break;
                case "CONVERTEIX_UNITATS":
                    System.out.println("Introdueix la conversio d'unitats a " +
                            "realitzar:");
                    String conv = entry.next();
                    switch (conv) {
                        case "RAD_GRAUS":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.RAD_GRAUS);
                            imprimir(res);
                            break;
                        case "GRAUS_RAD":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.GRAUS_RAD);
                            imprimir(res);
                            break;
                        case "KM_MILLA":
                        case "KMH_MILLAH":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.KM_MILLA);
                            imprimir(res);
                            break;
                        case "MILLA_KM":
                        case "MILLAH_KMH":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.MILLA_KM);
                            imprimir(res);
                            break;
                        case "KG_LLIURA":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.KG_LLIURA);
                            imprimir(res);
                            break;
                        case "LLIURA_KG":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.LLIURA_KG);
                            imprimir(res);
                            break;
                        case "LITRE_GALO":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.LITRE_GALO);
                            imprimir(res);
                            break;
                        case "GALO_LITRE":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.GALO_LITRE);
                            imprimir(res);
                            break;
                        case "CELSIUS_KELVIN":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.CELSIUS_KELVIN);
                            imprimir(res);
                            break;
                        case "KELVIN_CELSIUS":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.KELVIN_CELSIUS);
                            imprimir(res);
                            break;
                        case "KELVIN_FAHRENHEIT":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.KELVIN_FAHRENHEIT);
                            imprimir(res);
                            break;
                        case "FAHRENHEIT_KELVIN":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.FAHRENHEIT_KELVIN);
                            imprimir(res);
                            break;
                        case "FAHRENHEIT_CELSIUS":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.FAHRENHEIT_CELSIUS);
                            imprimir(res);
                            break;
                        case "CELSIU_FAHRENHEIT":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.CELSIU_FAHRENHEIT);
                            imprimir(res);
                            break;
                        case "KM2_HECATAREA":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.KM2_HECATAREA);
                            imprimir(res);
                            break;
                        case "HECTAREA_KM2":
                            res = op.converteixUnitats(bloc,
                                    ConversioUnitats.HECTAREA_KM2);
                            imprimir(res);
                            break;
                        default:
                            System.out.println("Conversio no valida");
                    }
                    break;
                case "EXTREU_LONGITUD_TEXT":
                    res = op.extreuLongitudText(bloc);
                    imprimir(res);
                    break;
                case "CERCA_OCURRENCIES":
                    System.out.println("Introdueix l'string a cercar");
                    String cerca = entry.next();
                    res = op.cercaOcurrencies(bloc, cerca);
                    imprimir(res);
                    break;
                case "CONVERTEIX_MAJUSCULES":
                    res = op.converteixMajuscules(bloc);
                    imprimir(res);
                    break;
                case "CONVERTEIX_MINUSCULES":
                    res = op.converteixMinuscules(bloc);
                    imprimir(res);
                    break;
                case "TRANSPOSA":
                    res = op.transposa(bloc);
                    imprimir(res);
                    break;
                case "REEMPLACA":
                    System.out.println("Introdueix l'string a cercar");
                    String cercaR = entry.next();
                    System.out.println("Introdueix l'string a reemplaçar");
                    String reem = entry.next();
                    res = op.reemplaca(bloc, cercaR,
                            reem);
                    imprimir(res);
                    break;
                case "ORDENA":
                    System.out.println("Introdueix la columna d'ordenacio:");
                    int col = entry.nextInt();
                    System.out.println("Introdueix el criteri d'ordenacio:");
                    String crit = entry.next();
                    switch (crit) {
                        case "ASCENDENT":
                            res = op.ordena(bloc, col,
                                    CriteriOrdenacio.ASCENDENT);
                            imprimir(res);
                            break;
                        case "DESCENDENT":
                            res = op.ordena(bloc, col,
                                    CriteriOrdenacio.DESCENDENT);
                            imprimir(res);
                            break;
                        default:
                            System.out.println("Criteri no valid");
                    }
                    break;
                case "FI":
                    return;
                default:
                    System.out.println("Operacio no valida");
            }
        }
    }
}