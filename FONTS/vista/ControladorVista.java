package vista;

import domini.ControladorDomini;
import domini.ExcepcioDomini;

public class ControladorVista {
    private final ControladorDomini controladorDomini;
    private final MainWindow window;

    public ControladorVista(ControladorDomini controladorDomini) {
        this.controladorDomini = controladorDomini;
        window = new MainWindow(this);
    }

    public void afegeixFull()
    {
        String[] message = {"OPERACIO_DOCUMENT,AFEGEIX_FULL"};

        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        int i = controladorDomini.getNumFulls() - 1;
        window.afegeixFull(controladorDomini.getNumFiles(i), controladorDomini.getNumCols(i));
    }

    public void esborraFull(int index)
    {
        // construeix missatge a controlador domini
        String[] message = {"OPERACIO_DOCUMENT,AFEGEIX_FULL," + index};

        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        window.esborraFull(index);
    }

    public void creaDocument(String nom)
    {
        String[] message = {"OPERACIO_DOCUMENT,CREA_DOCUMENT", nom};

        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void carregaDocument(String nom) {
        String[] message = {"OPERACIO_DOCUMENT,CARREGA_DOCUMENT", nom};

        try {
            controladorDomini.executaOperacio(message);
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

    public void cerca(int full, int filaOrigen, int colOrigen,
                             int numFiles, int numCols, int filaDesti,
                             int colDesti, String stringCercada) {

        // CERCA NO FUNCIONA AIXI
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + "," +
                        filaDesti + "," + colDesti + ",CERCA_OCURRENCIES",
                        stringCercada};
        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

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

    private void setEntradesFull(int full) {
        SeleccioTaula s = new SeleccioTaula(0, 0, controladorDomini.getNumFiles(full), controladorDomini.getNumCols(full));
        window.buidaSeleccio(full, s);
        window.setEntradesFull(full, controladorDomini.getEntrades(full, s.fila, s.col, s.nfiles, s.ncols));
    }
}
