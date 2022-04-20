package domini;

public class ExcepcioDomini extends RuntimeException
{
    public enum TipusError
    {
        FILA_COLUMNA_INVALIDA,
        FORA_LIMITS,
        INDEX_FULL_INVALID
    }

    private final TipusError error;

    public ExcepcioDomini(TipusError error, String errorString)
    {
        super(errorString);
        this.error = error;
    }

    public TipusError getError()
    {
        return error;
    }
}
