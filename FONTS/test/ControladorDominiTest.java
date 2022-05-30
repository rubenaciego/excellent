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
    Document mockDocument = mock(Document.class);
    ArrayList<ControladorFull> mockControladorsFull = mock(ArrayList.class);

    @Before
    public void setUp() {
        controladorDomini = new ControladorDomini();
    }

    /**
     * @brief Test que comprova que les operacions creaDocument s'executen
     * quan se li passa aquest tipus d'operació.
     */
    @Test
    public void testExecutaOperacioDocumentCreaDocument() {
        String[] opSenseParsejarTest1 = new String[]{"OPERACIO_DOCUMENT," +
                "CREA_DOCUMENT", "Document nou"};
        controladorDomini.executaOperacio(opSenseParsejarTest1);
        assertEquals("Document nou", controladorDomini.getDocument().getNom());
    }

    /**
     * @brief Test que comprova que les operacions tanca_document s'executen
     * quan se li passa aquest tipus d'operació.
     */
    @Test
    public void testExecutaOperacioDocumentTancaDocument() {
        String[] opSenseParsejarTest2 = new String[]{"OPERACIO_DOCUMENT," +
                "TANCA_DOCUMENT"};
        controladorDomini.executaOperacio(opSenseParsejarTest2);
        assertEquals(null, controladorDomini.getDocument());
    }

    /**
     * @brief Test que comprova que les operacions eliminaFull s'executen
     * quan se li passa aquest tipus d'operació. Utilitza mock i verify per
     * veure que eliminaFull i remove s'executen només un cop quan es crida
     * executaOperacio
     */
    @Test
    public void testExecutaOperacioDocumentEliminaFull() {
        String[] opSenseParsejarTest3 = new String[]{"OPERACIO_DOCUMENT," +
                "ELIMINA_FULL,3"};
        controladorDomini = new ControladorDomini(mockDocument,
                mockControladorsFull);
        controladorDomini.executaOperacio(opSenseParsejarTest3);
        verify(mockDocument, times(1)).eliminaFull(3);
        verify(mockControladorsFull, times(1)).remove(3);
    }

    /**
     * @brief Test que comprova que les operacions afegeixFull s'executen
     * quan se li passa aquest tipus d'operació. Utilitza mock i verify per
     * veure que afegeixFull s'executa només un cop quan es crida
     * executaOperacio
     */
    @Test
    public void testExecutaOperacioDocumentAfegeixFull() {
        String[] opSenseParsejarTest3 = new String[]{"OPERACIO_DOCUMENT," +
                "AFEGEIX_FULL"};
        controladorDomini = new ControladorDomini(mockDocument,
                mockControladorsFull);
        controladorDomini.executaOperacio(opSenseParsejarTest3);
        verify(mockDocument, times(1)).afegeixFull(100, 100);
    }


}
