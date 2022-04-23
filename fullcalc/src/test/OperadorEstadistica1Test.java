package test;

import domini.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class OperadorEstadistica1Test {
    private Operador operador;
    private static final double TOL = 1e-13;

    @Before
    public void setUp() {
        operador = Operador.getInstance();
    }

    @Test
    public void testMitjana() {
        ArrayList<Double> dades = new ArrayList<Double>();

        Iterator<EntradaMatriuCeles> iterator =(Iterator<EntradaMatriuCeles>) mock(Iterator.class);
        OngoingStubbing<EntradaMatriuCeles> next = when(iterator.next());

        for (int j = 0; j < 2; ++j) {
            for (int i = 0; i < 3; ++i) {
                double val = (double) (i + j) / 3.0;
                dades.add(val);

                EntradaMatriuCeles e = mock(EntradaMatriuCeles.class);
                next = next.thenReturn(e);

                Cela c = mock(Cela.class);
                when(e.getCela()).thenReturn(c);
                when(c.getNum()).thenReturn(val);
                when(c.getInputUsuari()).thenReturn("test");
                when(e.getFila()).thenReturn(i);
                when(e.getColumna()).thenReturn(j);
            }
        }

        OngoingStubbing<Boolean> hasNext = when(iterator.hasNext());
        for (int j = 0; j < 2; ++j)
            for (int i = 0; i < 3; ++i)
                hasNext = hasNext.thenReturn(true);

        hasNext.thenReturn(false);

        MatriuCeles mc = mock(MatriuCeles.class);
        ArrayList<EntradaMatriuCeles> entrades = (ArrayList<EntradaMatriuCeles>) mock(ArrayList.class);
        when(entrades.iterator()).thenReturn(iterator);
        when(mc.getEntrades()).thenReturn(entrades);
        when(entrades.size()).thenReturn(2 * 3);

        double res = 0.0;
        for (double d : dades) res += d;
        res /= dades.size();

        MatriuCeles resMc = operador.executaOperacioEstadistica(mc, OperacioEstadistica.MITJANA);
        assertNotNull(resMc);
        assertEquals(1, resMc.getNumFiles());
        assertEquals(1, resMc.getNumCols());

        Cela c = resMc.getCela(0, 0);
        assertNotNull(c);
        assertTrue(c instanceof CelaNum);

        Double val = c.getNum();
        assertNotNull(val);
        assertTrue(Math.abs(res - val) < TOL);
    }

    @Test
    public void testMediana() {
        ArrayList<Double> dades = new ArrayList<Double>();

        Iterator<EntradaMatriuCeles> iterator =(Iterator<EntradaMatriuCeles>) mock(Iterator.class);
        OngoingStubbing<EntradaMatriuCeles> next = when(iterator.next());

        for (int j = 0; j < 9; ++j) {
            for (int i = 0; i < 43; ++i) {
                double val = (double) (i - j) / 5.0;
                dades.add(val);

                EntradaMatriuCeles e = mock(EntradaMatriuCeles.class);
                next = next.thenReturn(e);

                Cela c = mock(Cela.class);
                when(e.getCela()).thenReturn(c);
                when(c.getNum()).thenReturn(val);
                when(c.getInputUsuari()).thenReturn("test");
                when(e.getFila()).thenReturn(i);
                when(e.getColumna()).thenReturn(j);
            }
        }

        OngoingStubbing<Boolean> hasNext = when(iterator.hasNext());
        for (int j = 0; j < 9; ++j)
            for (int i = 0; i < 43; ++i)
                hasNext = hasNext.thenReturn(true);

        hasNext.thenReturn(false);

        MatriuCeles mc = mock(MatriuCeles.class);
        ArrayList<EntradaMatriuCeles> entrades = (ArrayList<EntradaMatriuCeles>) mock(ArrayList.class);
        when(entrades.iterator()).thenReturn(iterator);
        when(mc.getEntrades()).thenReturn(entrades);
        when(entrades.size()).thenReturn(43 * 9);

        Collections.sort(dades);
        double res = dades.get(dades.size() / 2);

        MatriuCeles resMc = operador.executaOperacioEstadistica(mc, OperacioEstadistica.MEDIANA);
        assertNotNull(resMc);
        assertEquals(1, resMc.getNumFiles());
        assertEquals(1, resMc.getNumCols());

        Cela c = resMc.getCela(0, 0);
        assertNotNull(c);
        assertTrue(c instanceof CelaNum);

        Double val = c.getNum();
        assertNotNull(val);
        assertTrue(Math.abs(res - val) < TOL);
    }

    @Test
    public void testVariancia() {
        ArrayList<Double> dades = new ArrayList<Double>();

        Iterator<EntradaMatriuCeles> iterator =(Iterator<EntradaMatriuCeles>) mock(Iterator.class);
        OngoingStubbing<EntradaMatriuCeles> next = when(iterator.next());

        for (int j = 0; j < 22; ++j) {
            for (int i = 0; i < 4; ++i) {
                double val = (double) (i + 2.0 * j) / 7.0;
                dades.add(val);

                EntradaMatriuCeles e = mock(EntradaMatriuCeles.class);
                next = next.thenReturn(e);

                Cela c = mock(Cela.class);
                when(e.getCela()).thenReturn(c);
                when(c.getNum()).thenReturn(val);
                when(c.getInputUsuari()).thenReturn("test");
                when(e.getFila()).thenReturn(i);
                when(e.getColumna()).thenReturn(j);
            }
        }

        OngoingStubbing<Boolean> hasNext = when(iterator.hasNext());
        for (int j = 0; j < 22; ++j)
            for (int i = 0; i < 4; ++i)
                hasNext = hasNext.thenReturn(true);

        hasNext.thenReturn(false);

        MatriuCeles mc = mock(MatriuCeles.class);
        ArrayList<EntradaMatriuCeles> entrades = (ArrayList<EntradaMatriuCeles>) mock(ArrayList.class);
        when(entrades.iterator()).thenReturn(iterator);
        when(mc.getEntrades()).thenReturn(entrades);
        when(entrades.size()).thenReturn(22 * 4);

        double mitj = 0.0;
        for (double d : dades) mitj += d;
        mitj /= dades.size();

        double res = 0.0;
        for (double d : dades) res += (d - mitj) * (d - mitj);
        res /= dades.size();

        MatriuCeles resMc = operador.executaOperacioEstadistica(mc, OperacioEstadistica.VARIANCIA);
        assertNotNull(resMc);
        assertEquals(1, resMc.getNumFiles());
        assertEquals(1, resMc.getNumCols());

        Cela c = resMc.getCela(0, 0);
        assertNotNull(c);
        assertTrue(c instanceof CelaNum);

        Double val = c.getNum();
        assertNotNull(val);
        assertTrue(Math.abs(res - val) < TOL);
    }

    @Test
    public void testDesviacioEstandard() {
        ArrayList<Double> dades = new ArrayList<Double>();

        Iterator<EntradaMatriuCeles> iterator =(Iterator<EntradaMatriuCeles>) mock(Iterator.class);
        OngoingStubbing<EntradaMatriuCeles> next = when(iterator.next());

        for (int j = 0; j < 234; ++j) {
            for (int i = 0; i < 65; ++i) {
                double val = (5.0 * i + 5.7 * j) / 8.34;
                dades.add(val);

                EntradaMatriuCeles e = mock(EntradaMatriuCeles.class);
                next = next.thenReturn(e);

                Cela c = mock(Cela.class);
                when(e.getCela()).thenReturn(c);
                when(c.getNum()).thenReturn(val);
                when(c.getInputUsuari()).thenReturn("test");
                when(e.getFila()).thenReturn(i);
                when(e.getColumna()).thenReturn(j);
            }
        }

        OngoingStubbing<Boolean> hasNext = when(iterator.hasNext());
        for (int j = 0; j < 234; ++j)
            for (int i = 0; i < 65; ++i)
                hasNext = hasNext.thenReturn(true);

        hasNext.thenReturn(false);

        MatriuCeles mc = mock(MatriuCeles.class);
        ArrayList<EntradaMatriuCeles> entrades = (ArrayList<EntradaMatriuCeles>) mock(ArrayList.class);
        when(entrades.iterator()).thenReturn(iterator);
        when(mc.getEntrades()).thenReturn(entrades);
        when(entrades.size()).thenReturn(234 * 65);

        double mitj = 0.0;
        for (double d : dades) mitj += d;
        mitj /= dades.size();

        double res = 0.0;
        for (double d : dades) res += (d - mitj) * (d - mitj);
        res = Math.sqrt(res / dades.size());

        MatriuCeles resMc = operador.executaOperacioEstadistica(mc, OperacioEstadistica.DESVIACIO_ESTANDARD);
        assertNotNull(resMc);
        assertEquals(1, resMc.getNumFiles());
        assertEquals(1, resMc.getNumCols());

        Cela c = resMc.getCela(0, 0);
        assertNotNull(c);
        assertTrue(c instanceof CelaNum);

        Double val = c.getNum();
        assertNotNull(val);
        assertTrue(Math.abs(res - val) < TOL);
    }
}
