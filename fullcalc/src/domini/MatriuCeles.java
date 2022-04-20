package domini;
import java.lang.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MatriuCeles {
    protected int numFiles;
    protected int numCols;

    protected ConcurrentSkipListMap<Integer, ConcurrentSkipListMap<Integer, Cela>> matriuCela;

    public MatriuCeles() {
        numFiles = 0;
        numCols = 0;
    }

    public MatriuCeles(int numFiles, int numCols) {
        this.numCols = numCols;
        this.numFiles = numFiles;
    }

    public Integer getNumFiles() {
        return numFiles;
    }

    public Integer getNumCols() {
        return numCols;
    }

    public Cela getCela(int fila, int col) {
        if (!matriuCela.containsKey(col)) {
            return null;
        } else {
            return matriuCela.get(col).get(fila);
        }
    }

    public void setCela(Cela novaCela, int fila, int col) {
        if (!matriuCela.containsKey(col)) {
            matriuCela.put(col, new ConcurrentSkipListMap<Integer, Cela>());
        }
        numFiles = Math.max(numFiles, fila);
        numCols = Math.max(numCols, col);
        matriuCela.get(col).put(fila, novaCela);
    }

    public void esborraCela(int fila, int col) {
        if (matriuCela.containsKey(col)) {
            matriuCela.get(col).remove(fila);
        }
    }

    public ArrayList<EntradaMatriuCeles> getEntrades() {
        ArrayList<EntradaMatriuCeles> entrades = new ArrayList<EntradaMatriuCeles>();

        for (ConcurrentSkipListMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> SLi: matriuCela.entrySet()) {
            for (ConcurrentSkipListMap.Entry<Integer, Cela> SLj: SLi.getValue().entrySet()) {
                EntradaMatriuCeles entry = new EntradaMatriuCeles(SLi.getKey(), SLj.getKey(), SLj.getValue());
                entrades.add(entry);
            }
        }
        return entrades;
    }

    public ArrayList<EntradaMatriuCeles> obteEntradesColumna(int col) {
        ArrayList<EntradaMatriuCeles> entrades = new ArrayList<EntradaMatriuCeles>();

        ConcurrentSkipListMap<Integer, Cela> SL = matriuCela.get(col);
        for (ConcurrentSkipListMap.Entry<Integer, Cela> SLi: SL.entrySet()) {
            EntradaMatriuCeles entry = new EntradaMatriuCeles(col, SLi.getKey(), SLi.getValue());
            entrades.add(entry);
        }
        return entrades;
    }
}
