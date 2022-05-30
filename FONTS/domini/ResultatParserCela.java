package domini;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Resultat de la decodificació en el parser d'una operació sobre una cel·la
 */
public class ResultatParserCela {
    /**
     * Input efectuat per l'usuari del contingut de la cel·la
     */
    String inputUsuari;
    /**
     * Valor numèric de la cel·la, si escau
     */
    double valorNumeric;
    /**
     * Data corresponent al valor de la cel·la, si escau
     */
    LocalDate data;
    /**
     * Fila de la cel·la a la qual referencia la cel·la, si escau
     */
    int filaRef;
    /**
     * Columna de la cel·la a la qual referencia la cel·la, si escau
     */
    int colRef;
    /**
     * Tipus de la cel·la
     */
    Cela.TipusCela tipus;

    /**
     * Getter del input de l'usuari de la cel·la
     * @return l'input de l'usuari de la cel·la
     */
    public String getInputUsuari() {
        return inputUsuari;
    }

    /**
     * Setter del input de l'usuari de la cel·la
     * @param inputUsuari input de l'usuari de la cel·la
     */
    public void setInputUsuari(String inputUsuari) {
        this.inputUsuari = inputUsuari;
    }

    /**
     * Getter del valor numèric de la cel·la
     * @return valor numèric de la cel·la
     */
    public double getValorNumeric() {
        return valorNumeric;
    }

    /**
     * Setter del valor numèric de la cel·la
     * @param valorNumeric valor numèric de la cel·la
     */
    public void setValorNumeric(Double valorNumeric) {
        this.valorNumeric = valorNumeric;
    }

    /**
     * Getter de la data de la cel·la
     * @return data de la cel·la
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Setter de la data de la cel·la
     * @param data data de la cel·la
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Getter del valor de la fila de la cel·la a la qual referencia la cel·la
     * @return fila de la cel·la a la qual referencia la cel·la
     */
    public int getFilaRef() {
        return filaRef;
    }

    /**
     * Setter del valor de la fila de la cel·la a la qual referencia la cel·la
     * @param filaRef fila de la cel·la a la qual referencia la cel·la
     */
    public void setFilaRef(Integer filaRef) {
        this.filaRef = filaRef;
    }

    /**
     * Getter del valor de la columna de la cel·la a la qual referencia la
     * cel·la
     * @return fila de la cel·la a la qual referencia la cel·la
     */
    public int getColRef() {
        return colRef;
    }

    /**
     * Setter del valor de la columna de la cel·la a la qual referencia la
     * cel·la
     * @param colRef columna de la cel·la a la qual referencia la cel·la
     */
    public void setColRef(Integer colRef) {
        this.colRef = colRef;
    }

    /**
     * Getter del tipus de la cel·la
     * @return tipus de la cel·la
     */
    public Cela.TipusCela getTipus() {
        return tipus;
    }

    /**
     * Setter del tipus de la cel·la
     * @param tipus tipus de la cel·la
     */
    public void setTipus(Cela.TipusCela tipus) {
        this.tipus = tipus;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof ResultatParserCela)) {
            return false;
        }

        ResultatParserCela c = (ResultatParserCela) o;

        return (inputUsuari == c.inputUsuari && valorNumeric == c.valorNumeric &&
                Objects.equals(data, c.data) && colRef == c.colRef &&
                String.valueOf(tipus) == String.valueOf(c.tipus));
    }
}
