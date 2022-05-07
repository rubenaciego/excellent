package domini;

import net.sf.json.JSONObject;

import java.time.format.DateTimeFormatter;

public class DocumentConverter {

    private final Document document;

    public DocumentConverter(Document doc) {
        document = doc;
    }

    public String convertTo(FormatDocument format) {
        switch (format) {
            case CSV:
                return convertToCSV();
            case JSON:
                return convertToJSON();
            default:
                throw new UnsupportedOperationException("Error: format de document " + format + " desconegut");
        }
    }

    public String convertToCSV() {
        return null;
    }

    public String convertToJSON() {
        JSONObject json = new JSONObject();

        json.put("dataModificacio", document.getDataModificacio().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        json.put("numFulls", document.getNumFulls());

        for (int i = 0; i < document.getNumFulls(); ++i) {
            Full full = document.getFull(i);
            JSONObject jsonFull = new JSONObject();
            jsonFull.put("numFiles", full.getNumFiles());
            jsonFull.put("numCols", full.getNumCols());

            for (EntradaMatriuCeles e : full.getEntrades())
                afegeixCelaJSON(jsonFull, e);

            json.put("full" + i, jsonFull);
        }

        return json.toString();
    }

    private void afegeixCelaJSON(JSONObject json, EntradaMatriuCeles e) {
        Cela c = e.getCela();
        JSONObject cela = new JSONObject();
        cela.put("inputUsuari", c.getInputUsuari());

        switch (c.getTipusCela()) {
            case NUMERICA:
                json.put("value", c.getNum());
                break;
            case TEXTUAL:
                json.put("value", c.getText());
                break;
            case DATADA:
                json.put("value", c.getData().format(DateTimeFormatter.ISO_LOCAL_DATE));
                break;
            case REFERENCIAL:
                json.put("value", c.getInputUsuari());
                break;
            default:
                throw new IncompatibleClassChangeError("Error: tipus de cel·la " + c.getTipusCela() + " desconegut");
        }

        json.put(e.getFila() + ":" + e.getColumna(), cela);
    }
}
