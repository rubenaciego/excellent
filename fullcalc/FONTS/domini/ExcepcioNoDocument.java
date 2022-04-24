package domini;

public class ExcepcioNoDocument extends ExcepcioDomini {
    public ExcepcioNoDocument(String error) {
        super(TipusError.NO_DOCUMENT, error);
    }
}
