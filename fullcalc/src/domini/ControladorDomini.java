package domini;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorDomini {
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

    public ControladorDomini() {
        parser = Parser.getInstance();
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
        throw new UnsupportedOperationException("carregaDocument encara no implementat");
    }

    private void tancaDocument() {
        document = null;
        controladorsFull = null;
    }

    private void desaDocument() {
        document.setDataModificacio(LocalDateTime.now());
        throw new UnsupportedOperationException("desaDocument encara no implementat");

    }
}
