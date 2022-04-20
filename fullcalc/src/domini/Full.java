package domini;

import java.util.Iterator;
import java.util.concurrent.*;

public class Full extends MatriuCeles {
    private Cela celaResultat;

    public Full() {
        super.numCols = 0;
        super.numFiles = 0;
    }

    public Full(Integer id, Integer files, Integer cols) {
        super.numCols = cols;
        super.numFiles = files;
    }

    @Override
    public Cela getCela(int fila, int col) {
        if (fila == -1 && col == -1) return celaResultat;
        else return super.getCela(fila, col);
    }

    @Override
    public void setCela(Cela novaCela, int fila, int col) {
        if (fila == -1 && col == -1) celaResultat = novaCela;
        else super.setCela(novaCela, fila, col);
    }

    public Error afegeixFila() {
        ++super.numFiles;
        return Error.NO_ERROR;
    }

    public Error afegeixColumna() {
        ++super.numCols;
        return Error.NO_ERROR;
    }

    public Error eliminaFila(Integer fila) {
        if (fila >= super.numFiles || fila < 0) return Error.NO_EXISTEIX;

        for (ConcurrentSkipListMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList : super.matriuCela.entrySet()) {
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

        return Error.NO_ERROR;
    }

    public Error eliminaColumna(Integer col) {
        if (col >= super.numCols || col < 0) return Error.NO_EXISTEIX;

        if (super.matriuCela.containsKey(col)) {
            super.matriuCela.get(col).clear();
        }
        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> tail = super.matriuCela.tailMap(col);

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList : tail.entrySet()) {
            ConcurrentSkipListMap<Integer, Cela> T = enSkipList.getValue();
            for (Iterator<ConcurrentSkipListMap.Entry<Integer, Cela>> j = T.entrySet().iterator(); j.hasNext(); ) {
                ConcurrentSkipListMap.Entry<Integer, Cela> en = j.next();
                Cela c = en.getValue();
                j.remove();
                if (!super.matriuCela.containsKey(enSkipList.getKey() - 1)) {
                    super.matriuCela.put(enSkipList.getKey() - 1, new ConcurrentSkipListMap<Integer, Cela>());
                }
                super.setCela(c, en.getKey(), enSkipList.getKey() - 1);
            }
        }

        return Error.NO_ERROR;
    }

    public Error buidaBloc(Integer filaIni, Integer colIni, Integer numFiles, Integer numCols) {
        if (filaIni < 0 || filaIni+numFiles-1 >= super.numFiles) return Error.FORA_LIMITS_BLOC;
        if (colIni < 0 || colIni+numCols-1 >= super.numCols) return Error.FORA_LIMITS_BLOC;

        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = super.matriuCela.subMap(colIni, colIni+numCols);

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList : subSL.entrySet()) {
            ConcurrentSkipListMap<Integer, Cela> SL = enSkipList.getValue();
            ConcurrentNavigableMap<Integer, Cela> subsubSL = SL.subMap(filaIni, filaIni + numFiles);
            subsubSL.clear();
        }

        return Error.NO_ERROR;
    }

    public Error copiaBloc(Integer filaIni, Integer colIni, Integer numFiles, Integer numCols, Integer filaFi, Integer colFi) {
        if (filaIni < 0 || filaIni+numFiles-1 >= super.numFiles) return Error.FORA_LIMITS_BLOC;
        if (colIni < 0 || colIni+numCols-1 >= super.numCols) return Error.FORA_LIMITS_BLOC;
        if (filaFi < 0 || filaFi+numFiles-1 >= super.numFiles) return Error.FORA_LIMITS_BLOC;
        if (colFi < 0 || colFi+numCols-1 >= super.numCols) return Error.FORA_LIMITS_BLOC;

        Integer chFila = filaFi-filaIni;
        Integer chCol = colFi-colIni;

        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = super.matriuCela.subMap(colIni, colIni+numCols);
        if (colIni < colFi || (colIni == colFi && filaIni < filaFi)) subSL  = subSL.descendingMap();

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList : subSL.entrySet()) {
            ConcurrentSkipListMap<Integer, Cela> T = enSkipList.getValue();
            ConcurrentNavigableMap<Integer, Cela> subsubSL = T.subMap(filaIni, filaIni + numFiles);

            if (colIni < colFi || (colIni == colFi && filaIni < filaFi)) subsubSL = subsubSL.descendingMap();
            for (ConcurrentSkipListMap.Entry<Integer, Cela> en : subsubSL.entrySet()) {
                Cela c = en.getValue();
                if (!super.matriuCela.containsKey(enSkipList.getKey() + chCol)) {
                    super.matriuCela.put(enSkipList.getKey() + chCol, new ConcurrentSkipListMap<Integer, Cela>());
                }
                super.setCela(c, en.getKey() + chFila, enSkipList.getKey() + chCol);
            }
        }

        return Error.NO_ERROR;
    }

    public Error mouBloc(Integer filaIni, Integer colIni, Integer numFiles, Integer numCols, Integer filaFi, Integer colFi) {
        if (filaIni < 0 || filaIni+numFiles-1 >= super.numFiles) return Error.FORA_LIMITS_BLOC;
        if (colIni < 0 || colIni+numCols-1 >= super.numCols) return Error.FORA_LIMITS_BLOC;
        if (filaFi < 0 || filaFi+numFiles-1 >= super.numFiles) return Error.FORA_LIMITS_BLOC;
        if (colFi < 0 || colFi+numCols-1 >= super.numCols) return Error.FORA_LIMITS_BLOC;

        Integer chFila = filaFi-filaIni;
        Integer chCol = colFi-colIni;

        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = super.matriuCela.subMap(colIni, colIni+numCols);
        if (colIni < colFi || (colIni == colFi && filaIni < filaFi)) subSL  = subSL.descendingMap();

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList : subSL.entrySet()) {
            ConcurrentSkipListMap<Integer, Cela> T = enSkipList.getValue();
            ConcurrentNavigableMap<Integer, Cela> subsubSL = T.subMap(filaIni, filaIni + numFiles);

            if (colIni < colFi || (colIni == colFi && filaIni < filaFi)) subsubSL = subsubSL.descendingMap();
            for (Iterator<ConcurrentNavigableMap.Entry<Integer, Cela>> j = subsubSL.entrySet().iterator(); j.hasNext(); ) {
                ConcurrentSkipListMap.Entry<Integer, Cela> en = j.next();
                Cela c = en.getValue();
                j.remove();
                if (!super.matriuCela.containsKey(enSkipList.getKey() + chCol)) {
                    super.matriuCela.put(enSkipList.getKey() + chCol, new ConcurrentSkipListMap<Integer, Cela>());
                }
                super.setCela(c, en.getKey() + chFila, enSkipList.getKey() + chCol);
            }
        }

        return Error.NO_ERROR;
    }

    //potser alguna excepcio
    public MatriuCeles obteBloc(Integer filaIni, Integer colIni, Integer numFiles, Integer numCols) {
        if (filaIni < 0 || filaIni+numFiles-1 >= super.numFiles) return null;
        if (colIni < 0 || colIni+numCols-1 >= super.numCols) return null;

        MatriuCeles bloc = new MatriuCeles();
        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = super.matriuCela.subMap(colIni, colIni+numCols);

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> SLi: subSL.entrySet()) {
            for (ConcurrentSkipListMap.Entry<Integer, Cela> SLj: SLi.getValue().entrySet()) {
                Cela c = SLj.getValue();
                bloc.setCela(c, SLi.getKey(), SLj.getKey());
            }
        }

        return bloc;
    }
}
