package test;

import domini.CelaText;
import domini.EntradaMatriuCeles;
import domini.MatriuCeles;
import domini.Operador;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class OperadorCercaTest {
    private Operador operador;

    @Parameterized.Parameter(0)
    public EntradaMatriuCeles cela1;
    @Parameterized.Parameter(1)
    public EntradaMatriuCeles cela2;
    @Parameterized.Parameter(2)
    public EntradaMatriuCeles cela3;
    @Parameterized.Parameter(3)
    public String cerca;
    @Parameterized.Parameter(4)
    public String json;

    @Before
    public void setUp() {
        operador = Operador.getInstance();
    }

    /**
     * @brief En el primer test els casos de prova consisteixen en un cas on la paraula cercada no hi és,
     * un altre on la paraula apareix un cop aïllada i un últim cas on apareix la paraula dos cops seguida de text.
     * El segon cas de prova comprova un string buit on no hi apareix la paraula i la cerca d'una paraula que es pot
     * solapar amb ella mateixa (acaba igual que comença). El tercer test prova la cerca d'un sol caràcter i l'últim test
     * prova el cas extrem de cercar un caràcter buit, el qual es troba a totes les posicions de l'string i, en el cas d'un
     * string buit considerem que es troba a la posició 0
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "la paraula no hi es"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "m'agrada molt el peix"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test", "peixexegepeix"))),
                        "peix", "{\"ocurrencies\": 3, \"0:0\":{\"ocurrencies\":0, \"indexos\":[]}, " +
                        "\"1:1\":{\"ocurrencies\": 1, \"indexos\": [17]}, \"2:2\": {\"ocurrencies\": 2, \"indexos\": [0, 9]} }"},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "lele"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "lelelelelele"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test", ""))),
                        "lele", "{\"0:0\":{\"ocurrencies\":1,\"indexos\":[0]},\"1:1\":{\"ocurrencies\":5," +
                        "\"indexos\":[0,2,4,6,8]},\"2:2\":{\"ocurrencies\":0,\"indexos\":[]},\"ocurrencies\":6}"},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "hola bon dia"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "a a a a a"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test",
                                "abcdefghijklmnopqrstuvwxyz0123456789"))), "a", "{\"0:0\":{\"ocurrencies\":2," +
                        "\"indexos\":[3,11]},\"1:1\":{\"ocurrencies\":5,\"indexos\":[0,2,4,6,8]}," +
                        "\"2:2\":{\"ocurrencies\":1,\"indexos\":[0]},\"ocurrencies\":8}"},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", ""))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "aa"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test", "arreu"))),
                        "", "{\"ocurrencies\": 8, \"0:0\":{\"ocurrencies\":1, \"indexos\":[0]}, " +
                        "\"1:1\":{\"ocurrencies\": 2, \"indexos\": [0,1]}, \"2:2\": {\"ocurrencies\": 5, \"indexos\": "
                        + "[0,1,2,3,4]} }"},
        };

        return Arrays.asList(data);
    }

    /**
     * @brief El test comprova que el resultat JSON coincideixi amb el que es passa com a paràmetre (que ha de
     * ser el correcte)
     */
    @Test
    public void testCerca() {
        MatriuCeles mc = new MatriuCeles(3, 3);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());

        MatriuCeles res = operador.cercaOcurrencies(mc, cerca);
        assertNotNull(res);

        assertEquals(1, res.getNumCols());
        assertEquals(1, res.getNumFiles());
        assertEquals(3, res.getEntrades().size());

        String jsonRes = res.getCela(0, 0).getText();
        assertNotNull(jsonRes);

        JSONObject obj1 = JSONObject.fromObject(jsonRes);
        JSONObject obj2 = JSONObject.fromObject(json);

        assertEquals(0, obj1.compareTo(obj2));
    }
}
