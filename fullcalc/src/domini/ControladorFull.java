package domini;

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
        MatriuCeles bloc = getBloc(parsejat);
        MatriuCeles res;

        switch (parsejat.getTipusOpFull()) {
            case EXTREU_HOROSCOP:
                res = op.extreuHoroscop(bloc);
                break;
            case EXTREU_ANY:
                res = op.extreuAny(bloc);
                break;
            case EXTREU_DIA:
                res = op.extreuDia(bloc);
                break;
            case EXTREU_DIA_SETMANA:
                res = op.extreuDiaSetmana(bloc);
                break;
            case EXECUTA_OPERACIO_ARITMETICA_UNARIA:
                res = op.executaOperacioAritmeticaUnaria(bloc, parsejat.getTipusOpAritmetica());
                break;
            case EXECUTA_FUNCIO_ESTADISTICA:
                res = op.executaFuncioEstadistica(bloc, parsejat.getTipusOpEstadistica());
                break;
            case TRUNCA_NUMERO:
                break;
            case CONVERTEIX_UNITATS:
                break;
            case CERCA_OCURRENCIES:
                break;
            case CONVERTEIX_MAJUSCULES:
                break;
            case CONVERTEIX_MINUSCULES:
                break;
            case TRANSPOSA:
                break;
            case REEMPLACA:
                break;
            case ORDENA:
                break;
            case MODIFICA_CELA:
                break;
            case AFEGEIX_COLUMNA:
                break;
            case AFEGEIX_FILA:
                break;
            case ELIMINA_COLUMNA:
                break;
            case ELIMINA_FILA:
                break;
            case MOU_BLOC:
                break;
            case COPIA_BLOC:
                break;
            case BUIDA_BLOC:
                break;
            default:
                // error
                break;
        }
    }

    private MatriuCeles getBloc(ResultatParserFull parsejat)
    {
        int filaIni = parsejat.getFilaOrigen();
        int colIni = parsejat.getColumnaOrigen();
        int numFiles = parsejat.getMidaFila();
        int numCols = parsejat.getMidaColumna();

        return full.obteBloc(filaIni, colIni, numFiles, numCols);
    }
}
