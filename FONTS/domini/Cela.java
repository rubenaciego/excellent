package domini;

import java.lang.*;
import java.time.LocalDate;
import java.util.HashSet;

/**
 * Classe abstracta que constitueix la mínima contenidora d’informació.
 */
public abstract class Cela {
    public enum TipusCela {
        NUMERICA,
        TEXTUAL,
        DATADA,
        REFERENCIAL
    }

    /**
     * Conté l’input inicial de l’usuari, més posteriors modificacions que aquest indica
     */
    protected String inputUsuari;
    /**
     * Conté de quin subtipus és la Cela
     */
    protected TipusCela tipusCela;
    /**
     * Conjunt de Cela que referencien la Cela actual
     */
    protected HashSet<CelaRef> referenciadores;

    /**
     * Constructora principal
     * @param inputUsuari input inicial que es rep de l'usuari
     */
    public Cela(String inputUsuari)
    {
        this.inputUsuari = inputUsuari;
        referenciadores = new HashSet<CelaRef>();
    }

    /**
     * Getter de inputUsuari
     * @return String que conté l'input actual de l'usuari
     */
    public String getInputUsuari() {
        return inputUsuari;
    }

    /**
     * Setter de inputUsuari
     * @param inputUsuari input nou que es rep de l'usuari
     */
    public void setInputUsuari(String inputUsuari) {
        this.inputUsuari = inputUsuari;
    }

    /**
     * Getter de tipusCela
     * @return TipusCela que conté el tipus de la Cela
     */
    public TipusCela getTipusCela() {
        return tipusCela;
    }

    /**
     * Getter de les Cela que referencien a l'actual
     * @return El conjunt de cel·les que referencien la Cela actual
     */
    public HashSet<CelaRef> getCelesReferenciadores() {
        return referenciadores;
    }

    /**
     * Getter del valor numèric de la Cela
     * @return El valor numèric de la Cela si és una CelaNum, sinó null.
     */
    public abstract Double getNum();

    /**
     * Getter de la data de la Cela
     * @return La data de la Cela si és una CelaData, sinó null.
     */
    public abstract LocalDate getData();

    /**
     * Getter del text de la Cela
     * @return El text de la Cela si és una CelaText, sinó null.
     */
    public abstract String getText();

    /**
     * Copiadora de la Cela
     * @return Deep copy de la Cela
     */
    public abstract Cela copy();

    /**
     * Comparadora de la Cela actual amb la Cela que es passa com a paràmetre
     * @param c Cela a comparar
     * @return Un enter positiu si l'actual és major que c, 0 si el contingut és el mateix i un enter negatiu si c és major que l'actual
     */
    public int compare(Cela c) {
        if (c.tipusCela == TipusCela.REFERENCIAL)
            return -c.compare(this);

        if (tipusCela != c.tipusCela)
            return tipusCela.compareTo(c.tipusCela);
        else return compareType(c);
    }

    /**
     * Comparadora per a Cela del mateix tipus
     * @param c Cela amb qui comparar
     * @return Un enter positiu si l'actual és major que c, 0 si el contingut és el mateix i un enter negatiu si c és major que l'actual
     */
    protected abstract int compareType(Cela c);
}
