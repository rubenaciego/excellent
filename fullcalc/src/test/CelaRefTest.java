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

    /**
     * @brief S'executa el constructor de la classe, i es comprova que efectivament s'ha executat correctament amb el
     * getter corresponent.
     */
    @Test
    public void TestConstructor() {
        String input = "=0:0";
        CelaNum c = new CelaNum("0.1", 0.1);
        celaRef = new CelaRef(input, c);
        assertEquals("=0:0", celaRef.getInputUsuari());
        assertEquals(c, celaRef.getRef());
        assertEquals(Cela.TipusCela.REFERENCIAL, celaRef.getTipusCela());
    }

    /**
     * @brief S'executa els setters i getters de la classe per veure que introdueixen i retornen les dades correctes.
     */
    @Test
    public void TestGetAndSetRef() {
        String input = "=3:0";
        CelaText c = new CelaText("Hola");
        celaRef.setRef(input, c);
        assertEquals("=3:0", celaRef.getInputUsuari());
        assertEquals(c, celaRef.getRef());
    }

    /**
     * @brief S'executa getNum() de celaRef que referencia a una CelaNum per veure que retorna el valor correcte.
     */
    @Test
    public void TestGetNum() {
        String input = "2.0";
        Double valorInput = 2.0;
        CelaNum c = new CelaNum("2.0", 2.0);
        celaRef.setRef("=43:12", c);
        assertEquals((double) 2.0, (double) celaRef.getNum(), 0.001);
    }

    /**
     * @brief S'executa getText() de celaRef que referencia a una CelaText per veure que retorna el valor correcte.
     */
    @Test
    public void TestGetText() {
        String input = "Ahoy!";
        CelaText c = new CelaText(input);
        celaRef.setRef("=6:9", c);
        assertEquals("Ahoy!", celaRef.getText());
    }

    /**
     * @brief S'executa getData() de celaRef que referencia a una CelaText per veure que retorna null.
     */
    @Test
    public void TestGetDataNull() {
        String text = "xd";
        CelaText c = new CelaText("xd");
        celaRef.setRef("=9:1",c);
        assertNull(celaRef.getData());
    }

    /**
     * @brief Primer test amb l'operacio compare(Cela). En aquest cas comprovem que la comparacio es correcta entre
     * celaRef (de fet, de la Cela que aquest referencia) i una cela d'un altre tipus.
     */
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

    /**
     * @brief Segon test amb l'operacio compare(Cela). Es comprova que la comparacio entre dues CelaRef (és a dir,
     * de les Cela que referencien) es correcta.
     */
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

    /**
     * @brief Test per comprovar el funcionament del metode copy de Cela. Es comprova que les dues celes tinguin el
     * mateix contingut pero que no referenciin al mateix objecte.
     */
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
