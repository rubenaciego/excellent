package test;

import domini.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OperadorMinusculesTest {
    private Operador operador;

    @Parameter(0)
    public EntradaMatriuCeles cela1;
    @Parameter(1)
    public EntradaMatriuCeles cela2;
    @Parameter(2)
    public EntradaMatriuCeles cela3;
    @Parameter(3)
    public String res1;
    @Parameter(4)
    public String res2;
    @Parameter(5)
    public String res3;

    @Before
    public void setUp() {
        operador = Operador.getInstance();
    }

    /**
     * @brief Els tests comrpoven casos de tot minúscules, algunes lletres amb majúscula i minúscula, paraules amb
     * símbols que no s'han de convertir, paraula buida i lletres amb accents i altres
     */
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "MAJÚSCULES"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "Una mica de Tot, Sí"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test",
                                "1234567890'¡!\"·$%&/()=?¿-,.\\<>`+'ç^*Ç{}[]ºª|@#~€¬;"))), "majúscules",
                        "una mica de tot, sí", "1234567890'¡!\"·$%&/()=?¿-,.\\<>`+'ç^*ç{}[]ºª|@#~€¬;"},
                {
                        (new EntradaMatriuCeles(0, 0, new CelaText("test", "minúscules"))),
                        (new EntradaMatriuCeles(1, 1, new CelaText("test", "ÁÀÄÂ"))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test", ""))),
                        "minúscules", "áàäâ", ""},

                {
                        (new EntradaMatriuCeles(0, 0, new CelaNum("test", 3.0))),
                        (new EntradaMatriuCeles(1, 1, new CelaData("test", LocalDate.now()))),
                        (new EntradaMatriuCeles(2, 2, new CelaText("test", "Aa"))),
                        null, null, "aa"},
        };

        return Arrays.asList(data);
    }

    /**
     * @brief El test comprova que el resultat de convertir a minúscules coincideix amb el passat com a paràmetre
     * (que ha de ser el correcte)
     */
    @Test
    public void testMinuscular()
    {
        MatriuCeles mc = new MatriuCeles(3, 3);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());

        MatriuCeles res = operador.converteixMinuscules(mc);

        assertEquals(res1, res.getCela(cela1.getFila(), cela1.getColumna()).getText());
        assertEquals(res2, res.getCela(cela2.getFila(), cela2.getColumna()).getText());
        assertEquals(res3, res.getCela(cela3.getFila(), cela3.getColumna()).getText());
    }
}
