package domini;

public class ExcepcioExtensioDocument extends ExcepcioDomini {
    public ExcepcioExtensioDocument(String nom) {
        super(TipusError.EXTENSIO_DOCUMENT, getMissatge(nom));
    }

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
