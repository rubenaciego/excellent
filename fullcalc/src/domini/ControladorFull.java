package domini;

import org.w3c.dom.xpath.XPathResult;

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
        MatriuCeles bloc = getBloc(parsejat);
        MatriuCeles res;

        int filaIni;
        int colIni;
        int numFiles;
        int numCols;
        int filaFi;
        int colFi;

        switch (parsejat.getTipusOpFull()) {
            case EXTREU_HOROSCOP:
                res = op.extreuHoroscop(bloc);
                guardaBloc(res, parsejat);
                break;
            case EXTREU_ANY:
                res = op.extreuAny(bloc);
                guardaBloc(res, parsejat);
                break;
            case EXTREU_DIA:
                res = op.extreuDia(bloc);
                guardaBloc(res, parsejat);
                break;
            case EXTREU_DIA_SETMANA:
                res = op.extreuDiaSetmana(bloc);
                guardaBloc(res, parsejat);
                break;
            case EXECUTA_OPERACIO_ARITMETICA_UNARIA:
                res = op.executaOperacioAritmeticaUnaria(bloc, parsejat.getTipusOpAritmetica());
                guardaBloc(res, parsejat);
                break;
            case EXECUTA_FUNCIO_ESTADISTICA:
                res = op.executaFuncioEstadistica(bloc, parsejat.getTipusOpEstadistica());
                guardaBloc(res, parsejat);
                break;
            case TRUNCA_NUMERO:
                break;
            case CONVERTEIX_UNITATS:
                res = op.converteixUnitats(bloc,
                        parsejat.getTipusConversioUnitats());
                guardaBloc(res, parsejat);
                break;
            case EXTREU_LONGITUD_TEXT:
                res = op.extreuLongitudText(bloc);
                guardaBloc(res, parsejat);
                break;
            case CERCA_OCURRENCIES:
                res = op.cercaOcurrencies(bloc, parsejat.getStringCercada());
                guardaBloc(res, parsejat);
                break;
            case CONVERTEIX_MAJUSCULES:
                res = op.converteixMajuscules(bloc);
                guardaBloc(res, parsejat);
                break;
            case CONVERTEIX_MINUSCULES:
                res = op.converteixMinuscules(bloc);
                guardaBloc(res, parsejat);
                break;
            case TRANSPOSA:
                res = op.transposa(bloc);
                guardaBloc(res, parsejat);
                break;
            case REEMPLACA:
                res = op.reemplaca(bloc, parsejat.getStringCercada(),
                        parsejat.getStringRemplacadora());
                guardaBloc(res, parsejat);
                break;
            case ORDENA:
                res = op.ordena(bloc, 1, parsejat.getTipusCriteriOrdenacio());
                guardaBloc(res, parsejat);
                break;
            case MODIFICA_CELA:
                Cela c;
                ContingutCelaModificada celaMod = parsejat.getCelaModificada();

                int fila = celaMod.getFilaRef();
                int col = celaMod.getColRef();

                switch(celaMod.getTipus()) {
                    case NUMERICA:
                        c = new CelaNum(celaMod.getInputUsuari(), celaMod.getValorNumeric());
                        break;
                    case DATADA:
                        c = new CelaData(celaMod.getInputUsuari(), celaMod.getData());
                        break;
                    case REFERENCIAL:
                        Cela cRef = full.getCela(celaMod.getFilaRef(),
                                    celaMod.getColRef());

                        if (cRef instanceof CelaRef) {
                            cRef = cRef.getRef();
                        }
                        c = new CelaRef(celaMod.getInputUsuari(), cRef);
                        break;
                    default:
                        c = new CelaText(celaMod.getInputUsuari());
                        break;
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
                full.eliminaColumna(1);
                break;
            case ELIMINA_FILA:
                full.eliminaFila(1);
                break;
            case MOU_BLOC:
                filaIni = parsejat.getFilaOrigen();
                colIni = parsejat.getColumnaOrigen();
                numFiles = parsejat.getMidaFila();
                numCols = parsejat.getMidaColumna();
                filaFi = parsejat.getFilaDesti();
                colFi = parsejat.getColumnaDesti();

                full.mouBloc(filaIni, colIni, numFiles, numCols, filaFi,
                            colFi);

                break;
            case COPIA_BLOC:
                filaIni = parsejat.getFilaOrigen();
                colIni = parsejat.getColumnaOrigen();
                numFiles = parsejat.getMidaFila();
                numCols = parsejat.getMidaColumna();
                filaFi = parsejat.getFilaDesti();
                colFi = parsejat.getColumnaDesti();

                full.copiaBloc(filaIni, colIni, numFiles, numCols, filaFi,
                            colFi);

                break;
            case BUIDA_BLOC:
                filaIni = parsejat.getFilaOrigen();
                colIni = parsejat.getColumnaOrigen();
                numFiles = parsejat.getMidaFila();
                numCols = parsejat.getMidaColumna();

                full.buidaBloc(filaIni, colIni, numFiles, numCols);

                break;
            default:
                System.out.println("No implementat :(");
                break;
        }
    }

    private MatriuCeles getBloc(ResultatParserFull parsejat)
    {
        int filaIni = parsejat.getFilaOrigen();
        int colIni = parsejat.getColumnaOrigen();
        int numFiles = parsejat.getMidaFila();
        int numCols = parsejat.getMidaColumna();

        MatriuCeles bloc =  full.obteBloc(filaIni, colIni, numFiles,
                    numCols);

        return bloc;
    }

    private void guardaBloc(MatriuCeles bloc, ResultatParserFull parsejat) {
        ArrayList<EntradaMatriuCeles> entrades = bloc.getEntrades();

        for (EntradaMatriuCeles e : entrades) {
            int filaDesti = e.getFila() + parsejat.getFilaDesti();
            int colDesti = e.getCol() + parsejat.getColumnaDesti();
            full.setCela(e.getCela(), filaDesti, colDesti);
        }
    }
}
