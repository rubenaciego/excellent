package domini;

import java.util.ArrayList;
import java.util.Locale;

import net.sf.json.*;

public class Operador {
    public MatriuCeles extreuHoroscop(MatriuCeles bloc) {}
    public MatriuCeles extreuAny(MatriuCeles bloc) {}
    public MatriuCeles extreuMes(MatriuCeles bloc) {}
    public MatriuCeles extreuDia(MatriuCeles bloc) {}
    public MatriuCeles extreuDiaSetmana(MatriuCeles bloc) {}
    public MatriuCeles executaOperacioAritmeticaUnaria(MatriuCeles bloc,
                                                       opAritmetica op) {}

    // fer aquesta tambe
    public MatriuCeles executaFuncioEstadistica(MatriuCeles bloc,
                                                opEstadistica op) {}
    public MatriuCeles truncaNumero(MatriuCeles bloc, Integer n) {}
    public MatriuCeles converteixUnitats(MatriuCeles bloc,
                                         conversioUnitats conv) {}

    // fer d'aqui cap avall

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
                ArrayList<int> indices = new ArrayList<int>();
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

    public MatriuCeles ordena(MatriuCeles bloc, Integer col, criteriOrdenacio criteri)
    {

    }
}
