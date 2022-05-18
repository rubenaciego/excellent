package domini;

import dades.ControladorDades;
import vista.ControladorVista;
import vista.EntradaTaula;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorDomini {
    private Document document;
    private ArrayList<ControladorFull> controladorsFull;
    private final ControladorDades controladorDades;
    private final ControladorVista controladorVista;
    private final Parser parser;

    public ControladorDomini() {
        parser = Parser.getInstance();
        controladorDades = new ControladorDades();
        controladorVista = new ControladorVista(this);
    }

    public ControladorDomini(Document document, ArrayList<ControladorFull> controladorsFull) {
        this();
        this.document = document;
        this.controladorsFull = controladorsFull;
    }

    public Document getDocument() {
        return document;
    }

    public int getNumFulls()
    {
        if (document != null)
            return document.getNumFulls();

        return -1;
    }

    public int getNumFiles(int full)
    {
        if (document != null)
        {
            Full f = document.getFull(full);

            if (f != null)
                return f.getNumFiles();
        }

        return -1;
    }

    public int getNumCols(int full)
    {
        if (document != null)
        {
            Full f = document.getFull(full);

            if (f != null)
                return f.getNumCols();
        }

        return -1;
    }

    public ArrayList<EntradaTaula> getEntrades(int full, int srow, int scol, int numFiles, int numCols)
    {
        ArrayList<EntradaTaula> entrades = new ArrayList<EntradaTaula>();

        if (document != null)
        {
            Full f = document.getFull(full);

            if (f != null)
            {
                ArrayList<EntradaMatriuCeles> e = f.getBloc(srow, scol, numFiles, numCols).getEntrades();

                for (EntradaMatriuCeles entrada : e)
                    entrades.add(new EntradaTaula(entrada.getFila(), entrada.getColumna(), entrada.getCela().toString()));
            }
        }

        return entrades;
    }

    public void executaOperacio(String[] opSenseParsejar) {
        if (opSenseParsejar.length == 0)
            throw new ExcepcioParser(opSenseParsejar);

        TipusOperacio tipus = parser.parseTipusOperacio(opSenseParsejar[0]);

        if (tipus == TipusOperacio.OPERACIO_DOCUMENT) {
            ResultatParserDocument resultat = parser.parseOpDocument(opSenseParsejar);
            executaOperacioDocument(resultat);
        } else {
            ResultatParserFull resultat = parser.parseOpFull(opSenseParsejar);

            if (resultat.getIdFull() >= controladorsFull.size() || resultat.getIdFull() < 0)
                throw new ExcepcioIndexFull(resultat.getIdFull(), document.getNumFulls());

            controladorsFull.get(resultat.getIdFull()).executaOperacio(resultat);
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
            case CANVIA_NOM_DOCUMENT:
                if (document == null)
                    throw new ExcepcioNoDocument("Error: no hi ha cap document obert");

                document.setNom(resultat.getNomDocument());
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
        String documentTxt;

        try {
            documentTxt = controladorDades.llegeixArxiu(nomDocument);
        } catch (IOException e) {
            throw new ExcepcioGuardarCarregar(nomDocument);
        }

        FormatDocument format = getTypeFromExtension(nomDocument);
        DocumentParser dp = new DocumentParser();
        document = dp.parseFrom(documentTxt, format);
        document.setNom(nomDocument);
        controladorsFull = new ArrayList<ControladorFull>();

        for (int i = 0; i < document.getNumFulls(); ++i)
            controladorsFull.add(new ControladorFull(document.getFull(i)));
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

        try {
            controladorDades.guardaArxiu(document.getNom(), documentTxt);
        } catch (IOException e) {
            throw new ExcepcioGuardarCarregar(document.getNom());
        }
    }

    private FormatDocument getTypeFromExtension(String nom) {
        int index = nom.lastIndexOf('.');

        if (index == -1)
            throw new ExcepcioExtensioDocument(nom);

        String ext = nom.substring(index + 1).toLowerCase();

        switch (ext) {
            case "csv":
                return FormatDocument.CSV;
            case "json":
                return FormatDocument.JSON;
            default:
                throw new ExcepcioExtensioDocument(nom);
        }
    }
}
