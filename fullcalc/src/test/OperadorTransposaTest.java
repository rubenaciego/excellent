package test;

import domini.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class OperadorTransposaTest {
    private Operador operador;

    @Parameterized.Parameter(0)
    public EntradaMatriuCeles cela1;
    @Parameterized.Parameter(1)
    public EntradaMatriuCeles cela2;
    @Parameterized.Parameter(2)
    public EntradaMatriuCeles cela3;
    @Parameterized.Parameter(3)
    public int numFiles;
    @Parameterized.Parameter(4)
    public int numCols;

    @Before
    public void setUp() {
        operador = Operador.getInstance();
    }

    /**
     * @brief Els casos comproven una matriu igual a la seva transposada (simètrica), una matriu fila i una matriu
     * amb files i columnes repartides
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "1"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "2"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test", "3"))), 3, 3},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "1"))),
                        (new EntradaMatriuCeles(0, 1, new CelaText("test", "2"))),
                        (new EntradaMatriuCeles(0, 2, new CelaText("test", "3"))), 1, 3},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "1"))),
                        (new EntradaMatriuCeles(0, 2, new CelaText("test", "2"))),
                        (new EntradaMatriuCeles(2, 3, new CelaText("test", "3"))), 3, 4}
        };

        return Arrays.asList(data);
    }

    /**
     * @brief El test comrpova que el resultat sigui una matriu cel·les igual que l'original però amb files i columnes
     * permutades (transposada)
     */
    @Test
    public void testTransposar() {
        MatriuCeles mc = new MatriuCeles(numFiles, numCols);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());

        MatriuCeles res = operador.transposa(mc);
        assertNotNull(res);

        assertEquals(numFiles, res.getNumCols());
        assertEquals(numCols, res.getNumFiles());

        ArrayList<EntradaMatriuCeles> entrades = res.getEntrades();
        assertEquals(3, entrades.size());

        EntradaMatriuCeles[] celes = {cela1, cela2, cela3};

        for (EntradaMatriuCeles e : entrades) {
            int index = Integer.parseInt(e.getCela().getText()) - 1;
            EntradaMatriuCeles e2 = celes[index];

            assertEquals(e2.getFila(), e.getColumna());
            assertEquals(e2.getColumna(), e.getFila());
            assertEquals(e2.getCela().getText(), e.getCela().getText());
        }
    }
}
