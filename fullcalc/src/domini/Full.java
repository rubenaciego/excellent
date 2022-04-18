package domini;

import java.util.Iterator;
import java.util.concurrent.*;

public class Full extends MatriuCeles {
    private Integer id;
    private Cela celaResultat;

    //Falta acabar aquesta funcio
    public Errors modificaCela() {
        return Errors.NOERROR;
    }

    public Errors afegeixFila() {
        ++super.numFiles;
        return Errors.NOERROR;
    }

    public Errors afegeixColumna() {
        ++super.numCols;
        return Errors.NOERROR;
    }

    public Errors eliminaFila(Integer fila) {
        if (fila > super.numFiles || fila < 0) return Errors.NOEXISTEIX;

        for (Iterator<ConcurrentSkipListMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>>> i = super.matriuCela.entrySet().iterator();
             i.hasNext(); ) {
            ConcurrentSkipListMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList = i.next();
            ConcurrentSkipListMap<Integer, Cela> SL = enSkipList.getValue();
            ConcurrentNavigableMap<Integer, Cela> tail = SL.tailMap(fila);
            for (Iterator<ConcurrentNavigableMap.Entry<Integer, Cela>> j = tail.entrySet().iterator(); j.hasNext(); ) {
                ConcurrentNavigableMap.Entry<Integer, Cela> en = j.next();
                Cela c = en.getValue();
                j.remove();
                if (!(en.getKey() == fila)) {
                    super.matriuCela.get(enSkipList.getKey()).put(en.getKey()-1, c);
                }
            }
        }

        return Errors.NOERROR;
    }

    public Errors eliminaColumna(Integer col) {
        if (col > super.numCols || col < 0) return Errors.NOEXISTEIX;

        if (super.matriuCela.containsKey(col)) {
            super.matriuCela.get(col).clear();
        }
        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> tail = super.matriuCela.tailMap(col);

        for (Iterator<ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>>> i = tail.entrySet().iterator(); i.hasNext(); ) {
            ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList = i.next();
            ConcurrentSkipListMap<Integer, Cela> T = enSkipList.getValue();
            for (Iterator<ConcurrentSkipListMap.Entry<Integer, Cela>> j = T.entrySet().iterator(); j.hasNext(); ) {
                ConcurrentSkipListMap.Entry<Integer, Cela> en = j.next();
                Cela c = en.getValue();
                j.remove();
                if (!super.matriuCela.containsKey(enSkipList.getKey()-1)) {
                    super.matriuCela.put(enSkipList.getKey()-1, new ConcurrentSkipListMap<Integer, Cela>());
                }
                super.matriuCela.get(enSkipList.getKey()-1).put(en.getKey(), c);
            }
        }

        return Errors.NOERROR;
    }

    public Errors buidaBloc(Integer filaIni, Integer colIni, Integer numFiles, Integer numCols) {
        if (filaIni < 0 || filaIni+numFiles-1 > super.numFiles) return Errors.FORALIMITSBLOC;
        if (colIni < 0 || colIni+numCols-1 > super.numCols) return Errors.FORALIMITSBLOC;

        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = super.matriuCela.subMap(colIni, colIni+numCols);

        for (Iterator<ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>>> i = subSL.entrySet().iterator(); i.hasNext(); ) {
            ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList = i.next();
            ConcurrentSkipListMap<Integer, Cela> SL = enSkipList.getValue();
            ConcurrentNavigableMap<Integer, Cela> subsubSL = SL.subMap(filaIni, filaIni+numFiles);
            subsubSL.clear();
        }

        return Errors.NOERROR;
    }

    public Errors copiaBloc(Integer filaIni, Integer colIni, Integer numFiles, Integer numCols, Integer filaFi, Integer colFi) {
        if (filaIni < 0 || filaIni+numFiles-1 > super.numFiles) return Errors.FORALIMITSBLOC;
        if (colIni < 0 || colIni+numCols-1 > super.numCols) return Errors.FORALIMITSBLOC;
        if (filaFi < 0 || filaFi+numFiles-1 > super.numFiles) return Errors.FORALIMITSBLOC;
        if (colFi < 0 || colFi+numCols-1 > super.numCols) return Errors.FORALIMITSBLOC;

        Integer chFila = filaFi-filaIni;
        Integer chCol = colFi-colIni;

        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = super.matriuCela.subMap(colIni, colIni+numCols);
        if (colIni < colFi || (colIni == colFi && filaIni < filaFi)) subSL  = subSL.descendingMap();

        for (Iterator<ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>>> i = subSL.entrySet().iterator(); i.hasNext(); ) {
            ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList = i.next();
            ConcurrentSkipListMap<Integer, Cela> T = enSkipList.getValue();
            ConcurrentNavigableMap<Integer, Cela> subsubSL = T.subMap(filaIni, filaIni+numFiles);

            if (colIni < colFi || (colIni == colFi && filaIni < filaFi)) subsubSL  = subsubSL.descendingMap();
            for (Iterator<ConcurrentNavigableMap.Entry<Integer, Cela>> j = subsubSL.entrySet().iterator(); j.hasNext(); ) {
                ConcurrentSkipListMap.Entry<Integer, Cela> en = j.next();
                Cela c = en.getValue();
                if (!super.matriuCela.containsKey(enSkipList.getKey()+chCol)) {
                    super.matriuCela.put(enSkipList.getKey()+chCol, new ConcurrentSkipListMap<Integer, Cela>());
                }
                super.matriuCela.get(enSkipList.getKey()+chCol).put(en.getKey()+chFila, c);
            }
        }

        return Errors.NOERROR;
    }

    public Errors mouBloc(Integer filaIni, Integer colIni, Integer numFiles, Integer numCols, Integer filaFi, Integer colFi) {
        if (filaIni < 0 || filaIni+numFiles-1 > super.numFiles) return Errors.FORALIMITSBLOC;
        if (colIni < 0 || colIni+numCols-1 > super.numCols) return Errors.FORALIMITSBLOC;
        if (filaFi < 0 || filaFi+numFiles-1 > super.numFiles) return Errors.FORALIMITSBLOC;
        if (colFi < 0 || colFi+numCols-1 > super.numCols) return Errors.FORALIMITSBLOC;

        Integer chFila = filaFi-filaIni;
        Integer chCol = colFi-colIni;

        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = super.matriuCela.subMap(colIni, colIni+numCols);
        if (colIni < colFi || (colIni == colFi && filaIni < filaFi)) subSL  = subSL.descendingMap();

        for (Iterator<ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>>> i = subSL.entrySet().iterator(); i.hasNext(); ) {
            ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> enSkipList = i.next();
            ConcurrentSkipListMap<Integer, Cela> T = enSkipList.getValue();
            ConcurrentNavigableMap<Integer, Cela> subsubSL = T.subMap(filaIni, filaIni+numFiles);

            if (colIni < colFi || (colIni == colFi && filaIni < filaFi)) subsubSL  = subsubSL.descendingMap();
            for (Iterator<ConcurrentNavigableMap.Entry<Integer, Cela>> j = subsubSL.entrySet().iterator(); j.hasNext(); ) {
                ConcurrentSkipListMap.Entry<Integer, Cela> en = j.next();
                Cela c = en.getValue();
                j.remove();
                if (!super.matriuCela.containsKey(enSkipList.getKey()+chCol)) {
                    super.matriuCela.put(enSkipList.getKey()+chCol, new ConcurrentSkipListMap<Integer, Cela>());
                }
                super.matriuCela.get(enSkipList.getKey()+chCol).put(en.getKey()+chFila, c);
            }
        }

        return Errors.NOERROR;
    }
}