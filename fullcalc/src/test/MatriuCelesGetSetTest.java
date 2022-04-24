package test;
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
        matriu = new MatriuCeles(20, 20);
    }

    /**
     * @brief S'executa getNumFiles() i getNumCols() despres del constructor per veure que introdueixen i retornen les
     * dades correctes.
     */
    @Test
    public void TestConstructorGetNumFilesCols() {
        matriu = new MatriuCeles(15, 10);
        assertEquals(15, matriu.getNumFiles());
        assertEquals(10, matriu.getNumCols());
    }

    /**
     * @brief S'executa afegeixFila() (i el getter corresponent) per comprovar que l'addicio es correcta.
     */
    @Test
    public void TestAfegeixFila() {
        matriu = new MatriuCeles(5, 10);
        matriu.afegeixFila();
        assertEquals(6, matriu.getNumFiles());
    }

    /**
     * @brief S'executa afegeixCol() (i el getter corresponent) per comprovar que l'addicio es correcta.
     */
    @Test
    public void TestAfegeixCol() {
        matriu = new MatriuCeles(5, 10);
        matriu.afegeixColumna();
        assertEquals(11, matriu.getNumCols());
    }

    /**
     * @brief Es crida a  setCela i getCela i es comprova que s'obte la mateixa Cela.
     */
    @Test
    public void TestSetCelaGetCela1() {
        CelaText celaText = new CelaText("Hola");
        matriu.setCela(celaText, 4, 5);
        assertEquals(celaText, matriu.getCela(4, 5));
    }

    /**
     * @brief Es crida a  setCela i getCela i es comprova que s'obte la mateixa Cela, pero en aquest cas amb un tipus de
     * Cela diferent a l'anterior.
     */
    @Test
    public void TestSetCelaGetCela2() {
        CelaNum celaNum = new CelaNum("546.7", 546.7);
        matriu.setCela(celaNum, 15, 2);
        assertEquals(celaNum, matriu.getCela(15, 2));
    }

    /**
     * @brief Es crida a setCela i llavors a esborraCela, per comprovar que aquesta ultima efectivament esborra la Cela
     * de la matriu.
     */
    @Test
    public void TestEsborraCela() {
        CelaText celaText = new CelaText("Per esborrar");
        matriu.setCela(celaText, 6, 9);
        matriu.esborraCela(6, 9);
        assertNull(matriu.getCela(6, 9));
    }
}