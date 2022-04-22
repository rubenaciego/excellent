package test;
import domini.CelaNum;
import domini.CelaText;
import domini.MatriuCeles;
import domini.EntradaMatriuCeles;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class MatriuCelesElimCol {

    @Parameter(0)
    public EntradaMatriuCeles cela1;
    @Parameter(1)
    public EntradaMatriuCeles cela2;
    @Parameter(2)
    public EntradaMatriuCeles cela3;
    @Parameter(3)
    public int colAEliminar;

    MatriuCeles matriu;

    @Before
    public void setUp() {
        matriu = new MatriuCeles(30, 30);
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{(new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                (new EntradaMatriuCeles(4, 2, new CelaText("Hola"))),
                (new EntradaMatriuCeles(15, 5, new CelaText("foo"))), 2},
                {(new EntradaMatriuCeles(6, 3, new CelaText("bar"))),
                        (new EntradaMatriuCeles(9, 6, new CelaNum("143.0", 143.0))),
                        (new EntradaMatriuCeles(10, 7, new CelaText("Hola"))), 6}};
        return Arrays.asList(data);
    }

    @Test
    public void TestEliminaCol() {
        matriu.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        matriu.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        matriu.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        matriu.eliminaColumna(colAEliminar);

        assertEquals(cela1.getCela(), matriu.getCela(cela1.getFila(), cela1.getColumna()));
        assertEquals(cela3.getCela(), matriu.getCela(cela3.getFila(), cela3.getColumna()-1));
        assertNotEquals(cela2.getCela(), matriu.getCela(cela2.getFila(), cela3.getColumna()));
        assertNotEquals(cela2.getCela(), matriu.getCela(cela2.getFila(), cela3.getColumna()-1));
    }
}
