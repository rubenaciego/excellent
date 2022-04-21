package domini;

import java.lang.*;
import java.text.ParseException;
import java.time.LocalDate;

public class Parser
{
    private static Parser parser;

    private Parser()
    {
    }

    public static Parser getInstance()
    {
        if (parser == null)
            parser = new Parser();

        return parser;
    }

    /**
     * @brief Fa el parseig d'una operació associada a full provinent de la capa
     * de presentació
     * * /pre opSenseParsejar és un array de tres strings on el primer conté el
     * codi d'operació, id full, fila d'origen, fila de destí, columna d'origen,
     * columna de destí, mida fila, mida columna separat per comes
     * (,); el segon string conté l'inputUsuari en cas que l'operació sigui
     * modificaCela o la stringCercada en cas que l'operació sigui
     * cercaOcurrencies o reemplaça;  el tercer string conté la
     * stringReemplaçadora en cas que l'operació sigui reemplaça
     * /post retorna les dades d'opSenseParsejar estructurades en un objecte de
     * ResultatParserFull
     */
    public ResultatParserFull parseOpFull(String[] opSenseParsejar)
    {
        // Qüestió a considerar, hauríem de tirar excepció quan l'string té més coses de
        // les necessàries?
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
            resultat.setFilaOrigen(Integer.parseInt(splitted[2]));
            resultat.setColumnaOrigen(Integer.parseInt(splitted[3]));
            resultat.setMidaFila(Integer.parseInt(splitted[4]));
            resultat.setMidaColumna(Integer.parseInt(splitted[5]));
            resultat.setFilaDesti(Integer.parseInt(splitted[6]));
            resultat.setColumnaDesti(Integer.parseInt(splitted[7]));
        } catch (NumberFormatException e) {
            throw new ExcepcioParser(opSenseParsejar);
        }

        switch (tipus) {
            case OPERACIO_ARITMETICA:
                resultat.setTipusOpAritmetica(OperacioAritmetica.valueOf(splitted[8]));
                break;
            case OPERACIO_ESTADISTICA:
                resultat.setTipusOpEstadistica(OperacioEstadistica.valueOf(splitted[8]));
                break;
            case CONVERSIO_UNITATS:
                resultat.setTipusConversioUnitats(ConversioUnitats.valueOf(splitted[8]));
                break;
            case ORDENA:
                if (splitted.length < 10)
                    throw new ExcepcioParser(opSenseParsejar);

                resultat.setTipusOpFull(OperacioFull.ORDENA);
                resultat.setTipusCriteriOrdenacio(CriteriOrdenacio.valueOf(splitted[8]));
                resultat.setColumnaOrdenacio(Integer.parseInt(splitted[9]));
                break;
            case TRUNCA_NUMERO:
                if (splitted.length < 10)
                    throw new ExcepcioParser(opSenseParsejar);

                resultat.setTipusOpFull(OperacioFull.TRUNCA_NUMERO);
                resultat.setTipusCriteriOrdenacio(CriteriOrdenacio.valueOf(splitted[8]));
                resultat.setDigitsTruncar(Integer.parseInt(splitted[9]));
                break;
            case OPERACIO_FULL:
                if (opSenseParsejar.length < 2)
                    throw new ExcepcioParser(opSenseParsejar);

                OperacioFull op = OperacioFull.valueOf(splitted[8]);
                resultat.setTipusOpFull(op);

                if (op == OperacioFull.CERCA_OCURRENCIES)
                    resultat.setStringCercada(opSenseParsejar[1]);
                else if (op == OperacioFull.REEMPLACA) {
                    if (opSenseParsejar.length < 3)
                        throw new ExcepcioParser(opSenseParsejar);

                    resultat.setStringCercada(opSenseParsejar[1]);
                    resultat.setStringRemplacadora(opSenseParsejar[2]);
                } else if (op == OperacioFull.MODIFICA_CELA)
                    resultat.setResultatParserCela(parseResultatCela(opSenseParsejar[1]));

                break;
        }

        return resultat;
    }

    /**
     * @brief Fa el parseig d'una operació associada a document provinent de la
     * capa de presentació
     * * /pre opSenseParsejar és un string que conté el codi d'operació, el
     * nom del document  i l'id del full a afegir o a eliminar, si
     * s'escau, separat per comes (,)
     * /post retorna les dades d'opSenseParsejar estructurades en un objecte de
     * ResultatParserDocument
     */
    public ResultatParserDocument parseOpDocument(String[] opSenseParsejar)
    {
        String[] splitted = opSenseParsejar[0].split(",");
        ResultatParserDocument resultat = new ResultatParserDocument();

        if (splitted.length < 2)
            throw new ExcepcioParser(opSenseParsejar);

        OperacioDocument op;

        try {
            op = OperacioDocument.valueOf(splitted[1]);
        } catch (IllegalArgumentException e) {
            throw new ExcepcioParser(opSenseParsejar);
        }

        if (op == OperacioDocument.CARREGA_DOCUMENT || op == OperacioDocument.CREA_DOCUMENT) {
            if (opSenseParsejar.length < 2)
                throw new ExcepcioParser(opSenseParsejar);

            resultat.setNomDocument(opSenseParsejar[1]);
        }
        else if (op == OperacioDocument.ELIMINA_FULL) {
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

    public TipusOperacio parseTipusOperacio(String opSenseParsejar)
    {
        int pos = opSenseParsejar.indexOf(',');
        String operacio = opSenseParsejar.substring(0, pos);

        try {
            return TipusOperacio.valueOf(operacio);
        } catch (IllegalArgumentException e) {
            String[] v = {opSenseParsejar};
            throw new ExcepcioParser(v);
        }
    }

    private ResultatParserCela parseResultatCela(String inputUsuari)
    {
        ResultatParserCela resultat = new ResultatParserCela();

        if (inputUsuari.matches("-?\\d+(\\.\\d+)?")) {
            resultat.setValorNumeric(Double.parseDouble(inputUsuari));
            resultat.setTipus(Cela.TipusCela.NUMERICA);
        } else if (inputUsuari.matches(("^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-(\\d{4})$"))) {
            String[] DDMMAAAA = inputUsuari.split("/");

            resultat.setData(LocalDate.of(Integer.parseInt(DDMMAAAA[0]),
                    Integer.parseInt(DDMMAAAA[1]),
                    Integer.parseInt(DDMMAAAA[2])));
            resultat.setTipus(Cela.TipusCela.DATADA);
        } else if (inputUsuari.startsWith("=")) {
            int index = inputUsuari.indexOf(':');
            String fila = inputUsuari.substring(1, index);
            String col = inputUsuari.substring(index + 1);

            try {
                resultat.setFilaRef(Integer.parseInt(fila));
                resultat.setColRef(Integer.parseInt(col));
            } catch (NumberFormatException e) {
                String[] v = {inputUsuari};
                throw new ExcepcioParser(v);
            }
            resultat.setTipus(Cela.TipusCela.REFERENCIAL);
        } else {
            resultat.setInputUsuari(inputUsuari);
            resultat.setTipus(Cela.TipusCela.TEXTUAL);
        }

        return resultat;
    }
}
