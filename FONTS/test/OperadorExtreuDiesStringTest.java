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
public class OperadorExtreuDiesStringTest {
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
    public String dia1;
    @Parameter(5)
    public Double dia2;
    @Parameter(6)
    public String dia3;
    @Parameter(7)
    public String dia4;
    @Parameter(8)
    public String hor1;
    @Parameter(9)
    public Double hor2;
    @Parameter(10)
    public String hor3;
    @Parameter(11)
    public String hor4;

    /**
     * @brief Parametritzacio de una Cel·la de cada tipus per executar els
     * testos i dels resultats esperats per cada Cel·la i
     * extreureDiaSetmana i extreureHoroscop.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        CelaData cd = new CelaData("test",
                LocalDate.of(2022, 4, 23));
        Object[][] data = new Object[][]{
                {(new EntradaMatriuCeles(0, 0, new CelaText("test",
                        "Feliç St Jordi :)"))),
                        (new EntradaMatriuCeles(0, 1, new CelaNum("test",
                                0.0))),
                        (new EntradaMatriuCeles(1, 0, cd)),
                        (new EntradaMatriuCeles(1,1, new CelaRef("test", cd))),
                        "Feliç St Jordi :)", 0.0, "dissabte", "dissabte",
                        "Feliç St Jordi :)", 0.0, "Tauro", "Tauro",
                }};
        return Arrays.asList(data);
    }

    /**
     * @brief Test parametritzat per comprovar que Operador executa
     * correctament l'operació extreuHoroscop amb tots els
     * tipus de Cel·la.
     */@Test
    public void testExtreuHoroscop()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.extreuHoroscop(mc);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(), hor1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                hor2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getText(), hor3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getText()
                , hor4);

    }

    /**
     * @brief Test parametritzat per comprovar que Operador executa
     * correctament l'operació extreuDiaSetmana amb tots els
     * tipus de Cel·la.
     */
    @Test
    public void testExtreuDia()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.extreuDiaSetmana(mc);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(), dia1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                dia2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getText(), dia3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getText()
                , dia4);

    }
}
