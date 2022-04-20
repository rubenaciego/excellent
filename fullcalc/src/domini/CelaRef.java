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

    public Double getNum() {
        return celaReferenciada.getNum();
    }

    public LocalDate getData() {
        return celaReferenciada.getData();
    }

    public String getText() {
        return celaReferenciada.getText();
    }
}
