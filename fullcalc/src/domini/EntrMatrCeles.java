package domini;

public class EntrMatrCeles {
    private Integer fila;
    private Integer columna;
    private Cela c;

    public EntrMatrCeles(Integer fila, Integer columna, Cela c) {
        this.fila = fila;
        this.columna = columna;
        this.c = c;
    }

    public Integer obteFila() {
        return fila;
    }

    public Integer obteCol() {
        return columna;
    }

    public Cela obteCela() {
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
