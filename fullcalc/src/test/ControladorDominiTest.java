package test;

import domini.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorDominiTest {
    private ControladorDomini controladorDomini;

    @Before
    public void setUp() {
        controladorDomini = new ControladorDomini();
    }

    @Test
    public void testCreaDocument() {
        controladorDomini.creaDocument("Document nou");
        assertEquals("Document nou", controladorDomini.getDocument().getNom());
    }

    @Test
    public void testTancaDocument() {
        controladorDomini.tancaDocument();
        assertEquals(null, controladorDomini.getDocument());
    }

    @Test
    public void testExecutaOperacioDocument() {
        String [] opSenseParsejarTest1 = new String[] {"OPERACIO_DOCUMENT," +
                "CREA_DOCUMENT", "Document nou"};
        controladorDomini.executaOperacio(opSenseParsejarTest1);
        assertEquals("Document nou", controladorDomini.getDocument().getNom());

        String [] opSenseParsejarTest2 = new String[] {"OPERACIO_DOCUMENT," +
                "ELIMINA_FULL,3"};
        controladorDomini.executaOperacio(opSenseParsejarTest2);
        assertEquals();

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
