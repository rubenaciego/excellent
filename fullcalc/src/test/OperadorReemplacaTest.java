package test;

import domini.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class OperadorReemplacaTest {
    private Operador operador;

    @Parameterized.Parameter(0)
    public EntradaMatriuCeles cela1;
    @Parameterized.Parameter(1)
    public EntradaMatriuCeles cela2;
    @Parameterized.Parameter(2)
    public EntradaMatriuCeles cela3;
    @Parameterized.Parameter(3)
    public String cerca;
    @Parameterized.Parameter(4)
    public String reemplacar;

    @Before
    public void setUp() {
        operador = Operador.getInstance();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "la paraula no hi es"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "m'agrada molt el peix"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test", "peixexegepeix"))),
                        "peix", "ruben"},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "lele"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "lelelelelele"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test", ""))),
                        "lele", "abab"},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "hola bon dia"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "a a a a a"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test",
                                "abcdefghijklmnopqrstuvwxyz0123456789"))), "a", "!!!!"}
        };

        return Arrays.asList(data);
    }

    @Test
    public void testReemplacar() {
        MatriuCeles mc = new MatriuCeles(3, 3);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());

        MatriuCeles res = operador.reemplaca(mc, cerca, reemplacar);
        assertNotNull(res);

        assertEquals(3, res.getNumCols());
        assertEquals(3, res.getNumFiles());

        assertEquals(cela1.getCela().getText().replaceAll(cerca, reemplacar),
                res.getCela(cela1.getFila(), cela1.getColumna()).getText());
        assertEquals(cela2.getCela().getText().replaceAll(cerca, reemplacar),
                res.getCela(cela2.getFila(), cela2.getColumna()).getText());
        assertEquals(cela2.getCela().getText().replaceAll(cerca, reemplacar),
                res.getCela(cela2.getFila(), cela2.getColumna()).getText());
    }
}
