package domini;

import java.lang.*;
import java.time.LocalDate;


public class Parser {
    /**
     * @brief Fa el parseig d'una operació provinent de la capa de presentació
     * * /pre opFull és un array de tres strings on el primer conté el codi
     * d'operació fila d'origen, fila de destí, columna d'origen, columna de
     * destí, mida fila, mida columna separat per comes (,); el segon string
     * conté l'inputUsuari en cas que l'operació sigui modificaCela o la
     * stringCercada en cas que l'operació sigui cercaOcurrencies o
     * reemplaça;  el tercer string conté la stringReemplaçadora en cas que
     * l'operació sigui reemplaça
     * /post retorna les dades d'opFull estructurades en un objecte de
     * ResultatParserFull
     */
    public ResultatParserFull parseOpFull(String[] opSenseParsejar) {
        String[] splitted = opSenseParsejar[0].split(",");
        ResultatParserFull resultat = new ResultatParserFull();

        int codiOp = Integer.parseInt(splitted[0]);
        if (codiOp >= 0 && codiOp < 10) {
            resultat.tipusOpAritmetica =
                    opAritmetica.valueOf(String.valueOf(codiOp));
        } else if (codiOp >= 10 && codiOp < 20) {
            resultat.tipusOpEstadistica =
                    opEstadistica.valueOf(String.valueOf(codiOp - 10));
        } else if (codiOp >= 20 && codiOp < 40) {
            resultat.tipusConversioUnitats =
                    conversioUnitats.valueOf(String.valueOf(codiOp - 20));
        } else if (codiOp >= 40 && codiOp < 50) {
            resultat.tipusCriteriOrdenacio =
                    criteriOrdenacio.valueOf(String.valueOf(codiOp - 40));
        } else if (codiOp >= 50 && codiOp < 80) {
            resultat.tipusOpFull =
                    opFull.valueOf(String.valueOf(codiOp - 50));

            if (codiOp == 60) resultat.stringCercada = opSenseParsejar[1];
            else if (codiOp == 64) {
                resultat.stringCercada = opSenseParsejar[1];
                resultat.stringRemplacadora = opSenseParsejar[2];
            } else if (codiOp == 66) {
                if (opSenseParsejar[1].matches("-?\\d+(\\.\\d+)?")) {
                    resultat.celaModificada.valorNumeric =
                            Double.parseDouble(opSenseParsejar[1]);
                    resultat.celaModificada.tipus = tipusCela.numerica;
                }
                else if (opSenseParsejar[1].matches(("^(0?[1-9]|[12][0-9]|3" +
                        "[01])-(0?[1-9]|1[012])-(\\d{4})$"))) {
                    String[] DDMMAAAA = opSenseParsejar[1].split("/");

                    resultat.celaModificada.data =
                            LocalDate.of(Integer.parseInt(DDMMAAAA[0]),
                                    Integer.parseInt(DDMMAAAA[1]),
                                    Integer.parseInt(DDMMAAAA[2]));
                    resultat.celaModificada.tipus = tipusCela.datada;
                }
                else if (opSenseParsejar[1].startsWith("=")) {
                    String[] ref =
                            opSenseParsejar[1].split("=")[0].split(":");
                    resultat.celaModificada.colRef =
                            Integer.parseInt(ref[0]);
                    resultat.celaModificada.filaRef =
                            Integer.parseInt(ref[1]);
                    resultat.celaModificada.tipus = tipusCela.referencial;
                }
                else {
                    resultat.celaModificada.inputUsuari = opSenseParsejar[1];
                    resultat.celaModificada.tipus = tipusCela.textual;
                }
            }

        }
        resultat.filaOrigen = Integer.parseInt(splitted[1]);
        resultat.filaDesti = Integer.parseInt(splitted[2]);
        resultat.columnaOrigen = Integer.parseInt(splitted[3]);
        resultat.columnaDesti = Integer.parseInt(splitted[4]);
        resultat.midaFila = Integer.parseInt(splitted[5]);
        resultat.midaColumna = Integer.parseInt(splitted[6]);

        return resultat;
    }

    public ResultatParserDocument parseOpDocument(String opSenseParsejar) {
        String[] splitted = opSenseParsejar.split(",");
        ResultatParserDocument resultat = new ResultatParserDocument();

        resultat.idFull = Integer.parseInt(splitted[0]);
        int codiOp = Integer.parseInt(splitted[1]);
        if (codiOp >= 80 && codiOp < 90) {
            resultat.tipusOpDocument =
                    opDocument.valueOf(String.valueOf(codiOp - 80));
        }
        resultat.nomDocument = splitted[2];

        return resultat;
    }
}
