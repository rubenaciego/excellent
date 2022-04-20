package domini;

public class ControladorFull {
    private Full full;
    private Operador op;

    public void executaOperacio(ResultatParserFull parsejat) {
        if (parsejat.getTipusOpFull() == OpFull.EXTREU_HOROSCOP) {
            MatriuCeles bloc = obteBloc(parsejat);
            MatriuCeles res = op.extreuHoroscop(bloc);
        }
        else if (parsejat.getTipusOpFull() == OpFull.EXTREU_ANY) {
            MatriuCeles bloc = obteBloc(parsejat);
            MatriuCeles res = op.extreuAny(bloc);
        }
        else if (parsejat.getTipusOpFull() == OpFull.EXTREU_DIA) {
            MatriuCeles bloc = obteBloc(parsejat);
            MatriuCeles res = op.extreuDia(bloc);
        }
        else if (parsejat.getTipusOpFull() == OpFull.EXTREU_DIA_SETMANA) {
            MatriuCeles bloc = obteBloc(parsejat);
            MatriuCeles res = op.extreuDiaSetmana(bloc);
        }
        else if (parsejat.getTipusOpFull() == OpFull.EXECUTA_OPERACIO_ARITMETICA_UNARIA) {
            MatriuCeles bloc = obteBloc(parsejat);
            MatriuCeles res = op.executaOperacioAritmeticaUnaria(bloc,
                    parsejat.getTipusOpAritmetica());
        }
        else if (parsejat.getTipusOpFull() == OpFull.EXECUTA_FUNCIO_ESTADISTICA) {
            MatriuCeles bloc = obteBloc(parsejat);
            MatriuCeles res = op.executaFuncioEstadistica(bloc,
                    parsejat.getTipusOpEstadistica());
        }

        else if (parsejat.getTipusOpFull() == OpFull.TRUNCA_NUMERO) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == OpFull.CONVERTEIX_UNITATS) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == OpFull.CERCA_OCURRENCIES) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == OpFull.CONVERTEIX_MAJUSCULES) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == OpFull.CONVERTEIX_MINUSCULES) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == OpFull.TRANSPOSA) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == OpFull.REEMPLACA) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == OpFull.ORDENA) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == OpFull.MODIFICA_CELA) {}
        else if (parsejat.getTipusOpFull() == OpFull.AFEGEIX_COLUMNA) {}
        else if (parsejat.getTipusOpFull() == OpFull.AFEGEIX_FILA) {}
        else if (parsejat.getTipusOpFull() == OpFull.ELIMINA_COLUMNA) {}
        else if (parsejat.getTipusOpFull() == OpFull.ELIMINA_FILA) {}
        else if (parsejat.getTipusOpFull() == OpFull.MOU_BLOC) {}
        else if (parsejat.getTipusOpFull() == OpFull.COPIA_BLOC) {}
        else if (parsejat.getTipusOpFull() == OpFull.BUIDA_BLOC) {}
    }

    private MatriuCeles obteBloc(ResultatParserFull parsejat) {
        Integer filaIni = parsejat.getFilaOrigen();
        Integer colIni = parsejat.getColumnaOrigen();
        Integer numFiles = parsejat.getMidaFila();
        Integer numCols = parsejat.getMidaColumna();
        MatriuCeles bloc = full.obteBloc(filaIni, colIni, numFiles,
                numCols);
        return bloc;
    }
}
