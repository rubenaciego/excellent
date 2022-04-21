package domini.test;
import domini.CelaNum;
import domini.CelaText;
import domini.MatriuCeles;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MatriuCelesGetSetTest {
    MatriuCeles matriu;

    @Before
    public void setUp() {
        matriu = new MatriuCeles();
    }

    @Test
    public void TestConstructorGetNumFilesCols() {
        matriu = new MatriuCeles(15, 10);
        assertEquals(15, matriu.getNumFiles());
        assertEquals(10, matriu.getNumCols());
    }

    @Test
    public void TestSetCelaGetCela1() {
        CelaText celaText = new CelaText("Hola");
        matriu.setCela(celaText, 4, 5);
        assertEquals(celaText, matriu.getCela(4, 5));
    }

    // Fer un cas fora limits, excepcions tb haurien d'estar a MatriuCeles
    @Test
    public void TestSetCelaGetCela2() {
        CelaNum celaNum = new CelaNum("546.7", 546.7);
        matriu.setCela(celaNum, 15, 2);
        assertEquals(celaNum, matriu.getCela(15, 2));
    }

    @Test
    public void TestEsborraCela() {
        CelaText celaText = new CelaText("Per esborrar");
        matriu.setCela(celaText, 6, 9);
        matriu.esborraCela(6, 9);
        assertNull(matriu.getCela(6, 9));
    }
}
