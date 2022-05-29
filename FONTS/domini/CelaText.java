package domini;

import java.lang.*;
import java.time.LocalDate;
import java.util.HashSet;

/**
 * Classe que hereda de Cela i té contingut tipus text
 */
public class CelaText extends Cela {
    /**
     * Conté el text substancial de l’input de l’usuari i les seves modificacions.
     */
    private String text;

    /**
     * Constructora simplificada
     * @param inputUsuari input inicial que es rep de l'usuari, que s'assigna també com a text de la Cela
     */
    public CelaText(String inputUsuari) {
        super(inputUsuari);
        this.text = inputUsuari;
        tipusCela = TipusCela.TEXTUAL;
    }

    /**
     * Constructora principal
     * @param inputUsuari input inicial que es rep de l'usuari
     * @param text text substancial corresponent a l'input d'usuari
     */
    public CelaText(String inputUsuari, String text) {
        super(inputUsuari);
        this.text = text;
        tipusCela = TipusCela.TEXTUAL;
    }

    /**
     * Setter de text
     * @param inputUsuari input de l'usuari corresponent a text en forma de String
     * @param text text substancial que l'usuari proporciona
     */
    public void setText(String inputUsuari, String text) {
        super.setInputUsuari(inputUsuari);
        this.text = text;
    }

    /**
     * {@inheritDoc}
     */

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
