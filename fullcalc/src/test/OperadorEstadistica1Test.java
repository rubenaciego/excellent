package test;

import domini.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

public class OperadorEstadistica1Test {
    private Operador operador;
    private static final double TOL = 1e-13;

    @Before
    public void setUp() {
        operador = Operador.getInstance();
    }

    @Test
    public void testMitjana() {
        MatriuCeles mc = new MatriuCeles(3, 2);
        ArrayList<Double> dades = new ArrayList<Double>();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 2; ++j) {
                double val = (double) (i + j) / 3.0;
                mc.setCela(new CelaNum("test", val), i, j);
                dades.add(val);
            }
        }

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
        MatriuCeles mc = new MatriuCeles(43, 9);
        ArrayList<Double> dades = new ArrayList<Double>();

        for (int i = 0; i < 43; ++i) {
            for (int j = 0; j < 9; ++j) {
                double val = (double) (i - j) / 5.0;
                mc.setCela(new CelaNum("test", val), i, j);
                dades.add(val);
            }
        }

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
        MatriuCeles mc = new MatriuCeles(4, 22);
        ArrayList<Double> dades = new ArrayList<Double>();

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 22; ++j) {
                double val = (double) (i + 2.0 * j) / 7.0;
                mc.setCela(new CelaNum("test", val), i, j);
                dades.add(val);
            }
        }

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
        MatriuCeles mc = new MatriuCeles(65, 234);
        ArrayList<Double> dades = new ArrayList<Double>();

        for (int i = 0; i < 65; ++i) {
            for (int j = 0; j < 234; ++j) {
                double val = (double) (5.0 * i + 5.7 * j) / 8.34;
                mc.setCela(new CelaNum("test", val), i, j);
                dades.add(val);
            }
        }

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
