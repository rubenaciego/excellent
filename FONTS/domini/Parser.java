package domini;

import javafx.util.Pair;
import util.Utilitats;

import java.rmi.UnexpectedException;
import java.time.LocalDate;

/**
 * Parser que decodifica missatges que corresponen a operacions a realitzar
 * pel Controlador Domini. Classe Singleton.
 */
public class Parser {
    /**
     * Parser que decodifica els missatges.
     */
    private static Parser parser;

    /**
     * Contructora del Parser.
     */
    private Parser() {
    }

    /**
     * Retorna l'instància del parser i si no existeix el crea.
     * @return el parser
     */
    public static Parser getInstance() {
        if (parser == null)
            parser = new Parser();

        return parser;
    }

    /**
     * Decodifica una operació associada a un full
     * @param opSenseParsejar missatge de l'operació a decodificar
     * @return resultat de la decodificació amb tots els paràmetres
     * necessaris per executar l'operació
     */
    public ResultatParserFull parseOpFull(String[] opSenseParsejar) {
        // Qüestió a considerar, hauríem de tirar excepció quan l'string té més coses de
        // les necessàries?
        // Pensar paràmetres de modificar cel·la
        if (opSenseParsejar.length == 0)
            throw new ExcepcioParser(opSenseParsejar);

        String[] splitted = opSenseParsejar[0].split(",");
        ResultatParserFull resultat = new ResultatParserFull();
        TipusOperacio tipus;

        if (splitted.length < 9)
            throw new ExcepcioParser(opSenseParsejar);

        try {
            tipus = TipusOperacio.valueOf(splitted[0]);
        } catch (IllegalArgumentException e) {
            throw new ExcepcioParser(opSenseParsejar);
        }

        try {
            //TODO mirar si es pot treure el primer if
            resultat.setIdFull(Integer.parseInt(splitted[1]));
            if (!splitted[8].equals(OperacioFull.ELIMINA_FILA.toString()) && !splitted[8].equals(
                    OperacioFull.ELIMINA_COLUMNA.toString())) {
                if (!splitted[2].isEmpty()) resultat.setFilaOrigen(Integer.parseInt(splitted[2]));
                if (!splitted[3].isEmpty()) resultat.setColumnaOrigen(Integer.parseInt(splitted[3]));
                if (!splitted[4].isEmpty()) resultat.setMidaFila(Integer.parseInt(splitted[4]));
                if (!splitted[5].isEmpty()) resultat.setMidaColumna(Integer.parseInt(splitted[5]));
                if (!splitted[6].isEmpty()) resultat.setFilaDesti(Integer.parseInt(splitted[6]));
                if (!splitted[7].isEmpty()) resultat.setColumnaDesti(Integer.parseInt(splitted[7]));
            }
        } catch (NumberFormatException e) {
            throw new ExcepcioParser(opSenseParsejar);
        }

        switch (tipus) {
            case OPERACIO_ARITMETICA:
                resultat.setTipusOpAritmetica(OperacioAritmetica.valueOf(splitted[8]));
                resultat.setTipusOpFull(OperacioFull.EXECUTA_OPERACIO_ARITMETICA_UNARIA);
                break;
            case OPERACIO_ESTADISTICA:
                resultat.setTipusOpEstadistica(OperacioEstadistica.valueOf(splitted[8]));
                resultat.setTipusOpFull(OperacioFull.EXECUTA_FUNCIO_ESTADISTICA);
                break;
            case CONVERSIO_UNITATS:
                resultat.setTipusConversioUnitats(ConversioUnitats.valueOf(splitted[8]));
                resultat.setTipusOpFull(OperacioFull.CONVERTEIX_UNITATS);
                break;
            case ORDENA:
                if (splitted.length < 10)
                    throw new ExcepcioParser(opSenseParsejar);

                resultat.setTipusOpFull(OperacioFull.ORDENA);
                resultat.setTipusCriteriOrdenacio(CriteriOrdenacio.valueOf(splitted[8]));
                resultat.setColumnaOrdenacio(Integer.parseInt(splitted[9]));
                break;
            case TRUNCA_NUMERO:
                resultat.setTipusOpFull(OperacioFull.TRUNCA_NUMERO);
                resultat.setDigitsTruncar(Integer.parseInt(splitted[8]));
                break;
            case OPERACIO_FULL:
                OperacioFull op = OperacioFull.valueOf(splitted[8]);
                resultat.setTipusOpFull(op);

                if (op == OperacioFull.CERCA_OCURRENCIES) {
                    if (opSenseParsejar.length < 2)
                        throw new ExcepcioParser(opSenseParsejar);
                    resultat.setStringCercada(opSenseParsejar[1]);
                }
                else if (op == OperacioFull.REEMPLACA) {
                    if (opSenseParsejar.length < 3)
                        throw new ExcepcioParser(opSenseParsejar);
                    resultat.setStringCercada(opSenseParsejar[1]);
                    resultat.setStringRemplacadora(opSenseParsejar[2]);
                } else if (op == OperacioFull.MODIFICA_CELA) {
                    if (opSenseParsejar.length < 2)
                        throw new ExcepcioParser(opSenseParsejar);
                    resultat.setResultatParserCela(parseResultatCela(opSenseParsejar[1]));
                } else if (op == OperacioFull.ELIMINA_FILA || op == OperacioFull.ELIMINA_COLUMNA) {
                    if (splitted.length < 10)
                        throw new ExcepcioParser(opSenseParsejar);
                    resultat.setFilaColEliminar(Integer.parseInt(splitted[9]));
                }

                break;
        }

        return resultat;
    }

    /**
     * Decodifica una operació associada a un document
     * @param opSenseParsejar missatge de l'operació a decodificar
     * @return resultat de la decodificació amb tots els paràmetres
     * necessaris per executar l'operació
     */
    public ResultatParserDocument parseOpDocument(String[] opSenseParsejar) {
        String[] splitted = opSenseParsejar[0].split(",");
        ResultatParserDocument resultat = new ResultatParserDocument();

        if (splitted.length < 2)
            throw new ExcepcioParser(opSenseParsejar);

        OperacioDocument op;

        try {
            op = OperacioDocument.valueOf(splitted[1]);
            resultat.setTipusOpDocument(op);
        } catch (IllegalArgumentException e) {
            throw new ExcepcioParser(opSenseParsejar);
        }

        if (op == OperacioDocument.CARREGA_DOCUMENT || op == OperacioDocument.CREA_DOCUMENT
                || op == OperacioDocument.CANVIA_NOM_DOCUMENT) {
            if (opSenseParsejar.length < 2)
                throw new ExcepcioParser(opSenseParsejar);

            resultat.setNomDocument(opSenseParsejar[1]);
        } else if (op == OperacioDocument.ELIMINA_FULL) {
            if (splitted.length < 3)
                throw new ExcepcioParser(opSenseParsejar);

            try {
                resultat.setIdFull(Integer.parseInt(splitted[2]));
            } catch (NumberFormatException e) {
                throw new ExcepcioParser(opSenseParsejar);
            }
        }

        return resultat;
    }

    /**
     * Extreu el tipus t'operació que codifica el missatge a decodificar.
     * @param opSenseParsejar missatge a decodificar
     * @return el tipus de l'operació a realitzar
     */
    public TipusOperacio parseTipusOperacio(String opSenseParsejar) {
        int pos = opSenseParsejar.indexOf(',');

        try {
            String operacio = opSenseParsejar.substring(0, pos);
            return TipusOperacio.valueOf(operacio);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            String[] v = {opSenseParsejar};
            throw new ExcepcioParser(v);
        }
    }

    /**
     * Extreu el tipus de dada i dada especificat per un text
      * @param inputUsuari text que especifica la dada a decodificar
     * @return decodificació contenent el tipus de dada i el seu valor
     */
    private ResultatParserCela parseResultatCela(String inputUsuari) {
        ResultatParserCela resultat = new ResultatParserCela();
        resultat.setInputUsuari(inputUsuari);

        if (inputUsuari.matches("-?\\d+(\\.\\d+)?")) {
            resultat.setValorNumeric(Double.parseDouble(inputUsuari));
            resultat.setTipus(Cela.TipusCela.NUMERICA);
        } else if (inputUsuari.matches(("^(0?[1-9]|[12][0-9]|3[01])/" +
                "(0?[1-9]|1[012])/(\\d{4})$"))) {
            String[] DDMMAAAA = inputUsuari.split("/");

            resultat.setData(LocalDate.of(Integer.parseInt(DDMMAAAA[2]),
                    Integer.parseInt(DDMMAAAA[1]),
                    Integer.parseInt(DDMMAAAA[0])));
            resultat.setTipus(Cela.TipusCela.DATADA);
        } else if (inputUsuari.matches("^=[A-Z]+[1-9][0-9]*$")) {
            Pair<Integer, Integer> p = Utilitats.convertirAIndexs(inputUsuari.substring(1));

            resultat.setFilaRef(p.getKey());
            resultat.setColRef(p.getValue());
            resultat.setTipus(Cela.TipusCela.REFERENCIAL);
        } else {
            resultat.setTipus(Cela.TipusCela.TEXTUAL);
        }

        return resultat;
    }
}
