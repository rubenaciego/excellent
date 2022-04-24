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
    ControladorFull mockControladorFull = mock(ControladorFull.class);

    @Before
    public void setUp() {
        controladorDomini = new ControladorDomini();
    }

    @Test
    public void testExecutaOperacioDocument() {
        String[] opSenseParsejarTest0 = new String[]{"OPERACIO_DOCUMENT," +
                "ELIMINA_FULL,3"};
        controladorDomini = new ControladorDomini(mockDocument,
                mockControladorsFull);
        controladorDomini.executaOperacio(opSenseParsejarTest0);
        verify(Parser.getInstance(), times(1)).parseOpDocument(opSenseParsejarTest0);
    }

    @Test
    public void testExecutaOperacioDocumentCreaDocument() {
        String[] opSenseParsejarTest1 = new String[]{"OPERACIO_DOCUMENT," +
                "CREA_DOCUMENT", "Document nou"};
        controladorDomini.executaOperacio(opSenseParsejarTest1);
        assertEquals("Document nou", controladorDomini.getDocument().getNom());
    }

    @Test
    public void testExecutaOperacioDocumentTancaDocument() {
        String[] opSenseParsejarTest2 = new String[]{"OPERACIO_DOCUMENT," +
                "TANCA_DOCUMENT"};
        controladorDomini.executaOperacio(opSenseParsejarTest2);
        assertEquals(null, controladorDomini.getDocument());
    }

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

    @Test
    public void testExecutaOperacioDocumentAfegeixFull() {
        String[] opSenseParsejarTest3 = new String[]{"OPERACIO_DOCUMENT," +
                "AFEGEIX_FULL"};
        controladorDomini = new ControladorDomini(mockDocument,
                mockControladorsFull);
        controladorDomini.executaOperacio(opSenseParsejarTest3);
        verify(mockDocument, times(1)).afegeixFull();
        //verify(mockControladorsFull, times(1)).add(new ());
    }

    @Test
    public void testExecutaOperacioFull() {
        String [] opSenseParsejarTest4 = new String[] {"OPERACIO_FULL,5,67,8,2,3,4,8,MODIFICA_CELA",
                        "4.5"};
        ResultatParserFull resultatTest4 = new ResultatParserFull();
        resultatTest4.setTipusOpFull(OperacioFull.MODIFICA_CELA);
        resultatTest4.setIdFull(5);
        resultatTest4.setFilaOrigen(67);
        resultatTest4.setColumnaOrigen(8);
        resultatTest4.setMidaFila(2);
        resultatTest4.setMidaColumna(3);
        resultatTest4.setFilaDesti(4);
        resultatTest4.setColumnaDesti(8);
        ResultatParserCela resultatParserCelaTest4 = new ResultatParserCela();
        resultatParserCelaTest4.setInputUsuari("4.5");
        resultatParserCelaTest4.setValorNumeric(4.5);
        resultatParserCelaTest4.setTipus(Cela.TipusCela.NUMERICA);
        resultatTest4.setResultatParserCela(resultatParserCelaTest4);

        controladorDomini = new ControladorDomini(mockDocument,
                mockControladorsFull);
        controladorDomini.executaOperacio(opSenseParsejarTest4);
        verify(Parser.getInstance(), times(1)).parseOpFull(opSenseParsejarTest4);
        verify(mockControladorsFull, times(1)).get(5).executaOperacio(resultatTest4);
    }
}
