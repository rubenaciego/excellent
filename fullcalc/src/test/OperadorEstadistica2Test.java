package test;

import domini.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class OperadorEstadistica2Test {
    private Operador operador;
    private static final double TOL = 1e-16;

    @Before
    public void setUp() {
        operador = Operador.getInstance();
    }

    @Test
    public void testCovariancia() {
        // Test resultat correcte
        MatriuCeles mc = new MatriuCeles(34, 2);
        ArrayList<Double> x = new ArrayList<Double>();
        ArrayList<Double> y = new ArrayList<Double>();

        for (int i = 0; i < 34; ++i) {
            double xval = 20.5 * i + 3.5;
            double yval = 0.5456 * i + 0.5;

            x.add(xval);
            y.add(yval);

            mc.setCela(new CelaNum("test", xval), i, 0);
            mc.setCela(new CelaNum("test", yval), i, 1);
        }

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
        mc = new MatriuCeles(34, 23);
        boolean excp = false;

        try {
            resMc = operador.executaOperacioEstadistica(mc, OperacioEstadistica.COVARIANCIA);
        } catch (ExcepcioOperador e) {
            assertEquals(ExcepcioDomini.TipusError.OPERADOR, e.getTipusError());
            excp = true;
        }

        assertTrue(excp);

        // Test diferent quantitat de dades
        mc = new MatriuCeles(37, 2);

        for (int i = 0; i < 37; ++i) {
            double xval = 20.5 * i + 3.5;
            double yval = 0.5456 * i + 0.5;

            mc.setCela(new CelaNum("test", xval), i, 0);

            if (i % 2 == 0)
                mc.setCela(new CelaNum("test", yval), i, 1);
        }

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
        MatriuCeles mc = new MatriuCeles(34, 2);
        ArrayList<Double> x = new ArrayList<Double>();
        ArrayList<Double> y = new ArrayList<Double>();

        for (int i = 0; i < 34; ++i) {
            double xval = 20.5 * i + 3.5;
            double yval = 0.5456 * i + 0.5;

            x.add(xval);
            y.add(yval);

            mc.setCela(new CelaNum("test", xval), i, 0);
            mc.setCela(new CelaNum("test", yval), i, 1);
        }

        double mx = 0.0;
        double my = 0.0;
        double varx = 0.0;
        double vary = 0.0;
        double res = 0.0;

        for (double d : x) mx += d;
        for (double d : y) my += d;
        mx /= x.size();
        my /= y.size();
        for (int i = 0; i < x.size(); ++i) res += (x.get(i) - mx) * (y.get(i) - my);
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
        mc = new MatriuCeles(34, 23);
        boolean excp = false;

        try {
            resMc = operador.executaOperacioEstadistica(mc, OperacioEstadistica.COEFICIENT_PEARSON);
        } catch (ExcepcioOperador e) {
            assertEquals(ExcepcioDomini.TipusError.OPERADOR, e.getTipusError());
            excp = true;
        }

        assertTrue(excp);

        // Test diferent quantitat de dades
        mc = new MatriuCeles(37, 2);

        for (int i = 0; i < 37; ++i) {
            double xval = 20.5 * i + 3.5;
            double yval = 0.5456 * i + 0.5;

            mc.setCela(new CelaNum("test", xval), i, 0);

            if (i % 2 == 0)
                mc.setCela(new CelaNum("test", yval), i, 1);
        }

        excp = false;

        try {
            resMc = operador.executaOperacioEstadistica(mc, OperacioEstadistica.COEFICIENT_PEARSON);
        } catch (ExcepcioOperador e) {
            assertEquals(ExcepcioDomini.TipusError.OPERADOR, e.getTipusError());
            excp = true;
        }

        assertTrue(excp);
    }
}
