package domini;

public class ControladorFull {
    private Full full;
    private Operador op;

    public void executaOperacio(ResultatParserFull parsejat) {
        if (parsejat.getTipusOpFull() == opFull.extreuHoroscop) {
            MatriuCeles bloc = obteBloc(parsejat);
            MatriuCeles res = op.extreuHorocop(bloc);
        }
        else if (parsejat.getTipusOpFull() == opFull.extreuAny) {
            MatriuCeles bloc = obteBloc(parsejat);
            MatriuCeles res = op.extreuAny(bloc);
        }
        else if (parsejat.getTipusOpFull() == opFull.extreuDia) {
            MatriuCeles bloc = obteBloc(parsejat);
            MatriuCeles res = op.extreuDia(bloc);
        }
        else if (parsejat.getTipusOpFull() == opFull.extreuDiaSetmana) {
            MatriuCeles bloc = obteBloc(parsejat);
            MatriuCeles res = op.extreuDiaSetmana(bloc);
        }
        else if (parsejat.getTipusOpFull() == opFull.executaOperacioAritmeticaUnaria) {
            MatriuCeles bloc = obteBloc(parsejat);
            MatriuCeles res = op.executaOperacioAritmeticaUnaria(bloc,
                    parsejat.getTipusOpAritmetica());
        }
        else if (parsejat.getTipusOpFull() == opFull.executaFuncioEstadistica) {
            MatriuCeles bloc = obteBloc(parsejat);
            MatriuCeles res = op.executaFuncioEstadistica(bloc,
                    parsejat.getTipusOpEstadistica());
        }

        else if (parsejat.getTipusOpFull() == opFull.truncaNumero) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == opFull.converteixUnitats) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == opFull.cercaOcurrencies) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == opFull.converteixMajuscules) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == opFull.converteixMinuscules) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == opFull.transposa) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == opFull.reemplaca) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == opFull.ordena) {
            MatriuCeles bloc = obteBloc(parsejat);
        }
        else if (parsejat.getTipusOpFull() == opFull.modificaCela) {}
        else if (parsejat.getTipusOpFull() == opFull.afegeixColumna) {}
        else if (parsejat.getTipusOpFull() == opFull.afegeixFila) {}
        else if (parsejat.getTipusOpFull() == opFull.eliminaColumna) {}
        else if (parsejat.getTipusOpFull() == opFull.eliminaFila) {}
        else if (parsejat.getTipusOpFull() == opFull.mouBloc) {}
        else if (parsejat.getTipusOpFull() == opFull.copiaBloc) {}
        else if (parsejat.getTipusOpFull() == opFull.buidaBloc) {}
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
