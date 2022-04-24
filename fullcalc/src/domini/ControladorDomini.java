package domini;

import java.util.ArrayList;

public class ControladorDomini {
    private Document document;
    private final ArrayList<ControladorFull> controladorsFull;
    private final Parser parser;


    public ControladorDomini() {
        parser = Parser.getInstance();
        controladorsFull = new ArrayList<ControladorFull>();
    }

    public void creaDocument(String nomDocument) {
        document = new Document(nomDocument);
    }

    public void carregaDocument(String nomDocument) {
        // mock
        throw new UnsupportedOperationException("carregaDocument encara no implementat");
    }

    public void tancaDocument() {
        document = null;
    }

    /**
     * @brief Executa l'operació codificada dins resultat
     * * /pre resultat és del tipus ResultatParserDocument
     * /post Executa l'operació codificada dins resultat
     */
    private void executaOperacioDocument(ResultatParserDocument resultat) {
        switch (resultat.getTipusOpDocument()) {
            case CREA_DOCUMENT:
                creaDocument(resultat.getNomDocument());
                break;
            case CARREGA_DOCUMENT:
                carregaDocument(resultat.getNomDocument());
                break;
            case TANCA_DOCUMENT:
                tancaDocument();
                break;
            case AFEGEIX_FULL:
                document.afegeixFull();
                controladorsFull.add(new ControladorFull(
                        document.getFull(document.getNumFulls() - 1)));
                break;
            case ELIMINA_FULL:
                document.eliminaFull(resultat.getIdFull());
                controladorsFull.remove(resultat.getIdFull());
                break;
            case DESA_DOCUMENT:
                document.desa();
                break;
            default:
                throw new IncompatibleClassChangeError("Operació " + resultat.getTipusOpDocument() + " desconeguda");
        }
    }

    public void executaOperacio(String[] opSenseParsejar) {
        TipusOperacio tipus = parser.parseTipusOperacio(opSenseParsejar[0]);

        if (tipus == TipusOperacio.OPERACIO_DOCUMENT) {
            ResultatParserDocument resultat = parser.parseOpDocument(opSenseParsejar);
            executaOperacioDocument(resultat);
        } else {
            ResultatParserFull resultat = parser.parseOpFull(opSenseParsejar);
            controladorsFull.get(resultat.getIdFull()).executaOperacio(resultat);
        }
    }

}
