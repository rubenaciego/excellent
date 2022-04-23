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
        tipusCela = TipusCela.DATADA;
    }

    @Override
    public Double getNum()
    {
        return null;
    }

    @Override
    public LocalDate getData()
    {
        return data;
    }

    @Override
    public String getText()
    {
        return null;
    }

    @Override
    public CelaData copy() {
        CelaData nova = new CelaData(new String(inputUsuari), data);
        return nova;
    }

    @Override
    protected int compareType(Cela c)
    {
        LocalDate d = c.getData();
        return data.compareTo(d);
    }
}
