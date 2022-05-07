package domini;

import java.lang.*;
import java.time.LocalDate;

public class CelaData extends Cela {
    private LocalDate data;

    public CelaData(String inputUsuari, LocalDate dataInput) {
        super.setInputUsuari(inputUsuari);
        data = dataInput;
        tipusCela = TipusCela.DATADA;
    }

    public void setData(String inputUsuari, LocalDate dataInput) {
        super.setInputUsuari(inputUsuari);
        data = dataInput;
        tipusCela = TipusCela.DATADA;
    }

    @Override
    public Double getNum() {
        return null;
    }

    @Override
    public LocalDate getData() {
        return data;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public Cela copy() {
        return new CelaData(inputUsuari, data);
    }

    @Override
    protected int compareType(Cela c) {
        LocalDate d = c.getData();
        return data.compareTo(d);
    }
}
