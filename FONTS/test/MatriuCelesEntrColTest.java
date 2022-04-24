package test;
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
import static org.junit.Assert.assertNull;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class MatriuCelesEntrColTest {

    @Parameter(0)
    public EntradaMatriuCeles cela1;
    @Parameter(1)
    public EntradaMatriuCeles cela2;
    @Parameter(2)
    public int col;
    @Parameter(3)
    public int mida;

    MatriuCeles matriu;

    @Before
    public void setUp() {
        matriu = new MatriuCeles(30, 40);
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { (new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                (new EntradaMatriuCeles(4, 5, new CelaText("Hola"))), 1, 1},
                { (new EntradaMatriuCeles(15, 2, new CelaText("foo"))),
                        (new EntradaMatriuCeles(28, 2, new CelaText("Hola"))), 2, 2},
                { (new EntradaMatriuCeles(7, 6, new CelaNum("-42.7", -42.7))),
                        (new EntradaMatriuCeles(23, 30, new CelaNum("500.023", 500.023))), 5, 0}};
        return Arrays.asList(data);
    }

    /**
     * @brief Test parametritzat per comprovar que el metode getEntradesCol obte totes les entrades de la columna
     * pertinent de MatriuCeles. Per fer-ho, s'executa l'operacio i es confirma que les Cela obtingudes son les
     * mateixes que s'han passat i son de la columna que es demana, i que per tant no s'estan obtenint entrades d'altres
     * columnes.
     */
    @Test
    public void TestGetEntradesCol() {
        matriu.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        matriu.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());

        ArrayList<EntradaMatriuCeles> entradesCol = matriu.getEntradesColumna(col);
        assertEquals(mida, entradesCol.size());
    }
}
