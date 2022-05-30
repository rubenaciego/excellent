package domini;

/**
 * Excepció provocada al intentar carregar o guardar un arxiu
 */
public class ExcepcioGuardarCarregar extends ExcepcioDomini {
    /**
     * Constructora principal
     * @param nom nom del document que s'intenta obrir o desar
     */
    public ExcepcioGuardarCarregar(String nom) {
        super(TipusError.GUARDAR_CARREGAR, "Error: no es pot desar o obrir el document " + nom);
    }
}
