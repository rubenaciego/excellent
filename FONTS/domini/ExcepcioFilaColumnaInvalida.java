package domini;

public class ExcepcioFilaColumnaInvalida extends ExcepcioDomini {
    public ExcepcioFilaColumnaInvalida(int filacol, int maxfilacol) {
        super(TipusError.FILA_COLUMNA_INVALIDA, "Error: fila o columna " + filacol + " fora de rang [0, " +
                maxfilacol + ")");
    }
}
