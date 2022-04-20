package domini;

import java.util.ArrayList;

public class ControladorDomini
{
    private Document document;
    private ArrayList<ControladorFull> controladorsFull;
    private final Parser parser;

    /**
     * @brief Executa l'operació codificada dins opSenseParsejar
     * * /pre opSenseParsejar és un array de tres strings que conté el codi
     * d'operació, el nom del document  i l'id del full a afegir o a
     * eliminar, si s'escau, separat per comes (,) en el primer string si és
     * una operació de document (els altres dos strings són buits) o
     * un array de tres strings on el primer conté el codi d'operació, id
     * full, fila d'origen, fila de destí, columna d'origen, columna de
     * destí, mida fila, mida columna separat per comes (,); el segon string
     * conté l'inputUsuari en cas que l'operació sigui modificaCela o la
     * stringCercada en cas que l'operació sigui cercaOcurrencies o
     * reemplaça; el tercer string conté la stringReemplaçadora en cas que
     * l'operació sigui reemplaça, si és una operació de full
     * /post retorna les dades d'opSenseParsejar estructurades en un objecte de
     * ResultatParserDocument
     */

    public ControladorDomini()
    {
        parser = Parser.getInstance();
        controladorsFull = new ArrayList<ControladorFull>();
    }


    public void creaDocument(String nomDocument)
    {
        document = new Document(nomDocument);
    }

    public void carregaDocument(String nomDocument)
    {
        // mock
    }

    public void tancaDocument()
    {
        document = null;
    }

    /**
     * @brief Executa l'operació codificada dins resultat
     * * /pre resultat és del tipus ResultatParserDocument
     * /post Executa l'operació codificada dins resultat
     */
    public void executaOperacio(ResultatParserDocument resultat)
    {
        if (resultat.getTipusOpDocument() == OperacioDocument.AFEGEIX_FULL) {
            document.afegeixFull();
        } else if (resultat.getTipusOpDocument() == OperacioDocument.ELIMINA_FULL) {
            document.eliminaFull(resultat.getIdFull());
        } else if (resultat.getTipusOpDocument() == OperacioDocument.DESA_DOCUMENT) {
            document.desa();
        }
    }

    public void executaOperacio(String[] opSenseParsejar)
    {
        /* Hem de distingir dos tipus d'operacions
            - Operacions de document: s'executen AQUI mateix
            - Operacions de full: es passen al controlador full adient

            PD: hauriem d'ajuntar aquesta funció i la de dalt
         */
        // D'alguna manera també hauríem de detectar quines operacions modifiquen el full/doc (si no totes)
        // i actualitzar la data de modificacio del document
        if (opSenseParsejar[0].startsWith("8")) {
            // Aqui falten crear, eliminar fulls
            ResultatParserDocument resultat =
                    parser.parseOpDocument(opSenseParsejar[0]);
            if (resultat.getTipusOpDocument() == OperacioDocument.CREA_DOCUMENT) {
                creaDocument(resultat.getNomDocument());
            } else if (resultat.getTipusOpDocument() == OperacioDocument.CARREGA_DOCUMENT) {
                carregaDocument(resultat.getNomDocument());
            } else if (resultat.getTipusOpDocument() == OperacioDocument.TANCA_DOCUMENT) {
                tancaDocument();
            } else {
                executaOperacio(resultat);
            }
        } else {
            ResultatParserFull resultat = parser.parseOpFull(opSenseParsejar);
            controladorsFull.get(resultat.getIdFull()).executaOperacio(resultat);
        }
    }
}
