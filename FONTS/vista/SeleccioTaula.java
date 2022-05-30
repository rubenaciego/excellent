package vista;

/**
 * Selecció en la taula que mostra el contingut d'un full
 */
public class SeleccioTaula {
    /**
     * Fila on s'inicia la selecció
     */
    public int fila;
    /**
     * Columna on s'inicia la selecció
     */
    public int col;
    /**
     * Nombre de files de la selecció
     */
    public int nfiles;
    /**
     * Nombre de columnes de la selecció
     */
    public int ncols;

    /**
     * Constructora principal
     * @param f fila on s'inicia la selecció
     * @param c columna on s'inicia la selecció
     * @param nf nombre de files de la selecció
     * @param nc nombre de columnes de la selecció
     */
    public SeleccioTaula(int f, int c, int nf, int nc) {
        fila = f;
        col = c;
        nfiles = nf;
        ncols = nc;
    }

    /**
     * Comprova si la selecció es buida
     * @return si la selecció es buida
     */
    public boolean empty() {
        return nfiles == 0 || ncols == 0 || fila == -1 || col == -1;
    }
}
