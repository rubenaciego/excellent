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
    public void testExecutaOperacioDocument() {
        String [] opSenseParsejarTest1 = new String[] {"OPERACIO_DOCUMENT," +
                "CREA_DOCUMENT", "Document nou"};
        controladorDomini.executaOperacio(opSenseParsejarTest1);
        assertEquals("Document nou", controladorDomini.getDocument().getNom());


        String [] opSenseParsejarTest2 = new String[] {"OPERACIO_DOCUMENT," +
                "TANCA_DOCUMENT"};
        controladorDomini.executaOperacio(opSenseParsejarTest2);
        assertEquals(null, controladorDomini.getDocument());

        Document document = mock(Document.class);
        ControladorFull controladorFull = mock(ControladorFull.class);
        String [] opSenseParsejarTest3 = new String[] {"OPERACIO_DOCUMENT," +
                "ELIMINA_FULL,3"};
        controladorDomini.executaOperacio(opSenseParsejarTest3);

    }
}
