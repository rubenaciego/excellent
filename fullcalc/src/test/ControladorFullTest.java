package test;

import domini.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class ControladorFullTest {

    ControladorFull controlador;

    @Mock
    Operador op;

    Full full;

    @Before
    public void setUp() {
        full = mock(Full.class);
        controlador = new ControladorFull(full);
        op = mock(Operador.class);
    }

    /**
     * @brief Test amb mocks de MatriuCeles i Operador per comprovar que les
     * crides que es fan a ambdues classes a ControladorFull son rebudes
     * correctament per aquestes.
     */
    @Test
    public void TestExecutaOperacio() {
        MatriuCeles bloc = mock(MatriuCeles.class);

        MatriuCeles resHor = mock(MatriuCeles.class);
        when(resHor.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "extreuHoroscop cridat! :)"));

        MatriuCeles resAny = mock(MatriuCeles.class);
        when(resAny.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "extreuAny cridat! :)"));

        MatriuCeles resMes = mock(MatriuCeles.class);
        when(resMes.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "extreuMes cridat! :)"));

        MatriuCeles resDia = mock(MatriuCeles.class);
        when(resDia.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "extreuDia cridat! :)"));

        MatriuCeles resDiaSet = mock(MatriuCeles.class);
        when(resDiaSet.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "extreuDiaSetmana cridat! :)"));

        MatriuCeles resOpAr = mock(MatriuCeles.class);
        when(resOpAr.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "executaOperacioAritmeticaUnaria cridat! :)"));

        MatriuCeles resOpEs = mock(MatriuCeles.class);
        when(resOpEs.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "executaOperacioEstadistica cridat! :)"));

        MatriuCeles resTrunc = mock(MatriuCeles.class);
        when(resTrunc.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "truncaNumero cridat! :)"));

        MatriuCeles resConvUn = mock(MatriuCeles.class);
        when(resConvUn.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "converteixUnitats cridat! :)"));

        MatriuCeles resLonTxt = mock(MatriuCeles.class);
        when(resLonTxt.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "extreuLongitudText cridat! :)"));

        MatriuCeles resCerca = mock(MatriuCeles.class);
        when(resCerca.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "cercaOcurrencies cridat! :)"));

        MatriuCeles resMaj = mock(MatriuCeles.class);
        when(resMaj.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "converteixMajuscules cridat! :)"));

        MatriuCeles resMin = mock(MatriuCeles.class);
        when(resMin.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "converteixMinuscules cridat! :)"));

        MatriuCeles resT = mock(MatriuCeles.class);
        when(resT.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "transposa cridat! :)"));

        MatriuCeles resReem = mock(MatriuCeles.class);
        when(resReem.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "reemplaca cridat! :)"));

        MatriuCeles resOrd = mock(MatriuCeles.class);
        when(resOrd.getCela(0,0)).thenReturn(new CelaText("Operador " +
                "ordena cridat! :)"));

        when(op.extreuHoroscop(bloc)).thenReturn(resHor);
        when(op.extreuAny(bloc)).thenReturn(resAny);
        when(op.extreuMes(bloc)).thenReturn(resMes);
        when(op.extreuDia(bloc)).thenReturn(resDia);
        when(op.extreuDiaSetmana(bloc)).thenReturn(resDiaSet);
        when(op.executaOperacioAritmeticaUnaria(bloc,
                OperacioAritmetica.VALOR_ABSOLUT)).thenReturn(resOpAr);
        when(op.executaOperacioEstadistica(bloc, OperacioEstadistica.MITJANA)).thenReturn(resOpEs);
        when(op.truncaNumero(bloc, 1)).thenReturn(resTrunc);
        when(op.converteixUnitats(bloc, ConversioUnitats.RAD_GRAUS)).thenReturn(resConvUn);
        when(op.extreuLongitudText(bloc)).thenReturn(resLonTxt);
        when(op.cercaOcurrencies(bloc, ":)")).thenReturn(resCerca);
        when(op.converteixMajuscules(bloc)).thenReturn(resMaj);
        when(op.converteixMinuscules(bloc)).thenReturn(resMin);
        when(op.transposa(bloc)).thenReturn(resT);
        when(op.reemplaca(bloc, ":(", ":)")).thenReturn(resReem);
        when(op.ordena(bloc, 0, CriteriOrdenacio.ASCENDENT)).thenReturn(resOrd);


        assertEquals(op.extreuHoroscop(bloc).getCela(0,0).getText(),
                "Operador extreuHoroscop cridat! :)");
        assertEquals(op.extreuAny(bloc).getCela(0,0).getText(),
                "Operador extreuAny cridat! :)");
        assertEquals(op.extreuMes(bloc).getCela(0,0).getText(),
                "Operador extreuMes cridat! :)");
        assertEquals(op.extreuDia(bloc).getCela(0,0).getText(),
                "Operador extreuDia cridat! :)");
        assertEquals(op.extreuDiaSetmana(bloc).getCela(0,0).getText(),
                "Operador extreuDiaSetmana cridat! :)");
        assertEquals(op.executaOperacioAritmeticaUnaria(bloc, OperacioAritmetica.VALOR_ABSOLUT).getCela(0,
                        0).getText(),
                "Operador executaOperacioAritmeticaUnaria cridat! :)");
        assertEquals(op.executaOperacioEstadistica(bloc, OperacioEstadistica.MITJANA).getCela(0,0).getText(),
                "Operador executaOperacioEstadistica cridat! :)");
        assertEquals(op.truncaNumero(bloc, 1).getCela(0,0).getText(),
                "Operador truncaNumero cridat! :)");
        assertEquals(op.converteixUnitats(bloc, ConversioUnitats.RAD_GRAUS).getCela(0,
                        0).getText(),
                "Operador converteixUnitats cridat! :)");
        assertEquals(op.extreuLongitudText(bloc).getCela(0,0).getText(),
                "Operador extreuLongitudText cridat! :)");
        assertEquals(op.cercaOcurrencies(bloc, ":)").getCela(0,0).getText(),
                "Operador cercaOcurrencies cridat! :)");
        assertEquals(op.converteixMajuscules(bloc).getCela(0,0).getText(),
                "Operador converteixMajuscules cridat! :)");
        assertEquals(op.converteixMinuscules(bloc).getCela(0,0).getText(),
                "Operador converteixMinuscules cridat! :)");
        assertEquals(op.transposa(bloc).getCela(0,0).getText(),
                "Operador transposa cridat! :)");
        assertEquals(op.reemplaca(bloc, ":(", ":)").getCela(0,0).getText(),
                "Operador reemplaca cridat! :)");
        assertEquals(op.ordena(bloc, 0, CriteriOrdenacio.ASCENDENT).getCela(0,0).getText(),
                "Operador ordena cridat! :)");
    }

    /**
     * @brief Test amb mock de Full per comprovar que les crides a setCela i
     * getCela que es fan des de OperadorFull son rebudes correctament per la
     * classe Full.
     */
    @Test
    public void TestExecutaOperacioModCela() {
        CelaText cNova = new CelaText("Cela Nova :)");
        doNothing().when(full).setCela(cNova,0,0);
        when(full.getCela(0,0)).thenReturn(cNova);

        full.setCela(cNova,0,0);
        assertEquals(full.getCela(0,0).getText(), "Cela Nova :)");
    }
}