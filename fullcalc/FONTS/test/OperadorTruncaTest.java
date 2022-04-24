package test;

import domini.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OperadorTruncaTest {

    private Operador operador;

    @Before
    public void setUp() {
        operador = Operador.getInstance();
    }

    @Parameter(0)
    public EntradaMatriuCeles cela1;
    @Parameter(1)
    public EntradaMatriuCeles cela2;
    @Parameter(2)
    public EntradaMatriuCeles cela3;
    @Parameter(3)
    public EntradaMatriuCeles cela4;

    @Parameter(4)
    public String res1;
    @Parameter(5)
    public Double res2;
    @Parameter(6)
    public LocalDate res3;
    @Parameter(7)
    public Double res4;

    /**
     * @brief Parametritzacio de una Cel·la de cada tipus per executar els
     * testos i dels resultats esperats per cada Cel·la i truncar.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Double val = -3.141592;
        CelaNum cn = new CelaNum("test", val);
        LocalDate d = LocalDate.of(2022, 4, 23);

        Object[][] data = new Object[][]{
                {(new EntradaMatriuCeles(0, 0, new CelaText("test",
                        "Feliç St Jordi :)"))),
                        (new EntradaMatriuCeles(0, 1, cn)),
                        (new EntradaMatriuCeles(1, 0,
                                new CelaData("test", d))),
                        (new EntradaMatriuCeles(1,1, new CelaRef("test", cn))),
                        "Feliç St Jordi :)", -3.141, d, -3.141,
                }};
        return Arrays.asList(data);
    }

    /**
     * @brief Test parametritzat per comprovar que Operador executa
     * correctament l'operació truncar amb tots els
     * tipus de Cel·la.
     */
    @Test
    public void testTruncar()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.truncaNumero(mc, 3);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                res1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                res2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), res3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , res4);
    }
}
