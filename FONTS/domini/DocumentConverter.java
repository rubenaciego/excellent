package domini;

import com.opencsv.CSVWriter;
import net.sf.json.JSONObject;

import java.io.StringWriter;
import java.time.format.DateTimeFormatter;

/**
 * Conversor d'un document al format adequat per al seu emmagatzematge en
 * memòria i persistència.
 */
public class DocumentConverter {

    /**
     * Document a convertir al format adequat per emmagatzemar en memòria.
     */
    private final Document document;

    /**
     * Contructora principal que carrega el document a convertir.
     * @param doc document a convertir
     */
    public DocumentConverter(Document doc) {
        document = doc;
    }

    /**
     * Converteix el document al format especificat (CSV o JSON).
     * @param format format al que convertir el document
     * @return document convertit al format especificat
     */
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

    /**
     * Converteix el document a CSV
     * @return el document codificat com a CSV
     */
    public String convertToCSV() {
        StringWriter sw = new StringWriter();
        CSVWriter writer = new CSVWriter(sw);

        String dataMod = document.getDataModificacio().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        int nFulls = document.getNumFulls();

        writer.writeNext(new String[]{dataMod, Integer.toString(nFulls)});

        for (int i = 0; i < nFulls; ++i) {
            Full full = document.getFull(i);
            writer.writeNext(new String[]{"full" + i, Integer.toString(full.getNumFiles()),
                    Integer.toString(full.getNumCols())});

            for (EntradaMatriuCeles e : full.getEntrades())
                afegeixCelaCSV(writer, e);
        }

        return sw.toString();
    }

    /**
     * Converteix el document a JSON
     * @return el document codificat com a JSON
     */
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

    /**
     * Afegeix a un JSON una cel·la codificada adequadament.
     * @param json json al que afegir la cel·la
     * @param e cel·la a afegir al json
     */
    private void afegeixCelaJSON(JSONObject json, EntradaMatriuCeles e) {
        Cela c = e.getCela();
        JSONObject cela = new JSONObject();
        cela.put("inputUsuari", c.getInputUsuari());
        cela.put("type", c.getTipusCela());

        switch (c.getTipusCela()) {
            case NUMERICA:
                cela.put("value", c.getNum());
                break;
            case TEXTUAL:
                cela.put("value", c.getText());
                break;
            case DATADA:
                cela.put("value", c.getData().format(DateTimeFormatter.ISO_LOCAL_DATE));
                break;
            case REFERENCIAL:
                cela.put("value", c.getInputUsuari());
                break;
            default:
                throw new IncompatibleClassChangeError("Error: tipus de cel·la " + c.getTipusCela() + " desconegut");
        }

        json.put(e.getFila() + ":" + e.getColumna(), cela);
    }

    /**
     * Afegeix a un CSV una cel·la codificada adequadament.
     * @param writer CSV al que afegir la cel·la
     * @param e cel·la a afegir al json
     */
    private void afegeixCelaCSV(CSVWriter writer, EntradaMatriuCeles e) {
        Cela c = e.getCela();
        String value;

        switch (c.getTipusCela()) {
            case NUMERICA:
                value = c.getNum().toString();
                break;
            case TEXTUAL:
                value = c.getText();
                break;
            case DATADA:
                value = c.getData().format(DateTimeFormatter.ISO_LOCAL_DATE);
                break;
            case REFERENCIAL:
                value = c.getInputUsuari();
                break;
            default:
                throw new IncompatibleClassChangeError("Error: tipus de cel·la " + c.getTipusCela() + " desconegut");
        }

        writer.writeNext(new String[]{e.getFila() + ":" + e.getColumna(), c.getInputUsuari(),
            c.getTipusCela().toString(), value});
    }
}
