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
public class OperadorOpAritmeticaTest {
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
    public String abs1;
    @Parameter(5)
    public Double abs2;
    @Parameter(6)
    public LocalDate abs3;
    @Parameter(7)
    public Double abs4;

    @Parameter(8)
    public String inc1;
    @Parameter(9)
    public Double inc2;
    @Parameter(10)
    public LocalDate inc3;
    @Parameter(11)
    public Double inc4;

    @Parameter(12)
    public String dec1;
    @Parameter(13)
    public Double dec2;
    @Parameter(14)
    public LocalDate dec3;
    @Parameter(15)
    public Double dec4;

    @Parameter(16)
    public String exp1;
    @Parameter(17)
    public Double exp2;
    @Parameter(18)
    public LocalDate exp3;
    @Parameter(19)
    public Double exp4;

    @Parameter(20)
    public String cos1;
    @Parameter(21)
    public Double cos2;
    @Parameter(22)
    public LocalDate cos3;
    @Parameter(23)
    public Double cos4;

    @Parameter(24)
    public String sin1;
    @Parameter(25)
    public Double sin2;
    @Parameter(26)
    public LocalDate sin3;
    @Parameter(27)
    public Double sin4;

    @Parameter(28)
    public String cosh1;
    @Parameter(29)
    public Double cosh2;
    @Parameter(30)
    public LocalDate cosh3;
    @Parameter(31)
    public Double cosh4;

    @Parameter(32)
    public String sinh1;
    @Parameter(33)
    public Double sinh2;
    @Parameter(34)
    public LocalDate sinh3;
    @Parameter(35)
    public Double sinh4;

    @Parameter(36)
    public String tanh1;
    @Parameter(37)
    public Double tanh2;
    @Parameter(38)
    public LocalDate tanh3;
    @Parameter(39)
    public Double tanh4;


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
                        "Feliç St Jordi :)", Math.abs(val), d, Math.abs(val),
                        "Feliç St Jordi :)", (val+1.0), d, (val+1.0),
                        "Feliç St Jordi :)", (val-1.0), d, (val-1.0),
                        "Feliç St Jordi :)", Math.exp(val), d,
                        Math.exp(val),
                        "Feliç St Jordi :)", Math.cos(val), d,
                        Math.cos(val),
                        "Feliç St Jordi :)", Math.sin(val), d,
                        Math.sin(val),
                        "Feliç St Jordi :)", Math.cosh(val), d,
                        Math.cosh(val),
                        "Feliç St Jordi :)", Math.sinh(val), d,
                        Math.sinh(val),
                        "Feliç St Jordi :)", Math.tanh(val), d,
                        Math.tanh(val),
                }};
        return Arrays.asList(data);
    }

    @Test
    public void testValorAbsolut()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.executaOperacioAritmeticaUnaria(mc, OperacioAritmetica.VALOR_ABSOLUT);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                abs1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                abs2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), abs3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , abs4);
    }

    @Test
    public void testIncrementar()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.executaOperacioAritmeticaUnaria(mc,
                OperacioAritmetica.INCREMENTAR);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                inc1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                inc2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), inc3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , inc4);
    }

    @Test
    public void testDecrementar()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.executaOperacioAritmeticaUnaria(mc,
                OperacioAritmetica.DECREMENTAR);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                dec1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                dec2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), dec3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , dec4);
    }

    @Test
    public void testCos()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.executaOperacioAritmeticaUnaria(mc,
                OperacioAritmetica.COSINUS);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                cos1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                cos2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), cos3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , cos4);
    }

    @Test
    public void testSin()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.executaOperacioAritmeticaUnaria(mc,
                OperacioAritmetica.SINUS);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                sin1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                sin2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), sin3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , sin4);
    }

    @Test
    public void testCosH()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.executaOperacioAritmeticaUnaria(mc,
                OperacioAritmetica.COSINUS_HIPERBOLIC);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                cosh1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                cosh2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), cosh3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , cosh4);
    }

    @Test
    public void testSinH()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.executaOperacioAritmeticaUnaria(mc,
                OperacioAritmetica.SINUS_HIPERBOLIC);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                sinh1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                sinh2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), sinh3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , sinh4);
    }

    @Test
    public void testTanH()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.executaOperacioAritmeticaUnaria(mc,
                OperacioAritmetica.TANGENT_HIPERBOLIC);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                tanh1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                tanh2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), tanh3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , tanh4);
    }

}
