package vista;

public class SeleccioTaula {
    public int fila;
    public int col;
    public int nfiles;
    public int ncols;

    public SeleccioTaula(int f, int c, int nf, int nc) {
        fila = f;
        col = c;
        nfiles = nf;
        ncols = nc;
    }

    public boolean empty() {
        return nfiles == 0 || ncols == 0 || fila == -1 || col == -1;
    }
}
