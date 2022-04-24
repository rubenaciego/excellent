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
public class OperadorOrdenaTest {
    private Operador operador;

    @Parameterized.Parameter(0)
    public EntradaMatriuCeles cela1;
    @Parameterized.Parameter(1)
    public EntradaMatriuCeles cela2;
    @Parameterized.Parameter(2)
    public EntradaMatriuCeles cela3;
    @Parameterized.Parameter(3)
    public EntradaMatriuCeles cela4;
    @Parameterized.Parameter(4)
    public EntradaMatriuCeles cela5;
    @Parameterized.Parameter(5)
    public int numFiles;
    @Parameterized.Parameter(6)
    public int numCols;
    @Parameterized.Parameter(7)
    public int[] ordreFiles;
    @Parameterized.Parameter(8)
    public int colOrdenar;

    @Before
    public void setUp() {
        operador = Operador.getInstance();
    }

    /**
     * @brief Els testos comproven casos amb cel·les d'un mateix tipus (numèriques, datades i textuals), un cas
     * amb tipus de cel·les combinades i un cas amb més d'una columna
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {
                        (new EntradaMatriuCeles(0, 0, new CelaNum("0", 2.0))),
                        (new EntradaMatriuCeles(1, 0, new CelaNum("1", 1.0))),
                        (new EntradaMatriuCeles(2, 0, new CelaNum("2", 3.0))),
                        (new EntradaMatriuCeles(3, 0, new CelaNum("3", 4.0))),
                        (new EntradaMatriuCeles(4, 0, new CelaNum("4", 0.0))),
                        5, 1, new int[]{4, 1, 0, 2, 3}, 0},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaData("0", LocalDate.now()))),
                        (new EntradaMatriuCeles(1, 0, new CelaText("1", "aa"))),
                        (new EntradaMatriuCeles(2, 0, new CelaNum("2", 2.0))),
                        (new EntradaMatriuCeles(3, 0, new CelaData("3", LocalDate.EPOCH))),
                        (new EntradaMatriuCeles(4, 0, new CelaNum("4", 0.0))),
                        5, 1, new int[]{4, 2, 1, 3, 0}, 0},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("0", "ooop"))),
                        (new EntradaMatriuCeles(1, 0, new CelaText("1", "acb"))),
                        (new EntradaMatriuCeles(2, 0, new CelaText("2", "uu"))),
                        (new EntradaMatriuCeles(3, 0, new CelaText("3", "oab"))),
                        (new EntradaMatriuCeles(4, 0, new CelaText("4", "abc"))),
                        5, 1, new int[]{4, 1, 3, 0, 2}, 0},
                {
                        (new EntradaMatriuCeles(0, 1, new CelaNum("0", 2.0))),
                        (new EntradaMatriuCeles(1, 1, new CelaNum("1", 1.0))),
                        (new EntradaMatriuCeles(2, 1, new CelaNum("2", 3.0))),
                        (new EntradaMatriuCeles(0, 0, new CelaNum("3", -1.0))),
                        (new EntradaMatriuCeles(1, 0, new CelaNum("4", 0.0))),
                        3, 2, new int[]{1, 0, 2}, 1}
        };

        return Arrays.asList(data);
    }

    /**
     * @brief El test comprova que l'ordre resultant de les files coincideix amb el que es passa com a paràmetre
     * (que ha de ser l'ordre després d'ordenar respecte la columna passada com a paràmetre) i que es mantenen els
     * continguts de les cel·les així com la columna on eren
     */
    @Test
    public void testOrdenar() {
        EntradaMatriuCeles[] celes = {cela1, cela2, cela3, cela4, cela5};
        MatriuCeles mc = new MatriuCeles(numFiles, numCols);

        for (EntradaMatriuCeles e : celes)
            mc.setCela(e.getCela(), e.getFila(), e.getColumna());

        MatriuCeles res = operador.ordena(mc, colOrdenar, CriteriOrdenacio.ASCENDENT);
        assertNotNull(res);

        assertEquals(numFiles, res.getNumFiles());
        assertEquals(numCols, res.getNumCols());

        ArrayList<EntradaMatriuCeles> entrades = res.getEntrades();
        assertEquals(5, entrades.size());

        for (EntradaMatriuCeles e : entrades) {
            int index = Integer.parseInt(e.getCela().getInputUsuari());
            EntradaMatriuCeles e2 = celes[index];

            assertEquals(e2.getFila(), ordreFiles[e.getFila()]);
            assertEquals(e2.getColumna(), e.getColumna());
            assertEquals(e2.getCela().getText(), e.getCela().getText());
            assertEquals(e2.getCela().getData(), e.getCela().getData());
            assertEquals(e2.getCela().getNum(), e.getCela().getNum());
        }
    }
}
