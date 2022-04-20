package domini;

public class Operador {
    public MatriuCeles extreuHorocop(MatriuCeles bloc) {}
    public MatriuCeles extreuAny(MatriuCeles bloc) {}
    public MatriuCeles extreuMes(MatriuCeles bloc) {}
    public MatriuCeles extreuDia(MatriuCeles bloc) {}
    public MatriuCeles extreuDiaSetmana(MatriuCeles bloc) {}
    public MatriuCeles executaOperacioAritmeticaUnaria(MatriuCeles bloc,
                                                       opAritmetica op) {}
    public MatriuCeles executaFuncioEstadistica(MatriuCeles bloc,
                                                opEstadistica op) {}
    public MatriuCeles truncaNumero(MatriuCeles bloc, Integer n) {}
    public MatriuCeles converteixUnitats(MatriuCeles bloc,
                                         conversioUnitats conv) {}
    public MatriuCeles extreuLongitudText(MatriuCeles bloc) {}
    public MatriuCeles cercaOcurrencies(MatriuCeles bloc, String aCercar) {}
    public MatriuCeles converteixMajuscules(MatriuCeles bloc) {}
    public MatriuCeles converteixMinuscules(MatriuCeles bloc) {}
    public MatriuCeles transposa(MatriuCeles bloc) {}
    public MatriuCeles reemplaca(MatriuCeles bloc, String aCercar,
                                 String aSubstituir) {}
    public MatriuCeles ordena(MatriuCeles bloc, Integer col,
                              criteriOrdenacio criteri) {}
}
