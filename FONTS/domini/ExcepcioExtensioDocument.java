package domini;

/**
 * Excepció al carregar un document amb una extensió no vàlida
 */
public class ExcepcioExtensioDocument extends ExcepcioDomini {
    /**
     * Creadora principal
     * @param nom nom del document que correspon a l'error
     */
    public ExcepcioExtensioDocument(String nom) {
        super(TipusError.EXTENSIO_DOCUMENT, getMissatge(nom));
    }

    /**
     * Crea el missatge en funció del nom de document que es passa com a argument
     * @param nom nom del document que dona error
     * @return el missatge d'error en format String
     */
    private static String getMissatge(String nom) {
        StringBuilder sb = new StringBuilder();
        sb.append("Error: extensió del fitxer ");
        sb.append(nom);
        sb.append(" no vàlida, els formats suportats són:");

        for (FormatDocument fd : FormatDocument.values()) {
            sb.append(' ');
            sb.append(fd.toString());
        }

        return sb.toString();
    }
}
