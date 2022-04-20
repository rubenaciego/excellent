package domini;
import java.lang.*;
import java.time.LocalDate;

public class CelaRef extends Cela{
    private Cela celaReferenciada;

    public CelaRef() {}
    public CelaRef(String inputUsuari, Cela celaRef) {
        super.setInputUsuari(inputUsuari);
        celaReferenciada = celaRef;
    }

    public void setRef(String inputUsuari, Cela celaRef) {
        super.setInputUsuari(inputUsuari);
        celaReferenciada = celaRef;
    }

    public Double obteNum() {
        return celaReferenciada.obteNum();
    }

    public LocalDate obteData() {
        return celaReferenciada.obteData();
    }

    public String obteText() {
        return celaReferenciada.obteText();
    }
}
