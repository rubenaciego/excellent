package domini;

import java.lang.*;
import java.time.LocalDate;


public class Parser {
    /**
     * @brief Fa el parseig d'una operació associada a full provinent de la capa
     * de presentació
     * * /pre opSenseParsejar és un array de tres strings on el primer conté el
     * codi d'operació, id full, fila d'origen, fila de destí, columna d'origen,
     * columna de destí, mida fila, mida columna separat per comes
     * (,); el segon string conté l'inputUsuari en cas que l'operació sigui
     * modificaCela o la stringCercada en cas que l'operació sigui
     * cercaOcurrencies o reemplaça;  el tercer string conté la
     * stringReemplaçadora en cas que l'operació sigui reemplaça
     * /post retorna les dades d'opSenseParsejar estructurades en un objecte de
     * ResultatParserFull
     */
    public ResultatParserFull parseOpFull(String[] opSenseParsejar) {
        String[] splitted = opSenseParsejar[0].split(",");
        ResultatParserFull resultat = new ResultatParserFull();

        int codiOp = Integer.parseInt(splitted[0]);
        if (codiOp >= 0 && codiOp < 10) {
            resultat.setTipusOpAritmetica(opAritmetica.valueOf(String.valueOf(codiOp)));
        } else if (codiOp >= 10 && codiOp < 20) {
            resultat.setTipusOpEstadistica(opEstadistica.valueOf(String.valueOf(codiOp - 10)));
        } else if (codiOp >= 20 && codiOp < 40) {
            resultat.setTipusConversioUnitats(conversioUnitats.valueOf(String.valueOf(codiOp - 20)));
        } else if (codiOp >= 40 && codiOp < 50) {
            resultat.setTipusCriteriOrdenacio(criteriOrdenacio.valueOf(String.valueOf(codiOp - 40)));
        } else if (codiOp >= 50 && codiOp < 80) {
            resultat.setTipusOpFull(opFull.valueOf(String.valueOf(codiOp - 50)));

            if (codiOp == 60) resultat.setStringCercada(opSenseParsejar[1]);
            else if (codiOp == 64) {
                resultat.setStringCercada(opSenseParsejar[1]);
                resultat.setStringRemplacadora(opSenseParsejar[2]);
            } else if (codiOp == 66) {
                ContingutCelaModificada celaModificada = resultat.getCelaModificada();
                if (opSenseParsejar[1].matches("-?\\d+(\\.\\d+)?")) {
                    celaModificada.setValorNumeric(Double.parseDouble(opSenseParsejar[1]));
                    celaModificada.setTipus(tipusCela.numerica);
                }
                else if (opSenseParsejar[1].matches(("^(0?[1-9]|[12][0-9]|3" +
                        "[01])-(0?[1-9]|1[012])-(\\d{4})$"))) {
                    String[] DDMMAAAA = opSenseParsejar[1].split("/");

                    celaModificada.setData(LocalDate.of(Integer.parseInt(DDMMAAAA[0]),
                            Integer.parseInt(DDMMAAAA[1]),
                            Integer.parseInt(DDMMAAAA[2])));
                    celaModificada.setTipus(tipusCela.datada);
                }
                else if (opSenseParsejar[1].startsWith("=")) {
                    String[] ref =
                            opSenseParsejar[1].split("=")[0].split(":");
                    celaModificada.setColRef(Integer.parseInt(ref[0]));
                    celaModificada.setFilaRef(Integer.parseInt(ref[1]));
                    celaModificada.setTipus(tipusCela.referencial);
                }
                else {
                    celaModificada.setInputUsuari(opSenseParsejar[1]);
                    celaModificada.setTipus(tipusCela.textual);
                }
                resultat.setCelaModificada(celaModificada);
            }

        }

        resultat.setIdFull(Integer.parseInt(splitted[1]));
        resultat.setFilaOrigen(Integer.parseInt(splitted[2]));
        resultat.setFilaDesti(Integer.parseInt(splitted[3]));
        resultat.setColumnaOrigen(Integer.parseInt(splitted[4]));
        resultat.setColumnaDesti(Integer.parseInt(splitted[5]));
        resultat.setMidaFila(Integer.parseInt(splitted[6]));
        resultat.setMidaColumna(Integer.parseInt(splitted[7]));

        return resultat;
    }

    /**
     * @brief Fa el parseig d'una operació associada a document provinent de la
     * capa de presentació
     * * /pre opSenseParsejar és un string que conté el codi d'operació, el
     * nom del document  i l'id del full a afegir o a eliminar, si
     * s'escau, separat per comes (,)
     * /post retorna les dades d'opSenseParsejar estructurades en un objecte de
     * ResultatParserDocument
     */
    public ResultatParserDocument parseOpDocument(String opSenseParsejar) {
        String[] splitted = opSenseParsejar.split(",");
        ResultatParserDocument resultat = new ResultatParserDocument();

        int codiOp = Integer.parseInt(splitted[0]);
        if (codiOp >= 80 && codiOp < 90) {
            resultat.setTipusOpDocument(opDocument.valueOf(String.valueOf(codiOp - 80)));
        }
        resultat.setNomDocument(splitted[1]);
        resultat.setIdFull(Integer.parseInt(splitted[2]));

        return resultat;
    }
}
