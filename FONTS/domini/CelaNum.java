package domini;

import java.lang.*;
import java.time.LocalDate;
import java.util.HashSet;

public class CelaNum extends Cela {
    private double valor;

    public CelaNum(String inputUsuari, double valorInput) {
        super(inputUsuari);
        valor = valorInput;
        tipusCela = TipusCela.NUMERICA;
    }

    public void setNum(String inputUsuari, double valorInput) {
        super.setInputUsuari(inputUsuari);
        valor = valorInput;
        tipusCela = TipusCela.NUMERICA;
    }

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
