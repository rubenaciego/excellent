package test;

import domini.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

 @RunWith(Parameterized.class)
 public class ParserDocumentTest {

     @Parameterized.Parameter(0)
     public String[] opSenseParsejar;
     @Parameterized.Parameter(1)
     public ResultatParserDocument resultatParserDocument;

     @Parameterized.Parameters
     public static Collection<Object[]> data() {
         String [] opSenseParsejarTest1 = new String[] {"OPERACIO_DOCUMENT," +
                 "CARREGA_DOCUMENT", "Document carregat"};
         ResultatParserDocument resultatTest1 = new ResultatParserDocument();
         resultatTest1.setTipusOpDocument(OperacioDocument.CARREGA_DOCUMENT);
         resultatTest1.setNomDocument("Document carregat");

         String [] opSenseParsejarTest2 = new String[] {"OPERACIO_DOCUMENT," +
                 "CREA_DOCUMENT", "Document nou"};
         ResultatParserDocument resultatTest2 = new ResultatParserDocument();
         resultatTest2.setTipusOpDocument(OperacioDocument.CREA_DOCUMENT);
         resultatTest2.setNomDocument("Document nou");

         String [] opSenseParsejarTest3 = new String[] {"OPERACIO_DOCUMENT," +
                 "ELIMINA_FULL,3"};
         ResultatParserDocument resultatTest3 = new ResultatParserDocument();
         resultatTest3.setTipusOpDocument(OperacioDocument.ELIMINA_FULL);
         resultatTest3.setIdFull(3);

         String [] opSenseParsejarTest4 = new String[] {"OPERACIO_DOCUMENT," +
                 "AFEGEIX_FULL"};
         ResultatParserDocument resultatTest4 = new ResultatParserDocument();
         resultatTest4.setTipusOpDocument(OperacioDocument.AFEGEIX_FULL);

         String [] opSenseParsejarTest5 = new String[] {"OPERACIO_DOCUMENT," +
                 "DESA_DOCUMENT"};
         ResultatParserDocument resultatTest5 = new ResultatParserDocument();
         resultatTest5.setTipusOpDocument(OperacioDocument.DESA_DOCUMENT);

         String [] opSenseParsejarTest6 = new String[] {"OPERACIO_DOCUMENT," +
                 "TANCA_DOCUMENT,3"};
         ResultatParserDocument resultatTest6 = new ResultatParserDocument();
         resultatTest6.setTipusOpDocument(OperacioDocument.TANCA_DOCUMENT);

         Object[][] data = new Object[][]{{opSenseParsejarTest1,
                 resultatTest1}, {opSenseParsejarTest2, resultatTest2},
                 {opSenseParsejarTest3, resultatTest3}, {opSenseParsejarTest4
                 , resultatTest4}, {opSenseParsejarTest5, resultatTest5},
                 {opSenseParsejarTest6, resultatTest6}};
         return Arrays.asList(data);
     }

     @Test
     public void TestParseOpDocument() {
         ResultatParserDocument resultatOutput =
                 Parser.getInstance().parseOpDocument(opSenseParsejar);
         assertEquals(resultatParserDocument, resultatOutput);
     }
}





