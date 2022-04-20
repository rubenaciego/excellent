package domini.test;
import domini.CelaText;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

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
    }

    @Test
    public void testConstructor2Parameters() {
        String input = "convMin(HolA)!";
        String text = "hola";
        celaText = new CelaText(input, text);
        assertEquals("convMin(HolA)!", celaText.getInputUsuari());
        assertEquals( "hola", celaText.getText());
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
}
