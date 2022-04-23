package domini.test;
import domini.CelaNum;
import domini.CelaText;
import domini.Full;
import domini.MatriuCeles;
import domini.EntradaMatriuCeles;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;
import org.mockito.Spy;

@RunWith(Parameterized.class)
public class FullBuidaBlocTest {
    @Parameter(0)
    public EntradaMatriuCeles cela1;
    @Parameter(1)
    public EntradaMatriuCeles cela2;
    @Parameter(2)
    public EntradaMatriuCeles cela3;
    @Parameter(3)
    public int filaIni;
    @Parameter(4)
    public int colIni;
    @Parameter(5)
    public int numFiles;
    @Parameter(6)
    public int numCols;
    @Parameter(7)
    public int celesIn;

    Full full;

    @Spy
    MatriuCeles matriu;

    @Before
    public void setUp() {
        full = new Full(30, 15);
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{(new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                (new EntradaMatriuCeles(4, 5, new CelaText("Hola"))),
                (new EntradaMatriuCeles(7, 8, new CelaText("foo"))), 0, 1, 8, 8, 3},
                {(new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                        (new EntradaMatriuCeles(4, 5, new CelaText("Hola"))),
                        (new EntradaMatriuCeles(7, 8, new CelaText("foo"))), 2, 1, 8, 8, 2},
                {(new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                        (new EntradaMatriuCeles(4, 5, new CelaText("Hola"))),
                        (new EntradaMatriuCeles(7, 8, new CelaText("foo"))), 1, 1, 5, 5, 2},
                {(new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                        (new EntradaMatriuCeles(4, 5, new CelaText("Hola"))),
                        (new EntradaMatriuCeles(7, 8, new CelaText("foo"))), 2, 1, 3, 6, 1},
                {(new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                        (new EntradaMatriuCeles(4, 5, new CelaText("Hola"))),
                        (new EntradaMatriuCeles(7, 8, new CelaText("foo"))), 2, 1, 2, 2, 0}};
        return Arrays.asList(data);
    }

    @Test
    public void TestBuidaBloc() {
        full.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        full.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        full.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());

        full.buidaBloc(filaIni, colIni, numFiles, numCols);
        matriu = full.getBloc(filaIni, colIni, numFiles, numCols);
        ArrayList<EntradaMatriuCeles> entrades = matriu.getEntrades();
        assertEquals(0, entrades.size());

        entrades = full.getEntrades();
        assertEquals(3-celesIn, entrades.size());

        for (EntradaMatriuCeles E: entrades) {
            assertTrue(E.getFila() < filaIni || E.getColumna() < colIni
                    || E.getFila() >= filaIni+numFiles || E.getFila() >= colIni+numCols);
        }
    }
}