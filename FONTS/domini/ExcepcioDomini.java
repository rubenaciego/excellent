package domini;

/**
 * Excepció general a la capa de domini que engloba tots els subtipus d'excepcions
 */
public class ExcepcioDomini extends RuntimeException {
    public enum TipusError {
        FILA_COLUMNA_INVALIDA,
        CELA_FORA_LIMITS,
        INDEX_FULL_INVALID,
        FORMAT_PARSER_INVALID,
        OPERADOR,
        NO_DOCUMENT,
        GUARDAR_CARREGAR,
        EXTENSIO_DOCUMENT,
        FORMAT_DOCUMENT
    }

    /**
     * Especifica el tipus d'error o excepció
     */
    private final TipusError error;

    /**
     * Constructora principal
     * @param error el tipus d'error de l'excepció
     * @param errorString el missatge d'error de l'excepció
     */
    public ExcepcioDomini(TipusError error, String errorString) {
        super(errorString);
        this.error = error;
    }

    /**
     * Getter d'error
     * @return el TipusError corresponent a l'excepció actual
     */
    public TipusError getTipusError() {
        return error;
    }
}
