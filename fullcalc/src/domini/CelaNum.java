package domini;
import java.lang.*;
import java.time.LocalDate;

public class CelaNum extends Cela {
    Double valor;

    public void setNum(String inputUsuari, Double valorInput) {
        super.setInputUsuari(inputUsuari);
        valor = valorInput;
    }

    public Double getNum() {
        return valor;
    }

    public LocalDate getDate() {
        return null;
    }

    public String getText() {
        return null;
    }
}
