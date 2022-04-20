package domini;
import java.lang.*;
import java.time.LocalDate;

public class CelaText extends Cela {

    public CelaText() {}
    public CelaText(String inputUsuari) {
        super.setInputUsuari(inputUsuari);
    }

    public Double obteNum() {
        return null;
    }

    public LocalDate obteData() {
        return null;
    }

    public String obteText() {
        return super.inputUsuari;
    }
}
