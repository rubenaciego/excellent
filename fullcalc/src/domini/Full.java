package domini;

import java.util.Iterator;
import java.util.concurrent.*;

public class Full extends MatriuCeles {
    private Cela celaResultat;

    public Full() {
        super();
    }

    public Full(int files, int cols) {
        super(files, cols);
    }

    @Override
    public Cela getCela(int fila, int col) {
        if (fila == -1 && col == -1) return celaResultat;
        else return super.getCela(fila, col);
    }

    @Override
    public void setCela(Cela novaCela, int fila, int col) {
        if (fila == -1 && col == -1 && novaCela != null) celaResultat = novaCela;
        else super.setCela(novaCela, fila, col);
    }

    public void buidaBloc(int filaIni, int colIni, int numFiles, int numCols) {
        if (blocInvalid(filaIni, colIni, numFiles, numCols))
            throw new ExcepcioForaLimits(filaIni, colIni, numFiles, numCols, this.numFiles, this.numCols);

        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = matriuCela.subMap(colIni, colIni + numCols);

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList : subSL.entrySet()) {
            ConcurrentSkipListMap<Integer, Cela> SL = enSkipList.getValue();
            ConcurrentNavigableMap<Integer, Cela> subsubSL = SL.subMap(filaIni, filaIni + numFiles);
            subsubSL.clear();
        }
    }

    //Mirar si s'ha de fer copy express
    public void copiaBloc(int filaIni, int colIni, int numFiles, int numCols, int filaFi, int colFi) {
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
                setCela(c, en.getKey() + chFila, enSkipList.getKey() + chCol);
            }
        }
    }

    public void mouBloc(int filaIni, int colIni, int numFiles, int numCols, int filaFi, int colFi) {
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
                setCela(c, en.getKey() + chFila, enSkipList.getKey() + chCol);
            }
        }
    }
}
