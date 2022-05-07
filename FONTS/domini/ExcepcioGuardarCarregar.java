package domini;

public class ExcepcioGuardarCarregar extends ExcepcioDomini {
    public ExcepcioGuardarCarregar(String nom) {
        super(TipusError.GUARDAR_CARREGAR, "Error: no es pot desar o obrir el document " + nom);
    }
}
