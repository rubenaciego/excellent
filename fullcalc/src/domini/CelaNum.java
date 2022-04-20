package domini;

import java.lang.*;
import java.time.LocalDate;

public class CelaNum extends Cela
{
    private double valor;

    public CelaNum(String inputUsuari, double valorInput)
    {
        super.setInputUsuari(inputUsuari);
        valor = valorInput;
        tipusCela = TipusCela.NUMERICA;
    }

    public void setNum(String inputUsuari, double valorInput)
    {
        super.setInputUsuari(inputUsuari);
        valor = valorInput;
    }

    @Override
    public Double getNum()
    {
        return valor;
    }

    @Override
    public LocalDate getData()
    {
        return null;
    }

    @Override
    public String getText()
    {
        return null;
    }

    @Override
    protected int compareType(Cela c)
    {
        return Double.compare(valor, c.getNum());
    }
}
