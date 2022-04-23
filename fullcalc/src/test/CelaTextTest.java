package test;
import domini.CelaText;
import domini.CelaData;
import domini.Cela;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.time.LocalDate;

//falta el compare i compareType
public class CelaTextTest {
    CelaText celaText;

    @Before
    public void setUp() {
        celaText = new CelaText("");
    }

    @Test
    public void testConstructor1Parameter() {
        String input = "Bon dia!";
        celaText = new CelaText(input);
        assertEquals("Bon dia!", celaText.getInputUsuari());
        assertEquals( "Bon dia!", celaText.getText());
        assertEquals(Cela.TipusCela.TEXTUAL, celaText.getTipusCela());
    }

    @Test
    public void testConstructor2Parameters() {
        String input = "convMin(HolA)!";
        String text = "hola";
        celaText = new CelaText(input, text);
        assertEquals("convMin(HolA)!", celaText.getInputUsuari());
        assertEquals( "hola", celaText.getText());
        assertEquals(Cela.TipusCela.TEXTUAL, celaText.getTipusCela());
    }

    @Test
    public void testSetAndGetText() {
        String input = "convMaj(hola)";
        String text = "HOLA";
        celaText.setText(input, text);
        assertEquals("convMaj(hola)", celaText.getInputUsuari());
        assertEquals("HOLA", celaText.getText());
    }

    @Test
    public void testDataAndText() {
        String input = "Adeu";
        String text = "Adeu";
        celaText.setText(input, text);
        assertNull(celaText.getData());
        assertNull(celaText.getNum());
    }

    @Test
    public void TestCompare1() {
        String input = "abc";
        celaText = new CelaText(input);
        CelaData cela2 = new CelaData("01/01/1900", LocalDate.of(1900, 1, 1));
        assertTrue(0 > celaText.compare(cela2));
    }

    @Test
    public void TestCompare2() {
        String input = "abc";
        celaText = new CelaText(input);
        CelaText cela2 = new CelaText("bcd");
        assertTrue(0 > celaText.compare(cela2));
    }

    @Test
    public void TestCopy() {
        String input = "abc";
        celaText = new CelaText(input);
        Cela c = celaText.copy();
        assertTrue(0 == celaText.compare(c));
        assertNotSame(celaText, c);
    }
}
