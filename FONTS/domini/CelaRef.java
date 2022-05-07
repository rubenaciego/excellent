package domini;

import java.lang.*;
import java.time.LocalDate;


public class CelaRef extends Cela {
    private Cela celaReferenciada;

    public CelaRef(String inputUsuari, Cela celaRef) {
        super.setInputUsuari(inputUsuari);
        celaReferenciada = celaRef;
        tipusCela = TipusCela.REFERENCIAL;
    }

    public void setRef(String inputUsuari, Cela celaRef) {
        super.setInputUsuari(inputUsuari);
        celaReferenciada = celaRef;
        tipusCela = TipusCela.REFERENCIAL;
    }

    public Cela getRef() {
        return celaReferenciada;
    }

    @Override
    public Double getNum() {
        return celaReferenciada.getNum();
    }

    @Override
    public LocalDate getData() {
        return celaReferenciada.getData();
    }

    @Override
    public String getText() {
        return celaReferenciada.getText();
    }

    @Override
    public String toString() {
        return celaReferenciada.toString();
    }

    @Override
    public Cela copy() {
        return new CelaRef(inputUsuari, celaReferenciada);
    }

    @Override
    public int compare(Cela c) {
        return celaReferenciada.compare(c);
    }

    @Override
    protected int compareType(Cela c) {
        throw new UnsupportedOperationException("compareType de CelaRef mai no pot ser cridat");
    }
}
