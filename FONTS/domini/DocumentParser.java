package domini;

import net.sf.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class DocumentParser {

    public Document parseFrom(String txt, FormatDocument format) {
        switch (format) {
            case CSV:
                return parseFromCSV(txt);
            case JSON:
                return parseFromJSON(txt);
            default:
                throw new UnsupportedOperationException("Error: format de document " + format + " desconegut");
        }
    }

    public Document parseFromCSV(String csv) {
        return null;
    }

    public Document parseFromJSON(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        Document document = new Document("");
        LocalDateTime dataMod = LocalDateTime.parse(jsonObj.getString("dataModificacio"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        int numFulls = jsonObj.getInt("numFulls");

        document.setDataModificacio(dataMod);

        ArrayList<EntradaMatriuCeles> celesRef = new ArrayList<EntradaMatriuCeles>();

        for (int i = 0; i < numFulls; ++i) {
            JSONObject fullJson = jsonObj.getJSONObject("full" + i);
            document.afegeixFull(fullJson.getInt("numFiles"), fullJson.getInt("numCols"));
            Full full = document.getFull(i);

            for (Iterator it = fullJson.keys(); it.hasNext(); ) {
                String key = (String) it.next();
                if (key.equals("numFiles") || key.equals("numCols"))
                    continue;

                JSONObject cela = fullJson.getJSONObject(key);
                int split = key.indexOf(':');
                int fila = Integer.parseInt(key.substring(0, split));
                int col = Integer.parseInt(key.substring(split + 1));

                Cela c = getCelaFromJSON(cela);

                // Les cel·les refèrencia s'afegeixen les últimes, un cop tenim totes les cel·les
                if (c.getTipusCela() == Cela.TipusCela.REFERENCIAL)
                    celesRef.add(new EntradaMatriuCeles(fila, col, c));
                else
                    full.setCela(c, fila, col);
            }

            for (EntradaMatriuCeles e : celesRef) {
                String refTxt = e.getCela().getInputUsuari();
                int split = refTxt.indexOf(':');
                int fRef = Integer.parseInt(refTxt.substring(0, split));
                int cRef = Integer.parseInt(refTxt.substring(split + 1));
                Cela aRef = full.getCela(fRef, cRef);

                if (aRef != null) {
                    if (aRef instanceof CelaRef) {
                        e.getCela().setInputUsuari(aRef.getInputUsuari());
                        aRef = ((CelaRef) aRef).getRef();
                    }

                    full.setCela(new CelaRef(e.getCela().getInputUsuari(), aRef), e.getFila(), e.getColumna());
                }
            }
        }

        return document;
    }

    private Cela getCelaFromJSON(JSONObject json) {
        Cela res;
        String inputUsuari = json.getString("inputUsuari");
        Cela.TipusCela tipus = Cela.TipusCela.valueOf(json.getString("type"));

        switch (tipus) {
            case NUMERICA:
                res = new CelaNum(inputUsuari, json.getDouble("value"));
                break;
            case TEXTUAL:
                res = new CelaText(inputUsuari, json.getString("value"));
                break;
            case DATADA:
                res = new CelaData(inputUsuari, LocalDate.parse(json.getString("value"), DateTimeFormatter.ISO_LOCAL_DATE));
                break;
            case REFERENCIAL:
                res = new CelaRef(inputUsuari, null);
                break;
            default:
                throw new IncompatibleClassChangeError("Error: tipus de cel·la " + tipus + " desconegut");
        }

        return res;
    }
}
