package domini;
import java.lang.*;
import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListMap;

public class MatriuCeles {
    protected Integer numFiles;
    protected Integer numCols;

    protected ConcurrentSkipListMap<Integer, ConcurrentSkipListMap<Integer, Cela>> matriuCela;

    public Cela obteCela(Integer fila, Integer col) {
        if (!matriuCela.containsKey(col)) {
            return null;
        } else {
            return matriuCela.get(col).get(fila);
        }
    }

    public void setCela(Cela novaCela, Integer fila, Integer col) {
        if (!matriuCela.containsKey(col)) {
            matriuCela.put(col, new ConcurrentSkipListMap<Integer, Cela>());
        }
        matriuCela.get(col).put(fila, novaCela);
    }

    public void esborraCela(Integer fila, Integer col) {
        if (matriuCela.containsKey(col)) {
            matriuCela.get(col).remove(fila);
        }
    }

    public Collection<Cela> obteValorsColumna(Integer col) {
        if (!matriuCela.containsKey(col)) {
            return null;
        } else {
            return matriuCela.get(col).values();
        }
    }
}
