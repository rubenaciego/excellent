package test;

import domini.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParserFullTest {
    Parser parser;

    @Parameterized.Parameter(0)
    public String[] opSenseParsejar;
    @Parameterized.Parameter(1)
    public ResultatParserFull resultatParserFull;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        String [] opSenseParsejarTest1 =
                new String[] {"OPERACIO_ARITMETICA,2,66,9,80,70,1,6,VALOR_ABSOLUT"};
        ResultatParserFull resultatTest1 = new ResultatParserFull();
        resultatTest1.setTipusOpAritmetica(OperacioAritmetica.VALOR_ABSOLUT);
        resultatTest1.setIdFull(2);
        resultatTest1.setFilaOrigen(66);
        resultatTest1.setColumnaOrigen(9);
        resultatTest1.setMidaFila(80);
        resultatTest1.setMidaColumna(70);
        resultatTest1.setFilaDesti(1);
        resultatTest1.setColumnaDesti(6);

        String [] opSenseParsejarTest2 =
                new String[] {"OPERACIO_ESTADISTICA,4,7,8,1,2,6,4,MITJANA"};
        ResultatParserFull resultatTest2 = new ResultatParserFull();
        resultatTest2.setTipusOpEstadistica(OperacioEstadistica.MITJANA);
        resultatTest2.setIdFull(4);
        resultatTest2.setFilaOrigen(7);
        resultatTest2.setColumnaOrigen(8);
        resultatTest2.setMidaFila(1);
        resultatTest2.setMidaColumna(2);
        resultatTest2.setFilaDesti(6);
        resultatTest2.setColumnaDesti(4);

        String [] opSenseParsejarTest3 =
                new String[] {"CONVERSIO_UNITATS,5,70,80,6,5,40,50,RAD_GRAUS"};
        ResultatParserFull resultatTest3 = new ResultatParserFull();
        resultatTest3.setTipusConversioUnitats(ConversioUnitats.RAD_GRAUS);
        resultatTest3.setIdFull(5);
        resultatTest3.setFilaOrigen(70);
        resultatTest3.setColumnaOrigen(80);
        resultatTest3.setMidaFila(6);
        resultatTest3.setMidaColumna(5);
        resultatTest3.setFilaDesti(40);
        resultatTest3.setColumnaDesti(50);

        String [] opSenseParsejarTest4 =
                new String[] {"ORDENA,1,1,1,1,1,1,4,ASCENDENT,5"};
        ResultatParserFull resultatTest4 = new ResultatParserFull();
        resultatTest4.setTipusCriteriOrdenacio(CriteriOrdenacio.ASCENDENT);
        resultatTest4.setTipusOpFull(OperacioFull.ORDENA);
        resultatTest4.setIdFull(1);
        resultatTest4.setFilaOrigen(1);
        resultatTest4.setColumnaOrigen(1);
        resultatTest4.setMidaFila(1);
        resultatTest4.setMidaColumna(1);
        resultatTest4.setFilaDesti(1);
        resultatTest4.setColumnaDesti(4);
        resultatTest4.setColumnaOrdenacio(5);

        String [] opSenseParsejarTest5 =
                new String[] {"TRUNCA_NUMERO,6,7,8,9,1,2,4,3"};
        ResultatParserFull resultatTest5 = new ResultatParserFull();
        resultatTest5.setTipusOpFull(OperacioFull.TRUNCA_NUMERO);
        resultatTest5.setIdFull(6);
        resultatTest5.setFilaOrigen(7);
        resultatTest5.setColumnaOrigen(8);
        resultatTest5.setMidaFila(9);
        resultatTest5.setMidaColumna(1);
        resultatTest5.setFilaDesti(2);
        resultatTest5.setColumnaDesti(4);
        resultatTest5.setDigitsTruncar(3);

        String [] opSenseParsejarTest6 =
                new String[] {"OPERACIO_FULL,4,5,6,3,10,87,4," +
                        "CERCA_OCURRENCIES", "hola"};
        ResultatParserFull resultatTest6 = new ResultatParserFull();
        resultatTest6.setTipusOpFull(OperacioFull.CERCA_OCURRENCIES);
        resultatTest6.setIdFull(4);
        resultatTest6.setFilaOrigen(5);
        resultatTest6.setColumnaOrigen(6);
        resultatTest6.setMidaFila(3);
        resultatTest6.setMidaColumna(10);
        resultatTest6.setFilaDesti(87);
        resultatTest6.setColumnaDesti(4);
        resultatTest6.setStringCercada("hola");

        String [] opSenseParsejarTest7 =
                new String[] {"OPERACIO_FULL,2,66,9,80,70,1,6,REEMPLACA",
                        "Maria", "Mar"};
        ResultatParserFull resultatTest7 = new ResultatParserFull();
        resultatTest7.setTipusOpFull(OperacioFull.REEMPLACA);
        resultatTest7.setIdFull(2);
        resultatTest7.setFilaOrigen(66);
        resultatTest7.setColumnaOrigen(9);
        resultatTest7.setMidaFila(80);
        resultatTest7.setMidaColumna(70);
        resultatTest7.setFilaDesti(1);
        resultatTest7.setColumnaDesti(6);
        resultatTest7.setStringCercada("Maria");
        resultatTest7.setStringRemplacadora("Mar");

        String [] opSenseParsejarTest8 =
                new String[] {"OPERACIO_FULL,5,67,8,2,3,4,8,MODIFICA_CELA",
                        "text"};
        ResultatParserFull resultatTest8 = new ResultatParserFull();
        resultatTest8.setTipusOpFull(OperacioFull.MODIFICA_CELA);
        resultatTest8.setIdFull(5);
        resultatTest8.setFilaOrigen(67);
        resultatTest8.setColumnaOrigen(8);
        resultatTest8.setMidaFila(2);
        resultatTest8.setMidaColumna(3);
        resultatTest8.setFilaDesti(4);
        resultatTest8.setColumnaDesti(8);
        ResultatParserCela resultatParserCelaTest8 = new ResultatParserCela();
        resultatParserCelaTest8.setInputUsuari("text");
        resultatParserCelaTest8.setTipus(Cela.TipusCela.TEXTUAL);
        resultatTest8.setResultatParserCela(resultatParserCelaTest8);

        String [] opSenseParsejarTest9 =
                new String[] {"OPERACIO_FULL,5,67,8,2,3,4,8,MODIFICA_CELA",
                        "4.5"};
        ResultatParserFull resultatTest9 = new ResultatParserFull();
        resultatTest9.setTipusOpFull(OperacioFull.MODIFICA_CELA);
        resultatTest9.setIdFull(5);
        resultatTest9.setFilaOrigen(67);
        resultatTest9.setColumnaOrigen(8);
        resultatTest9.setMidaFila(2);
        resultatTest9.setMidaColumna(3);
        resultatTest9.setFilaDesti(4);
        resultatTest9.setColumnaDesti(8);
        ResultatParserCela resultatParserCelaTest9 = new ResultatParserCela();
        resultatParserCelaTest9.setInputUsuari("4.5");
        resultatParserCelaTest9.setValorNumeric(4.5);
        resultatParserCelaTest9.setTipus(Cela.TipusCela.NUMERICA);
        resultatTest9.setResultatParserCela(resultatParserCelaTest9);

        String [] opSenseParsejarTest10 =
                new String[] {"OPERACIO_FULL,5,67,8,2,3,4,8,MODIFICA_CELA",
                        "20/04/2022"};
        ResultatParserFull resultatTest10 = new ResultatParserFull();
        resultatTest10.setTipusOpFull(OperacioFull.MODIFICA_CELA);
        resultatTest10.setIdFull(5);
        resultatTest10.setFilaOrigen(67);
        resultatTest10.setColumnaOrigen(8);
        resultatTest10.setMidaFila(2);
        resultatTest10.setMidaColumna(3);
        resultatTest10.setFilaDesti(4);
        resultatTest10.setColumnaDesti(8);
        ResultatParserCela resultatParserCelaTest10 = new ResultatParserCela();
        resultatParserCelaTest10.setInputUsuari("20/04/2022");
        resultatParserCelaTest10.setData(LocalDate.of(2022,04,20));
        resultatParserCelaTest10.setTipus(Cela.TipusCela.DATADA);
        resultatTest10.setResultatParserCela(resultatParserCelaTest10);

        String [] opSenseParsejarTest11 =
                new String[] {"OPERACIO_FULL,5,67,8,2,3,4,8,MODIFICA_CELA",
                        "=10:1"};
        ResultatParserFull resultatTest11 = new ResultatParserFull();
        resultatTest11.setTipusOpFull(OperacioFull.MODIFICA_CELA);
        resultatTest11.setIdFull(5);
        resultatTest11.setFilaOrigen(67);
        resultatTest11.setColumnaOrigen(8);
        resultatTest11.setMidaFila(2);
        resultatTest11.setMidaColumna(3);
        resultatTest11.setFilaDesti(4);
        resultatTest11.setColumnaDesti(8);
        ResultatParserCela resultatParserCelaTest11 = new ResultatParserCela();
        resultatParserCelaTest11.setInputUsuari("=10:1");
        resultatParserCelaTest11.setFilaRef(10);
        resultatParserCelaTest11.setColRef(1);
        resultatParserCelaTest11.setTipus(Cela.TipusCela.REFERENCIAL);
        resultatTest11.setResultatParserCela(resultatParserCelaTest11);

        String [] opSenseParsejarTest12 =
                new String[] {"OPERACIO_FULL,1,,,,,,,ELIMINA_FILA,4"};
        ResultatParserFull resultatTest12 = new ResultatParserFull();
        resultatTest12.setTipusOpFull(OperacioFull.ELIMINA_FILA);
        resultatTest12.setIdFull(1);
        resultatTest12.setFilaColEliminar(4);

        String [] opSenseParsejarTest13 =
                new String[] {"OPERACIO_FULL,1,,,,,,,ELIMINA_COLUMNA,3"};
        ResultatParserFull resultatTest13 = new ResultatParserFull();
        resultatTest13.setTipusOpFull(OperacioFull.ELIMINA_COLUMNA);
        resultatTest13.setIdFull(1);
        resultatTest13.setFilaColEliminar(3);

        String [] opSenseParsejarTest14 =
                new String[] {"OPERACIO_FULL,4,5,6,3,10,87,4,EXTREU_MES"};
        ResultatParserFull resultatTest14 = new ResultatParserFull();
        resultatTest14.setTipusOpFull(OperacioFull.EXTREU_MES);
        resultatTest14.setIdFull(4);
        resultatTest14.setFilaOrigen(5);
        resultatTest14.setColumnaOrigen(6);
        resultatTest14.setMidaFila(3);
        resultatTest14.setMidaColumna(10);
        resultatTest14.setFilaDesti(87);
        resultatTest14.setColumnaDesti(4);


        Object[][] data = new Object[][]{{opSenseParsejarTest1,
                resultatTest1}, {opSenseParsejarTest2, resultatTest2},
                {opSenseParsejarTest3, resultatTest3}, {opSenseParsejarTest4
                , resultatTest4}, {opSenseParsejarTest5, resultatTest5},
                {opSenseParsejarTest6, resultatTest6}, {opSenseParsejarTest7,
                resultatTest7}, {opSenseParsejarTest8,
                resultatTest8}, {opSenseParsejarTest9, resultatTest9},
                {opSenseParsejarTest10, resultatTest10},
                {opSenseParsejarTest11, resultatTest11},
                {opSenseParsejarTest12, resultatTest12},
                {opSenseParsejarTest13, resultatTest13},
                {opSenseParsejarTest14, resultatTest14}};
        return Arrays.asList(data);
    }

    @Test
    public void TestParseOpFull() {
        ResultatParserFull resultatOutput =
                Parser.getInstance().parseOpFull(opSenseParsejar);
        assertEquals(resultatParserFull, resultatOutput);
    }
}
