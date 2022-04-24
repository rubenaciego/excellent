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
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class OperadorReemplacaTest {
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
    public String reemplacar;
    @Parameterized.Parameter(5)
    public String res1;
    @Parameterized.Parameter(6)
    public String res2;
    @Parameterized.Parameter(7)
    public String res3;

    @Before
    public void setUp() {
        operador = Operador.getInstance();
    }

    /**
     * @brief En el primer test els casos de prova consisteixen en un cas on la paraula a substituïr no hi és,
     * un altre on la paraula apareix un cop aïllada i un últim cas on apareix la paraula dos cops seguida de text.
     * El segon cas de prova comprova un string buit on no hi apareix la paraula i la substitució d'una paraula que es pot
     * solapar amb ella mateixa (acaba igual que comença). El tercer test prova la substitució d'un sol caràcter i l'últim test
     * prova el cas extrem de reemplaçar un caràcter buit, el qual es troba a totes les posicions de l'string i per tant s'ha
     * d'incerir en totes les posicions, incloent al principi i al final, en el cas d'un string buit només s'incereix un cop
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "la paraula no hi es"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "m'agrada molt el peix"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test", "peixexegepeix"))),
                        "peix", "ruben", "la paraula no hi es", "m'agrada molt el ruben", "rubenexegeruben"},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "lele"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "lelelelelele"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test", ""))),
                        "lele", "abab", "abab", "abababababab", ""},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "hola bon dia"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "a a a a a"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test",
                                "abcdefghijklmnopqrstuvwxyz0123456789"))), "a", "!!!",
                        "hol!!! bon di!!!", "!!! !!! !!! !!! !!!", "!!!bcdefghijklmnopqrstuvwxyz0123456789"},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", ""))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "aa"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test", "arreu"))),
                        "", "o", "o", "oaoao", "oaororoeouo"},
        };

        return Arrays.asList(data);
    }

    /**
     * @brief El test comrpova que el text reemplaçat de cada cel·la coincideixi amb l'argument que es passa, que ha
     * de ser el text reemplaçat correctament
     */
    @Test
    public void testReemplacar() {
        MatriuCeles mc = new MatriuCeles(3, 3);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());

        MatriuCeles res = operador.reemplaca(mc, cerca, reemplacar);
        assertNotNull(res);

        assertEquals(3, res.getNumCols());
        assertEquals(3, res.getNumFiles());
        assertEquals(3, res.getEntrades().size());

        assertEquals(res1,
                res.getCela(cela1.getFila(), cela1.getColumna()).getText());
        assertEquals(res2,
                res.getCela(cela2.getFila(), cela2.getColumna()).getText());
        assertEquals(res3,
                res.getCela(cela3.getFila(), cela3.getColumna()).getText());
    }
}
