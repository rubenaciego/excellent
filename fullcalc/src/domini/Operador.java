package domini;

import java.util.ArrayList;
import java.lang.Math;

public class Operador {
    public MatriuCeles extreuHorocop(MatriuCeles bloc) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteData() != null) {
                String signe =
                        horoscop(e.obteCela().obteData().getDayOfMonth(),
                                e.obteCela().obteData().getMonthValue());
                CelaText novaCela = new CelaText(signe);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuAny(MatriuCeles bloc) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteData() != null) {
                Double any = (double) e.obteCela().obteData().getYear();
                CelaNum novaCela = new CelaNum(any.toString(), any);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuMes(MatriuCeles bloc) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteData() != null) {
                Double mes = (double) e.obteCela().obteData().getMonthValue();
                CelaNum novaCela = new CelaNum(mes.toString(), mes);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuDia(MatriuCeles bloc) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteData() != null) {
                Double dia = (double) e.obteCela().obteData().getDayOfMonth();
                CelaNum novaCela = new CelaNum(dia.toString(), dia);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }
        return bloc;
    }

    public MatriuCeles extreuDiaSetmana(MatriuCeles bloc) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteData() != null) {
                Double diaSet =
                        (double) e.obteCela().obteData().getDayOfWeek().getValue();
                CelaNum novaCela = new CelaNum(diaSet.toString(), diaSet);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }
        return bloc;
    }

    public MatriuCeles executaOperacioAritmeticaUnaria(MatriuCeles bloc,
                                                       opAritmetica op) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();

        if (op == opAritmetica.valorAbsolut) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double absolut = Math.abs(e.obteCela().obteNum());
                    CelaNum novaCela = new CelaNum(absolut.toString(), absolut);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (op == opAritmetica.incrementar) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum() + 1.0;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (op == opAritmetica.decrementar) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum() - 1.0;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (op == opAritmetica.exponencial) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = Math.exp(e.obteCela().obteNum());
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (op == opAritmetica.cosinus) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = Math.cos(e.obteCela().obteNum());
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (op == opAritmetica.sinus) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = Math.sin(e.obteCela().obteNum());
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (op == opAritmetica.cosinusHiperbolic) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = Math.cosh(e.obteCela().obteNum());
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (op == opAritmetica.sinusHiperbolic) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = Math.sinh(e.obteCela().obteNum());
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (op == opAritmetica.tangentHiperbolic) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = Math.tanh(e.obteCela().obteNum());
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        }
        return bloc;
    }

    public MatriuCeles executaFuncioEstadistica(MatriuCeles bloc,
                                                opEstadistica op) {
        return bloc;
    }

    public MatriuCeles truncaNumero(MatriuCeles bloc, Integer n) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteNum() != null) {
                Double tmp = e.obteCela().obteNum() * Math.pow(10.0, n);
                Double truncat = (double) tmp.intValue() / Math.pow(10.0, n);
                CelaNum novaCela = new CelaNum(truncat.toString(), truncat);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }
        return bloc;
    }

    public MatriuCeles converteixUnitats(MatriuCeles bloc,
                                         conversioUnitats conv) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        if (conv == conversioUnitats.radGraus) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum()*180.0/Math.PI;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (conv == conversioUnitats.grausRad) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum()*Math.PI/180.0;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if ((conv == conversioUnitats.kmMilla) || (conv == conversioUnitats.kmhMillah)) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum() * 0.621371;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if ((conv == conversioUnitats.millaKm) || (conv == conversioUnitats.millahKmh)) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum() / 0.621371;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (conv == conversioUnitats.kgLliura) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum() * 2.20462;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (conv == conversioUnitats.lliuraKg) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum() / 2.20462;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (conv == conversioUnitats.litreGalo) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum() * 0.264172;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (conv == conversioUnitats.galoLitre) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum() / 0.264172;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        }
        else if (conv == conversioUnitats.celsiusKelvin) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum() + 273.15;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (conv == conversioUnitats.kelvinCelsius) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum() - 273.15;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (conv == conversioUnitats.kelvinFahrenheit) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = 1.8*(e.obteCela().obteNum()-273.15) + 32;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (conv == conversioUnitats.fahrenheitKelvin) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = (e.obteCela().obteNum() + 459.67) * 5.0/9.0;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (conv == conversioUnitats.fahrenheitCelsius) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val =
                            (e.obteCela().obteNum() + 459.67) * 5.0/9.0 - 273.15;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (conv == conversioUnitats.celsiuFahrenheit) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = 1.8*e.obteCela().obteNum() + 32;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (conv == conversioUnitats.km2Hecatarea) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum() * 100.0;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        } else if (conv == conversioUnitats.hectareaKm2) {
            for (EntrMatrCeles e : entrades) {
                if (e.obteCela().obteNum() != null) {
                    Double val = e.obteCela().obteNum() / 100.0;
                    CelaNum novaCela = new CelaNum(val.toString(), val);
                    bloc.setCela(novaCela, e.obteFila(), e.obteCol());
                }
            }
            return bloc;
        }

        return bloc;
    }

    public MatriuCeles extreuLongitudText(MatriuCeles bloc) {
        ArrayList<EntrMatrCeles> entrades = bloc.obteEntrades();
        for (EntrMatrCeles e : entrades) {
            if (e.obteCela().obteText() != null) {
                Double l = (double) e.obteCela().obteText().length();
                CelaNum novaCela = new CelaNum(l.toString(), l);
                bloc.setCela(novaCela, e.obteFila(), e.obteCol());
            }
        }
        return bloc;
    }

    public MatriuCeles cercaOcurrencies(MatriuCeles bloc, String aCercar) {
        return bloc;
    }

    public MatriuCeles converteixMajuscules(MatriuCeles bloc) {
        return bloc;
    }

    public MatriuCeles converteixMinuscules(MatriuCeles bloc) {
        return bloc;
    }

    public MatriuCeles transposa(MatriuCeles bloc) {
        return bloc;
    }

    public MatriuCeles reemplaca(MatriuCeles bloc, String aCercar,
                                 String aSubstituir) {
        return bloc;
    }

    public MatriuCeles ordena(MatriuCeles bloc, Integer col,
                              criteriOrdenacio criteri) {
        return bloc;
    }

    private String horoscop(Integer dia, Integer mes) {
        String astro_sign = "";

        if (mes == 12) {

            if (dia < 22)
                astro_sign = "Sagittarius";
            else
                astro_sign = "capricorn";
        } else if (mes == 1) {
            if (dia < 20)
                astro_sign = "Capricorn";
            else
                astro_sign = "aquarius";
        } else if (mes == 2) {
            if (dia < 19)
                astro_sign = "Aquarius";
            else
                astro_sign = "pisces";
        } else if (mes == 3) {
            if (dia < 21)
                astro_sign = "Pisces";
            else
                astro_sign = "aries";
        } else if (mes == 4) {
            if (dia < 20)
                astro_sign = "Aries";
            else
                astro_sign = "taurus";
        } else if (mes == 5) {
            if (dia < 21)
                astro_sign = "Taurus";
            else
                astro_sign = "gemini";
        } else if (mes == 6) {
            if (dia < 21)
                astro_sign = "Gemini";
            else
                astro_sign = "cancer";
        } else if (mes == 7) {
            if (dia < 23)
                astro_sign = "Cancer";
            else
                astro_sign = "leo";
        } else if (mes == 8) {
            if (dia < 23)
                astro_sign = "Leo";
            else
                astro_sign = "virgo";
        } else if (mes == 9) {
            if (dia < 23)
                astro_sign = "Virgo";
            else
                astro_sign = "libra";
        } else if (mes == 10) {
            if (dia < 23)
                astro_sign = "Libra";
            else
                astro_sign = "scorpio";
        } else if (mes == 11) {
            if (dia < 22)
                astro_sign = "scorpio";
            else
                astro_sign = "sagittarius";
        }
        return astro_sign;
    }
}
