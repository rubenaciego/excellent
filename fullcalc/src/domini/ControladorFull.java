package domini;

import java.util.ArrayList;

public class ControladorFull
{
    private final Full full;
    private final Operador op;

    public ControladorFull(Full full)
    {
        this.full = full;
        op = Operador.getInstance();
    }

    public void executaOperacio(ResultatParserFull parsejat)
    {
        int filaIni = parsejat.getFilaOrigen();
        int colIni = parsejat.getColumnaOrigen();
        int numFiles = parsejat.getMidaFila();
        int numCols = parsejat.getMidaColumna();
        int filaDest = parsejat.getFilaDesti();
        int colDest = parsejat.getColumnaDesti();

        MatriuCeles bloc = full.getBloc(filaIni, colIni, numFiles, numCols);
        MatriuCeles res;

        switch (parsejat.getTipusOpFull()) {
            case EXTREU_HOROSCOP:
                res = op.extreuHoroscop(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case EXTREU_ANY:
                res = op.extreuAny(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case EXTREU_DIA:
                res = op.extreuDia(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case EXTREU_DIA_SETMANA:
                res = op.extreuDiaSetmana(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case EXECUTA_OPERACIO_ARITMETICA_UNARIA:
                res = op.executaOperacioAritmeticaUnaria(bloc, parsejat.getTipusOpAritmetica());
                guardaBloc(res, filaDest, colDest);
                break;
            case EXECUTA_FUNCIO_ESTADISTICA:
                res = op.executaFuncioEstadistica(bloc, parsejat.getTipusOpEstadistica());
                guardaBloc(res, filaDest, colDest);
                break;
            case TRUNCA_NUMERO:
                res = op.truncaNumero(bloc, parsejat.getDigitsTruncar());
                guardaBloc(res, filaDest, colDest);
                break;
            case CONVERTEIX_UNITATS:
                res = op.converteixUnitats(bloc,
                        parsejat.getTipusConversioUnitats());
                guardaBloc(res, filaDest, colDest);
                break;
            case EXTREU_LONGITUD_TEXT:
                res = op.extreuLongitudText(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case CERCA_OCURRENCIES:
                res = op.cercaOcurrencies(bloc, parsejat.getStringCercada());
                guardaBloc(res, filaDest, colDest);
                break;
            case CONVERTEIX_MAJUSCULES:
                res = op.converteixMajuscules(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case CONVERTEIX_MINUSCULES:
                res = op.converteixMinuscules(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case TRANSPOSA:
                res = op.transposa(bloc);
                guardaBloc(res, filaDest, colDest);
                break;
            case REEMPLACA:
                res = op.reemplaca(bloc, parsejat.getStringCercada(), parsejat.getStringRemplacadora());
                guardaBloc(res, filaDest, colDest);
                break;
            case ORDENA:
                res = op.ordena(bloc, parsejat.getColumnaOrdenacio(), parsejat.getTipusCriteriOrdenacio());
                guardaBloc(res, filaDest, colDest);
                break;
            case MODIFICA_CELA:
                Cela c;
                ResultatParserCela resCela = parsejat.getResultatParserCela();

                int fila = resCela.getFilaRef();
                int col = resCela.getColRef();

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
                filaIni = parsejat.getFilaOrigen();
                colIni = parsejat.getColumnaOrigen();
                numFiles = parsejat.getMidaFila();
                numCols = parsejat.getMidaColumna();
                filaDest = parsejat.getFilaDesti();
                colDest = parsejat.getColumnaDesti();

                full.mouBloc(filaIni, colIni, numFiles, numCols, filaDest, colDest);
                break;
            case COPIA_BLOC:
                filaIni = parsejat.getFilaOrigen();
                colIni = parsejat.getColumnaOrigen();
                numFiles = parsejat.getMidaFila();
                numCols = parsejat.getMidaColumna();
                filaDest = parsejat.getFilaDesti();
                colDest = parsejat.getColumnaDesti();

                full.copiaBloc(filaIni, colIni, numFiles, numCols, filaDest, colDest);
                break;
            case BUIDA_BLOC:
                filaIni = parsejat.getFilaOrigen();
                colIni = parsejat.getColumnaOrigen();
                numFiles = parsejat.getMidaFila();
                numCols = parsejat.getMidaColumna();

                full.buidaBloc(filaIni, colIni, numFiles, numCols);
                break;
            default:
                throw new IncompatibleClassChangeError("Operació " + parsejat.getTipusOpFull() + " desconeguda");
        }
    }

    private void guardaBloc(MatriuCeles bloc, int filaDest, int colDest)
    {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            int filaDesti = e.getFila() + filaDest;
            int colDesti = e.getColumna() + colDest;
            full.setCela(e.getCela(), filaDesti, colDesti);
        }
    }
}
