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
            if (col == numCols-1) {
                Integer maxim = 0;
                for (Iterator<ConcurrentSkipListMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>>> i = matriuCela.descendingMap().entrySet().iterator();
                     i.hasNext(); ) {
                    ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList = i.next();
                    if (!enSkipList.getValue().isEmpty()) {
                        maxim = enSkipList.getKey();
                        break;
                    }
                }
                numCols = maxim;
            }
            if (fila == numFiles-1) {
                Integer maxim = 0;
                for (ConcurrentSkipListMap<Integer, Cela> SL: matriuCela.values()) {
                    maxim = Math.max(maxim, SL.lastKey());
                }
                numFiles = maxim;
            }
        }
    }

    public ArrayList<EntrMatrCeles> obteEntrades() {
        ArrayList<EntrMatrCeles> entrades = new ArrayList<EntrMatrCeles>();

        for (ConcurrentSkipListMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> SLi: matriuCela.entrySet()) {
            for (ConcurrentSkipListMap.Entry<Integer, Cela> SLj: SLi.getValue().entrySet()) {
                EntrMatrCeles entry = new EntrMatrCeles(SLi.getKey(), SLj.getKey(), SLj.getValue());
                entrades.add(entry);
            }
        }
        return entrades;
    }

    public ArrayList<EntrMatrCeles> obteEntradesColumna(int col) {
        ArrayList<EntrMatrCeles> entrades = new ArrayList<EntrMatrCeles>();

        ConcurrentSkipListMap<Integer, Cela> SL = matriuCela.get(col);
        for (ConcurrentSkipListMap.Entry<Integer, Cela> SLi: SL.entrySet()) {
            EntrMatrCeles entry = new EntrMatrCeles(col, SLi.getKey(), SLi.getValue());
            entrades.add(entry);
        }
        return entrades;
    }
}
