package domini;

public class EntradaMatriuCeles {
    private int fila;
    private int columna;
    private Cela c;

    public EntradaMatriuCeles(int fila, int columna, Cela c) {
        this.fila = fila;
        this.columna = columna;
        this.c = c;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Cela getCela() {
        return c;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public void setColumna(Integer columna) {
        this.columna = columna;
    }

    public void setCela(Cela c) {
        this.c = c;
    }
}
