package domini;

import org.junit.Test;
import java.util.Iterator;
import java.util.concurrent.*;

public class Full extends MatriuCeles {
    private Integer id;
    private Cela celaResultat;

    public Full(Integer id) {
        this.id = id;
        super.numCols = 0;
        super.numFiles = 0;
    }

    public Full(Integer id, Integer files, Integer cols) {
        this.id = id;
        super.numCols = cols;
        super.numFiles = files;
    }

    public Integer obteId() {
        return id;
    }

    public Cela obteCelaResultat() {
        return celaResultat;
    }

    public Errors modificaCela(Integer fila, Integer col, ContingutCelaModificada celaMod) {
        Cela c;
        switch(celaMod.getTipus()) {
            case NUMERICA:
                c = new CelaNum(celaMod.getInputUsuari(), celaMod.getValorNumeric());
                break;
            case DATADA:
                c = new CelaData(celaMod.getInputUsuari(), celaMod.getData());
                break;
            case REFERENCIAL:
                Cela cRef = super.obteCela(celaMod.getFilaRef(), celaMod.getColRef());
                c = new CelaRef(celaMod.getInputUsuari(),  cRef);
                break;
            default:
                c = new CelaText(celaMod.getInputUsuari());
                break;
        }

        if (fila == -1 && col == -1) celaResultat = c;
        else if (fila < 0 || fila >= super.numFiles || col < 0 || col >= super.numCols) {
            return Errors.NOEXISTEIX;
        } else super.setCela(c, fila, col);

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
        if (fila >= super.numFiles || fila < 0) return Errors.NOEXISTEIX;

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
                    super.setCela(c, en.getKey()-1, enSkipList.getKey());
                }
            }
        }

        return Errors.NOERROR;
    }

    public Errors eliminaColumna(Integer col) {
        if (col >= super.numCols || col < 0) return Errors.NOEXISTEIX;

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
                super.setCela(c, en.getKey(), enSkipList.getKey()-1);
            }
        }

        return Errors.NOERROR;
    }

    public Errors buidaBloc(Integer filaIni, Integer colIni, Integer numFiles, Integer numCols) {
        if (filaIni < 0 || filaIni+numFiles-1 >= super.numFiles) return Errors.FORALIMITSBLOC;
        if (colIni < 0 || colIni+numCols-1 >= super.numCols) return Errors.FORALIMITSBLOC;

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
        if (filaIni < 0 || filaIni+numFiles-1 >= super.numFiles) return Errors.FORALIMITSBLOC;
        if (colIni < 0 || colIni+numCols-1 >= super.numCols) return Errors.FORALIMITSBLOC;
        if (filaFi < 0 || filaFi+numFiles-1 >= super.numFiles) return Errors.FORALIMITSBLOC;
        if (colFi < 0 || colFi+numCols-1 >= super.numCols) return Errors.FORALIMITSBLOC;

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
                super.setCela(c, en.getKey()+chFila, enSkipList.getKey()+chCol);
            }
        }

        return Errors.NOERROR;
    }

    public Errors mouBloc(Integer filaIni, Integer colIni, Integer numFiles, Integer numCols, Integer filaFi, Integer colFi) {
        if (filaIni < 0 || filaIni+numFiles-1 >= super.numFiles) return Errors.FORALIMITSBLOC;
        if (colIni < 0 || colIni+numCols-1 >= super.numCols) return Errors.FORALIMITSBLOC;
        if (filaFi < 0 || filaFi+numFiles-1 >= super.numFiles) return Errors.FORALIMITSBLOC;
        if (colFi < 0 || colFi+numCols-1 >= super.numCols) return Errors.FORALIMITSBLOC;

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
                super.setCela(c, en.getKey()+chFila, enSkipList.getKey()+chCol);
            }
        }

        return Errors.NOERROR;
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
