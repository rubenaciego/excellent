package domini;

import dades.ControladorDades;
import vista.ControladorVista;
import vista.EntradaTaula;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Controlador de la capa de Domini i principal controlador de l'aplicació.
 */
public class ControladorDomini {
    /**
     * Document sobre el que s'està treballant en la capa de Domini.
     */
    private Document document;
    /**
     * Controladors de tots els fulls del document sobre el que s'està
     * treballant.
     */
    private ArrayList<ControladorFull> controladorsFull;
    /**
     * Controlador de la capa de Dades per gestionar la persistència.
     */
    private final ControladorDades controladorDades;
    /**
     * Controlador de la capa de Dades per gestionar les vistes de l'aplicació.
     */
    private final ControladorVista controladorVista;
    /**
     * Parser encarregat de decodificar les instruccions que arribin al
     * controlador.
     */
    private final Parser parser;

    /**
     * Constructora principal que inicialitza també controladors de dades i
     * vista.
     */
    public ControladorDomini() {
        parser = Parser.getInstance();
        controladorDades = new ControladorDades();
        controladorVista = new ControladorVista(this);
    }

    /**
     * Constructora secundaria que inicialitza un controlador domini i hi
     * carrega un document i els controladors dels fulls del document
     * @param document document que es carrega al controlador
     * @param controladorsFull controladors dels fulls del document que s'ha
     *                         carregat
     */
    public ControladorDomini(Document document, ArrayList<ControladorFull> controladorsFull) {
        this();
        this.document = document;
        this.controladorsFull = controladorsFull;
    }

    /**
     * Getter que retorna el document sobre el que està treballant el
     * controlador.
     * @return el document sobre el que està treballant el controlador
     */
    public Document getDocument() {
        return document;
    }

    /**
     * Getter del nombre de fulls del document sobre el que està treballant el controlador.
     * @return nombre de fulls del document sobre el que està treballant el controlador
     */
    public int getNumFulls() {
        if (document == null)
            throw new ExcepcioNoDocument("Error: no hi ha document obert");

        return controladorsFull.size();
    }

    /**
     * Getter del nombre de files del full especificat del document sobre el
     * que està treballant el controlador.
     * @param full full del que es vol obtenir el nombre de files
     * @return nombre de files del full especificat del document sobre el que
     * està treballant el controlador
     */
    public int getNumFiles(int full) {
        if (document == null)
            throw new ExcepcioNoDocument("Error: no hi ha document obert");
        if (full < 0 || full >= controladorsFull.size())
            throw new ExcepcioIndexFull(full, controladorsFull.size());

        return controladorsFull.get(full).getNumFiles();
    }

    /**
     * Getter del nombre de columnes del full especificat del document sobre el
     * que està treballant el controlador.
     * @param full full del que es vol obtenir el nombre de columnes
     * @return nombre de columnes del full del document sobre el
     *      * que està treballant el controlador
     */
    public int getNumCols(int full) {
        if (document == null)
            throw new ExcepcioNoDocument("Error: no hi ha document obert");
        if (full < 0 || full >= controladorsFull.size())
            throw new ExcepcioIndexFull(full, controladorsFull.size());

        return controladorsFull.get(full).getNumCols();
    }

    /**
     * Getter de l'string input usuari de la cel·la
     * @param full full on és la cel·la
     * @param fila fila de cel·la
     * @param col columna de cel·la
     * @return inputUsuari de la cel·la i un string buit si no hi ha cap
     */
    public String getInputUsuari(int full, int fila, int col) {
        if (document == null)
            throw new ExcepcioNoDocument("Error: no hi ha document obert");
        if (full < 0 || full >= controladorsFull.size())
            throw new ExcepcioIndexFull(full, controladorsFull.size());

        return controladorsFull.get(full).getInputUsuari(fila, col);
    }

    /**
     * Getter d'un bloc de les entrades d'un full del document sobre el que
     * està treballant el controlador.
     * @param full full del que es vol obtenir les entrades
     * @param srow fila des d'on començar a obtenir les entrades
     * @param scol columna des d'on començar a obtenir les entrades
     * @param numFiles nombre de files d'entrades a obtenir
     * @param numCols nombre de columnes d'entrades a obtenir
     * @return les entrades no nul·les del full especificat que formin part de
     * l'interval
     * [srow, srow+numFiles]x[scol+numCols]
     */
    public ArrayList<EntradaTaula> getEntrades(int full, int srow, int scol, int numFiles, int numCols) {
        if (document == null)
            throw new ExcepcioNoDocument("Error: no hi ha document obert");
        if (full < 0 || full >= controladorsFull.size())
            throw new ExcepcioIndexFull(full, controladorsFull.size());

        return controladorsFull.get(full).getEntrades(srow, scol, numFiles, numCols);
    }

    /**
     * Executa l'operació donada per un missatge.
     * @param opSenseParsejar missatge que codifica l'operació sense parsejar
     */
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
     * Executa una operació sobre el document en que s'està treballant.
     * @param resultat resultat del parsejat del missatge que codifica
     *                 l'operació amb tots els paràmetres necessaris per a la
     *                 seva correcta execució
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

                document.afegeixFull(resultat.getNumFiles(), resultat.getNumCols());
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

    /**
     * Getter de la cel·la resultat d'un full del document sobre el que
     * està treballant el controlador.
     * @param full full del que es vol obtenir la cel·la resultat
     * @return retorna el contingut de la cel·la resultat del full indicat
     */
    public String getCelaResultat(int full) {
        if (document == null)
            throw new ExcepcioNoDocument("Error: no hi ha document obert");
        if (full < 0 || full >= controladorsFull.size())
            throw new ExcepcioIndexFull(full, controladorsFull.size());

        return controladorsFull.get(full).getCelaResultat();
    }

    /**
     * Crea un nou document i el carrega per treballar sobre el mateix.
     * @param nomDocument nom del document que a crear
     */
    private void creaDocument(String nomDocument) {
        document = new Document(nomDocument);
        controladorsFull = new ArrayList<ControladorFull>();
    }

    /**
     * Carrega de memòria un document sobre el que treballar.
     * @param nomDocument nom del document a carregar
     */
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

    /**
     * Tanca el document sobre el que s'està treballant.
     */
    private void tancaDocument() {
        document = null;
        controladorsFull = null;
    }

    /**
     * Desa a memòria el document sobre el que s'està treballant.
     */
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

    /**
     * Retorna el format del document especificat (CVS o JSON).
     * @param nom nom del document del que es vol extreure el format en memòria.
     * @return el format d'arxiu del document especificat.
     */
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
