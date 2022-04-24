package test;

import domini.CelaText;
import domini.EntradaMatriuCeles;
import domini.MatriuCeles;
import domini.Operador;
import net.sf.json.JSONObject;
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
public class OperadorLongitudTest {
    private Operador operador;

    @Parameterized.Parameter(0)
    public EntradaMatriuCeles cela1;
    @Parameterized.Parameter(1)
    public EntradaMatriuCeles cela2;
    @Parameterized.Parameter(2)
    public EntradaMatriuCeles cela3;

    @Before
    public void setUp() {
        operador = Operador.getInstance();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("0", "la paraula no hi es"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("1", "m'agrada molt el peix"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("2", "peixexegepeix")))},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("0", "lele"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("1", "lelelelelele"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("2", "")))},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("0", "hola bon dia"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("1", "a a a a a"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("2",
                                "abcdefghijklmnopqrstuvwxyz0123456789")))}
        };

        return Arrays.asList(data);
    }

    @Test
    public void testLongitud() {
        MatriuCeles mc = new MatriuCeles(3, 3);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());

        double[] longs = {cela1.getCela().getText().length(),
            cela2.getCela().getText().length(),
            cela3.getCela().getText().length()};

        MatriuCeles res = operador.extreuLongitudText(mc);
        assertNotNull(res);

        assertEquals(3, res.getNumCols());
        assertEquals(3, res.getNumFiles());

        ArrayList<EntradaMatriuCeles> entrades = res.getEntrades();

        for (int i = 0; i < entrades.size(); ++i) {
            Double l = entrades.get(i).getCela().getNum();
            assertNotNull(l);
            assertEquals((Double)longs[i], l);
        }
    }
}
