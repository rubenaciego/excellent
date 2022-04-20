package domini;
import java.lang.*;
import java.time.LocalDate;

public class CelaNum extends Cela {
    private Double valor;

    public CelaNum() {}

    public CelaNum(String inputUsuari, Double valorInput) {
        super.setInputUsuari(inputUsuari);
        valor = valorInput;
    }

    public void setNum(String inputUsuari, Double valorInput) {
        super.setInputUsuari(inputUsuari);
        valor = valorInput;
    }

    public Double obteNum() {
        return valor;
    }

    public LocalDate obteData() {
        return null;
    }

    public String obteText() {
        return null;
    }
}
