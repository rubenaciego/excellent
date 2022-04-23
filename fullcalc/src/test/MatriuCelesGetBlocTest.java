package test;

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

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;
import org.mockito.Spy;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MatriuCelesGetBlocTest {
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

    private Full full;

    @Spy
    private MatriuCeles matriu;

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

    /**
     * @brief Test parametritzat per comprovar que el metode getBloc buida el bloc concret que se li passa. Per
     * fer-ho, s'obte el mateix bloc que es volia esborrar i es comprova que efectivament no hi ha cap entrada.
     * Finalment es comprova que cap de la resta d'entrades del full s'han esborrat. Cada un dels testos parametritzats
     * es dedica a casos diferenciats: s'esborren totes les Cela de full, una part d'elles o cap.
     */
    @Test
    public void TestGetBloc() {
        full.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        full.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        full.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        matriu = full.getBloc(filaIni, colIni, numFiles, numCols);
        ArrayList<EntradaMatriuCeles> entrades = matriu.getEntrades();
        assertEquals(celesIn, entrades.size());

        for (EntradaMatriuCeles E: entrades) {
            assertTrue(E.getFila() >= 0 && E.getColumna() >= 0
                    && E.getFila() < numFiles && E.getFila() < numCols);
        }
    }
}
