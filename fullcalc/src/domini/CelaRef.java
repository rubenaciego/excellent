package domini;
import java.lang.*;
import java.time.LocalDate;

public class CelaRef extends Cela{
    Cela celaReferenciada;

    public void setRef(String inputUsuari, Cela celaRef) {
        super.setInputUsuari(inputUsuari);
        celaReferenciada = celaRef;
    }

    public Double obteNum() {
        return celaReferenciada.obteNum();
    }

    public LocalDate obteDate() {
        return celaReferenciada.obteDate();
    }

    public String obteText() {
        return celaReferenciada.obteText();
    }
}
