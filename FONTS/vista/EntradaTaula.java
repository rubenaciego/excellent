package vista;

/**
 * Entrada de la taula en que es mostra el contingut d'un full
 */
public class EntradaTaula {
    /**
     * índex de la fila que ocupa l'entrada
     */
    public int fila;
    /**
     * índex de la columna que ocupa l'entrada
     */
    public int columna;
    /**
     * Valor de l'entrada
     */
    public String valor;

    /**
     * Constructora principal
     * @param fila fila que ocupa l'entrada
     * @param columna columna que ocupa l'entrada
     * @param valor valor de l'entrada
     */
    public EntradaTaula(int fila, int columna, String valor)
    {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
    }
}
