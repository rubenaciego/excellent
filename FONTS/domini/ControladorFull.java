package domini;

import vista.EntradaTaula;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Controlador que fa d’intermediari entre el Controlador Domini i un Full quan s’executen Operacions sobre el Full.
 */
public class ControladorFull {
    /**
     * Full corresponent a l'actual ControladorFull
     */
    private final Full full;
    /**
     * Operador encarregat d'executar totes les operacions de full
     */
    private final Operador op;

    /**
     * Constructora principal
     * @param full full corresponent al nou ControladorFull
     */
    public ControladorFull(Full full) {
        this.full = full;
        op = Operador.getInstance();
    }

    /**
     * Executa l’operació que li delega el ControladorDomini
     * @param parsejat representa l’operació a executar i tots els paràmetres necessaris per la seva execució
     */
    public void executaOperacio(ResultatParserFull parsejat) {
        int filaIni = parsejat.getFilaOrigen();
        int colIni = parsejat.getColumnaOrigen();
        int numFiles = parsejat.getMidaFila();
        int numCols = parsejat.getMidaColumna();
        int filaDest = parsejat.getFilaDesti();
        int colDest = parsejat.getColumnaDesti();

        MatriuCeles bloc;
        MatriuCeles res;

        switch (parsejat.getTipusOpFull()) {
            case EXTREU_HOROSCOP:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.extreuHoroscop(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case EXTREU_ANY:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.extreuAny(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case EXTREU_MES:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.extreuMes(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case EXTREU_DIA:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.extreuDia(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case EXTREU_DIA_SETMANA:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.extreuDiaSetmana(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case EXECUTA_OPERACIO_ARITMETICA_UNARIA:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.executaOperacioAritmeticaUnaria(bloc, parsejat.getTipusOpAritmetica());
                guardaBloc(res, filaDest, colDest);
                break;
            case EXECUTA_FUNCIO_ESTADISTICA:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.executaOperacioEstadistica(bloc, parsejat.getTipusOpEstadistica());
                guardaBloc(res, filaDest, colDest);
                break;
            case TRUNCA_NUMERO:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.truncaNumero(bloc, parsejat.getDigitsTruncar());
                guardaBloc(res, filaDest, colDest);
                break;
            case CONVERTEIX_UNITATS:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.converteixUnitats(bloc, parsejat.getTipusConversioUnitats());
                guardaBloc(res, filaDest, colDest);
                break;
            case EXTREU_LONGITUD_TEXT:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.extreuLongitudText(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case CERCA_OCURRENCIES:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.cercaOcurrencies(bloc, parsejat.getStringCercada());
                guardaBloc(res, filaDest, colDest);
                break;
            case CONVERTEIX_MAJUSCULES:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.converteixMajuscules(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case CONVERTEIX_MINUSCULES:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.converteixMinuscules(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case TRANSPOSA:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.transposa(bloc);

                if (filaIni == filaDest && colIni == colDest)
                {
                    if (numFiles > numCols)
                        full.buidaBloc(filaIni + numCols, colIni ,numFiles - numCols, numCols);
                    else if (numCols > numFiles)
                        full.buidaBloc(filaIni, colIni + numFiles, numFiles, numCols - numFiles);
                }

                guardaBloc(res, filaDest, colDest);
                break;
            case REEMPLACA:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.reemplaca(bloc, parsejat.getStringCercada(), parsejat.getStringRemplacadora());
                guardaBloc(res, filaDest, colDest);
                break;
            case ORDENA:
                bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
                res = op.ordena(bloc, parsejat.getColumnaOrdenacio(), parsejat.getTipusCriteriOrdenacio());
                guardaBloc(res, filaDest, colDest);
                break;
            case MODIFICA_CELA:
                ResultatParserCela resCela = parsejat.getResultatParserCela();
                modificaCela(resCela, parsejat.getFilaDesti(), parsejat.getColumnaDesti());
                break;
            case AFEGEIX_COLUMNA:
                full.afegeixColumna();
                break;
            case AFEGEIX_FILA:
                full.afegeixFila();
                break;
            case ELIMINA_COLUMNA:
                full.eliminaColumna(parsejat.getFilaColEliminar());
                break;
            case ELIMINA_FILA:
                full.eliminaFila(parsejat.getFilaColEliminar());
                break;
            case MOU_BLOC:
                full.mouBloc(filaIni, colIni, numFiles, numCols, filaDest, colDest);
                break;
            case COPIA_BLOC:
                full.copiaBloc(filaIni, colIni, numFiles, numCols, filaDest, colDest);
                break;
            case BUIDA_BLOC:
                full.buidaBloc(filaIni, colIni, numFiles, numCols);
                break;
            default:
                throw new IncompatibleClassChangeError("Operació " + parsejat.getTipusOpFull() + " desconeguda");
        }
    }

    /**
     * Getter del número de files del full associat
     * @return el número de files del full associat
     */
    public int getNumFiles() {
        return full.getNumFiles();
    }

    /**
     * Getter del número de columnes del full associat
     * @return el número de columnes del full associat
     */
    public int getNumCols() {
        return full.getNumCols();
    }

    /**
     * Getter de la Cela resultat del full associat
     * @return la Cela resultat en format String
     */
    public String getCelaResultat() {
        Cela c = full.getCela(-1, -1);
        if (c != null) return c.toString();
        return "";
    }

    /**
     * S’obtenen totes les entrades no nul·les del bloc del full associat definit per la Cela superior esquerra indexada per (srow, scol) i que té numFiles files i numCols columnes.
     * @param srow fila de la Cela superior esquerra del bloc inicial
     * @param scol columna de la Cela superior esquerra del bloc inicial
     * @param numFiles número de files del bloc
     * @param numCols número de columnes del bloc
     * @return les entrades no nul·les del bloc especificat
     */
    public ArrayList<EntradaTaula> getEntrades(int srow, int scol, int numFiles, int numCols) {
        ArrayList<EntradaTaula> entrades = new ArrayList<EntradaTaula>();
        ArrayList<EntradaMatriuCeles> e = full.getBloc(srow, scol, numFiles, numCols).getEntrades();

        for (EntradaMatriuCeles entrada : e)
            entrades.add(new EntradaTaula(entrada.getFila() + srow,
                    entrada.getColumna() + scol,
                    entrada.getCela().toString()));

        return entrades;
    }

    /**
     * Modifica la Cela identificada per fila i col del full segons la informació especificada resCela
     * @param resCela resultat obtingut del Parser referent a la Cela
     * @param fila fila on està situada la Cela
     * @param col columna on està situada la Cela
     */
    private void modificaCela(ResultatParserCela resCela, int fila, int col) {
        Cela c = null;

        if (resCela.inputUsuari.isEmpty()) full.esborraCela(fila, col);

        switch (resCela.getTipus()) {
            case NUMERICA:
                c = new CelaNum(resCela.getInputUsuari(), resCela.getValorNumeric());
                break;
            case DATADA:
                c = new CelaData(resCela.getInputUsuari(), resCela.getData());
                break;
            case REFERENCIAL:
                Cela cRef = full.getCela(resCela.getFilaRef(), resCela.getColRef());

                if (cRef != null) {
                    if (cRef instanceof CelaRef) {
                        resCela.setInputUsuari(cRef.getInputUsuari());
                        cRef = ((CelaRef) cRef).getRef();
                    }

                    c = new CelaRef(resCela.getInputUsuari(), cRef);
                    cRef.getCelesReferenciadores().add((CelaRef)c);
                }
                break;
            case TEXTUAL:
                c = new CelaText(resCela.getInputUsuari());
                break;
            default:
                throw new IncompatibleClassChangeError("Tipus cel·la " + resCela.getTipus() + " desconegut");
        }

        actualitzaReferencies(c, fila, col);
        full.setCela(c, fila, col);
    }

    /**
     * Afegeix la MatriuCeles al bloc del full associat que comença a la Cela superior esquerra indexada per (filaDest, colDest)
     * @param bloc MatriuCeles que actualitza el full associat
     * @param filaDest fila de la Cela superior esquerra del bloc destí
     * @param colDest columna de la Cela superior esquerra del bloc destí
     * @throws ExcepcioForaLimits si el bloc definit cau fora dels límits del full associat
     */
    private void guardaBloc(MatriuCeles bloc, int filaDest, int colDest) {
        if (full.blocInvalid(filaDest, colDest, bloc.getNumFiles(), bloc.getNumCols()))
            throw new ExcepcioForaLimits(filaDest, colDest, bloc.getNumFiles(), bloc.getNumCols(),
                    full.getNumFiles(), full.getNumCols());

        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            int filaDesti = e.getFila() + filaDest;
            int colDesti = e.getColumna() + colDest;
            actualitzaReferencies(e.getCela(), filaDesti, colDesti);
        }

        full.buidaBloc(filaDest, colDest, bloc.getNumFiles(), bloc.getNumCols());

        for (EntradaMatriuCeles e : entrades) {
            int filaDesti = e.getFila() + filaDest;
            int colDesti = e.getColumna() + colDest;
            full.setCela(e.getCela(), filaDesti, colDesti);
        }
    }

    /**
     * Actualitza les referències de la Cela c indexada a (fila, col)
     * @param c nova Cela a la que s'han d'actualitzar les referències
     * @param fila fila on està situada c
     * @param col columna on està situada c
     */
    private void actualitzaReferencies(Cela c, int fila, int col) {
        Cela prev = full.getCela(fila, col);

        if (prev != null) {
            /* Si la cel·la on escrivim no era buida hem de fer que totes les que la referenciaven
             * ara facin referència a la nova cel·la
             */
            HashSet<CelaRef> refs = prev.getCelesReferenciadores();

            /* Si la nova cel·la és una referència, aleshores fem que les cel·les que referenciaven a l'anterior (prev)
             * passin a referenciar a la mateixa cel·la a la que fa referència la nova.
             * En cas que la nova cel·la no sigui una refèrencia, les cel·les que referenciàven a l'anterior passen
             * a referenciar a aquesta nova cel·la
             */
            Cela aRef = (c instanceof CelaRef ? ((CelaRef) c).getRef() : c);
            aRef.getCelesReferenciadores().addAll(refs);

            for (CelaRef r : refs)
                r.setRef(aRef);
        }
    }
}
