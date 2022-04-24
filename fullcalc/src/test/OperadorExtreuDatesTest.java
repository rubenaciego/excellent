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
public class OperadorExtreuDatesTest {
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
    public String any1;
    @Parameter(5)
    public Double any2;
    @Parameter(6)
    public Double any3;
    @Parameter(7)
    public Double any4;
    @Parameter(8)
    public String mes1;
    @Parameter(9)
    public Double mes2;
    @Parameter(10)
    public Double mes3;
    @Parameter(11)
    public Double mes4;
    @Parameter(12)
    public String dia1;
    @Parameter(13)
    public Double dia2;
    @Parameter(14)
    public Double dia3;
    @Parameter(15)
    public Double dia4;

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
                        "Feliç St Jordi :)", 0.0, 2022.0, 2022.0,
                        "Feliç St Jordi :)", 0.0, 4.0, 4.0,
                        "Feliç St Jordi :)", 0.0, 23.0, 23.0
                }};
        return Arrays.asList(data);
    }

    @Test
    public void testExtreuAny()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.extreuAny(mc);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(), any1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                any2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getNum(), any3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , any4);

    }

    @Test
    public void testExtreuMes()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.extreuMes(mc);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(), mes1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                mes2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getNum(), mes3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , mes4);

    }

    @Test
    public void testExtreuDia()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.extreuDia(mc);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(), dia1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                dia2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getNum(), dia3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , dia4);

    }
}

