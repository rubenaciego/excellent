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
        matriuCela = new ConcurrentSkipListMap<Integer, ConcurrentSkipListMap<Integer, Cela>>();
        numFiles = 0;
        numCols = 0;
    }

    public MatriuCeles(int numFiles, int numCols) {
        matriuCela = new ConcurrentSkipListMap<Integer, ConcurrentSkipListMap<Integer, Cela>>();
        this.numCols = numCols;
        this.numFiles = numFiles;
    }

    public int getNumFiles() {
        return numFiles;
    }

    public int getNumCols() {
        return numCols;
    }

    public Cela getCela(int fila, int col) {
        if (blocInvalid(fila, col, 1, 1))
            throw new ExcepcioForaLimits(fila, col, 1, 1, this.numFiles, this.numCols);

        if (!matriuCela.containsKey(col)) {
            return null;
        } else {
            return matriuCela.get(col).get(fila);
        }
    }

    public void setCela(Cela novaCela, int fila, int col) {
        if (blocInvalid(fila, col, 1, 1))
            throw new ExcepcioForaLimits(fila, col, 1, 1, this.numFiles, this.numCols);

        if (novaCela != null) {
            if (!matriuCela.containsKey(col))
                matriuCela.put(col, new ConcurrentSkipListMap<Integer, Cela>());

            matriuCela.get(col).put(fila, novaCela);
        }
    }

    public void esborraCela(int fila, int col) {
        if (blocInvalid(fila, col, 1, 1))
            throw new ExcepcioForaLimits(fila, col, 1, 1, this.numFiles, this.numCols);

        if (matriuCela.containsKey(col)) {
            matriuCela.get(col).remove(fila);
        }
    }

    public void afegeixFila() {
        ++numFiles;
    }

    public void afegeixColumna() {
        ++numCols;
    }

    public void eliminaFila(int fila) {
        if (fila >= numFiles || fila < 0)
            throw new ExcepcioFilaColumnaInvalida(fila, numFiles);

        for (ConcurrentSkipListMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList : matriuCela.entrySet()) {
            ConcurrentSkipListMap<Integer, Cela> SL = enSkipList.getValue();
            ConcurrentNavigableMap<Integer, Cela> tail = SL.tailMap(fila);
            for (Iterator<ConcurrentNavigableMap.Entry<Integer, Cela>> j = tail.entrySet().iterator(); j.hasNext(); ) {
                ConcurrentNavigableMap.Entry<Integer, Cela> en = j.next();
                Cela c = en.getValue();
                j.remove();

                if (!(en.getKey() == fila))
                    setCela(c, en.getKey() - 1, enSkipList.getKey());
            }
        }

        --numFiles;
    }

    public void eliminaColumna(int col) {
        if (col >= numCols || col < 0)
            throw new ExcepcioFilaColumnaInvalida(col, numCols);

        if (matriuCela.containsKey(col))
            matriuCela.get(col).clear();

        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> tail = matriuCela.tailMap(col);

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList : tail.entrySet()) {
            ConcurrentSkipListMap<Integer, Cela> T = enSkipList.getValue();
            for (Iterator<ConcurrentSkipListMap.Entry<Integer, Cela>> j = T.entrySet().iterator(); j.hasNext(); ) {
                ConcurrentSkipListMap.Entry<Integer, Cela> en = j.next();
                Cela c = en.getValue();
                j.remove();

                if (!matriuCela.containsKey(enSkipList.getKey() - 1))
                    matriuCela.put(enSkipList.getKey() - 1, new ConcurrentSkipListMap<Integer, Cela>());

                setCela(c, en.getKey(), enSkipList.getKey() - 1);
            }
        }

        --numCols;
    }

    public MatriuCeles getBloc(int filaIni, int colIni, int numFiles, int numCols) {
        if (blocInvalid(filaIni, colIni, numFiles, numCols))
            throw new ExcepcioForaLimits(filaIni, colIni, numFiles, numCols, this.numFiles, this.numCols);

        MatriuCeles bloc = new MatriuCeles(numFiles, numCols);
        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = matriuCela.subMap(colIni, colIni + numCols);

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> SLi : subSL.entrySet()) {
            for (ConcurrentSkipListMap.Entry<Integer, Cela> SLj : SLi.getValue().entrySet()) {
                Cela c = SLj.getValue();
                bloc.setCela(c, SLi.getKey() - filaIni, SLj.getKey() - colIni);
            }
        }

        return bloc;
    }

    public ArrayList<EntradaMatriuCeles> getEntrades() {
        ArrayList<EntradaMatriuCeles> entrades = new ArrayList<EntradaMatriuCeles>();

        for (ConcurrentSkipListMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> SLi : matriuCela.entrySet()) {
            for (ConcurrentSkipListMap.Entry<Integer, Cela> SLj : SLi.getValue().entrySet()) {
                EntradaMatriuCeles entry = new EntradaMatriuCeles(SLj.getKey(), SLi.getKey(), SLj.getValue());
                entrades.add(entry);
            }
        }
        return entrades;
    }

    public ArrayList<EntradaMatriuCeles> getEntradesColumna(int col) {
        if (col >= numCols || col < 0)
            throw new ExcepcioFilaColumnaInvalida(col, numCols);

        ArrayList<EntradaMatriuCeles> entrades = new ArrayList<EntradaMatriuCeles>();

        if (matriuCela.containsKey(col)) {
            ConcurrentSkipListMap<Integer, Cela> SL = matriuCela.get(col);
            for (ConcurrentSkipListMap.Entry<Integer, Cela> SLi : SL.entrySet()) {
                EntradaMatriuCeles entry = new EntradaMatriuCeles(SLi.getKey(), col, SLi.getValue());
                entrades.add(entry);
            }
        }
        return entrades;
    }

    protected boolean blocInvalid(int filaIni, int colIni, int numFiles, int numCols) {
        if (filaIni < 0 || filaIni + numFiles - 1 >= this.numFiles) return true;
        return colIni < 0 || colIni + numCols - 1 >= this.numCols;
    }
}