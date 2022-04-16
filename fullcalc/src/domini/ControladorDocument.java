package domini;

public class ControladorDocument
{
    ControladorDocument controladorDocument;
    Document document;
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
        if (resultat.tipusOpDocument == opDocument.afegeixFull) {
            document.afegeixFull();
        }
        else if (resultat.tipusOpDocument == opDocument.eliminaFull) {
            document.eliminaFull(resultat.idFull);
        }
        else if (resultat.tipusOpDocument == opDocument.desaDocument) {
            document.desa();
        }
    }
}
