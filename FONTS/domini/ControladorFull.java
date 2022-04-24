package domini;

import java.util.ArrayList;

public class ControladorFull {
    private final Full full;
    private final Operador op;

    public ControladorFull(Full full) {
        this.full = full;
        op = Operador.getInstance();
    }

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
                res = op.converteixUnitats(bloc,
                        parsejat.getTipusConversioUnitats());
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
                Cela c;
                ResultatParserCela resCela = parsejat.getResultatParserCela();

                int fila = parsejat.getFilaDesti();
                int col = parsejat.getColumnaDesti();

                switch (resCela.getTipus()) {
                    case NUMERICA:
                        c = new CelaNum(resCela.getInputUsuari(), resCela.getValorNumeric());
                        break;
                    case DATADA:
                        c = new CelaData(resCela.getInputUsuari(), resCela.getData());
                        break;
                    case REFERENCIAL:
                        Cela cRef = full.getCela(resCela.getFilaRef(),
                                resCela.getColRef());

                        if (cRef instanceof CelaRef)
                            cRef = ((CelaRef) cRef).getRef();

                        c = new CelaRef(resCela.getInputUsuari(), cRef);
                        break;
                    case TEXTUAL:
                        c = new CelaText(resCela.getInputUsuari());
                        break;
                    default:
                        throw new IncompatibleClassChangeError("Tipus cel·la " + resCela.getTipus() + " desconegut");
                }

                full.setCela(c, fila, col);
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

    private void guardaBloc(MatriuCeles bloc, int filaDest, int colDest) {
        if (full.blocInvalid(filaDest, colDest, bloc.getNumFiles(), bloc.getNumCols()))
            throw new ExcepcioForaLimits(filaDest, colDest, bloc.getNumFiles(), bloc.getNumCols(),
                    full.getNumFiles(), full.getNumCols());

        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            int filaDesti = e.getFila() + filaDest;
            int colDesti = e.getColumna() + colDest;
            full.setCela(e.getCela(), filaDesti, colDesti);
        }
    }
}
