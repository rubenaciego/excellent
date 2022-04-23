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

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;
import org.mockito.Spy;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FullCopiaBlocTest {
    @Parameter(0)
    public EntradaMatriuCeles cela1;
    @Parameter(1)
    public EntradaMatriuCeles cela2;
    @Parameter(2)
    public int filaIni;
    @Parameter(3)
    public int colIni;
    @Parameter(4)
    public int numFiles;
    @Parameter(5)
    public int numCols;
    @Parameter(6)
    public int filaFi;
    @Parameter(7)
    public int colFi;
    @Parameter(8)
    public int celesTotal;

    Full full;

    @Before
    public void setUp() {
        full = new Full(30, 30);
    }

    @Spy
    MatriuCeles matriu;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{(new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                (new EntradaMatriuCeles(2, 3, new CelaText("Hola"))), 0, 1, 3, 3, 4, 4, 4},
                {(new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                        (new EntradaMatriuCeles(2, 1, new CelaText("Hola"))), 1, 1, 2, 1, 0, 1, 3},
                {(new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                        (new EntradaMatriuCeles(2, 1, new CelaText("Hola"))), 1, 1, 2, 1, 2, 1, 3},
                {(new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                        (new EntradaMatriuCeles(1, 2, new CelaText("Hola"))), 1, 1, 1, 2, 1, 2, 3},
                {(new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                        (new EntradaMatriuCeles(1, 2, new CelaText("Hola"))), 1, 1, 1, 2, 1, 0, 3}};
        return Arrays.asList(data);
    }

    @Test
    public void TestCopiaBloc() {
        full.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        full.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());

        full.copiaBloc(filaIni, colIni, numFiles, numCols, filaFi, colFi);
        int chF = filaFi-filaIni;
        int chC = colFi-colIni;

        assertEquals(0, cela1.getCela().compare(full.getCela(cela1.getFila()+chF, cela1.getColumna()+chC)));
        assertNotSame(cela1.getCela(), full.getCela(cela1.getFila()+chF, cela1.getColumna()+chC));
        assertEquals(0, cela2.getCela().compare(full.getCela(cela2.getFila()+chF, cela2.getColumna()+chC)));
        assertNotSame(cela2.getCela(), full.getCela(cela2.getFila()+chF, cela2.getColumna()+chC));

        assertEquals(celesTotal, full.getEntrades().size());
    }
}