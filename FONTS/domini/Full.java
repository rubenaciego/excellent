package domini;

import java.util.Iterator;
import java.util.concurrent.*;

/**
 * Classe que hereda de MatriuCeles i és l’objecte de modificació principal de l’aplicació.
 */
public class Full extends MatriuCeles {
    /**
     * Cela especial que es pot usar per obtenir resultats que ocupin una sola Cela.
     */
    private Cela celaResultat;

    /**
     * Constructora per defecte
     */
    public Full() {
        super();
    }

    /**
     * Constructora principal
     * @param files número de files del nou Full
     * @param cols número de columnes del nou Full
     */
    public Full(int files, int cols) {
        super(files, cols);
    }

    /**
     * {@inheritDoc}
     */

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

    /**
     * Esborra totes les entrades del Full contingudes al bloc definit per la Cela superior esquerra indexada per (filaIni, colIni) i que té numFiles files i numCols columnes.
     * @param filaIni fila de la Cela superior esquerra del bloc
     * @param colIni columna de la Cela superior esquerra del bloc
     * @param numFiles número de files del bloc
     * @param numCols número de columnes del bloc
     */
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

    /**
     * Copia totes les entrades del Full contingudes al bloc definit per la Cela superior esquerra indexada per (filaIni, colIni) i que té numFiles files i numCols columnes.
     * El destí de les entrades copiades és el bloc definit per la Cela superior esquerra indexada per (filaFi, colFi) i que té numFiles files i numCols columnes.
     * @param filaIni fila de la Cela superior esquerra del bloc inicial
     * @param colIni columna de la Cela superior esquerra del bloc inicial
     * @param numFiles número de files del bloc
     * @param numCols número de columnes del bloc
     * @param filaFi fila de la Cela superior esquerra del bloc final
     * @param colFi columna de la Cela superior esquerra del bloc final
     */
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
                setCela(c.copy(), en.getKey() + chFila, enSkipList.getKey() + chCol);
            }
        }
    }

    /**
     * Mou totes les entrades del Full contingudes al bloc definit per la Cela superior esquerra indexada per (filaIni, colIni) i que té numFiles files i numCols columnes.
     * El destí de les entrades copiades és el bloc definit per la Cela superior esquerra indexada per (filaFi, colFi) i que té numFiles files i numCols columnes.
     * @param filaIni fila de la Cela superior esquerra del bloc inicial
     * @param colIni columna de la Cela superior esquerra del bloc inicial
     * @param numFiles número de files del bloc
     * @param numCols número de columnes del bloc
     * @param filaFi fila de la Cela superior esquerra del bloc final
     * @param colFi columna de la Cela superior esquerra del bloc final
     */
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

    @Override
    public boolean blocInvalid(int filaIni, int colIni, int numFiles, int numCols) {
        if (filaIni == -1 && colIni == -1 && numFiles <= 1 && numCols <= 1) return false;
        return super.blocInvalid(filaIni, colIni, numFiles, numCols);
    }
}
