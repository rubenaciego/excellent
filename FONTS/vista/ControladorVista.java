package vista;

import domini.ControladorDomini;
import domini.ExcepcioDomini;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.Utilitats;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Controlador de la capa de presentació.
 */
public class ControladorVista {
    /**
     * Controlador de domini enllaçat
     */
    private final ControladorDomini controladorDomini;
    /**
     * Finestra de la vista principal del programa
     */
    private final WindowPrincipal window;

    /**
     * Constructor principal del controlador que l'enllaça amb un controlador
     * de domini
     * @param controladorDomini controlador de domini amb que enllaçar el de
     *                          vista
     */
    public ControladorVista(ControladorDomini controladorDomini) {
        this.controladorDomini = controladorDomini;
        window = new WindowPrincipal(this);
    }

    /**
     * Afegeix un full
     */
    public void afegeixFull() {
        String[] message = {"OPERACIO_DOCUMENT,AFEGEIX_FULL"};

        try {
            controladorDomini.executaOperacio(message);
            int i = controladorDomini.getNumFulls() - 1;
            window.afegeixFull(controladorDomini.getNumFiles(i), controladorDomini.getNumCols(i));
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Esborra un full demanat
     * @param index index del full a esborrar
     */
    public void esborraFull(int index) {
        String[] message = {"OPERACIO_DOCUMENT,ELIMINA_FULL," + index};

        try {
            controladorDomini.executaOperacio(message);
            window.esborraFull(index);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Crea un document
     * @param nom nom del document a crear
     */
    public void creaDocument(String nom) {
        String[] message = {"OPERACIO_DOCUMENT,CREA_DOCUMENT", nom};

        try {
            controladorDomini.executaOperacio(message);
            window.setDocument(nom);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reanomena un document
     * @param nom nou nom del document
     */
    public void reanomenaDocument(String nom) {
        String[] message = {"OPERACIO_DOCUMENT,CANVIA_NOM_DOCUMENT", nom};

        try {
            controladorDomini.executaOperacio(message);
            window.setDocument(nom);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Desa un document en memòria
     */
    public void desaDocument() {
        String[] message = {"OPERACIO_DOCUMENT,DESA_DOCUMENT"};

        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tanca el document carregat a l'aplicació
     */
    public void tancaDocument() {
        String[] message = {"OPERACIO_DOCUMENT,TANCA_DOCUMENT"};

        try {
            controladorDomini.executaOperacio(message);
            window.esborraFulls();
            window.tancaDocument();
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Carrega un document a l'aplicació
     * @param nom nom del document a carregar
     */
    public void carregaDocument(String nom) {
        String[] message = {"OPERACIO_DOCUMENT,CARREGA_DOCUMENT", nom};

        try {
            controladorDomini.executaOperacio(message);
            window.setDocument(nom);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < controladorDomini.getNumFulls(); ++i) {
            int files = controladorDomini.getNumFiles(i);
            int cols = controladorDomini.getNumCols(i);
            window.afegeixFull(files, cols);
            window.setEntradesFull(i, controladorDomini.getEntrades(i, 0, 0, files, cols));
        }

        window.setFocusedFull(0);
    }

    /**
     * Modifica una cel·la donada d'un full amb un nou contingut
     * @param input nou contingut de la cel·la
     * @param full full on es troba la cel·la a modificar
     * @param filaDesti fila que ocupa la cel·la a modificar
     * @param colDesti columna que ocupa la cel·la a modificar
     */
    public void modificaCela(String input, int full, int filaDesti,
                             int colDesti) {
        String[] message =
                {"OPERACIO_FULL," + full + ",,,,," + filaDesti + "," + colDesti +
                        ",MODIFICA_CELA",
                        input};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula el valor absolut d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void valorAbsolut(int full, int filaOrigen, int colOrigen,
                             int numFiles, int numCols, int filaDesti,
                             int colDesti) {
        String[] message =
                {"OPERACIO_ARITMETICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",VALOR_ABSOLUT"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula l'increment d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void incrementar(int full, int filaOrigen, int colOrigen,
                            int numFiles, int numCols, int filaDesti,
                            int colDesti) {
        String[] message =
                {"OPERACIO_ARITMETICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",INCREMENTAR"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula el decrement d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void decrementar(int full, int filaOrigen, int colOrigen,
                            int numFiles, int numCols, int filaDesti,
                            int colDesti) {
        String[] message =
                {"OPERACIO_ARITMETICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",DECREMENTAR"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula l'exponencial d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void exponencial(int full, int filaOrigen, int colOrigen,
                            int numFiles, int numCols, int filaDesti,
                            int colDesti) {
        String[] message =
                {"OPERACIO_ARITMETICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",EXPONENCIAL"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula el cosinus d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void cosinus(int full, int filaOrigen, int colOrigen,
                        int numFiles, int numCols, int filaDesti,
                        int colDesti) {
        String[] message =
                {"OPERACIO_ARITMETICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",COSINUS"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula el sinus d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void sinus(int full, int filaOrigen, int colOrigen,
                      int numFiles, int numCols, int filaDesti,
                      int colDesti) {
        String[] message =
                {"OPERACIO_ARITMETICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",SINUS"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula el cosinus hiperbòlic d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void cosinusHiperbolic(int full, int filaOrigen, int colOrigen,
                                  int numFiles, int numCols, int filaDesti,
                                  int colDesti) {
        String[] message =
                {"OPERACIO_ARITMETICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",COSINUS_HIPERBOLIC"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula el sinus hiperbòlic d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void sinusHiperbolic(int full, int filaOrigen, int colOrigen,
                                int numFiles, int numCols, int filaDesti,
                                int colDesti) {
        String[] message =
                {"OPERACIO_ARITMETICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",SINUS_HIPERBOLIC"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula la tangent hiperbòlica d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void tangentHiperbolic(int full, int filaOrigen, int colOrigen,
                                  int numFiles, int numCols, int filaDesti,
                                  int colDesti) {
        String[] message =
                {"OPERACIO_ARITMETICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",TANGENT_HIPERBOLIC"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula el truncament d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     * @param digitsTruncar xifra del dígit al qual truncar
     */
    public void truncar(int full, int filaOrigen, int colOrigen,
                        int numFiles, int numCols, int filaDesti,
                        int colDesti, int digitsTruncar) {
        String[] message =
                {"TRUNCA_NUMERO," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + "," + digitsTruncar};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula la longitut del text d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void longitudText(int full, int filaOrigen, int colOrigen,
                             int numFiles, int numCols, int filaDesti,
                             int colDesti) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",EXTREU_LONGITUD_TEXT"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Converteix a majúscules d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void majuscules(int full, int filaOrigen, int colOrigen,
                           int numFiles, int numCols, int filaDesti,
                           int colDesti) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",CONVERTEIX_MAJUSCULES"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Converteix a minúscules d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void minuscules(int full, int filaOrigen, int colOrigen,
                           int numFiles, int numCols, int filaDesti,
                           int colDesti) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",CONVERTEIX_MINUSCULES"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Cerca en un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param stringCercada expressió a cercar
     */
    public void cerca(int full, int filaOrigen, int colOrigen,
                      int numFiles, int numCols, String stringCercada) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + ",-1,-1,CERCA_OCURRENCIES",
                        stringCercada};
        try {
            controladorDomini.executaOperacio(message);
            String res = controladorDomini.getCelaResultat(full);

            JSONObject json = JSONObject.fromObject(res);
            StringBuilder sb = new StringBuilder();

            sb.append("String cercada: ");
            sb.append(stringCercada);
            sb.append("\nOcurrències totals: ");
            sb.append(json.getInt("ocurrencies"));

            for (Iterator it = json.keys(); it.hasNext(); ) {
                String key = (String) it.next();
                if (key.equals("ocurrencies"))
                    continue;

                JSONObject cela = json.getJSONObject(key);
                int count = cela.getInt("ocurrencies");
                JSONArray indices = cela.getJSONArray("indexs");

                int i = key.indexOf(':');
                int fila = Integer.parseInt(key.substring(0, i)) + filaOrigen;
                int col = Integer.parseInt(key.substring(i + 1)) + colOrigen;
                String c = Utilitats.convertirATextCela(fila, col);

                sb.append("\n\nCel·la ");
                sb.append(c);
                sb.append(": ");
                sb.append(count);
                sb.append(" ocurrència/es\nÍndexos:");

                for (int j = 0; j < indices.size(); ++j) {
                    sb.append(' ');
                    sb.append(indices.getInt(j));
                }
            }

            window.missatge("Resultat cerca", sb.toString());
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reemplaça en un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param stringCercada expressió a cercar
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     * @param stringReemplacadora expressió per la qual reemplaçar
     *                            l'expressió a cercar
     *
     */
    public void reemplaca(int full, int filaOrigen, int colOrigen,
                          int numFiles, int numCols, int filaDesti,
                          int colDesti, String stringCercada,
                          String stringReemplacadora) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",REEMPLACA",
                        stringCercada, stringReemplacadora};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula la mitjana d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void mitjana(int full, int filaOrigen, int colOrigen,
                        int numFiles, int numCols, int filaDesti,
                        int colDesti) {
        String[] message =
                {"OPERACIO_ESTADISTICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",MITJANA"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula la mediana d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void mediana(int full, int filaOrigen, int colOrigen,
                        int numFiles, int numCols, int filaDesti,
                        int colDesti) {
        String[] message =
                {"OPERACIO_ESTADISTICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",MEDIANA"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula la variància d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void variancia(int full, int filaOrigen, int colOrigen,
                          int numFiles, int numCols, int filaDesti,
                          int colDesti) {
        String[] message =
                {"OPERACIO_ESTADISTICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",VARIANCIA"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula la desviació estàndard d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void desviacioEstandard(int full, int filaOrigen, int colOrigen,
                                   int numFiles, int numCols, int filaDesti,
                                   int colDesti) {
        String[] message =
                {"OPERACIO_ESTADISTICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",DESVIACIO_ESTANDARD"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula la covariància d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void covariancia(int full, int filaOrigen, int colOrigen,
                            int numFiles, int numCols, int filaDesti,
                            int colDesti) {
        String[] message =
                {"OPERACIO_ESTADISTICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",COVARIANCIA"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Calcula el coeficient de Pearson d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void coeficientPearson(int full, int filaOrigen, int colOrigen,
                                  int numFiles, int numCols, int filaDesti,
                                  int colDesti) {
        String[] message =
                {"OPERACIO_ESTADISTICA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",COEFICIENT_PEARSON"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Extreu el dia d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void extreureDia(int full, int filaOrigen, int colOrigen,
                            int numFiles, int numCols, int filaDesti,
                            int colDesti) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",EXTREU_DIA"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Extreu el mes d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void extreureMes(int full, int filaOrigen, int colOrigen,
                            int numFiles, int numCols, int filaDesti,
                            int colDesti) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",EXTREU_MES"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Extreu l'any d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void extreureAny(int full, int filaOrigen, int colOrigen,
                            int numFiles, int numCols, int filaDesti,
                            int colDesti) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",EXTREU_ANY"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Extreu el dia de la setmana d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void extreureDiaSetmana(int full, int filaOrigen, int colOrigen,
                                   int numFiles, int numCols, int filaDesti,
                                   int colDesti) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",EXTREU_DIA_SETMANA"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Extreu l'horòscop d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void extreureHoroscop(int full, int filaOrigen, int colOrigen,
                                 int numFiles, int numCols, int filaDesti,
                                 int colDesti) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",EXTREU_HOROSCOP"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Converteix les unitas d'un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     * @param conv conversió d'unitats a efectuar
     */
    public void convertirUnitats(int full, int filaOrigen, int colOrigen,
                                 int numFiles, int numCols, int filaDesti,
                                 int colDesti, String conv) {
        switch (conv) {
            case "km -> mi":
                conv = "KM_MILLA";
                break;
            case "mi -> km":
                conv = "MILLA_KM";
                break;
            case "km -> nmi":
                conv = "KM_MILLAN";
                break;
            case "nmi -> km":
                conv = "MILLAN_KM";
                break;
            case "m -> yd":
                conv = "M_IARDA";
                break;
            case "yd -> m":
                conv = "IARDA_M";
                break;
            case "km2 -> hac":
                conv = "KM2_HECATAREA";
                break;
            case "hac -> km2":
                conv = "HECTAREA_KM2";
                break;
            case "km2 -> acre":
                conv = "KM2_ACRE";
                break;
            case "acre -> km2":
                conv = "ACRE_KM2";
                break;
            case "km2 -> sqmi":
                conv = "KM2_MILLA2";
                break;
            case "sqmi -> km2":
                conv = "MILLA2_KM2";
                break;
            case "l -> gal":
                conv = "LITRE_GALO";
                break;
            case "gal -> l":
                conv = "GALO_LITRE";
                break;
            case "g -> oz":
                conv = "G_UNCA";
                break;
            case "oz -> g":
                conv = "UNCA_G";
                break;
            case "kg -> lb":
                conv = "KG_LLIURA";
                break;
            case "lb -> kg":
                conv = "LLIURA_KG";
                break;
            case "kg -> t":
                conv = "KG_TONA";
                break;
            case "t -> kg":
                conv = "TONA_KG";
                break;
            case "grad -> rad":
                conv = "GRAUS_RAD";
                break;
            case "rad -> grad":
                conv = "RAD_GRAUS";
                break;
            case "ºF -> ºC":
                conv = "FAHRENHEIT_CELSIUS";
                break;
            case "ºC -> ºF":
                conv = "CELSIU_FAHRENHEIT";
                break;
            case "ºC -> ºK":
                conv = "CELSIUS_KELVIN";
                break;
            case "ºK -> ºC":
                conv = "KELVIN_CELSIUS";
                break;
            default:
                throw new UnsupportedOperationException("Conversió " + conv + " desconeguda");
        }

        String[] message =
                {"CONVERSIO_UNITATS," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + "," + conv};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Transposa un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void transposaBloc(int full, int filaOrigen, int colOrigen,
                          int numFiles, int numCols, int filaDesti,
                          int colDesti) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",TRANSPOSA"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Ordena un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     * @param criteri criteri d'ordenació
     * @param colOrd columna respecte la qual ordenar
     */
    public void ordenaBloc(int full, int filaOrigen, int colOrigen,
                       int numFiles, int numCols, int filaDesti,
                       int colDesti, String criteri, int colOrd) {
        String[] message =
                {"ORDENA," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + "," +
                criteri + "," + colOrd};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Mou un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void mouBloc(int full, int filaOrigen, int colOrigen,
                           int numFiles, int numCols, int filaDesti,
                           int colDesti) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",MOU_BLOC"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Copia un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     * @param filaDesti fila de l'inici del bloc de desti sobre el que
     *                  aplicar l'operació
     * @param colDesti columna de l'inici del bloc de desti sobre el que
     *                 aplicar l'operació
     */
    public void copiaBloc(int full, int filaOrigen, int colOrigen,
                          int numFiles, int numCols, int filaDesti,
                          int colDesti) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",COPIA_BLOC"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Buida un bloc de cel·les d'un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param filaOrigen fila de l'inici del bloc d'origen sobre el que
     *                   aplicar l'operació
     * @param colOrigen columna de l'inici del bloc d'origen sobre el que
     *                  aplicar l'operació
     * @param numFiles nombre de files del bloc
     * @param numCols nombre de columnes del bloc
     */
    public void buidaBloc(int full, int filaOrigen, int colOrigen,
                          int numFiles, int numCols) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        "," + ",BUIDA_BLOC"};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Afegeix una fila a un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     */
    public void afegeixFila(int full) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + "," +
                        "," + "," + "," + "," + ",AFEGEIX_FILA"};
        try {
            controladorDomini.executaOperacio(message);
            window.afegeixFila(full);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Afegeix una columna a un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     */
    public void afegeixColumna(int full) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + "," +
                        "," + "," + "," + "," + ",AFEGEIX_COLUMNA"};
        try {
            controladorDomini.executaOperacio(message);
            window.afegeixColumna(full);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Elimina una fila a un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param n índex de la fila a eliminar
     */
    public void eliminaFila(int full, int n) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + "," +
                        "," + "," + "," + "," + ",ELIMINA_FILA" + "," + n};
        try {
            controladorDomini.executaOperacio(message);
            window.eliminaFila(full);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }

    /**
     * Elimina una columna a un full
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     * @param n índex de la columna a eliminar
     */
    public void eliminaColumna(int full, int n) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + "," +
                        "," + "," + "," + "," + ",ELIMINA_COLUMNA" + "," + n};
        try {
            controladorDomini.executaOperacio(message);
            window.eliminaColumna(full);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        setEntradesFull(full);
    }
    /**
     * Actualitza les entrades d'un full a la vista principal
     * @param full full on es troba el bloc sobre el que aplicar l'operació
     */
    private void setEntradesFull(int full) {
        SeleccioTaula s = new SeleccioTaula(0, 0, controladorDomini.getNumFiles(full), controladorDomini.getNumCols(full));
        window.buidaSeleccio(full, s);
        window.setEntradesFull(full, controladorDomini.getEntrades(full, s.fila, s.col, s.nfiles, s.ncols));
    }
}
