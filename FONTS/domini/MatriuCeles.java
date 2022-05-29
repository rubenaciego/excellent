package domini;

import java.lang.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Conjunt d’objectes Cela indexades per fila i per columna
 */
public class MatriuCeles {
    /**
     *  Nombre de files la de la matriu
     */
    protected int numFiles;

    /**
     * Nombre de columnes de la matriu
     */
    protected int numCols;

    /**
     * Contenidor de totes les Cela de la matriu
     */
    protected ConcurrentSkipListMap<Integer, ConcurrentSkipListMap<Integer, Cela>> matriuCela;

    /**
     * Constructora per defecte
     */
    public MatriuCeles() {
        matriuCela = new ConcurrentSkipListMap<Integer, ConcurrentSkipListMap<Integer, Cela>>();
        numFiles = 0;
        numCols = 0;
    }

    /**
     * Constructora principal
     * @param numFiles número de files de la nova MatriuCeles
     * @param numCols número de columnes de la nova MatriuCeles
     */
    public MatriuCeles(int numFiles, int numCols) {
        matriuCela = new ConcurrentSkipListMap<Integer, ConcurrentSkipListMap<Integer, Cela>>();
        this.numCols = numCols;
        this.numFiles = numFiles;
    }

    /**
     * Getter del nombre de files
     * @return el nombre de files de la matriu
     */
    public int getNumFiles() {
        return numFiles;
    }

    /**
     * Getter del nombre de columnes
     * @return el nombre de columnes de la matriu
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * Obté la Cela indexada a la posició (fila, col)
     * @param fila fila on es troba la Cela que es busca
     * @param col columna on es troba la Cela que es busca
     * @return la Cela que es troba (fila, col)
     */
    public Cela getCela(int fila, int col) {
        if (blocInvalid(fila, col, 1, 1))
            throw new ExcepcioForaLimits(fila, col, 1, 1, this.numFiles, this.numCols);

        if (!matriuCela.containsKey(col)) {
            return null;
        } else {
            return matriuCela.get(col).get(fila);
        }
    }

    /**
     * Posa una nova Cela indexada a la posició (fila, col).
     * @param novaCela la Cela que es vol introduir a la matriu
     * @param fila fila on es trobarà la nova Cela
     * @param col columna on es trobarà la nova Cela
     */
    public void setCela(Cela novaCela, int fila, int col) {
        if (blocInvalid(fila, col, 1, 1))
            throw new ExcepcioForaLimits(fila, col, 1, 1, this.numFiles, this.numCols);

        if (novaCela != null) {
            if (!matriuCela.containsKey(col))
                matriuCela.put(col, new ConcurrentSkipListMap<Integer, Cela>());

            matriuCela.get(col).put(fila, novaCela);
        }
    }

    /**
     * Esborra la Cela que es troba indexada a la posició (int, int).
     * @param fila fila on es troba la Cela a eliminar
     * @param col columna on es troba la Cela a eliminar
     */
    public void esborraCela(int fila, int col) {
        if (blocInvalid(fila, col, 1, 1))
            throw new ExcepcioForaLimits(fila, col, 1, 1, this.numFiles, this.numCols);

        if (matriuCela.containsKey(col)) {
            matriuCela.get(col).remove(fila);
        }
    }

    /**
     * Afegeix una nova fila al final de la matriu
     */
    public void afegeixFila() {
        ++numFiles;
    }

    /**
     * Afegeix una nova columna al final de la matriu
     */
    public void afegeixColumna() {
        ++numCols;
    }

    /**
     * S’elimina la fila amb índex fila de MatriuCeles, i com a conseqüència els índexs de files més grans es veuen reduïts en 1
     * @param fila fila a eliminar
     * @throws ExcepcioFilaColumnaInvalida si la fila introduïda no existeix a la matriu
     */
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

    /**
     * S’elimina la columna amb índex col de MatriuCeles, i com a conseqüència els índexs de columnes més grans es veuen reduïts en 1.
     * @param col columna a eliminar.
     * @throws ExcepcioFilaColumnaInvalida si la columna introduïda no existeix a la matriu
     */
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

    /**
     * S’obté el bloc definit per la Cela superior esquerra indexada per (filaIni, colIni) i que té numFiles files i numCols columnes.
     * @param filaIni fila de la Cela superior esquerra del bloc
     * @param colIni columna de la Cela superior esquerra del bloc
     * @param numFiles número de files del bloc
     * @param numCols número de columnes del bloc
     * @return MatriuCeles que conté el bloc demanat
     * @throws ExcepcioForaLimits si el bloc definit surt fora dels limits
     */
    public MatriuCeles getBloc(int filaIni, int colIni, int numFiles, int numCols) {
        if (blocInvalid(filaIni, colIni, numFiles, numCols))
            throw new ExcepcioForaLimits(filaIni, colIni, numFiles, numCols, this.numFiles, this.numCols);

        MatriuCeles bloc = new MatriuCeles(numFiles, numCols);
        ConcurrentNavigableMap<Integer, ConcurrentSkipListMap<Integer, Cela>> subSL = matriuCela.subMap(colIni, colIni + numCols);

        for (ConcurrentNavigableMap.Entry<Integer, ConcurrentSkipListMap<Integer, Cela>> SLi : subSL.entrySet()) {
            ConcurrentSkipListMap<Integer, Cela> T = SLi.getValue();
            ConcurrentNavigableMap<Integer, Cela> subsubSL = T.subMap(filaIni, filaIni + numFiles);
            for (ConcurrentSkipListMap.Entry<Integer, Cela> SLj : subsubSL.entrySet()) {
                Cela c = SLj.getValue();
                bloc.setCela(c, SLj.getKey() - filaIni, SLi.getKey() - colIni);
            }
        }

        return bloc;
    }

    /**
     * S’obtenen totes les entrades no nul·les de la matriu.
     * @return Arraylist de EntradaMatriuCeles contenint les entrades
     */
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

    /**
     * S’obtenen totes les entrades no nul·les de la columna col de la matriu.
     * @param col Columna d'on obtenir les entrades
     * @return Arraylist de EntradaMatriuCeles contenint les entrades
     * @throws ExcepcioFilaColumnaInvalida si la columna introduïda no existeix a la matriu
     */
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

    /**
     * Funció comprovadora que un bloc estigui contingut dins dels límits de la MatriuCeles
     * @param filaIni fila de la Cela superior esquerra del bloc
     * @param colIni columna de la Cela superior esquerra del bloc
     * @param numFiles número de files del bloc
     * @param numCols número de columnes del bloc
     * @return true si el bloc està contingut dins dels límits de la MatriuCeles, false altrament
     */
    public boolean blocInvalid(int filaIni, int colIni, int numFiles, int numCols) {
        if (filaIni < 0 || filaIni + numFiles - 1 >= this.numFiles) return true;
        return colIni < 0 || colIni + numCols - 1 >= this.numCols;
    }
}
