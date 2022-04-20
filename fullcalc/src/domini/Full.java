package domini;

import java.util.Iterator;
import java.util.concurrent.*;

public class Full extends MatriuCeles
{
    private Cela celaResultat;

    public Full()
    {
        numCols = 0;
        numFiles = 0;
    }

    public Full(int files, int cols)
    {
        numCols = cols;
        numFiles = files;
    }

    @Override
    public Cela getCela(int fila, int col)
    {
        if (fila == -1 && col == -1) return celaResultat;
        else return super.getCela(fila, col);
    }

    @Override
    public void setCela(Cela novaCela, int fila, int col)
    {
        if (fila == -1 && col == -1) celaResultat = novaCela;
        else super.setCela(novaCela, fila, col);
    }

    public void afegeixFila()
    {
        ++numFiles;
    }

    public void afegeixColumna()
    {
        ++numCols;
    }

    public void eliminaFila(int fila)
    {
        if (fila >= numFiles || fila < 0)
            throw new ExcepcioFilaColumnaInvalida(fila, numFiles);

        for (ConcurrentSkipListMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList : matriuCela.entrySet()) {
            ConcurrentSkipListMap<Integer, Cela> SL = enSkipList.getValue();
            ConcurrentNavigableMap<Integer, Cela> tail = SL.tailMap(fila);
            for (Iterator<ConcurrentNavigableMap.Entry<Integer, Cela>> j = tail.entrySet().iterator(); j.hasNext(); ) {
                ConcurrentNavigableMap.Entry<Integer, Cela> en = j.next();
                Cela c = en.getValue();
                j.remove();
                if (!(en.getKey() == fila)) {
                    super.setCela(c, en.getKey() - 1, enSkipList.getKey());
                }
            }
        }

        --numFiles;
    }

    public void eliminaColumna(int col)
    {
        if (col >= numCols || col < 0)
            throw new ExcepcioFilaColumnaInvalida(col, numCols);

        if (matriuCela.containsKey(col)) {
            matriuCela.get(col).clear();
        }
        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> tail = matriuCela.tailMap(col);

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList : tail.entrySet()) {
            ConcurrentSkipListMap<Integer, Cela> T = enSkipList.getValue();
            for (Iterator<ConcurrentSkipListMap.Entry<Integer, Cela>> j = T.entrySet().iterator(); j.hasNext(); ) {
                ConcurrentSkipListMap.Entry<Integer, Cela> en = j.next();
                Cela c = en.getValue();
                j.remove();
                if (!matriuCela.containsKey(enSkipList.getKey() - 1)) {
                    matriuCela.put(enSkipList.getKey() - 1, new ConcurrentSkipListMap<Integer, Cela>());
                }
                super.setCela(c, en.getKey(), enSkipList.getKey() - 1);
            }
        }

        --numCols;
    }

    public void buidaBloc(int filaIni, int colIni, int numFiles, int numCols)
    {
        if (blocInvalid(filaIni, colIni, numFiles, numCols))
            throw new ExcepcioForaLimits(filaIni, colIni, numFiles, numCols, this.numFiles, this.numCols);

        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = matriuCela.subMap(colIni, colIni + numCols);

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList : subSL.entrySet()) {
            ConcurrentSkipListMap<Integer, Cela> SL = enSkipList.getValue();
            ConcurrentNavigableMap<Integer, Cela> subsubSL = SL.subMap(filaIni, filaIni + numFiles);
            subsubSL.clear();
        }
    }

    public void copiaBloc(int filaIni, int colIni, int numFiles, int numCols, int filaFi, int colFi)
    {
        if (blocInvalid(filaIni, colIni, numFiles, numCols))
            throw new ExcepcioForaLimits(filaIni, colIni, numFiles, numCols, this.numFiles, this.numCols);

        if (blocInvalid(filaFi, colFi, numFiles, numCols))
            throw new ExcepcioForaLimits(filaFi, colFi, numFiles, numCols, this.numFiles, this.numCols);

        int chFila = filaFi - filaIni;
        int chCol = colFi - colIni;

        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = matriuCela.subMap(colIni, colIni + numCols);
        if (colIni < colFi || (colIni == colFi && filaIni < filaFi)) subSL = subSL.descendingMap();

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList : subSL.entrySet()) {
            ConcurrentSkipListMap<Integer, Cela> T = enSkipList.getValue();
            ConcurrentNavigableMap<Integer, Cela> subsubSL = T.subMap(filaIni, filaIni + numFiles);

            if (colIni < colFi || (colIni == colFi && filaIni < filaFi)) subsubSL = subsubSL.descendingMap();
            for (ConcurrentSkipListMap.Entry<Integer, Cela> en : subsubSL.entrySet()) {
                Cela c = en.getValue();
                if (!matriuCela.containsKey(enSkipList.getKey() + chCol)) {
                    matriuCela.put(enSkipList.getKey() + chCol, new ConcurrentSkipListMap<Integer, Cela>());
                }
                super.setCela(c, en.getKey() + chFila, enSkipList.getKey() + chCol);
            }
        }
    }

    public void mouBloc(int filaIni, int colIni, int numFiles, int numCols, int filaFi, int colFi)
    {
        if (blocInvalid(filaIni, colIni, numFiles, numCols))
            throw new ExcepcioForaLimits(filaIni, colIni, numFiles, numCols, this.numFiles, this.numCols);

        if (blocInvalid(filaFi, colFi, numFiles, numCols))
            throw new ExcepcioForaLimits(filaFi, colFi, numFiles, numCols, this.numFiles, this.numCols);

        int chFila = filaFi - filaIni;
        int chCol = colFi - colIni;

        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = matriuCela.subMap(colIni, colIni + numCols);
        if (colIni < colFi || (colIni == colFi && filaIni < filaFi)) subSL = subSL.descendingMap();

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList : subSL.entrySet()) {
            ConcurrentSkipListMap<Integer, Cela> T = enSkipList.getValue();
            ConcurrentNavigableMap<Integer, Cela> subsubSL = T.subMap(filaIni, filaIni + numFiles);

            if (colIni < colFi || (colIni == colFi && filaIni < filaFi)) subsubSL = subsubSL.descendingMap();
            for (Iterator<ConcurrentNavigableMap.Entry<Integer, Cela>> j = subsubSL.entrySet().iterator(); j.hasNext(); ) {
                ConcurrentSkipListMap.Entry<Integer, Cela> en = j.next();
                Cela c = en.getValue();
                j.remove();
                if (!matriuCela.containsKey(enSkipList.getKey() + chCol)) {
                    matriuCela.put(enSkipList.getKey() + chCol, new ConcurrentSkipListMap<Integer, Cela>());
                }
                super.setCela(c, en.getKey() + chFila, enSkipList.getKey() + chCol);
            }
        }
    }

    public MatriuCeles obteBloc(int filaIni, int colIni, int numFiles, int numCols)
    {
        if (blocInvalid(filaIni, colIni, numFiles, numCols))
            throw new ExcepcioForaLimits(filaIni, colIni, numFiles, numCols, this.numFiles, this.numCols);

        MatriuCeles bloc = new MatriuCeles();
        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = super.matriuCela.subMap(colIni, colIni + numCols);

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> SLi : subSL.entrySet()) {
            for (ConcurrentSkipListMap.Entry<Integer, Cela> SLj : SLi.getValue().entrySet()) {
                Cela c = SLj.getValue();
                bloc.setCela(c, SLi.getKey(), SLj.getKey());
            }
        }

        return bloc;
    }

    private boolean blocInvalid(int filaIni, int colIni, int numFiles, int numCols)
    {
        if (filaIni < 0 || filaIni + numFiles - 1 >= this.numFiles) return true;
        return colIni < 0 || colIni + numCols - 1 >= this.numCols;
    }
}
