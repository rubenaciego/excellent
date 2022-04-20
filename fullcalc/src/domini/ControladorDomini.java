package domini;

public class ControladorDomini
{
    private ControladorDocument controladorDocument;
    private ControladorFull controladorFull;
    private Parser parser;

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
    public void executaOperacio(String[] opSenseParsejar) {
        if(opSenseParsejar[0].startsWith("8")) {
            ResultatParserDocument resultat =
                    parser.parseOpDocument(opSenseParsejar[0]);
            if (resultat.getTipusOpDocument() == OperacioDocument.CREA_DOCUMENT) {
                controladorDocument.creaDocument(resultat.getNomDocument());
            }
            else if (resultat.getTipusOpDocument() == OperacioDocument.CARREGA_DOCUMENT) {
                controladorDocument.carregaDocument(resultat.getNomDocument());
            }
            else if (resultat.getTipusOpDocument() == OperacioDocument.TANCA_DOCUMENT) {
                controladorDocument.tancaDocument();
            }
            else {
                controladorDocument.executaOperacio(resultat);
            }
        }
        else {
            ResultatParserFull resultat = parser.parseOpFull(opSenseParsejar);
            controladorFull.executaOperacio(resultat);
        }
    }
}
