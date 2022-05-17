package domini;

import util.Utilitats;

import java.lang.*;
import java.text.ParseException;
import java.time.LocalDate;

public class Parser {
    private static Parser parser;

    private Parser() {
    }

    public static Parser getInstance() {
        if (parser == null)
            parser = new Parser();

        return parser;
    }

    /**
     * @brief Fa el parseig d'una operació associada a full
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
            resultat.setIdFull(Integer.parseInt(splitted[1]));
            if (!splitted[8].equals(OperacioFull.ELIMINA_FILA.toString()) && !splitted[8].equals(
                    OperacioFull.ELIMINA_COLUMNA.toString())) {
                resultat.setFilaOrigen(Integer.parseInt(splitted[2]));
                resultat.setColumnaOrigen(Integer.parseInt(splitted[3]));
                resultat.setMidaFila(Integer.parseInt(splitted[4]));
                resultat.setMidaColumna(Integer.parseInt(splitted[5]));
                resultat.setFilaDesti(Integer.parseInt(splitted[6]));
                resultat.setColumnaDesti(Integer.parseInt(splitted[7]));
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
     * @brief Fa el parseig d'una operació associada a document
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
            int index = 0;
            char c;

            do {
                ++index;
                c = inputUsuari.charAt(index);
            } while (c >= 'A' && c <= 'Z');

            String fila = inputUsuari.substring(index);
            String col = inputUsuari.substring(1, index);

            resultat.setFilaRef(Integer.parseInt(fila));
            resultat.setColRef(Utilitats.convertirBase26(col));
            resultat.setTipus(Cela.TipusCela.REFERENCIAL);
        } else {
            resultat.setTipus(Cela.TipusCela.TEXTUAL);
        }

        return resultat;
    }
}
