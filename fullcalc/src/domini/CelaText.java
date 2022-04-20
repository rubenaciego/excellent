package domini;
import java.lang.*;
import java.time.LocalDate;

public class CelaText extends Cela
{
    private String text;

    public CelaText()
    {
    }

    public CelaText(String inputUsuari)
    {
        super.setInputUsuari(inputUsuari);
        this.text = inputUsuari;
    }

    public CelaText(String inputUsuari, String text)
    {
        super.setInputUsuari(inputUsuari);
        this.text = text;
    }

    public void setNum(String inputUsuari, String text)
    {
        super.setInputUsuari(inputUsuari);
        this.text = text;
    }

    public Double obteNum()
    {
        return null;
    }

    public LocalDate obteData()
    {
        return null;
    }

    public String obteText()
    {
        return text;
    }
}
