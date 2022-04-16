package domini;
import java.lang.*;
import java.time.LocalDate;

public class CelaRef extends Cela{
    Cela celaReferenciada;

    public void setRef(String inputUsuari, Cela celaRef) {
        super.setInputUsuari(inputUsuari);
        celaReferenciada = celaRef;
    }

    public Double getNum() {
        return celaReferenciada.getNum();
    }

    public LocalDate getDate() {
        return celaReferenciada.getDate();
    }

    public String getText() {
        return celaReferenciada.getText();
    }
}
