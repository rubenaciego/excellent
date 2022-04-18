package domini;

import java.time.LocalDate;

public class ContingutCelaModificada
{
    String inputUsuari;
    double valorNumeric;
    LocalDate data;
    int filaRef;
    int colRef;
    tipusCela tipus;

    public String getInputUsuari() {
        return inputUsuari;
    }

    public void setInputUsuari(String inputUsuari) {
        this.inputUsuari = inputUsuari;
    }

    public double getValorNumeric() {
        return valorNumeric;
    }

    public void setValorNumeric(double valorNumeric) {
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

    public void setFilaRef(int filaRef) {
        this.filaRef = filaRef;
    }

    public int getColRef() {
        return colRef;
    }

    public void setColRef(int colRef) {
        this.colRef = colRef;
    }

    public tipusCela getTipus() {
        return tipus;
    }

    public void setTipus(tipusCela tipus) {
        this.tipus = tipus;
    }
}
