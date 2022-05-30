package domini;

/**
 * Excepció provocada al intentar carregar un arxiu corrupte
 */
public class ExcepcioFormatDocument extends ExcepcioDomini {
    /**
     * Constructora principal
     * @param format el format del document erroni a carregar
     * @param file el nom del document
     */
    public ExcepcioFormatDocument(FormatDocument format, String file) {
        super(TipusError.FORMAT_DOCUMENT, "Error al carregar el document en format " + format + ":\n" + file);
    }
}
