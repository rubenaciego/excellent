package domini;
import java.lang.*;
import java.time.LocalDate;

public class CelaNum extends Cela {
    Double valor;

    public void setNum(String inputUsuari, Double valorInput) {
        super.setInputUsuari(inputUsuari);
        valor = valorInput;
    }

    public Double obteNum() {
        return valor;
    }

    public LocalDate obteDate() {
        return null;
    }

    public String obteText() {
        return null;
    }
}
