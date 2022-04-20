package domini;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Locale;

import net.sf.json.*;

public class Operador {

    public MatriuCeles extreuHoroscop(MatriuCeles bloc) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteData() != null) {
                String signe = horoscop(e.obteCela().obteData().getDayOfMonth(),
                                e.obteCela().obteData().getMonthValue());
                CelaText novaCela = new CelaText("extreuHoroscop(" + e.obteCela().getInputUsuari() + ")",
                        signe);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuAny(MatriuCeles bloc) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteData() != null) {
                double any = e.obteCela().obteData().getYear();
                CelaNum novaCela = new CelaNum("extreuAny(" + e.obteCela().getInputUsuari() + ")", any);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuMes(MatriuCeles bloc) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteData() != null) {
                double mes = e.obteCela().obteData().getMonthValue();
                CelaNum novaCela = new CelaNum("extreuMes(" + e.obteCela().getInputUsuari() + ")", mes);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuDia(MatriuCeles bloc) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteData() != null) {
                double dia = e.obteCela().obteData().getDayOfMonth();
                CelaNum novaCela = new CelaNum("extreuDia(" + e.obteCela().getInputUsuari() + ")", dia);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuDiaSetmana(MatriuCeles bloc) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        final Locale locale = new Locale("ca", "ES");

        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteData() != null) {
                DayOfWeek diaSet = e.obteCela().obteData().getDayOfWeek();
                CelaText novaCela = new CelaText("extreuDiaSetmana(" + e.obteCela().getInputUsuari() + ")",
                        diaSet.getDisplayName(TextStyle.FULL_STANDALONE, locale));
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }
        return bloc;
    }

    public MatriuCeles executaOperacioAritmeticaUnaria(MatriuCeles bloc, OpAritmetica op) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();

        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteNum() != null) {
                double val = 0.0;
                double num = e.obteCela().obteNum();

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
                        e.obteCela().getInputUsuari() + ", " + op.toString() + ")", val);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }

        return bloc;
    }

    public MatriuCeles executaFuncioEstadistica(MatriuCeles bloc, OpEstadistica op) {
        return bloc;
    }

    public MatriuCeles truncaNumero(MatriuCeles bloc, Integer n) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteNum() != null) {
                double tmp = e.obteCela().obteNum() * Math.pow(10.0, n);
                double truncat = (double)(int) tmp / Math.pow(10.0, n);
                CelaNum novaCela = new CelaNum("truncarNUmero(" + e.obteCela().getInputUsuari() + ", " +
                        n + ")", truncat);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }
        return bloc;
    }

    public MatriuCeles converteixUnitats(MatriuCeles bloc, ConversioUnitats conv)
    {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();

        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteNum() != null) {
                double val = 0.0;
                double num = e.obteCela().obteNum();

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

                CelaNum novaCela = new CelaNum("convertexUnitats(" + e.obteCela().getInputUsuari() + ", " +
                        conv + ")", val);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }

        return bloc;
    }

    /**
     * @brief Extreu la longitud de les cel·les textuals
     * @param bloc Bloc de cel·les on aplicar l'operació
     * @return Bloc resultat d'aplicar l'operació
     */
    public MatriuCeles extreuLongitudText(MatriuCeles bloc)
    {
        MatriuCeles result = new MatriuCeles(bloc.obteNumFiles(), bloc.obteNumCols());
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();

        for (EntrMatrCeles e : entrades)
        {
            String text = e.obteCela().obteText();

            if (text == null)
            {
                // Deixem la cel·la original
                result.setCela(e.obteCela(), e.obteFila(), e.obteCol());
            }
            else
            {
                CelaNum c = new CelaNum("extreuLongitudText(" + e.obteCela().getInputUsuari() + ")",
                        (double)text.length());
                result.setCela(c, e.obteFila(), e.obteCol());
            }
        }

        return result;
    }

    public MatriuCeles cercaOcurrencies(MatriuCeles bloc, String aCercar) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        JSONObject obj = new JSONObject();
        int total = 0;

        for (EntrMatrCeles e : entrades) {
            String text = e.obteCela().obteText();

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

                obj.put(e.obteFila().toString() + ":" + e.obteCol().toString(),
                        cela);
                total += count;
            }
        }

        obj.put("occurrences", total);
        // problema que posar d'input usuari
        CelaText c = new CelaText("cercaOcurrencies(" + aCercar + ")", obj.toString());
        MatriuCeles mc = new MatriuCeles(1 , 1);
        mc.setCela(c, 0, 0);

        return mc;
    }

    public MatriuCeles converteixMajuscules(MatriuCeles bloc)
    {
        MatriuCeles result = new MatriuCeles(bloc.obteNumFiles(), bloc.obteNumCols());
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();

        for (EntrMatrCeles e : entrades)
        {
            String text = e.obteCela().obteText();

            if (text == null)
            {
                // Deixem la cel·la original
                result.setCela(e.obteCela(), e.obteFila(), e.obteCol());
            }
            else
            {
                CelaText c = new CelaText("converteixMajuscules(" + e.obteCela().getInputUsuari() + ")",
                        text.toUpperCase());
                result.setCela(c, e.obteFila(), e.obteCol());
            }
        }

        return result;
    }

    public MatriuCeles converteixMinuscules(MatriuCeles bloc)
    {
        MatriuCeles result = new MatriuCeles(bloc.obteNumFiles(), bloc.obteNumCols());
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();

        for (EntrMatrCeles e : entrades)
        {
            String text = e.obteCela().obteText();

            if (text == null)
            {
                // Deixem la cel·la original
                result.setCela(e.obteCela(), e.obteFila(), e.obteCol());
            }
            else
            {
                CelaText c = new CelaText("converteixMinuscules(" + e.obteCela().getInputUsuari() + ")",
                        text.toLowerCase());
                result.setCela(c, e.obteFila(), e.obteCol());
            }
        }

        return result;
    }

    public MatriuCeles transposa(MatriuCeles bloc)
    {
        MatriuCeles result = new MatriuCeles(bloc.obteNumCols(), bloc.obteNumFiles());
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();

        for (EntrMatrCeles e : entrades)
            result.setCela(e.obteCela(), e.obteCol(), e.obteFila());

        return result;
    }

    public MatriuCeles reemplaca(MatriuCeles bloc, String aCercar, String aSubstituir)
    {
        MatriuCeles result = new MatriuCeles(bloc.obteNumFiles(), bloc.obteNumCols());
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();

        for (EntrMatrCeles e : entrades)
        {
            String text = e.obteCela().obteText();

            if (text == null)
            {
                // Deixem la cel·la original
                result.setCela(e.obteCela(), e.obteFila(), e.obteCol());
            }
            else
            {
                CelaText c = new CelaText("reemplaça(" + e.obteCela().getInputUsuari() + ", " +
                        aCercar + ", " + aSubstituir + ")", text.replaceAll(aCercar, aSubstituir));
                result.setCela(c, e.obteFila(), e.obteCol());
            }
        }

        return result;
    }

    public MatriuCeles ordena(MatriuCeles bloc, Integer col, CriteriOrdenacio criteri) {
        return bloc;
    }

    private String horoscop(Integer dia, Integer mes) {
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
