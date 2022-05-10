package domini;

public class ExcepcioFormatDocument extends ExcepcioDomini {
    public ExcepcioFormatDocument(FormatDocument format, String file) {
        super(TipusError.FORMAT_DOCUMENT, "Error al carregar el document en format " + format + ":\n" + file);
    }
}
