package domini;

import java.time.LocalDate;

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
}
