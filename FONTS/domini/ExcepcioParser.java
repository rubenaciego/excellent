package domini;

import java.util.StringJoiner;

/**
 * Excepció provocada pel parser quan el format no és vàlid
 */
public class ExcepcioParser extends ExcepcioDomini {
    /**
     * Constructora principal
     * @param senseParsejar elements que formaran el missatge d'error
     */
    public ExcepcioParser(String[] senseParsejar) {
        super(TipusError.FORMAT_PARSER_INVALID, creaMissatge(senseParsejar));
    }

    /**
     * Crea el missatge d'error
     * @param senseParsejar elements que corresponien al missatge a parsejar
     * @return el missatge d'error en format String
     */
    private static String creaMissatge(String[] senseParsejar) {
        StringJoiner joiner = new StringJoiner(" ;; ");
        joiner.add("Error al parsejar el missatge: ");
        for (String s : senseParsejar) joiner.add(s);

        return joiner.toString();
    }
}
