package domini;

public class ExcepcioRefARef extends ExcepcioDomini
{
    public ExcepcioRefARef(int fila, int col)
    {
        super(TipusError.REF_A_REF, "Error: no es pot referenciar a una cel·la referència (" + fila +
                ", " + col + ")");
    }
}
