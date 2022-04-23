package domini.test;

import domini.Cela;
import domini.CelaData;
import domini.CelaText;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CelaDataTest {
    CelaData celaData;

    @Before
    public void setUp() {
        LocalDate localDate = LocalDate.of(1900, 1, 1);
        celaData = new CelaData("01/01/1900",localDate);
    }

    @Test
    public void TestConstructor() {
        String input = "24/04/2022";
        LocalDate data = LocalDate.of(2022, 4, 24);
        celaData = new CelaData(input, data);
        assertEquals("24/04/2022", celaData.getInputUsuari());
        assertEquals( LocalDate.parse("2022-04-24"), celaData.getData());
        assertEquals(Cela.TipusCela.DATADA, celaData.getTipusCela());
    }

    @Test
    public void TestSetAndGetText() {
        String input = "20/04/2022";
        LocalDate data = LocalDate.of(2022, 4, 20);
        celaData.setData(input, data);
        assertEquals("20/04/2022", celaData.getInputUsuari());
        assertEquals(LocalDate.parse("2022-04-20"), celaData.getData());
    }

    @Test
    public void TestNumAndText() {
        String input = "28/07/2001";
        LocalDate data = LocalDate.of(2001, 7, 28);
        celaData.setData(input, data);
        assertNull(celaData.getNum());
        assertNull(celaData.getText());
    }

    @Test
    public void TestCompare1() {
        LocalDate data = LocalDate.of(1900, 1, 1);
        celaData = new CelaData("01/01/1900", data);
        CelaText cela2 = new CelaText("Adeu!");
        assertTrue(0 < celaData.compare(cela2));
    }

    @Test
    public void TestCompare2() {
        LocalDate data = LocalDate.of(1900, 1, 1);
        celaData = new CelaData("01/01/1900", data);
        CelaData cela2 = new CelaData("24/04/2022", LocalDate.of(2022, 4, 24));
        assertTrue(0 > celaData.compare(cela2));
    }

    @Test
    public void TestCopy() {
        LocalDate data = LocalDate.of(2000, 1, 1);
        celaData = new CelaData("01/01/2000", data);
        CelaData c = celaData.copy();
        assertTrue(0 == celaData.compare(c));
        assertNotSame(celaData, c);
    }
}
