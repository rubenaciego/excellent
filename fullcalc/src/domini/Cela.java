package domini;
import java.lang.*;
import java.time.LocalDate;

public abstract class Cela {
    String inputUsuari;

    public String getInputUsuari() {
        return inputUsuari;
    }

    public void setInputUsuari(String inputUsuari) {
        this.inputUsuari = inputUsuari;
    }

    abstract public Double getNum();

    abstract public LocalDate getDate();

    abstract public String getText();
}
