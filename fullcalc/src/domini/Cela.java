package domini;

import java.lang.*;
import java.time.LocalDate;

public abstract class Cela
{
    public enum TipusCela
    {
        NUMERICA,
        TEXTUAL,
        DATADA,
        REFERENCIAL
    }

    protected String inputUsuari;
    protected TipusCela tipusCela;

    public String getInputUsuari()
    {
        return inputUsuari;
    }

    public void setInputUsuari(String inputUsuari)
    {
        this.inputUsuari = inputUsuari;
    }

    public abstract Double getNum();
    public abstract LocalDate getData();
    public abstract String getText();

    public int compare(Cela c)
    {
        if (c.tipusCela == TipusCela.REFERENCIAL)
            return -c.compare(this);

        if (tipusCela != c.tipusCela)
            return tipusCela.compareTo(c.tipusCela);
        else return compareType(c);

    }

    public abstract int compareType(Cela c);
}
