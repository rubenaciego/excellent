package domini;

public class ControladorDocument
{
    private ControladorDocument controladorDocument;
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
        if (resultat.getTipusOpDocument() == opDocument.afegeixFull) {
            document.afegeixFull();
        }
        else if (resultat.getTipusOpDocument() == opDocument.eliminaFull) {
            document.eliminaFull(resultat.getIdFull());
        }
        else if (resultat.getTipusOpDocument() == opDocument.desaDocument) {
            document.desa();
        }
    }
}
