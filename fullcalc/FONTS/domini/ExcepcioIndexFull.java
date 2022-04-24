package domini;

public class ExcepcioIndexFull extends ExcepcioDomini {
    public ExcepcioIndexFull(int index, int numFulls) {
        super(TipusError.INDEX_FULL_INVALID, "Error: full amb índex " + index + " no és vàlid, hi ha " +
                numFulls + " fulls");
    }
}
