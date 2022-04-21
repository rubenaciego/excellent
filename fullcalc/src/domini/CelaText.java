package domini;

import java.lang.*;
import java.time.LocalDate;

public class CelaText extends Cela
{
    private String text;

    public CelaText(String inputUsuari)
    {
        super.setInputUsuari(inputUsuari);
        this.text = inputUsuari;
        tipusCela = TipusCela.TEXTUAL;
    }

    public CelaText(String inputUsuari, String text)
    {
        super.setInputUsuari(inputUsuari);
        this.text = text;
        tipusCela = TipusCela.TEXTUAL;
    }

    public void setText(String inputUsuari, String text)
    {
        super.setInputUsuari(inputUsuari);
        this.text = text;
    }

    @Override
    public Double getNum()
    {
        return null;
    }

    @Override
    public LocalDate getData()
    {
        return null;
    }

    @Override
    public String getText()
    {
        return text;
    }

    @Override
    protected int compareType(Cela c)
    {
        return text.compareTo(c.getText());
    }
}
