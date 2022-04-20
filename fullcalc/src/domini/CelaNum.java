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
    }

    public void setNum(String inputUsuari, double valorInput)
    {
        super.setInputUsuari(inputUsuari);
        valor = valorInput;
    }

    public Double getNum()
    {
        return valor;
    }

    public LocalDate getData()
    {
        return null;
    }

    public String getText()
    {
        return null;
    }
}
