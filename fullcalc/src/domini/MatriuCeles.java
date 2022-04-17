package domini;
import java.lang.*;
import java.util.Collection;
import java.util.TreeMap;

public class MatriuCeles {
    TreeMap<Integer, TreeMap<Integer, Cela>> matriuCela;

    public Cela obteCela(Integer fila, Integer col) {
        if (!matriuCela.containsKey(col)) {
            return null;
        } else {
            return matriuCela.get(col).get(fila);
        }
    }

    public void setCela(Cela novaCela, Integer fila, Integer col) {
        if (!matriuCela.containsKey(col)) {
            matriuCela.put(col, new TreeMap<Integer, Cela>());
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
