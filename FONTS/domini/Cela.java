package domini;

import java.lang.*;
import java.time.LocalDate;
import java.util.HashSet;

public abstract class Cela {
    public enum TipusCela {
        NUMERICA,
        TEXTUAL,
        DATADA,
        REFERENCIAL
    }

    protected String inputUsuari;
    protected TipusCela tipusCela;
    protected HashSet<CelaRef> referenciadores;

    public Cela(String inputUsuari)
    {
        this.inputUsuari = inputUsuari;
        referenciadores = new HashSet<CelaRef>();
    }

    public String getInputUsuari() {
        return inputUsuari;
    }

    public void setInputUsuari(String inputUsuari) {
        this.inputUsuari = inputUsuari;
    }

    public TipusCela getTipusCela() {
        return tipusCela;
    }

    public HashSet<CelaRef> getCelesReferenciadores() {
        return referenciadores;
    }

    public abstract Double getNum();

    public abstract LocalDate getData();

    public abstract String getText();

    public abstract Cela copy();

    public int compare(Cela c) {
        if (c.tipusCela == TipusCela.REFERENCIAL)
            return -c.compare(this);

        if (tipusCela != c.tipusCela)
            return tipusCela.compareTo(c.tipusCela);
        else return compareType(c);
    }

    protected abstract int compareType(Cela c);
}
