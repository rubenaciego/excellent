package domini;

import java.time.LocalDate;
import java.util.Objects;

public class ResultatParserCela {
    String inputUsuari;
    double valorNumeric;
    LocalDate data;
    int filaRef;
    int colRef;
    Cela.TipusCela tipus;

    public String getInputUsuari() {
        return inputUsuari;
    }

    public void setInputUsuari(String inputUsuari) {
        this.inputUsuari = inputUsuari;
    }

    public double getValorNumeric() {
        return valorNumeric;
    }

    public void setValorNumeric(Double valorNumeric) {
        this.valorNumeric = valorNumeric;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getFilaRef() {
        return filaRef;
    }

    public void setFilaRef(Integer filaRef) {
        this.filaRef = filaRef;
    }

    public int getColRef() {
        return colRef;
    }

    public void setColRef(Integer colRef) {
        this.colRef = colRef;
    }

    public Cela.TipusCela getTipus() {
        return tipus;
    }

    public void setTipus(Cela.TipusCela tipus) {
        this.tipus = tipus;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof ResultatParserCela)) {
            return false;
        }

        ResultatParserCela c = (ResultatParserCela) o;

        return (inputUsuari == c.inputUsuari && valorNumeric == c.valorNumeric &&
                Objects.equals(data, c.data) && colRef == c.colRef &&
                String.valueOf(tipus) == String.valueOf(c.tipus));
    }
}
