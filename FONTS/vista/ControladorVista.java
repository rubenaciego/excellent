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

    public void creaDocument(String nom) {
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
                      int numFiles, int numCols, String stringCercada) {
        String[] message =
                {"OPERACIO_FULL," + full + "," + filaOrigen + "," +
                        colOrigen + "," + numFiles + "," + numCols + ",-1,-1,CERCA_OCURRENCIES",
                        stringCercada};
        try {
            controladorDomini.executaOperacio(message);
            String res = controladorDomini.getCelaResultat(full);

            System.out.println(res);
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
    private void setEntradesFull(int full) {
        SeleccioTaula s = new SeleccioTaula(0, 0, controladorDomini.getNumFiles(full), controladorDomini.getNumCols(full));
        window.buidaSeleccio(full, s);
        window.setEntradesFull(full, controladorDomini.getEntrades(full, s.fila, s.col, s.nfiles, s.ncols));
    }
}
