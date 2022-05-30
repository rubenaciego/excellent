package domini;

/**
 * Excepció provocada en intentar accedir a un full amb índex invàlid d’un document
 */
public class ExcepcioIndexFull extends ExcepcioDomini {
    /**
     * Constructora principal
     * @param index índex de full que entra l'usuari
     * @param numFulls nombre total de fulls del document
     */
    public ExcepcioIndexFull(int index, int numFulls) {
        super(TipusError.INDEX_FULL_INVALID, "Error: full amb índex " + index + " no és vàlid, hi ha " +
                numFulls + " fulls");
    }
}
