package domini;

import java.lang.*;
import java.time.LocalDate;

public class CelaData extends Cela
{
    private LocalDate data;

    public CelaData(String inputUsuari, LocalDate dataInput)
    {
        super.setInputUsuari(inputUsuari);
        data = dataInput;
        tipusCela = TipusCela.DATADA;
    }

    public void setData(String inputUsuari, LocalDate dataInput)
    {
        super.setInputUsuari(inputUsuari);
        data = dataInput;
    }

    public Double getNum()
    {
        return null;
    }

    public LocalDate getData()
    {
        return data;
    }

    public String getText()
    {
        return null;
    }

    public int compareType(Cela c)
    {
        LocalDate d = c.getData();

        return data.compareTo(d);
    }
}
