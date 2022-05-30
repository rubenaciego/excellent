package domini;

/**
 * Excepció provocada al intentar una operació sobre un document quan no hi ha cap document obert
 */
public class ExcepcioNoDocument extends ExcepcioDomini {
    /**
     * Constructora principal
     * @param error missatge d'error
     */
    public ExcepcioNoDocument(String error) {
        super(TipusError.NO_DOCUMENT, error);
    }
}
