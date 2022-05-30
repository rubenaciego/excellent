package domini;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;
import java.lang.Math;

import net.sf.json.*;

/**
 * Classe operador singleton, realitza diverses operacions sobre matrius de cel·les i retorna un resultat
 */
public class Operador {
    private static Operador operador = null;

    private Operador() {
    }

    /**
     * S'obté la instància de l'Operador, típica del patró Singleton
     * @return la instància de l'Operador
     */
    public static Operador getInstance() {
        if (operador == null)
            operador = new Operador();

        return operador;
    }

    /**
     * Substitueix cada CelaData de bloc per una CelaText amb l'horòscop corresponent a la CelaData original
     * @param bloc bloc original on s'aplica l'operació
     * @return bloc resultant després d'aplicar l'operació
     */
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
                result.setCela(e.getCela().copy(), e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * Substitueix cada CelaData de bloc per una CelaNum amb l'any corresponent a la CelaData original
     * @param bloc bloc original on s'aplica l'operació
     * @return bloc resultant després d'aplicar l'operació
     */
    public MatriuCeles extreuAny(MatriuCeles bloc) {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());

        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getData() != null) {
                double any = e.getCela().getData().getYear();
                CelaNum novaCela = new CelaNum("extreuAny(" + e.getCela().getInputUsuari() + ")", any);
                result.setCela(novaCela, e.getFila(), e.getColumna());
            } else {
                result.setCela(e.getCela().copy(), e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * Substitueix cada CelaData de bloc per una CelaNum amb el mes corresponent a la CelaData original
     * @param bloc bloc original on s'aplica l'operació
     * @return bloc resultant després d'aplicar l'operació
     */
    public MatriuCeles extreuMes(MatriuCeles bloc) {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());

        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getData() != null) {
                double mes = e.getCela().getData().getMonthValue();
                CelaNum novaCela = new CelaNum("extreuMes(" + e.getCela().getInputUsuari() + ")", mes);
                result.setCela(novaCela, e.getFila(), e.getColumna());
            } else {
                result.setCela(e.getCela().copy(), e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * Substitueix cada CelaData de bloc per una CelaNum amb el dia corresponent a la CelaData original
     * @param bloc bloc original on s'aplica l'operació
     * @return bloc resultant després d'aplicar l'operació
     */
    public MatriuCeles extreuDia(MatriuCeles bloc) {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());

        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getData() != null) {
                double dia = e.getCela().getData().getDayOfMonth();
                CelaNum novaCela = new CelaNum("extreuDia(" + e.getCela().getInputUsuari() + ")", dia);
                result.setCela(novaCela, e.getFila(), e.getColumna());
            } else {
                result.setCela(e.getCela().copy(), e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * Substitueix cada CelaData de bloc per una CelaText amb el dia de la setmana corresponent a la CelaData original
     * @param bloc bloc original on s'aplica l'operació
     * @return bloc resultant després d'aplicar l'operació
     */
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
                result.setCela(e.getCela().copy(), e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * Substitueix cada CelaNum de bloc per una CelaNum amb el valor resultant d’executar l’operació aritmètica unària op
     * @param bloc bloc original on s'aplica l'operació
     * @param op operació aritmètica unària que s'executa
     * @return bloc resultant després d'aplicar l'operació
     */
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
                result.setCela(e.getCela().copy(), e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * Efectua la funció estadística op a cada CelaNum de la MatriuCeles bloc d’entrada.
     * @param bloc bloc on executar l'operació estadística
     * @param op operació estadística a executar
     * @return bloc resultant després d'aplicar l'operació
     */
    public MatriuCeles executaOperacioEstadistica(MatriuCeles bloc, OperacioEstadistica op) {
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

            if (entrades.size() != 0) {
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
        }

        // mirar com posar el bloc
        CelaNum c = new CelaNum("operacioEstadistica(" + op + ")", res);
        MatriuCeles result = new MatriuCeles(1, 1);
        result.setCela(c, 0, 0);

        return result;
    }

    /**
     * Substitueix cada CelaNum de bloc per una CelaNum amb el valor resultant de truncar el valor de la CelaNum original
     * @param bloc bloc original on s'aplica l'operació
     * @param n número de decimals a truncar
     * @return bloc resultant després d'aplicar l'operació
     */
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
        return result;
    }

    /**
     * Substitueix cada CelaNum de bloc per una CelaNum amb el valor resultant d'executar la conversió d'unitats conv sobre el valor de la CelaNum original
     * @param bloc bloc original on s'aplica l'operació
     * @param conv conversió d'unitats a executar
     * @return bloc resultant després d'aplicar l'operació
     */
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
                    case KG_TONA:
                        val = num/1000;
                        break;
                    case TONA_KG:
                        val = 1000*num;
                        break;
                    case G_UNCA:
                        val = 0.035274*num;
                        break;
                    case UNCA_G:
                        val = 28.35*num;
                        break;
                    case KM2_MILLA2:
                        val = 0.386*num;
                        break;
                    case MILLA2_KM2:
                        val = 2.5899*num;
                        break;
                    case M_IARDA:
                        val = 1.0936*num;
                        break;
                    case IARDA_M:
                        val = 0.9144*num;
                        break;
                    case KM_MILLAN:
                        val = 0.539957*num;
                        break;
                    case MILLAN_KM:
                        val = 1.852*num;
                        break;
                    case KM2_ACRE:
                        val = 247.105*num;
                        break;
                    case ACRE_KM2:
                        val = 0.00405*num;
                        break;
                    default:
                        throw new IncompatibleClassChangeError("Conversió d'unitats " + conv + " desconeguda");
                }

                CelaNum novaCela = new CelaNum("convertexUnitats(" + e.getCela().getInputUsuari() + ", " +
                        conv + ")", val);
                result.setCela(novaCela, e.getFila(), e.getColumna());
            } else {
                result.setCela(e.getCela().copy(), e.getFila(), e.getColumna());
            }
        }
        return result;
    }

    /**
     * Substitueix cada CelaText de bloc per una CelaNum amb la longitud del text de la CelaText original
     * @param bloc bloc original on s'aplica l'operació
     * @return Bloc resultat d'aplicar l'operació
     */
    public MatriuCeles extreuLongitudText(MatriuCeles bloc) {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            String text = e.getCela().getText();

            if (text == null) {
                // Deixem la cel·la original
                result.setCela(e.getCela().copy(), e.getFila(), e.getColumna());
            } else {
                CelaNum c = new CelaNum("extreuLongitudText(" + e.getCela().getInputUsuari() + ")",
                        (double) text.length());
                result.setCela(c, e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * Cerca el número d’ocurrències total i, per cada CelaText, el número d’ocurrències i els índexs on es troba aCercar.
     * @param bloc bloc on cercar ocurrències
     * @param aCercar text a cercar
     * @return Una matriu de cel·les amb una sola cel·la textual que conté el resultat de la cerca en format JSON
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

                    if (index == text.length())
                        break;
                }

                if (count != 0) {
                    JSONObject cela = new JSONObject();
                    cela.put("ocurrencies", count);
                    cela.put("indexs", indices);

                    obj.put(e.getFila() + ":" + e.getColumna(), cela);
                    total += count;
                }
            }
        }

        obj.put("ocurrencies", total);
        CelaText c = new CelaText("cercaOcurrencies(" + aCercar + ")", obj.toString());
        MatriuCeles mc = new MatriuCeles(1, 1);
        mc.setCela(c, 0, 0);

        return mc;
    }

    /**
     * Substitueix cada CelaText de bloc per una CelaText amb el text en majúscules de la CelaText original
     * @param bloc bloc que convertir a majúscules
     * @return Bloc resultat d'aplicar l'operació
     */
    public MatriuCeles converteixMajuscules(MatriuCeles bloc) {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            String text = e.getCela().getText();

            if (text == null) {
                // Deixem la cel·la original
                result.setCela(e.getCela().copy(), e.getFila(), e.getColumna());
            } else {
                CelaText c = new CelaText("converteixMajuscules(" + e.getCela().getInputUsuari() + ")",
                        text.toUpperCase());
                result.setCela(c, e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * Substitueix cada CelaText de bloc per una CelaText amb el text en minúscules de la CelaText original
     * @param bloc bloc que convertir a minúscules
     * @return Bloc resultat d'aplicar l'operació
     */
    public MatriuCeles converteixMinuscules(MatriuCeles bloc) {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            String text = e.getCela().getText();

            if (text == null) {
                // Deixem la cel·la original
                result.setCela(e.getCela().copy(), e.getFila(), e.getColumna());
            } else {
                CelaText c = new CelaText("converteixMinuscules(" + e.getCela().getInputUsuari() + ")",
                        text.toLowerCase());
                result.setCela(c, e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * Transposa la MatriuCeles bloc
     * @param bloc bloc a transposar
     * @return Mateix bloc transposat i de dimensions
     */
    public MatriuCeles transposa(MatriuCeles bloc) {
        MatriuCeles result = new MatriuCeles(bloc.getNumCols(), bloc.getNumFiles());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades)
            result.setCela(e.getCela().copy(), e.getColumna(), e.getFila());

        return result;
    }

    /**
     * Substitueix cada CelaText de bloc per una CelaText amb totes les ocurrències de aCercar reemplaçades per aSubstituir
     * @param bloc bloc on realitzar la substitució
     * @param aCercar text a reemplaçar
     * @param aSubstituir text reemplaçat
     * @return Bloc resultat d'aplicar l'operació
     */
    public MatriuCeles reemplaca(MatriuCeles bloc, String aCercar, String aSubstituir) {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            String text = e.getCela().getText();

            if (text == null) {
                // Deixem la cel·la original
                result.setCela(e.getCela().copy(), e.getFila(), e.getColumna());
            } else {
                CelaText c = new CelaText("reemplaça(" + e.getCela().getInputUsuari() + ", " +
                        aCercar + ", " + aSubstituir + ")", text.replaceAll(aCercar, aSubstituir));
                result.setCela(c, e.getFila(), e.getColumna());
            }
        }

        return result;
    }

    /**
     * Ordena les files de bloc respecte l'ordre marcat per la columna col i el criteri d'ordenació criteri
     * @param bloc    Bloc on realitzar l'ordenació
     * @param col     Columna del bloc respecta la que ordenar
     * @param criteri Criteri respecte al qual es realitza l'ordenació
     * @return Bloc resultat d'aplicar l'operació
     */
    public MatriuCeles ordena(MatriuCeles bloc, int col, CriteriOrdenacio criteri) {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> columna = bloc.getEntradesColumna(col);
        ArrayList<Integer> nouOrdre = new ArrayList<Integer>(Collections.nCopies(bloc.getNumFiles(), -1));
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

        for (int i = 0; i < columna.size(); ++i) {
            EntradaMatriuCeles e = columna.get(i);
            int fila = e.getFila();
            nouOrdre.set(e.getFila(), i);
            vist.set(fila, true);
        }

        // Afegim les files que no apareixen en la columna respecte la qual ordenem
        int curr = columna.size();
        for (int i = 0; i < vist.size(); ++i) {
            if (!vist.get(i))
                nouOrdre.set(i, curr++);
        }

        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades)
            result.setCela(e.getCela().copy(), nouOrdre.get(e.getFila()), e.getColumna());

        return result;
    }

    /**
     * Extreu el signe de l'horòscop
     * @param dia dia de la data en qüestió
     * @param mes mes de la data en qüestió
     * @return El signe del zodíac corresponent a la data
     */
    private String horoscop(int dia, int mes) {
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
     * Calcula la mitjana de dades
     * @param dades Vector sobre el que calcular la mitjana
     * @return la mitjana dels valors
     */
    private double mitjana(ArrayList<Double> dades) {
        if (dades.size() == 0) return 0.0;

        double res = 0.0;

        for (double d : dades)
            res += d;

        res /= (double) dades.size();
        return res;
    }

    /**
     * Calcula la medina de dades
     * @param dades Vector sobre el que calcular la mediana
     * @return la mediana dels valors
     */
    private double mediana(ArrayList<Double> dades) {
        if (dades.size() == 0) return 0.0;
        Collections.sort(dades);
        return dades.get(dades.size() / 2);
    }

    /**
     * Calcula la variància
     * @param dades Vector sobre el que calcular la variància
     * @return la variància dels valors
     */
    private double variancia(ArrayList<Double> dades) {
        if (dades.size() == 0) return 0.0;

        double mitj = mitjana(dades);
        double res = 0.0;

        for (double d : dades)
            res += (d - mitj) * (d - mitj);

        res /= (double) dades.size();
        return res;
    }

    /**
     * Calcula la desviació estàndard
     * @param dades Vector sobre el que calcular la variància
     * @return la desviació estàndard dels valors
     */
    private double desviacioEstandard(ArrayList<Double> dades) {
        return Math.sqrt(variancia(dades));
    }

    /**
     * Calcula la covariància
     * @param x Primer conjunt de dades de mida n
     * @param y Segon conjunt de dades de mida n
     * @return Covariància entre les dades de x i y
     */
    private double covariancia(ArrayList<Double> x, ArrayList<Double> y) {
        if (x.size() != y.size())
            throw new ExcepcioOperador("Error en l'operador: quantitat de dades diferents al calcular la covariancia");
        else if (x.size() == 0) return 0.0;

        double mx = mitjana(x);
        double my = mitjana(y);
        double res = 0.0;

        for (int i = 0; i < x.size(); ++i)
            res += (x.get(i) - mx) * (y.get(i) - my);

        res /= (double) x.size();
        return res;
    }

    /**
     * Calcula el coeficient de correlació de Pearson
     * @param x Primer conjunt de dades de mida n
     * @param y Segon conjunt de dades de mida n
     * @return Coeficient de Pearson entre les dades de x i y
     */
    private double coeficientPearson(ArrayList<Double> x, ArrayList<Double> y) {
        double div = Math.sqrt(variancia(x) * variancia(y));
        if (div == 0.0) return 0.0;
        return covariancia(x, y) / div;
    }
}
