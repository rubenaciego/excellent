package test;

import domini.ExcepcioDomini;
import domini.Full;
import domini.Document;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorDominiTest {
    private Document document;

    @Before
    public void setUp() {
        document = new Document("DocumentTest");
    }

    @Test
    public void testContructor() {
        document = new Document("TestName");
        assertEquals("TestName", document.getNom());
    }

    @Test
    public void testSetGetData() {
        LocalDateTime now = LocalDateTime.now();
        document.setDataModificacio(now);
        assertEquals(now, document.getDataModificacio());
    }

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
