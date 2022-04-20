package domini.test;

import domini.CelaData;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CelaDataTest {
    CelaData celaData;

    @Before
    public void setUp() {
        LocalDate localDate = LocalDate.of(1900, 1, 1);
        celaData = new CelaData("01/01/1900",localDate);
    }

    @Test
    public void testConstructor() {
        String input = "24/04/2022";
        LocalDate data = LocalDate.of(2022, 4, 24);
        celaData = new CelaData(input, data);
        assertEquals("24/04/2022", celaData.getInputUsuari());
        assertEquals( LocalDate.parse("2022-04-24"), celaData.getData());
    }

    @Test
    public void testSetAndGetText() {
        String input = "20/04/2022";
        LocalDate data = LocalDate.of(2022, 4, 20);
        celaData.setData(input, data);
        assertEquals("20/04/2022", celaData.getInputUsuari());
        assertEquals(LocalDate.parse("2022-04-20"), celaData.getData());
    }

    @Test
    public void testNumAndText() {
        String input = "28/07/2001";
        LocalDate data = LocalDate.of(2001, 7, 28);
        celaData.setData(input, data);
        assertNull(celaData.getNum());
        assertNull(celaData.getText());
    }
}
