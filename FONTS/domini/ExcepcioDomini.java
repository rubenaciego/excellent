package domini;

public class ExcepcioDomini extends RuntimeException {
    public enum TipusError {
        NO_ERROR,
        FILA_COLUMNA_INVALIDA,
        CELA_FORA_LIMITS,
        INDEX_FULL_INVALID,
        FORMAT_PARSER_INVALID,
        OPERADOR,
        NO_DOCUMENT,
        GUARDAR_CARREGAR,
        EXTENSIO_DOCUMENT
    }

    private final TipusError error;

    public ExcepcioDomini(TipusError error, String errorString) {
        super(errorString);
        this.error = error;
    }

    public TipusError getTipusError() {
        return error;
    }
}
