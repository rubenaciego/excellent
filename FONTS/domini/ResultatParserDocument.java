package domini;
import java.lang.*;

/**
 * Resultat de la decodificació en el parser d'una operació sobre una document
 */
public class ResultatParserDocument {
    /**
     * id del full sobre el que aplicar l'operació, si escau
     */
    private int idFull;
    /**
     * Tipus de l'operació a executar
     */
    private OperacioDocument tipusOperacioDocument;
    /**
     * Nom del document, si escau
     */
    private String nomDocument;

    /**
     * Getter del id del full
     * @return id del full
     */
    public int getIdFull() {
        return idFull;
    }

    /**
     * Setter del id del full
     * @param idFull id del full
     */
    public void setIdFull(int idFull) {
        this.idFull = idFull;
    }

    /**
     * Getter del nom del document
     * @return nom del document
     */
    public String getNomDocument() {
        return nomDocument;
    }

    /**
     * Setter del nom del document
     * @param nomDocument nom del document
     */
    public void setNomDocument(String nomDocument) {
        this.nomDocument = nomDocument;
    }

    /**
     * Getter del tipus d'operació a executar
     * @return tipus d'operació a executar
     */
    public OperacioDocument getTipusOpDocument() {
        return tipusOperacioDocument;
    }

    /**
     * Setter del tipus d'operació a executar
     * @param tipusOperacioDocument tipus d'operació a executar
     */
    public void setTipusOpDocument(OperacioDocument tipusOperacioDocument) {
        this.tipusOperacioDocument = tipusOperacioDocument;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof ResultatParserDocument)) {
            return false;
        }

        ResultatParserDocument c = (ResultatParserDocument) o;

        return (idFull == c.idFull && tipusOperacioDocument == c.tipusOperacioDocument && nomDocument == c.nomDocument);
    }
}
