package domini;
import java.lang.*;
import java.time.LocalDate;

public class CelaData extends Cela {
    private LocalDate data;

    public CelaData() {}
    public CelaData(String inputUsuari, LocalDate dataInput) {
        super.setInputUsuari(inputUsuari);
        data = dataInput;
    }

    public void setData(String inputUsuari, LocalDate dataInput) {
        super.setInputUsuari(inputUsuari);
        data = dataInput;
    }

    public Double obteNum() {
        return null;
    }

    public LocalDate obteData() {
        return data;
    }

    public String obteText() {
        return null;
    }

}
