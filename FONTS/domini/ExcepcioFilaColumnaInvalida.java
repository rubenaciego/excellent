package domini;

/**
 * Excepció al intentar accedir a una fila o columna invàlida d’un full
 */
public class ExcepcioFilaColumnaInvalida extends ExcepcioDomini {
    /**
     * Creadora principal
     * @param filacol fila o columna errònia que ha entrat l'usuari
     * @param maxfilacol fila o columna màxima disponible en el full en qüestió
     */
    public ExcepcioFilaColumnaInvalida(int filacol, int maxfilacol) {
        super(TipusError.FILA_COLUMNA_INVALIDA, "Error: fila o columna " + filacol + " fora de rang [0, " +
                maxfilacol + ")");
    }
}
