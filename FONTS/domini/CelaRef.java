package domini;

import java.lang.*;
import java.time.LocalDate;

/**
 * Classe que hereda de Cela i conté una referència a una altra Cela.
 */
public class CelaRef extends Cela {
    /**
     * Conté la Cela que s'està referenciant
     */
    private Cela celaReferenciada;

    /**
     * Constructora principal
     * @param inputUsuari input inicial que es rep de l'usuari
     * @param celaRef Cela referenciada corresponent a l'input d'usuari
     */
    public CelaRef(String inputUsuari, Cela celaRef) {
        super(inputUsuari);
        celaReferenciada = celaRef;
        tipusCela = TipusCela.REFERENCIAL;
    }

    /**
     * Setter de celaReferenciada
     * @param celaRef Cela a referenciar
     */
    public void setRef(Cela celaRef) {
        celaReferenciada = celaRef;
    }

    /**
     * Getter de celaReferenciada
     * @return la Cela a la que es referencia
     */
    public Cela getRef() {
        return celaReferenciada;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getNum() {
        return celaReferenciada.getNum();
    }

    @Override
    public LocalDate getData() {
        return celaReferenciada.getData();
    }

    @Override
    public String getText() {
        return celaReferenciada.getText();
    }

    @Override
    public String toString() {
        return celaReferenciada.toString();
    }

    @Override
    public Cela copy() {
        return new CelaRef(inputUsuari, celaReferenciada);
    }

    @Override
    public int compare(Cela c) {
        return celaReferenciada.compare(c);
    }

    @Override
    protected int compareType(Cela c) {
        throw new UnsupportedOperationException("compareType de CelaRef mai no pot ser cridat");
    }
}
