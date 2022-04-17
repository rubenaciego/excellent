package domini;
import java.lang.*;
import java.time.LocalDate;

public class CelaData extends Cela {
    LocalDate data;

    public void setData(String inputUsuari, LocalDate dataInput) {
        super.setInputUsuari(inputUsuari);
        data = dataInput;
    }

    public Double obteNum() {
        return null;
    }

    public LocalDate obteDate() {
        return data;
    }

    public String obteText() {
        return null;
    }

}
