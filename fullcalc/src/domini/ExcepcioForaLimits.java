package domini;

public class ExcepcioForaLimits extends ExcepcioDomini {
    public ExcepcioForaLimits(int filaInici, int colInici, int numFiles, int numCols, int totalFiles, int totalCols) {
        super(TipusError.FORA_LIMITS, "Error: accés fora de límits al bloc amb cel·la inicial (" +
                filaInici + ", " + colInici + ") i mida (" + numFiles + ", " + numCols + "), on la mida total és (" +
                totalFiles + ", " + totalCols + ")");
    }
}
