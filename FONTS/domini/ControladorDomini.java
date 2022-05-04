package domini;

import dades.ControladorDades;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorDomini {
    private Document document;
    private ArrayList<ControladorFull> controladorsFull;
    private ControladorDades controladorDades;
    private final Parser parser;


    public ControladorDomini() {
        parser = Parser.getInstance();
        controladorDades = new ControladorDades();
    }

    public ExcepcioDomini.TipusError executaOperacio(String[] opSenseParsejar) {
        try {
            TipusOperacio tipus = parser.parseTipusOperacio(opSenseParsejar[0]);

            if (tipus == TipusOperacio.OPERACIO_DOCUMENT) {
                ResultatParserDocument resultat = parser.parseOpDocument(opSenseParsejar);
                executaOperacioDocument(resultat);
            } else {
                ResultatParserFull resultat = parser.parseOpFull(opSenseParsejar);
                controladorsFull.get(resultat.getIdFull()).executaOperacio(resultat);
            }

            return ExcepcioDomini.TipusError.NO_ERROR;
        } catch (ExcepcioDomini e) {
            System.out.println(e.getMessage());
            return e.getTipusError();
        }
    }

    /**
     * @brief Executa l'operació codificada dins resultat
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
                if (document == null)
                    throw new ExcepcioNoDocument("Error: no hi ha cap document obert");

                document.afegeixFull();
                controladorsFull.add(new ControladorFull(
                        document.getFull(document.getNumFulls() - 1)));
                break;
            case ELIMINA_FULL:
                if (document == null)
                    throw new ExcepcioNoDocument("Error: no hi ha cap document obert");

                document.eliminaFull(resultat.getIdFull());
                controladorsFull.remove(resultat.getIdFull());
                break;
            case DESA_DOCUMENT:
                if (document == null)
                    throw new ExcepcioNoDocument("Error: no hi ha cap document obert");

                desaDocument();
                break;
            default:
                throw new IncompatibleClassChangeError("Operació " + resultat.getTipusOpDocument() + " desconeguda");
        }
    }

    private void creaDocument(String nomDocument) {
        document = new Document(nomDocument);
        controladorsFull = new ArrayList<ControladorFull>();
    }

    private void carregaDocument(String nomDocument) {
        String documentTxt = controladorDades.llegeixArxiu(nomDocument);
        FormatDocument format = getTypeFromExtension(nomDocument);
        DocumentParser dp = new DocumentParser();
        document = dp.parseFrom(documentTxt, format);
    }

    private void tancaDocument() {
        document = null;
        controladorsFull = null;
    }

    private void desaDocument() {
        document.setDataModificacio(LocalDateTime.now());
        FormatDocument format = getTypeFromExtension(document.getNom());
        DocumentConverter dc = new DocumentConverter(document);
        String documentTxt = dc.convertTo(format);

        controladorDades.guardaArxiu(document.getNom(), documentTxt);
    }

    public Document getDocument() {
        return document;
    }

    private FormatDocument getTypeFromExtension(String nom)
    {
        int index = nom.lastIndexOf('.');

        if (index == -1)
            throw new ExcepcioExtensioDocument(nom);

        String ext = nom.substring(index + 1).toLowerCase();

        switch (ext)
        {
            case "csv": return FormatDocument.CSV;
            case "json": return FormatDocument.JSON;
            default: throw new ExcepcioExtensioDocument(nom);
        }
    }
}
