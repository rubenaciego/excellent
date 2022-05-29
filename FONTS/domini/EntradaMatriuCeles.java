package domini;

/**
 * Classe pensada per albergar entrades d'una MatriuCeles
 */
public class EntradaMatriuCeles {
    /**
     * Número de fila de l’entrada en qüestió.
     */
    private int fila;
    /**
     * Número de fila de l’entrada en qüestió.
     */
    private int columna;
    /**
     * Cela corresponent a l’entrada en qüestió.
     */
    private Cela c;

    /**
     * Constructora principal
     * @param fila fila de l'entrada de MatriuCeles
     * @param columna columna de l'entrada de MatriuCeles
     * @param c Cela de l'entrada de MatriuCeles
     */
    public EntradaMatriuCeles(int fila, int columna, Cela c) {
        this.fila = fila;
        this.columna = columna;
        this.c = c;
    }

    /**
     * Getter de fila
     * @return la fila de l'entrada
     */
    public int getFila() {
        return fila;
    }

    /**
     * Getter de columna
     * @return la columna de l'entrada
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Getter de c
     * @return la Cela de l'entrada
     */
    public Cela getCela() {
        return c;
    }

    /**
     * Setter de fila
     * @param fila la nova fila de l'entrada
     */
    public void setFila(Integer fila) {
        this.fila = fila;
    }

    /**
     * Setter de columna
     * @param columna la nova columna de l'entrada
     */
    public void setColumna(Integer columna) {
        this.columna = columna;
    }

    /**
     * Setter de c
     * @param c la nova Cela de l'entrada
     */
    public void setCela(Cela c) {
        this.c = c;
    }
}
