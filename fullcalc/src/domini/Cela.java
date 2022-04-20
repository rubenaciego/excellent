package domini;
import java.lang.*;
import java.time.LocalDate;

public abstract class Cela {
    protected String inputUsuari;

    public String getInputUsuari() {
        return inputUsuari;
    }

    public void setInputUsuari(String inputUsuari) {
        this.inputUsuari = inputUsuari;
    }

    abstract public Double getNum();
    abstract public LocalDate getData();
    abstract public String getText();
}
