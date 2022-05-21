package domini;

import java.lang.*;
import java.time.LocalDate;
import java.util.HashSet;

public class CelaText extends Cela {
    private String text;

    public CelaText(String inputUsuari) {
        super(inputUsuari);
        this.text = inputUsuari;
        tipusCela = TipusCela.TEXTUAL;
    }

    public CelaText(String inputUsuari, String text) {
        super(inputUsuari);
        this.text = text;
        tipusCela = TipusCela.TEXTUAL;
    }

    public void setText(String inputUsuari, String text) {
        super.setInputUsuari(inputUsuari);
        this.text = text;
    }

    @Override
    public Double getNum() {
        return null;
    }

    @Override
    public LocalDate getData() {
        return null;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public Cela copy() {
        return new CelaText(inputUsuari, text);
    }

    @Override
    protected int compareType(Cela c) {
        return text.compareTo(c.getText());
    }
}
