package test;
import domini.CelaText;
import domini.Full;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FullGetSetTest {
    Full full;

    @Before
    public void setUp() {
        full = new Full(20, 20);
    }

    /**
     * @brief S'executa getNumFiles() i getNumCols() despres del constructor per veure que introdueixen i retornen les
     * dades correctes.
     */
    @Test
    public void TestConstructorGetNumFilesCols() {
        full = new Full(15, 10);
        assertEquals(15, full.getNumFiles());
        assertEquals(10, full.getNumCols());
    }

    /**
     * @brief S'executa getCela() heredat de MatriuCeles, pero en un cas que en aquest no es contempla, que es per
     * obtenir la Cela Resultat.
     */
    @Test
    public void TestSetCelaGetCelaRes() {
        CelaText celaText = new CelaText("Hola");
        full.setCela(celaText, -1, -1);
        assertEquals(celaText, full.getCela(-1, -1));
    }
}
