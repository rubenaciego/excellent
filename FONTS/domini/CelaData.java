package domini;

import java.lang.*;
import java.time.LocalDate;
import java.util.HashSet;

/**
 * Classe que hereda de Cela i conté una data
 */
public class CelaData extends Cela {
    /**
     * Conté la data corresponent a l’input de l’usuari i les seves modificacions.
     */
    private LocalDate data;

    /**
     * Constructora principal
     * @param inputUsuari input inicial que es rep de l'usuari
     * @param dataInput data corresponent a l'input d'usuari
     */
    public CelaData(String inputUsuari, LocalDate dataInput) {
        super(inputUsuari);
        data = dataInput;
        tipusCela = TipusCela.DATADA;
    }

    /**
     * Setter de data
     * @param inputUsuari input de l'usuari corresponent a dataInput en forma de String
     * @param dataInput data que l'usuari proporciona
     */
    public void setData(String inputUsuari, LocalDate dataInput) {
        super.setInputUsuari(inputUsuari);
        data = dataInput;
        tipusCela = TipusCela.DATADA;
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
        return data;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public Cela copy() {
        return new CelaData(inputUsuari, data);
    }

    @Override
    protected int compareType(Cela c) {
        LocalDate d = c.getData();
        return data.compareTo(d);
    }
}
