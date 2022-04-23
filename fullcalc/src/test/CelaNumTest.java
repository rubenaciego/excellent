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

    @Test
    public void TestConstructor() {
        String input = "0.1";
        Double valorInput = 0.1;
        celaNum = new CelaNum(input, valorInput);
        assertEquals("0.1", celaNum.getInputUsuari());
        assertEquals((double) 0.1, (double) celaNum.getNum(), 0.001);
        assertEquals(Cela.TipusCela.NUMERICA, celaNum.getTipusCela());
    }

    @Test
    public void TestGetAndSetNum() {
        String input = "2.0";
        Double valorInput = 2.0;
        celaNum.setNum(input, valorInput);
        assertEquals("2.0", celaNum.getInputUsuari());
        assertEquals((double) 2.0, (double) celaNum.getNum(), 0.001);
    }

    @Test
    public void TestSetAndGetNum() {
        String input = "2.0";
        Double valorInput = 2.0;
        celaNum = new CelaNum("0", 0);
        celaNum.setNum(input, valorInput);
        assertEquals("2.0", celaNum.getInputUsuari());
        assertEquals((double) 2.0, (double) celaNum.getNum(), 0.001);
    }

    @Test
    public void TestDataAndText() {
        String input = "143.789";
        Double valorInput = 143.789;
        celaNum = new CelaNum("0", 0);
        celaNum.setNum(input, valorInput);
        assertNull(celaNum.getData());
        assertNull(celaNum.getText());
    }

    @Test
    public void TestCompare1() {
        String input = "143.789";
        Double valorInput = 143.789;
        celaNum = new CelaNum(input, valorInput);
        CelaText cela2 = new CelaText("Hola");
        assertTrue(0 > celaNum.compare(cela2));
    }

    @Test
    public void TestCompare2() {
        String input = "143.789";
        Double valorInput = 143.789;
        celaNum = new CelaNum(input, valorInput);
        CelaNum cela2 = new CelaNum("2.1", 2.1);
        assertTrue(0 < celaNum.compare(cela2));
    }

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
