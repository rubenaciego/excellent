package domini;

import java.lang.*;
import java.time.LocalDate;
import java.util.HashSet;

/**
 * Classe que hereda de Cela i que conté un valor numèric.
 */
public class CelaNum extends Cela {
    /**
     * Conté el valor numèric de l’input de l’usuari i les seves modificacions.
     */
    private double valor;

    /**
     * Constructora principal
     * @param inputUsuari input inicial que es rep de l'usuari
     * @param valorInput valor numèric corresponent a l'input d'usuari
     */
    public CelaNum(String inputUsuari, double valorInput) {
        super(inputUsuari);
        valor = valorInput;
        tipusCela = TipusCela.NUMERICA;
    }

    /**
     * Setter de valor
     * @param inputUsuari input de l'usuari corresponent a valorInput en forma de String
     * @param valorInput valor numèric que l'usuari proporciona
     */
    public void setNum(String inputUsuari, double valorInput) {
        super.setInputUsuari(inputUsuari);
        valor = valorInput;
        tipusCela = TipusCela.NUMERICA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getNum() {
        return valor;
    }

    @Override
    public LocalDate getData() {
        return null;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public String toString() {
        return Double.toString(valor);
    }

    @Override
    public Cela copy()
    {
        return new CelaNum(inputUsuari, valor);
    }

    @Override
    protected int compareType(Cela c) {
        return Double.compare(valor, c.getNum());
    }
}
