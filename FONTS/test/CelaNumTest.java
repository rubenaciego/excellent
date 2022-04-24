package test;
import domini.Cela;
import domini.CelaNum;
import static org.junit.Assert.*;

import domini.CelaText;
import org.junit.Test;
import org.junit.Before;

public class CelaNumTest {
    CelaNum celaNum;

    @Before
    public void setUp() {
        celaNum = new CelaNum("", 0.0);
    }

    /**
     * @brief S'executa el constructor de la classe, i es comprova que efectivament s'ha executat correctament amb el
     * getter corresponent.
     */
    @Test
    public void TestConstructor() {
        String input = "0.1";
        Double valorInput = 0.1;
        celaNum = new CelaNum(input, valorInput);
        assertEquals("0.1", celaNum.getInputUsuari());
        assertEquals((double) 0.1, (double) celaNum.getNum(), 0.001);
        assertEquals(Cela.TipusCela.NUMERICA, celaNum.getTipusCela());
    }

    /**
     * @brief S'executa els setters i getters de la classe per veure que introdueixen i retornen les dades correctes.
     */
    @Test
    public void TestGetAndSetNum() {
        String input = "2.0";
        Double valorInput = 2.0;
        celaNum.setNum(input, valorInput);
        assertEquals("2.0", celaNum.getInputUsuari());
        assertEquals((double) 2.0, (double) celaNum.getNum(), 0.001);
    }

    /**
     * @brief S'executa getData() i getText() en celaNum per veure que efectivament retornen null.
     */
    @Test
    public void TestDataAndText() {
        String input = "143.789";
        Double valorInput = 143.789;
        celaNum = new CelaNum("0", 0);
        celaNum.setNum(input, valorInput);
        assertNull(celaNum.getData());
        assertNull(celaNum.getText());
    }

    /**
     * @brief Primer test amb l'operació compare(Cela). En aquest cas comprovem que la comparacio es correcta entre
     * celaNum i una cela d'un altre tipus.
     */
    @Test
    public void TestCompare1() {
        String input = "143.789";
        Double valorInput = 143.789;
        celaNum = new CelaNum(input, valorInput);
        CelaText cela2 = new CelaText("Hola");
        assertTrue(0 > celaNum.compare(cela2));
    }

    /**
     * @brief Segon test amb l'operacio compare(Cela). Es comprova que la comparacio entre dues CelaNum es correcta.
     */
    @Test
    public void TestCompare2() {
        String input = "143.789";
        Double valorInput = 143.789;
        celaNum = new CelaNum(input, valorInput);
        CelaNum cela2 = new CelaNum("2.1", 2.1);
        assertTrue(0 < celaNum.compare(cela2));
    }

    /**
     * @brief Test per comprovar el funcionament del metode copy de Cela. Es comprova que les dues celes tinguin el
     * mateix contingut pero que no referenciin al mateix objecte.
     */
    @Test
    public void TestCopy() {
        String input = "42.15";
        Double valorInput = 42.15;
        celaNum = new CelaNum(input, valorInput);
        Cela c = celaNum.copy();
        assertTrue(0 == celaNum.compare(c));
        assertNotSame(celaNum, c);
    }
}
