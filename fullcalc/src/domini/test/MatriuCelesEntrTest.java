package domini.test;
import domini.CelaNum;
import domini.CelaText;
import domini.MatriuCeles;
import domini.EntradaMatriuCeles;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class MatriuCelesEntrTest {

    @Parameter(0)
    public EntradaMatriuCeles cela1;
    @Parameter(1)
    public EntradaMatriuCeles cela2;
    @Parameter(2)
    public int numNulls;

    MatriuCeles matriu;

    @Before
    public void setUp() {
        matriu = new MatriuCeles(30, 15);
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{(new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                (new EntradaMatriuCeles(4, 5, new CelaText("Hola"))), 0},
                {(new EntradaMatriuCeles(15, 2, new CelaText("foo"))),
                        (new EntradaMatriuCeles(28, 2, new CelaText("Hola"))), 0},
                {(new EntradaMatriuCeles(13, 1, null)),
                        (new EntradaMatriuCeles(9, 6, null)), 2},
                {(new EntradaMatriuCeles(6, 10, null)),
                        (new EntradaMatriuCeles(3, 3, new CelaText("Hola"))), 1}};
        return Arrays.asList(data);
    }

    @Test
    public void TestGetEntrades() {
        matriu.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        matriu.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());

        ArrayList<EntradaMatriuCeles> entrades = matriu.getEntrades();
        if (numNulls == 0) {
            assertEquals(entrades.get(0).getCela(), cela1.getCela());
            assertEquals(entrades.get(1).getCela(), cela2.getCela());
        } else assertEquals(2-numNulls, entrades.size());
    }
}