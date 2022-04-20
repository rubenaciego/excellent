package domini;

import java.time.LocalDate;

public class ContingutCelaModificada
{
    String inputUsuari;
    Double valorNumeric;
    LocalDate data;
    Integer filaRef;
    Integer colRef;
    TipusCela tipus;

    public String getInputUsuari() {
        return inputUsuari;
    }

    public void setInputUsuari(String inputUsuari) {
        this.inputUsuari = inputUsuari;
    }

    public Double getValorNumeric() {
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

    public Integer getFilaRef() {
        return filaRef;
    }

    public void setFilaRef(Integer filaRef) {
        this.filaRef = filaRef;
    }

    public Integer getColRef() {
        return colRef;
    }

    public void setColRef(Integer colRef) {
        this.colRef = colRef;
    }

    public TipusCela getTipus() {
        return tipus;
    }

    public void setTipus(TipusCela tipus) {
        this.tipus = tipus;
    }
}
