package domini;

import java.util.StringJoiner;

public class ExcepcioParser extends ExcepcioDomini
{
    public ExcepcioParser(String[] senseParsejar)
    {
        super(TipusError.FORMAT_PARSER_INVALID, creaMissatge(senseParsejar));
    }

    private static String creaMissatge(String[] senseParsejar)
    {
        StringJoiner joiner = new StringJoiner(" ;; ");
        joiner.add("Error al parsejar el missatge: ");
        for (String s : senseParsejar) joiner.add(s);

        return joiner.toString();
    }
}
