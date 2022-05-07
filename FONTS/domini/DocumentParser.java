package domini;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        CSVReader reader = new CSVReader(new StringReader(csv));
        String[] line;

        Document document = new Document("");

        try {

            line = reader.readNext();
            LocalDateTime dataMod = LocalDateTime.parse(line[0], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            int nFulls = Integer.parseInt(line[1]);
            int currFull = -1;

            document.setDataModificacio(dataMod);

            ArrayList<EntradaMatriuCeles> celesRef = null;

            while ((line = reader.readNext()) != null) {
                String first = line[0];

                if (first.startsWith("full")) {
                    if (currFull != -1)
                        afegeixCelesRef(document.getFull(currFull), celesRef);

                    celesRef = new ArrayList<EntradaMatriuCeles>();

                    int fIndex = Integer.parseInt(first.substring(4));
                    if (fIndex != ++currFull)
                        throw new ExcepcioFormatDocument(FormatDocument.CSV, csv);

                    int nfiles = Integer.parseInt(line[1]);
                    int ncols = Integer.parseInt(line[2]);
                    document.afegeixFull(nfiles, ncols);
                } else {
                    int split = first.indexOf(':');
                    int fila = Integer.parseInt(first.substring(0, split));
                    int col = Integer.parseInt(first.substring(split + 1));

                    String inputUsuari = line[1];
                    Cela.TipusCela tipus = Cela.TipusCela.valueOf(line[2]);
                    Cela cela;

                    switch (tipus) {
                        case NUMERICA:
                            cela = new CelaNum(inputUsuari, Double.parseDouble(line[3]));
                            break;
                        case TEXTUAL:
                            cela = new CelaText(inputUsuari, line[3]);
                            break;
                        case DATADA:
                            cela = new CelaData(inputUsuari, LocalDate.parse(line[3], DateTimeFormatter.ISO_LOCAL_DATE));
                            break;
                        case REFERENCIAL:
                            cela = new CelaRef(inputUsuari, null);
                            break;
                        default:
                            throw new IncompatibleClassChangeError("Error: tipus de cel·la " + tipus + " desconegut");
                    }

                    // Les cel·les refèrencia s'afegeixen les últimes, un cop tenim totes les cel·les
                    if (cela.getTipusCela() == Cela.TipusCela.REFERENCIAL)
                        celesRef.add(new EntradaMatriuCeles(fila, col, cela));
                    else
                        document.getFull(currFull).setCela(cela, fila, col);
                }
            }

            if (currFull != -1)
                afegeixCelesRef(document.getFull(currFull), celesRef);

            if (nFulls != document.getNumFulls())
                throw new ExcepcioFormatDocument(FormatDocument.CSV, csv);
        } catch (IOException | IndexOutOfBoundsException | CsvValidationException
                | IllegalArgumentException | DateTimeParseException | NullPointerException e) {
            throw new ExcepcioFormatDocument(FormatDocument.CSV, csv);
        }

        return document;
    }

    public Document parseFromJSON(String json) {
        Document document = new Document("");

        try {
            JSONObject jsonObj = JSONObject.fromObject(json);
            LocalDateTime dataMod = LocalDateTime.parse(jsonObj.getString("dataModificacio"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            int numFulls = jsonObj.getInt("numFulls");

            document.setDataModificacio(dataMod);

            for (int i = 0; i < numFulls; ++i) {
                ArrayList<EntradaMatriuCeles> celesRef = new ArrayList<EntradaMatriuCeles>();
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

                afegeixCelesRef(full, celesRef);
            }
        } catch (JSONException | IndexOutOfBoundsException | IllegalArgumentException | DateTimeParseException e) {
            throw new ExcepcioFormatDocument(FormatDocument.JSON, json);
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

    private void afegeixCelesRef(Full full, ArrayList<EntradaMatriuCeles> celesRef) {
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

                ((CelaRef) e.getCela()).setRef(aRef);
                full.setCela(e.getCela(), e.getFila(), e.getColumna());
            }
        }
    }
}
