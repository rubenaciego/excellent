package domini;

/**
 * Excepció provocada per l’operador en fallar en alguna operació
 */
public class ExcepcioOperador extends ExcepcioDomini {
    /**
     * Constructora principal
     * @param error missatge de l'error
     */
    public ExcepcioOperador(String error) {
        super(TipusError.OPERADOR, error);
    }
}
