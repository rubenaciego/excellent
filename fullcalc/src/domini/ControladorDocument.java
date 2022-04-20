package domini;

public class ControladorDocument
{
    private Document document;

    public void creaDocument(String nomDocument) {
    }
    public void carregaDocument(String nomDocument) {

    }
    public void tancaDocument() {

    }

    /**
     * @brief Executa l'operació codificada dins resultat
     * * /pre resultat és del tipus ResultatParserDocument
     * /post Executa l'operació codificada dins resultat
     */
    public void executaOperacio(ResultatParserDocument resultat) {
        if (resultat.getTipusOpDocument() == OpDocument.AFEGEIX_FULL) {
            document.afegeixFull();
        }
        else if (resultat.getTipusOpDocument() == OpDocument.ELIMINA_FULL) {
            document.eliminaFull(resultat.getIdFull());
        }
        else if (resultat.getTipusOpDocument() == OpDocument.DESA_DOCUMENT) {
            document.desa();
        }
    }
}
