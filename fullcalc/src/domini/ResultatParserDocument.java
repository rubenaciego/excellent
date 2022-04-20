package domini;
import java.lang.*;

public class ResultatParserDocument
{
    private int idFull;
    private OperacioDocument tipusOperacioDocument;
    private String nomDocument;

    public int getIdFull() {
        return idFull;
    }

    public void setIdFull(int idFull) {
        this.idFull = idFull;
    }

    public String getNomDocument() {
        return nomDocument;
    }

    public void setNomDocument(String nomDocument) {
        this.nomDocument = nomDocument;
    }

    public OperacioDocument getTipusOpDocument() {
        return tipusOperacioDocument;
    }

    public void setTipusOpDocument(OperacioDocument tipusOperacioDocument) {
        this.tipusOperacioDocument = tipusOperacioDocument;
    }
}
