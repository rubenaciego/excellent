package domini;

public class ExcepcioOperador extends ExcepcioDomini
{
    public ExcepcioOperador(String error)
    {
        super(TipusError.OPERADOR, error);
    }
}
