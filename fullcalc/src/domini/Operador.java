package domini;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;
import java.lang.Math;

import net.sf.json.*;

public class Operador
{
    private static Operador operador = null;

    private Operador()
    {
    }

    public static Operador getInstance()
    {
        if (operador == null)
            operador = new Operador();

        return operador;
    }

    public MatriuCeles extreuHoroscop(MatriuCeles bloc)
    {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getData() != null) {
                String signe = horoscop(e.getCela().getData().getDayOfMonth(),
                        e.getCela().getData().getMonthValue());
                CelaText novaCela = new CelaText("extreuHoroscop(" + e.getCela().getInputUsuari() + ")",
                        signe);
                bloc.setCela(novaCela, e.getFila(), e.getCol());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuAny(MatriuCeles bloc)
    {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getData() != null) {
                double any = e.getCela().getData().getYear();
                CelaNum novaCela = new CelaNum("extreuAny(" + e.getCela().getInputUsuari() + ")", any);
                bloc.setCela(novaCela, e.getFila(), e.getCol());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuMes(MatriuCeles bloc)
    {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getData() != null) {
                double mes = e.getCela().getData().getMonthValue();
                CelaNum novaCela = new CelaNum("extreuMes(" + e.getCela().getInputUsuari() + ")", mes);
                bloc.setCela(novaCela, e.getFila(), e.getCol());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuDia(MatriuCeles bloc)
    {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getData() != null) {
                double dia = e.getCela().getData().getDayOfMonth();
                CelaNum novaCela = new CelaNum("extreuDia(" + e.getCela().getInputUsuari() + ")", dia);
                bloc.setCela(novaCela, e.getFila(), e.getCol());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuDiaSetmana(MatriuCeles bloc)
    {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        final Locale locale = new Locale("ca", "ES");

        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getData() != null) {
                DayOfWeek diaSet = e.getCela().getData().getDayOfWeek();
                CelaText novaCela = new CelaText("extreuDiaSetmana(" + e.getCela().getInputUsuari() + ")",
                        diaSet.getDisplayName(TextStyle.FULL_STANDALONE, locale));
                bloc.setCela(novaCela, e.getFila(), e.getCol());
            }
        }
        return bloc;
    }

    public MatriuCeles executaOperacioAritmeticaUnaria(MatriuCeles bloc, OperacioAritmetica op)
    {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

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
                        // error
                        break;
                }

                CelaNum novaCela = new CelaNum("executaOperacioAritmeticaUnaria(" +
                        e.getCela().getInputUsuari() + ", " + op.toString() + ")", val);
                bloc.setCela(novaCela, e.getFila(), e.getCol());
            }
        }

        return bloc;
    }

    public MatriuCeles executaFuncioEstadistica(MatriuCeles bloc, OperacioEstadistica op)
    {

        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        ArrayList<Double> dades = new ArrayList<>(entrades.size());

        for (EntradaMatriuCeles e : entrades) {
            Double d = e.getCela().getNum();
            if (d != null) dades.add(d);
        }

        double res = 0.0;

        switch (op) {
            case MITJANA:
                break;
            case MEDIANA:
                break;
            case DESVIACIO_ESTANDARD:
                break;
            case VARIANCIA:
                break;
            case COVARIANCIA:
                break;
            case COEFICIENT_PEARSON:
                break;
            default:
                // error
                break;
        }

        // mirar com posar el bloc
        CelaNum c = new CelaNum("operacioEstadistica(" + op + ")", res);
        MatriuCeles result = new MatriuCeles(1, 1);
        result.setCela(c, 0, 0);

        return result;
    }

    public MatriuCeles truncaNumero(MatriuCeles bloc, int n)
    {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();
        for (EntradaMatriuCeles e : entrades) {
            if (e.getCela().getNum() != null) {
                double tmp = e.getCela().getNum() * Math.pow(10.0, n);
                double truncat = (double) (int) tmp / Math.pow(10.0, n);
                CelaNum novaCela = new CelaNum("truncarNUmero(" + e.getCela().getInputUsuari() + ", " +
                        n + ")", truncat);
                bloc.setCela(novaCela, e.getFila(), e.getCol());
            }
        }
        return bloc;
    }

    public MatriuCeles converteixUnitats(MatriuCeles bloc, ConversioUnitats conv)
    {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

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
                        // error
                        break;
                }

                CelaNum novaCela = new CelaNum("convertexUnitats(" + e.getCela().getInputUsuari() + ", " +
                        conv + ")", val);
                bloc.setCela(novaCela, e.getFila(), e.getCol());
            }
        }

        return bloc;
    }

    /**
     * @param bloc Bloc de cel·les on aplicar l'operació
     * @return Bloc resultat d'aplicar l'operació
     * @brief Extreu la longitud de les cel·les textuals
     */
    public MatriuCeles extreuLongitudText(MatriuCeles bloc)
    {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            String text = e.getCela().getText();

            if (text == null) {
                // Deixem la cel·la original
                result.setCela(e.getCela(), e.getFila(), e.getCol());
            } else {
                CelaNum c = new CelaNum("extreuLongitudText(" + e.getCela().getInputUsuari() + ")",
                        (double) text.length());
                result.setCela(c, e.getFila(), e.getCol());
            }
        }

        return result;
    }

    public MatriuCeles cercaOcurrencies(MatriuCeles bloc, String aCercar)
    {
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

                obj.put(e.getFila() + ":" + e.getCol(), cela);
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

    public MatriuCeles converteixMajuscules(MatriuCeles bloc)
    {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            String text = e.getCela().getText();

            if (text == null) {
                // Deixem la cel·la original
                result.setCela(e.getCela(), e.getFila(), e.getCol());
            } else {
                CelaText c = new CelaText("converteixMajuscules(" + e.getCela().getInputUsuari() + ")",
                        text.toUpperCase());
                result.setCela(c, e.getFila(), e.getCol());
            }
        }

        return result;
    }

    public MatriuCeles converteixMinuscules(MatriuCeles bloc)
    {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            String text = e.getCela().getText();

            if (text == null) {
                // Deixem la cel·la original
                result.setCela(e.getCela(), e.getFila(), e.getCol());
            } else {
                CelaText c = new CelaText("converteixMinuscules(" + e.getCela().getInputUsuari() + ")",
                        text.toLowerCase());
                result.setCela(c, e.getFila(), e.getCol());
            }
        }

        return result;
    }

    public MatriuCeles transposa(MatriuCeles bloc)
    {
        MatriuCeles result = new MatriuCeles(bloc.getNumCols(), bloc.getNumFiles());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades)
            result.setCela(e.getCela(), e.getCol(), e.getFila());

        return result;
    }

    public MatriuCeles reemplaca(MatriuCeles bloc, String aCercar, String aSubstituir)
    {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            String text = e.getCela().getText();

            if (text == null) {
                // Deixem la cel·la original
                result.setCela(e.getCela(), e.getFila(), e.getCol());
            } else {
                CelaText c = new CelaText("reemplaça(" + e.getCela().getInputUsuari() + ", " +
                        aCercar + ", " + aSubstituir + ")", text.replaceAll(aCercar, aSubstituir));
                result.setCela(c, e.getFila(), e.getCol());
            }
        }

        return result;
    }

    public MatriuCeles ordena(MatriuCeles bloc, int col, CriteriOrdenacio criteri)
    {
        MatriuCeles result = new MatriuCeles(bloc.getNumFiles(), bloc.getNumCols());
        ArrayList<EntradaMatriuCeles> columna = bloc.getEntradesColumna(col);
        ArrayList<Integer> nouOrdre = new ArrayList<Integer>(bloc.getNumFiles());
        ArrayList<Boolean> vist = new ArrayList<Boolean>(Collections.nCopies(bloc.getNumFiles(), false));

        if (criteri == CriteriOrdenacio.ASCENDENT) {
            columna.sort(new Comparator<EntradaMatriuCeles>()
            {
                @Override
                public int compare(EntradaMatriuCeles a, EntradaMatriuCeles b)
                {
                    return a.getCela().compare(b.getCela());
                }
            });
        } else if (criteri == CriteriOrdenacio.DESCENDENT) {
            columna.sort(new Comparator<EntradaMatriuCeles>()
            {
                @Override
                public int compare(EntradaMatriuCeles a, EntradaMatriuCeles b)
                {
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
            result.setCela(e.getCela(), nouOrdre.get(e.getFila()), e.getCol());

        return result;
    }

    private String horoscop(Integer dia, Integer mes)
    {
        String astro_sign = "";

        if (mes == 12) {
            if (dia < 22)
                astro_sign = "Sagitari";
            else
                astro_sign = "Capricorn";
        } else if (mes == 1) {
            if (dia < 20)
                astro_sign = "Capricorn";
            else
                astro_sign = "Aquari";
        } else if (mes == 2) {
            if (dia < 19)
                astro_sign = "Aquari";
            else
                astro_sign = "Piscis";
        } else if (mes == 3) {
            if (dia < 21)
                astro_sign = "Piscis";
            else
                astro_sign = "Aries";
        } else if (mes == 4) {
            if (dia < 20)
                astro_sign = "Aries";
            else
                astro_sign = "Tauro";
        } else if (mes == 5) {
            if (dia < 21)
                astro_sign = "Tauro";
            else
                astro_sign = "Geminis";
        } else if (mes == 6) {
            if (dia < 21)
                astro_sign = "Geminis";
            else
                astro_sign = "Cancer";
        } else if (mes == 7) {
            if (dia < 23)
                astro_sign = "Cancer";
            else
                astro_sign = "Leo";
        } else if (mes == 8) {
            if (dia < 23)
                astro_sign = "Leo";
            else
                astro_sign = "Virgo";
        } else if (mes == 9) {
            if (dia < 23)
                astro_sign = "Virgo";
            else
                astro_sign = "Libra";
        } else if (mes == 10) {
            if (dia < 23)
                astro_sign = "Libra";
            else
                astro_sign = "Escorpio";
        } else if (mes == 11) {
            if (dia < 22)
                astro_sign = "Escorpio";
            else
                astro_sign = "Sagitari";
        }
        return astro_sign;
    }
}
