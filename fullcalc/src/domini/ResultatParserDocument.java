package domini;
import java.lang.*;

public class ResultatParserDocument
{
    private int idFull;
    private OpDocument tipusOpDocument;
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

    public OpDocument getTipusOpDocument() {
        return tipusOpDocument;
    }

    public void setTipusOpDocument(OpDocument tipusOpDocument) {
        this.tipusOpDocument = tipusOpDocument;
    }
}
