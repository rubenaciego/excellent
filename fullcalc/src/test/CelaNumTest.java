package test;
import domini.Cela;
import domini.CelaNum;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class CelaNumTest {
    CelaNum celaNum;

    @Before
    public void setUp() {
        celaNum = new CelaNum("", 0.0);
    }

    @Test
    public void testConstructor() {
        String input = "0.1";
        Double valorInput = 0.1;
        celaNum = new CelaNum(input, valorInput);
        assertEquals("0.1", celaNum.getInputUsuari());
        assertEquals((double) 0.1, (double) celaNum.getNum(), 0.001);
        assertEquals(Cela.TipusCela.NUMERICA, celaNum.getTipusCela());
    }

    @Test
    public void testGetAndSetNum() {
        String input = "2.0";
        Double valorInput = 2.0;
        celaNum.setNum(input, valorInput);
        assertEquals("2.0", celaNum.getInputUsuari());
        assertEquals((double) 2.0, (double) celaNum.getNum(), 0.001);
    }

    @Test
    public void testSetAndGetNum() {
        String input = "2.0";
        Double valorInput = 2.0;
        celaNum = new CelaNum("0", 0);
        celaNum.setNum(input, valorInput);
        assertEquals("2.0", celaNum.getInputUsuari());
        assertEquals((double) 2.0, (double) celaNum.getNum(), 0.001);
    }

    @Test
    public void testDataAndText() {
        String input = "143.789";
        Double valorInput = 143.789;
        celaNum = new CelaNum("0", 0);
        celaNum.setNum(input, valorInput);
        assertNull(celaNum.getData());
        assertNull(celaNum.getText());
    }
}