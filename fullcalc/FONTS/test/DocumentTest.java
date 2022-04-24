package test;

import domini.ExcepcioDomini;
import domini.Full;
import domini.Document;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DocumentTest {
    private Document document;

    @Before
    public void setUp() {
        document = new Document("DocumentTest");
    }

    /**
     * @brief Test del constructor de Document, només cal comprovar que s'ha guardat el nom
     * del document corrcetament i que és buit (zero fulls)
     */
    @Test
    public void testContructor() {
        document = new Document("TestName");
        assertEquals("TestName", document.getNom());
        assertEquals(0, document.getNumFulls());
    }

    /**
     * @brief Test que comprova el getter i setter de la data
     */
    @Test
    public void testSetGetData() {
        LocalDateTime now = LocalDateTime.now();
        document.setDataModificacio(now);
        assertEquals(now, document.getDataModificacio());
    }

    /**
     * @brief En aquest test comprovem que podem afegir i eliminar fulls i el resultat és l'esperat,
     * que al eliminar un full en un índex concret el vector de fulls es desplaci
     */
    @Test
    public void testFulls() {
        for (int i = 0; i < 10; ++i)
            document.afegeixFull();

        assertEquals(10, document.getNumFulls());
        ArrayList<Full> fulls = new ArrayList<Full>();

        for (int i = 0; i < 10; ++i) {
            Full f = document.getFull(i);
            assertNotNull(f);
            fulls.add(f);
        }

        document.eliminaFull(5);
        fulls.remove(5);

        for (int i = 0; i < 9; ++i) {
            Full f1 = document.getFull(i);
            Full f2 = fulls.get(i);

            assertEquals(f1, f2);
        }
    }

    /**
     * @brief En aquest test comprovem que els accessos a índexs invàlids de fulls del document
     * disparen correctament les excepcions
     */
    @Test
    public void testFullInvalid() {
        for (int i = 0; i < 10; ++i)
            document.afegeixFull();

        boolean excep = false;

        try {
            document.getFull(10);
        } catch (ExcepcioDomini e)
        {
            assertEquals(ExcepcioDomini.TipusError.INDEX_FULL_INVALID, e.getTipusError());
            excep = true;
        }

        assertTrue(excep);

        try {
            document.eliminaFull(100);
        } catch (ExcepcioDomini e)
        {
            assertEquals(ExcepcioDomini.TipusError.INDEX_FULL_INVALID, e.getTipusError());
            excep = true;
        }

        assertTrue(excep);
    }
}
