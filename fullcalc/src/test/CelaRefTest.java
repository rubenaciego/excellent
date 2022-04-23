package test;

import domini.CelaNum;
import domini.CelaRef;
import domini.Cela;
import static org.junit.Assert.*;

import domini.CelaText;
import org.junit.Test;
import org.junit.Before;

public class CelaRefTest {
    CelaRef celaRef;

    @Before
    public void setUp() {
        celaRef = new CelaRef("", null);
    }

    @Test
    public void TestConstructor() {
        String input = "=0:0";
        CelaNum c = new CelaNum("0.1", 0.1);
        celaRef = new CelaRef(input, c);
        assertEquals("=0:0", celaRef.getInputUsuari());
        assertEquals(c, celaRef.getRef());
        assertEquals(Cela.TipusCela.REFERENCIAL, celaRef.getTipusCela());
    }

    @Test
    public void TestGetAndSetRef() {
        String input = "=3:0";
        CelaText c = new CelaText("Hola");
        celaRef.setRef(input, c);
        assertEquals("=3:0", celaRef.getInputUsuari());
        assertEquals(c, celaRef.getRef());
    }

    @Test
    public void TestGetNum() {
        String input = "2.0";
        Double valorInput = 2.0;
        CelaNum c = new CelaNum("2.0", 2.0);
        celaRef.setRef("=43:12", c);
        assertEquals((double) 2.0, (double) celaRef.getNum(), 0.001);
    }

    @Test
    public void TestGetText() {
        String input = "Ahoy!";
        CelaText c = new CelaText(input);
        celaRef.setRef("=6:9", c);
        assertEquals("Ahoy!", celaRef.getText());
    }

    @Test
    public void TestGetDataNull() {
        String text = "xd";
        CelaText c = new CelaText("xd");
        celaRef.setRef("=9:1",c);
        assertNull(celaRef.getData());
    }

    @Test
    public void TestCompare1() {
        String text = "xd";
        CelaText c = new CelaText("xd");
        String input = "143.789";
        Double valorInput = 143.789;
        CelaNum c2 = new CelaNum(input, valorInput);
        celaRef = new CelaRef("=7:7", c);

        assertTrue(0 < celaRef.compare(c2));
    }

    @Test
    public void TestCompare2() {
        String text = "xd";
        CelaText c = new CelaText("xd");
        String input = "143.789";
        Double valorInput = 143.789;
        CelaNum c2 = new CelaNum(input, valorInput);
        celaRef = new CelaRef("=7:7", c);
        CelaRef ref2 = new CelaRef("=4:2", c2);

        assertTrue(0 < celaRef.compare(ref2));
    }

    @Test
    public void TestCopy() {
        String input = "42.15";
        Double valorInput = 42.15;
        CelaNum c = new CelaNum(input, valorInput);
        celaRef = new CelaRef("=7:7", c);
        Cela copia = celaRef.copy();
        assertTrue(0 == celaRef.compare(copia));
        assertNotSame(celaRef, copia);
    }
}
