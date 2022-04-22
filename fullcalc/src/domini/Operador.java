package domini;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;
import java.lang.Math;

import net.sf.json.*;

/**
 * @class Operador
 * @brief Classe operador singleton, realitza diverses operacions sobre matrius de cel·les i
 * retorna un resultat
 */
public class Operador {
    private static Operador operador = null;

    private Operador() {
    }

    public static Operador getInstance() {
        if (operador == null)
            operador = new Operador();

        return operador;
    }

    public MatriuCeles extreuHoroscop(MatriuCeles bloc) {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());

        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getData() != null) {
                String signe = horoscop(e.getCela().getData().getDayOfMonth(),
                        e.getCela().getData().getMonthValue());
                CelaText novaCela = new CelaText("extreuHoroscop(" + e.getCela().getInputUsuari() + ")",
                        signe);
                result.setCela(novaCela, e.getFila(), e.getColumna());
            } else {
                result.setCela(e.getCela(), e.getFila(), e.getColumna());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuAny(MatriuCeles bloc) {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());

        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getData() != null) {
                double any = e.getCela().getData().getYear();
                CelaNum novaCela = new CelaNum("extreuAny(" + e.getCela().getInputUsuari() + ")", any);
                result.setCela(novaCela, e.getFila(), e.getColumna());
            } else {
                result.setCela(e.getCela(), e.getFila(), e.getColumna());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuMes(MatriuCeles bloc) {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());

        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getData() != null) {
                double mes = e.getCela().getData().getMonthValue();
                CelaNum novaCela = new CelaNum("extreuMes(" + e.getCela().getInputUsuari() + ")", mes);
                result.setCela(novaCela, e.getFila(), e.getColumna());
            } else {
                result.setCela(e.getCela(), e.getFila(), e.getColumna());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuDia(MatriuCeles bloc) {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());

        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getData() != null) {
                double dia = e.getCela().getData().getDayOfMonth();
                CelaNum novaCela = new CelaNum("extreuDia(" + e.getCela().getInputUsuari() + ")", dia);
                result.setCela(novaCela, e.getFila(), e.getColumna());
            } else {
                result.setCela(e.getCela(), e.getFila(), e.getColumna());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuDiaSetmana(MatriuCeles bloc) {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());

        final Locale locale = new Locale("ca", "ES");

        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getData() != null) {
                DayOfWeek diaSet = e.getCela().getData().getDayOfWeek();
                CelaText novaCela = new CelaText("extreuDiaSetmana(" + e.getCela().getInputUsuari() + ")",
                        diaSet.getDisplayName(TextStyle.FULL_STANDALONE, locale));
                result.setCela(novaCela, e.getFila(), e.getColumna());
            } else {
                result.setCela(e.getCela(), e.getFila(), e.getColumna());
            }
        }
        return bloc;
    }

    public MatriuCeles executaOperacioAritmeticaUnaria(MatriuCeles bloc, OperacioAritmetica op) {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());

        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getNum() != null) {
                double val = 0.0;
                double num = e.getCela().getNum();

                switch (op) {
                    case VALOR_ABSOLUT:
                        val = Math.abs(num);
                        break;
                    case INCREMENTAR:
                        val = num + 1.0;
                        break;
                    case DECREMENTAR:
                        val = num - 1.0;
                        break;
                    case EXPONENCIAL:
                        val = Math.exp(num);
                        break;
                    case COSINUS:
                        val = Math.cos(num);
                        break;
                    case SINUS:
                        val = Math.sin(num);
                        break;
                    case COSINUS_HIPERBOLIC:
                        val = Math.cosh(num);
                        break;
                    case SINUS_HIPERBOLIC:
                        val = Math.sinh(num);
                        break;
                    case TANGENT_HIPERBOLIC:
                        val = Math.tanh(num);
                        break;
                    default:
                        throw new IncompatibleClassChangeError("Operació aritmètica unària" + op + " desconeguda");
                }

                CelaNum novaCela = new CelaNum("executaOperacioAritmeticaUnaria(" +
                        e.getCela().getInputUsuari() + ", " + op.toString() + ")", val);
                result.setCela(novaCela, e.getFila(), e.getColumna());
            } else {
                result.setCela(e.getCela(), e.getFila(), e.getColumna());
            }
        }

        return bloc;
    }

    /**
     * @param bloc Bloc on executar l'operació estadística
     * @param op   Operació estadística a executar
     * @return Matriu de cel·les que només conté una cel·la, el resultat de l'operació
     */
    public MatriuCeles executaFuncioEstadistica(MatriuCeles bloc, OperacioEstadistica op) {
        double res = 0.0;

        if (op.compareTo(OperacioEstadistica.COVARIANCIA) >= 0) {
            // podriem canviar-ho
            if (bloc.getNumCols() != 2) {
                throw new ExcepcioOperador("Error en l'operador: operació estadística de dues variables " + op +
                        " requereix exactament dues columnes");
            }

            ArrayList<EntradaMatriuCeles> entradesX = bloc.getEntradesColumna(0);
            ArrayList<EntradaMatriuCeles> entradesY = bloc.getEntradesColumna(1);

            ArrayList<Double> dadesX = new ArrayList<>(entradesX.size());
            ArrayList<Double> dadesY = new ArrayList<>(entradesY.size());

            for (EntradaMatriuCeles e : entradesX) {
                Double d = e.getCela().getNum();
                if (d != null) dadesX.add(d);
            }

            for (EntradaMatriuCeles e : entradesY) {
                Double d = e.getCela().getNum();
                if (d != null) dadesY.add(d);
            }

            switch (op) {
                case COVARIANCIA:
                    res = covariancia(dadesX, dadesY);
                    break;
                case COEFICIENT_PEARSON:
                    res = coeficientPearson(dadesX, dadesY);
                    break;
                default:
                    throw new IncompatibleClassChangeError("Operació estadística" + op + " desconeguda");
            }
        } else {
            ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
            ArrayList<Double> dades = new ArrayList<>(entrades.size());

            for (EntradaMatriuCeles e : entrades) {
                Double d = e.getCela().getNum();
                if (d != null) dades.add(d);
            }

            switch (op) {
                case MITJANA:
                    res = mitjana(dades);
                    break;
                case MEDIANA:
                    res = mediana(dades);
                    break;
                case VARIANCIA:
                    res = variancia(dades);
                    break;
                case DESVIACIO_ESTANDARD:
                    res = desviacioEstandard(dades);
                    break;
                default:
                    throw new IncompatibleClassChangeError("Operació estadística" + op + " desconeguda");
            }
        }

        // mirar com posar el bloc
        CelaNum c = new CelaNum("operacioEstadistica(" + op + ")", res);
        MatriuCeles result = new MatriuCeles(1, 1);
        result.setCela(c, 0, 0);

        return result;
    }

    public MatriuCeles truncaNumero(MatriuCeles bloc, int n) {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());

        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getNum() != null) {
                double tmp = e.getCela().getNum() * Math.pow(10.0, n);
                double truncat = (double) (int) tmp / Math.pow(10.0, n);
                CelaNum novaCela = new CelaNum("truncarNUmero(" + e.getCela().getInputUsuari() + ", " +
                        n + ")", truncat);
                result.setCela(novaCela, e.getFila(), e.getColumna());
            } else {
                result.setCela(e.getCela(), e.getFila(), e.getColumna());
            }
        }
        return bloc;
    }

    public MatriuCeles converteixUnitats(MatriuCeles bloc, ConversioUnitats conv) {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());

        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getNum() != null) {
                double val = 0.0;
                double num = e.getCela().getNum();

                switch (conv) {
                    case RAD_GRAUS:
                        val = num * 180.0 / Math.PI;
                        break;
                    case GRAUS_RAD:
                        val = num * Math.PI / 180.0;
                        break;
                    case KM_MILLA:
                    case KMH_MILLAH:
                        val = num * 0.621371;
                        break;
                    case MILLA_KM:
                    case MILLAH_KMH:
                        val = num / 0.621371;
                        break;
                    case KG_LLIURA:
                        val = num * 2.20462;
                        break;
                    case LLIURA_KG:
                        val = num / 2.20462;
                        break;
                    case LITRE_GALO:
                        val = num * 0.264172;
                        break;
                    case GALO_LITRE:
                        val = num / 0.264172;
                        break;
                    case CELSIUS_KELVIN:
                        val = num + 273.15;
                        break;
                    case KELVIN_CELSIUS:
                        val = num - 273.15;
                        break;
                    case KELVIN_FAHRENHEIT:
                        val = 1.8 * (num - 273.15) + 32;
                        break;
                    case FAHRENHEIT_KELVIN:
                        val = (num + 459.67) * 5.0 / 9.0;
                        break;
                    case FAHRENHEIT_CELSIUS:
                        val = (num + 459.67) * 5.0 / 9.0 - 273.15;
                        break;
                    case CELSIU_FAHRENHEIT:
                        val = 1.8 * num + 32;
                        break;
                    case KM2_HECATAREA:
                        val = num * 100.0;
                        break;
                    case HECTAREA_KM2:
                        val = num / 100.0;
                        break;
                    default:
                        throw new IncompatibleClassChangeError("Conversió d'unitats " + conv + " desconeguda");
                }

                CelaNum novaCela = new CelaNum("convertexUnitats(" + e.getCela().getInputUsuari() + ", " +
                        conv + ")", val);
                result.setCela(novaCela, e.getFila(), e.getColumna());
            } else {
                result.setCela(e.getCela(), e.getFila(), e.getColumna());
            }
        }

        return bloc;
    }

    /**
     * @param bloc Bloc de cel·les on aplicar l'operació
     * @return Bloc resultat d'aplicar l'operació
     * @brief Extreu la longitud de les cel·les textuals
     */
    public MatriuCeles extreuLongitudText(MatriuCeles bloc) {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            String text = e.getCela().getText();

            if (text == null) {
                // Deixem la cel·la original
                result.setCela(e.getCela(), e.getFila(), e.getColumna());
            } else {
                CelaNum c = new CelaNum("extreuLongitudText(" + e.getCela().getInputUsuari() + ")",
                        (double) text.length());
                result.setCela(c, e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * @param bloc    Bloc on cercar ocurrències
     * @param aCercar Text a cercar
     * @return Una matriu de cel·les amb una sola cel·la textual que conté el resultat de la cerca en format JSON,
     * on s'indica el nombre total d'ocurrències i les ocurrències dins de cada cel·la i les posicions on s'han trobat
     */
    public MatriuCeles cercaOcurrencies(MatriuCeles bloc, String aCercar) {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        JSONObject obj = new JSONObject();
        int total = 0;

        for (EntradaMatriuCeles e : entrades) {
            String text = e.getCela().getText();

            if (text != null) {
                ArrayList<Integer> indices = new ArrayList<Integer>();
                int count = 0;
                int index = text.indexOf(aCercar);

                while (index != -1) {
                    ++count;
                    indices.add(index);
                    index = text.indexOf(aCercar, index + 1);
                }

                JSONObject cela = new JSONObject();
                cela.put("occurrences", count);
                cela.put("indices", indices);

                obj.put(e.getFila() + ":" + e.getColumna(), cela);
                total += count;
            }
        }

        obj.put("occurrences", total);
        // problema que posar d'input usuari
        CelaText c = new CelaText("cercaOcurrencies(" + aCercar + ")", obj.toString());
        MatriuCeles mc = new MatriuCeles(1, 1);
        mc.setCela(c, 0, 0);

        return mc;
    }

    /**
     * @param bloc Bloc que convertir a majúscules
     * @return Matriu de cel·les resultat de la mateixa mida que el bloc on les cel·les textuals s'han convertit a
     * majúscules
     */
    public MatriuCeles converteixMajuscules(MatriuCeles bloc) {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            String text = e.getCela().getText();

            if (text == null) {
                // Deixem la cel·la original
                result.setCela(e.getCela(), e.getFila(), e.getColumna());
            } else {
                CelaText c = new CelaText("converteixMajuscules(" + e.getCela().getInputUsuari() + ")",
                        text.toUpperCase());
                result.setCela(c, e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * @param bloc Bloc que convertir a minúscules
     * @return Matriu de cel·les resultat de la mateixa mida que el bloc on les cel·les textuals s'han convertit a
     * minúscules
     */
    public MatriuCeles converteixMinuscules(MatriuCeles bloc) {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            String text = e.getCela().getText();

            if (text == null) {
                // Deixem la cel·la original
                result.setCela(e.getCela(), e.getFila(), e.getColumna());
            } else {
                CelaText c = new CelaText("converteixMinuscules(" + e.getCela().getInputUsuari() + ")",
                        text.toLowerCase());
                result.setCela(c, e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * @param bloc Bloc a transposar
     * @return Mateix bloc de mida transposada i transposat
     */
    public MatriuCeles transposa(MatriuCeles bloc) {
        MatriuCeles result = new MatriuCeles(bloc.getNumCols(), bloc.getNumFiles());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades)
            result.setCela(e.getCela(), e.getColumna(), e.getFila());

        return result;
    }

    /**
     * @param bloc        Bloc on realitzar la substitució
     * @param aCercar     Text a reemplaçar
     * @param aSubstituir Text reemplaçat
     * @return Matriu de cel·les de la mateixa mida on a les cel·les textuals s'ha reemplçat el text aCercar pel
     * text aSubstituir
     */
    public MatriuCeles reemplaca(MatriuCeles bloc, String aCercar, String aSubstituir) {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            String text = e.getCela().getText();

            if (text == null) {
                // Deixem la cel·la original
                result.setCela(e.getCela(), e.getFila(), e.getColumna());
            } else {
                CelaText c = new CelaText("reemplaça(" + e.getCela().getInputUsuari() + ", " +
                        aCercar + ", " + aSubstituir + ")", text.replaceAll(aCercar, aSubstituir));
                result.setCela(c, e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * @param bloc    Bloc on realitzar l'ordenació
     * @param col     Columna del bloc respecta la que ordenar
     * @param criteri Criteri respecte el qual es realitza l'ordenació
     * @return Retorna el mateix bloc però amb les files permutades respecte l'ordre marcat per la columna col
     * i el criteri d'ordenació
     */
    public MatriuCeles ordena(MatriuCeles bloc, int col, CriteriOrdenacio criteri) {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> columna = bloc.getEntradesColumna(col);
        ArrayList<Integer> nouOrdre = new ArrayList<Integer>(bloc.getNumFiles());
        ArrayList<Boolean> vist = new ArrayList<Boolean>(Collections.nCopies(bloc.getNumFiles(), false));

        if (criteri == CriteriOrdenacio.ASCENDENT) {
            columna.sort(new Comparator<EntradaMatriuCeles>() {
                @Override
                public int compare(EntradaMatriuCeles a, EntradaMatriuCeles b) {
                    return a.getCela().compare(b.getCela());
                }
            });
        } else if (criteri == CriteriOrdenacio.DESCENDENT) {
            columna.sort(new Comparator<EntradaMatriuCeles>() {
                @Override
                public int compare(EntradaMatriuCeles a, EntradaMatriuCeles b) {
                    return b.getCela().compare(a.getCela());
                }
            });
        }

        for (EntradaMatriuCeles e : columna) {
            int fila = e.getFila();
            nouOrdre.add(fila);
            vist.set(fila, true);
        }

        // Afegim les files que no apareixen en la columna respecte la qual ordenem
        for (int i = 0; i < vist.size(); ++i) {
            if (!vist.get(i))
                nouOrdre.add(i);
        }

        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades)
            result.setCela(e.getCela(), nouOrdre.get(e.getFila()), e.getColumna());

        return result;
    }

    private String horoscop(Integer dia, Integer mes) {
        String astroSign = "";

        if (mes == 12) {
            if (dia < 22)
                astroSign = "Sagitari";
            else
                astroSign = "Capricorn";
        } else if (mes == 1) {
            if (dia < 20)
                astroSign = "Capricorn";
            else
                astroSign = "Aquari";
        } else if (mes == 2) {
            if (dia < 19)
                astroSign = "Aquari";
            else
                astroSign = "Piscis";
        } else if (mes == 3) {
            if (dia < 21)
                astroSign = "Piscis";
            else
                astroSign = "Aries";
        } else if (mes == 4) {
            if (dia < 20)
                astroSign = "Aries";
            else
                astroSign = "Tauro";
        } else if (mes == 5) {
            if (dia < 21)
                astroSign = "Tauro";
            else
                astroSign = "Geminis";
        } else if (mes == 6) {
            if (dia < 21)
                astroSign = "Geminis";
            else
                astroSign = "Cancer";
        } else if (mes == 7) {
            if (dia < 23)
                astroSign = "Cancer";
            else
                astroSign = "Leo";
        } else if (mes == 8) {
            if (dia < 23)
                astroSign = "Leo";
            else
                astroSign = "Virgo";
        } else if (mes == 9) {
            if (dia < 23)
                astroSign = "Virgo";
            else
                astroSign = "Libra";
        } else if (mes == 10) {
            if (dia < 23)
                astroSign = "Libra";
            else
                astroSign = "Escorpio";
        } else if (mes == 11) {
            if (dia < 22)
                astroSign = "Escorpio";
            else
                astroSign = "Sagitari";
        }
        return astroSign;
    }

    /**
     * @param dades Vector sobre el que calcular la mitjana
     * @return Mitjana dels valors del vector
     */
    private double mitjana(ArrayList<Double> dades) {
        double res = 0.0;

        for (double d : dades)
            res += d;

        res /= (double) dades.size();
        return res;
    }

    /**
     * @param dades Vector sobre el que calcular la mediana
     * @return Mediana dels valors del vector
     */
    private double mediana(ArrayList<Double> dades) {
        Collections.sort(dades);
        return dades.get(dades.size() / 2);
    }

    /**
     * @param dades Vector sobre el que calcular la variància
     * @return Variància dels valors del vector
     */
    private double variancia(ArrayList<Double> dades) {
        double mitj = mitjana(dades);
        double res = 0.0;

        for (double d : dades)
            res += (d - mitj) * (d - mitj);

        res /= (double) dades.size();
        return res;
    }

    /**
     * @param x Primer conjunt de dades de mida n
     * @param y Segon conjunt de dades de mida n
     * @return Covariància entre les dades de x i y
     */
    private double covariancia(ArrayList<Double> x, ArrayList<Double> y) throws ExcepcioOperador {
        if (x.size() != y.size())
            throw new ExcepcioOperador("Error en l'operador: quantitat de dades diferents al calcular la covariancia");

        double mx = mitjana(x);
        double my = mitjana(y);
        double res = 0.0;

        for (int i = 0; i < x.size(); ++i)
            res += (x.get(i) - mx) * (y.get(i) - my);

        res /= (double) x.size();
        return res;
    }

    private double desviacioEstandard(ArrayList<Double> dades) {
        return Math.sqrt(variancia(dades));
    }

    private double coeficientPearson(ArrayList<Double> x, ArrayList<Double> y) {
        // Comprovar divisions entre 0?
        return covariancia(x, y) / Math.sqrt(variancia(x) * variancia(y));
    }
}
