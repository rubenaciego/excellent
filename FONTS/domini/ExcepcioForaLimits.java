package domini;

/**
 * Excepció provocada en accedir a una Cela fora de límits d’una MatriuCeles
 */
public class ExcepcioForaLimits extends ExcepcioDomini {
    /**
     * Constructora principal
     * @param filaInici fila de la Cela superior esquerra del bloc
     * @param colInici columna de la Cela superior esquerra del bloc
     * @param numFiles número de files del bloc
     * @param numCols número de columnes del bloc
     * @param totalFiles número de files de la MatriuCeles corresponent
     * @param totalCols número de columnes de la MatriuCeles corresponent
     */
    public ExcepcioForaLimits(int filaInici, int colInici, int numFiles, int numCols, int totalFiles, int totalCols) {
        super(TipusError.CELA_FORA_LIMITS, "Error: accés fora de límits al bloc amb cel·la inicial (" +
                filaInici + ", " + colInici + ") i mida (" + numFiles + ", " + numCols + "), on la mida total és (" +
                totalFiles + ", " + totalCols + ")");
    }
}
