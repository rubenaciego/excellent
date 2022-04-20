package domini;
import java.lang.*;
import java.time.LocalDate;

public class CelaText extends Cela
{
    private String text;

    public CelaText() {
    }

    public CelaText(String inputUsuari) {
        super.setInputUsuari(inputUsuari);
        this.text = inputUsuari;
    }

    public CelaText(String inputUsuari, String text) {
        super.setInputUsuari(inputUsuari);
        this.text = text;
    }

    public void setText(String inputUsuari, String text) {
        super.setInputUsuari(inputUsuari);
        this.text = text;
    }

    public Double getNum() {
        return null;
    }

    public LocalDate getData() {
        return null;
    }

    public String getText() {
        return text;
    }
}
