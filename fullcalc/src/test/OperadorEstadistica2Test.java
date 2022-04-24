package test;

import domini.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;

public class OperadorEstadistica2Test {
    private Operador operador;
    private static final double TOL = 1e-13;

    @Before
    public void setUp() {
        operador = Operador.getInstance();
    }

    @Test
    public void testCovariancia() {
        // Test resultat correcte
        ArrayList<Double> x = new ArrayList<Double>();
        ArrayList<Double> y = new ArrayList<Double>();

        Iterator<EntradaMatriuCeles> iterator1 =(Iterator<EntradaMatriuCeles>) mock(Iterator.class);
        OngoingStubbing<EntradaMatriuCeles> next1 = when(iterator1.next());

        for (int i = 0; i < 34; ++i) {
            double xval = 20.5 * i + 3.5;
            x.add(xval);

            EntradaMatriuCeles e = mock(EntradaMatriuCeles.class);
            next1 = next1.thenReturn(e);

            Cela c = mock(Cela.class);
            when(e.getCela()).thenReturn(c);
            when(c.getNum()).thenReturn(xval);
            when(c.getInputUsuari()).thenReturn("test");
            when(e.getFila()).thenReturn(i);
            when(e.getColumna()).thenReturn(0);
        }

        OngoingStubbing<Boolean> hasNext1 = when(iterator1.hasNext());
        for (int i = 0; i < 34; ++i)
            hasNext1 = hasNext1.thenReturn(true);

        hasNext1.thenReturn(false);

        Iterator<EntradaMatriuCeles> iterator2 =(Iterator<EntradaMatriuCeles>) mock(Iterator.class);
        OngoingStubbing<EntradaMatriuCeles> next2 = when(iterator2.next());

        for (int i = 0; i < 34; ++i) {
            double yval = 0.5456 * i + 0.5;
            y.add(yval);

            EntradaMatriuCeles e = mock(EntradaMatriuCeles.class);
            next2 = next2.thenReturn(e);

            Cela c = mock(Cela.class);
            when(e.getCela()).thenReturn(c);
            when(c.getNum()).thenReturn(yval);
            when(c.getInputUsuari()).thenReturn("test");
            when(e.getFila()).thenReturn(i);
            when(e.getColumna()).thenReturn(1);
        }

        OngoingStubbing<Boolean> hasNext2 = when(iterator2.hasNext());
        for (int i = 0; i < 34; ++i)
            hasNext2 = hasNext2.thenReturn(true);

        hasNext2.thenReturn(false);

        MatriuCeles mc = mock(MatriuCeles.class);
        ArrayList<EntradaMatriuCeles> col1 = (ArrayList<EntradaMatriuCeles>) mock(ArrayList.class);
        ArrayList<EntradaMatriuCeles> col2 = (ArrayList<EntradaMatriuCeles>) mock(ArrayList.class);
        when(col1.iterator()).thenReturn(iterator1);
        when(col2.iterator()).thenReturn(iterator2);
        when(mc.getNumCols()).thenReturn(2);
        when(mc.getNumFiles()).thenReturn(34);
        when(mc.getEntradesColumna(0)).thenReturn(col1);
        when(mc.getEntradesColumna(1)).thenReturn(col2);
        when(col1.size()).thenReturn(34);
        when(col2.size()).thenReturn(34);

        double mx = 0.0;
        double my = 0.0;
        double res = 0.0;

        for (double d : x) mx += d;
        for (double d : y) my += d;
        mx /= x.size();
        my /= y.size();
        for (int i = 0; i < x.size(); ++i) res += (x.get(i) - mx) * (y.get(i) - my);
        res /= x.size();

        MatriuCeles resMc = operador.executaOperacioEstadistica(mc, OperacioEstadistica.COVARIANCIA);
        assertNotNull(resMc);
        assertEquals(1, resMc.getNumFiles());
        assertEquals(1, resMc.getNumCols());

        Cela c = resMc.getCela(0, 0);
        assertNotNull(c);
        assertTrue(c instanceof CelaNum);

        Double val = c.getNum();
        assertNotNull(val);
        assertTrue(Math.abs(res - val) < TOL);

        // Test numero incorrecte de columnes
        when(mc.getNumCols()).thenReturn(23);
        when(mc.getNumFiles()).thenReturn(34);
        boolean excp = false;

        try {
            resMc = operador.executaOperacioEstadistica(mc, OperacioEstadistica.COVARIANCIA);
        } catch (ExcepcioOperador e) {
            assertEquals(ExcepcioDomini.TipusError.OPERADOR, e.getTipusError());
            excp = true;
        }

        assertTrue(excp);

        // Test diferent quantitat de dades
        x = new ArrayList<Double>();
        y = new ArrayList<Double>();

        iterator1 =(Iterator<EntradaMatriuCeles>) mock(Iterator.class);
        next1 = when(iterator1.next());

        for (int i = 0; i < 34; ++i) {
            double xval = 20.5 * i + 3.5;
            x.add(xval);

            EntradaMatriuCeles e = mock(EntradaMatriuCeles.class);
            next1 = next1.thenReturn(e);

            Cela c2 = mock(Cela.class);
            when(e.getCela()).thenReturn(c2);
            when(c2.getNum()).thenReturn(xval);
            when(c2.getInputUsuari()).thenReturn("test");
            when(e.getFila()).thenReturn(i);
            when(e.getColumna()).thenReturn(0);
        }

        hasNext1 = when(iterator1.hasNext());
        for (int i = 0; i < 34; ++i)
            hasNext1 = hasNext1.thenReturn(true);

        hasNext1.thenReturn(false);

        iterator2 =(Iterator<EntradaMatriuCeles>) mock(Iterator.class);
        next2 = when(iterator2.next());

        for (int i = 0; i < 34; ++i) {
            if (i % 2 == 0) {
                double yval = 0.5456 * i + 0.5;
                y.add(yval);

                EntradaMatriuCeles e = mock(EntradaMatriuCeles.class);
                next2 = next2.thenReturn(e);

                Cela c2 = mock(Cela.class);
                when(e.getCela()).thenReturn(c2);
                when(c2.getNum()).thenReturn(yval);
                when(c2.getInputUsuari()).thenReturn("test");
                when(e.getFila()).thenReturn(i);
                when(e.getColumna()).thenReturn(1);
            }
        }

        hasNext2 = when(iterator2.hasNext());
        for (int i = 0; i < 17; ++i)
            hasNext2 = hasNext2.thenReturn(true);

        hasNext2.thenReturn(false);

        mc = mock(MatriuCeles.class);
        col1 = (ArrayList<EntradaMatriuCeles>) mock(ArrayList.class);
        col2 = (ArrayList<EntradaMatriuCeles>) mock(ArrayList.class);
        when(col1.iterator()).thenReturn(iterator1);
        when(col2.iterator()).thenReturn(iterator2);
        when(mc.getNumCols()).thenReturn(2);
        when(mc.getNumFiles()).thenReturn(34);
        when(mc.getEntradesColumna(0)).thenReturn(col1);
        when(mc.getEntradesColumna(1)).thenReturn(col2);
        when(col1.size()).thenReturn(34);
        when(col2.size()).thenReturn(17);

        excp = false;

        try {
            resMc = operador.executaOperacioEstadistica(mc, OperacioEstadistica.COVARIANCIA);
        } catch (ExcepcioOperador e) {
            assertEquals(ExcepcioDomini.TipusError.OPERADOR, e.getTipusError());
            excp = true;
        }

        assertTrue(excp);
    }

    @Test
    public void testPerason() {
        // Test resultat correcte
        ArrayList<Double> x = new ArrayList<Double>();
        ArrayList<Double> y = new ArrayList<Double>();

        Iterator<EntradaMatriuCeles> iterator1 =(Iterator<EntradaMatriuCeles>) mock(Iterator.class);
        OngoingStubbing<EntradaMatriuCeles> next1 = when(iterator1.next());

        for (int i = 0; i < 34; ++i) {
            double xval = 20.5 * i + 3.5;
            x.add(xval);

            EntradaMatriuCeles e = mock(EntradaMatriuCeles.class);
            next1 = next1.thenReturn(e);

            Cela c = mock(Cela.class);
            when(e.getCela()).thenReturn(c);
            when(c.getNum()).thenReturn(xval);
            when(c.getInputUsuari()).thenReturn("test");
            when(e.getFila()).thenReturn(i);
            when(e.getColumna()).thenReturn(0);
        }

        OngoingStubbing<Boolean> hasNext1 = when(iterator1.hasNext());
        for (int i = 0; i < 34; ++i)
            hasNext1 = hasNext1.thenReturn(true);

        hasNext1.thenReturn(false);

        Iterator<EntradaMatriuCeles> iterator2 =(Iterator<EntradaMatriuCeles>) mock(Iterator.class);
        OngoingStubbing<EntradaMatriuCeles> next2 = when(iterator2.next());

        for (int i = 0; i < 34; ++i) {
            double yval = 0.5456 * i + 0.5;
            y.add(yval);

            EntradaMatriuCeles e = mock(EntradaMatriuCeles.class);
            next2 = next2.thenReturn(e);

            Cela c = mock(Cela.class);
            when(e.getCela()).thenReturn(c);
            when(c.getNum()).thenReturn(yval);
            when(c.getInputUsuari()).thenReturn("test");
            when(e.getFila()).thenReturn(i);
            when(e.getColumna()).thenReturn(1);
        }

        OngoingStubbing<Boolean> hasNext2 = when(iterator2.hasNext());
        for (int i = 0; i < 34; ++i)
            hasNext2 = hasNext2.thenReturn(true);

        hasNext2.thenReturn(false);

        MatriuCeles mc = mock(MatriuCeles.class);
        ArrayList<EntradaMatriuCeles> col1 = (ArrayList<EntradaMatriuCeles>) mock(ArrayList.class);
        ArrayList<EntradaMatriuCeles> col2 = (ArrayList<EntradaMatriuCeles>) mock(ArrayList.class);
        when(col1.iterator()).thenReturn(iterator1);
        when(col2.iterator()).thenReturn(iterator2);
        when(mc.getNumCols()).thenReturn(2);
        when(mc.getNumFiles()).thenReturn(34);
        when(mc.getEntradesColumna(0)).thenReturn(col1);
        when(mc.getEntradesColumna(1)).thenReturn(col2);
        when(col1.size()).thenReturn(34);
        when(col2.size()).thenReturn(34);

        double mx = 0.0;
        double my = 0.0;
        double res = 0.0;

        for (double d : x) mx += d;
        for (double d : y) my += d;
        mx /= x.size();
        my /= y.size();
        for (int i = 0; i < x.size(); ++i) res += (x.get(i) - mx) * (y.get(i) - my);

        double varx = 0.0;
        double vary = 0.0;

        for (double d : x) varx += (d - mx) * (d - mx);
        for (double d : y) vary += (d - my) * (d - my);

        res = res / Math.sqrt(varx * vary);

        MatriuCeles resMc = operador.executaOperacioEstadistica(mc, OperacioEstadistica.COEFICIENT_PEARSON);
        assertNotNull(resMc);
        assertEquals(1, resMc.getNumFiles());
        assertEquals(1, resMc.getNumCols());

        Cela c = resMc.getCela(0, 0);
        assertNotNull(c);
        assertTrue(c instanceof CelaNum);

        Double val = c.getNum();
        assertNotNull(val);
        assertTrue(Math.abs(res - val) < TOL);

        // Test numero incorrecte de columnes
        when(mc.getNumCols()).thenReturn(23);
        when(mc.getNumFiles()).thenReturn(34);
        boolean excp = false;

        try {
            resMc = operador.executaOperacioEstadistica(mc, OperacioEstadistica.COVARIANCIA);
        } catch (ExcepcioOperador e) {
            assertEquals(ExcepcioDomini.TipusError.OPERADOR, e.getTipusError());
            excp = true;
        }

        assertTrue(excp);

        // Test diferent quantitat de dades
        x = new ArrayList<Double>();
        y = new ArrayList<Double>();

        iterator1 =(Iterator<EntradaMatriuCeles>) mock(Iterator.class);
        next1 = when(iterator1.next());

        for (int i = 0; i < 34; ++i) {
            double xval = 20.5 * i + 3.5;
            x.add(xval);

            EntradaMatriuCeles e = mock(EntradaMatriuCeles.class);
            next1 = next1.thenReturn(e);

            Cela c2 = mock(Cela.class);
            when(e.getCela()).thenReturn(c2);
            when(c2.getNum()).thenReturn(xval);
            when(c2.getInputUsuari()).thenReturn("test");
            when(e.getFila()).thenReturn(i);
            when(e.getColumna()).thenReturn(0);
        }

        hasNext1 = when(iterator1.hasNext());
        for (int i = 0; i < 34; ++i)
            hasNext1 = hasNext1.thenReturn(true);

        hasNext1.thenReturn(false);

        iterator2 =(Iterator<EntradaMatriuCeles>) mock(Iterator.class);
        next2 = when(iterator2.next());

        for (int i = 0; i < 34; ++i) {
            if (i % 2 == 0) {
                double yval = 0.5456 * i + 0.5;
                y.add(yval);

                EntradaMatriuCeles e = mock(EntradaMatriuCeles.class);
                next2 = next2.thenReturn(e);

                Cela c2 = mock(Cela.class);
                when(e.getCela()).thenReturn(c2);
                when(c2.getNum()).thenReturn(yval);
                when(c2.getInputUsuari()).thenReturn("test");
                when(e.getFila()).thenReturn(i);
                when(e.getColumna()).thenReturn(1);
            }
        }

        hasNext2 = when(iterator2.hasNext());
        for (int i = 0; i < 17; ++i)
            hasNext2 = hasNext2.thenReturn(true);

        hasNext2.thenReturn(false);

        mc = mock(MatriuCeles.class);
        col1 = (ArrayList<EntradaMatriuCeles>) mock(ArrayList.class);
        col2 = (ArrayList<EntradaMatriuCeles>) mock(ArrayList.class);
        when(col1.iterator()).thenReturn(iterator1);
        when(col2.iterator()).thenReturn(iterator2);
        when(mc.getNumCols()).thenReturn(2);
        when(mc.getNumFiles()).thenReturn(34);
        when(mc.getEntradesColumna(0)).thenReturn(col1);
        when(mc.getEntradesColumna(1)).thenReturn(col2);
        when(col1.size()).thenReturn(34);
        when(col2.size()).thenReturn(17);

        excp = false;

        try {
            resMc = operador.executaOperacioEstadistica(mc, OperacioEstadistica.COVARIANCIA);
        } catch (ExcepcioOperador e) {
            assertEquals(ExcepcioDomini.TipusError.OPERADOR, e.getTipusError());
            excp = true;
        }

        assertTrue(excp);
    }
}
