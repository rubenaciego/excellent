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
    }

    public void setText(String inputUsuari, String text)
    {
        super.setInputUsuari(inputUsuari);
        this.text = text;
    }

    public Double getNum()
    {
        return null;
    }

    public LocalDate getData()
    {
        return null;
    }

    public String getText()
    {
        return text;
    }

    public int compareType(Cela c)
    {
        return text.compareTo(c.getText());
    }
}
