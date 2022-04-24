package test;

import domini.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OperadorExtreuLongitutTextTest {
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
    public Double lon1;
    @Parameter(5)
    public Double lon2;
    @Parameter(6)
    public LocalDate lon3;
    @Parameter(7)
    public Double lon4;

    /**
     * @brief Parametritzacio de una Cel·la de cada tipus per executar els
     * testos i dels resultats esperats per cada Cel·la i
     * extreuLongitudText
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        CelaText ct = new CelaText("test", "Feliç St Jordi :)");
        LocalDate d = LocalDate.of(2022, 4, 23);
        Object[][] data = new Object[][]{
                {(new EntradaMatriuCeles(0, 0, ct)),
                (new EntradaMatriuCeles(0, 1, new CelaNum("test",
                        0.0))),
                (new EntradaMatriuCeles(1, 0,
                        new CelaData("test", d))),
                (new EntradaMatriuCeles(1,1, new CelaRef("test", ct))),
                    17.0, 0.0, d, 17.0,
                }};
        return Arrays.asList(data);
    }

    /**
     * @brief Test parametritzat per comprovar que Operador executa
     * correctament l'operació extreuLongitudText amb tots els
     * tipus de Cel·la.
     */
    @Test
    public void testExtreuLongitudText()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.extreuLongitudText(mc);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getNum(), lon1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                lon2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), lon3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , lon4);

    }
}
