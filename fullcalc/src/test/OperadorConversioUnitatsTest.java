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
public class OperadorConversioUnitatsTest
{

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
    public String r2g1;
    @Parameter(5)
    public Double r2g2;
    @Parameter(6)
    public LocalDate r2g3;
    @Parameter(7)
    public Double r2g4;

    @Parameter(8)
    public String g2r1;
    @Parameter(9)
    public Double g2r2;
    @Parameter(10)
    public LocalDate g2r3;
    @Parameter(11)
    public Double g2r4;

    @Parameter(12)
    public String km2m1;
    @Parameter(13)
    public Double km2m2;
    @Parameter(14)
    public LocalDate km2m3;
    @Parameter(15)
    public Double km2m4;

    @Parameter(16)
    public String m2km1;
    @Parameter(17)
    public Double m2km2;
    @Parameter(18)
    public LocalDate m2km3;
    @Parameter(19)
    public Double m2km4;

    @Parameter(20)
    public String kg2lb1;
    @Parameter(21)
    public Double kg2lb2;
    @Parameter(22)
    public LocalDate kg2lb3;
    @Parameter(23)
    public Double kg2lb4;

    @Parameter(24)
    public String lb2kg1;
    @Parameter(25)
    public Double lb2kg2;
    @Parameter(26)
    public LocalDate lb2kg3;
    @Parameter(27)
    public Double lb2kg4;

    @Parameter(28)
    public String l2gal1;
    @Parameter(29)
    public Double l2gal2;
    @Parameter(30)
    public LocalDate l2gal3;
    @Parameter(31)
    public Double l2gal4;

    @Parameter(32)
    public String gal2l1;
    @Parameter(33)
    public Double gal2l2;
    @Parameter(34)
    public LocalDate gal2l3;
    @Parameter(35)
    public Double gal2l4;

    @Parameter(36)
    public String c2k1;
    @Parameter(37)
    public Double c2k2;
    @Parameter(38)
    public LocalDate c2k3;
    @Parameter(39)
    public Double c2k4;

    // AQUI

    @Parameter(40)
    public String k2c1;
    @Parameter(41)
    public Double k2c2;
    @Parameter(42)
    public LocalDate k2c3;
    @Parameter(43)
    public Double k2c4;

    @Parameter(44)
    public String k2f1;
    @Parameter(45)
    public Double k2f2;
    @Parameter(46)
    public LocalDate k2f3;
    @Parameter(47)
    public Double k2f4;

    @Parameter(48)
    public String f2k1;
    @Parameter(49)
    public Double f2k2;
    @Parameter(50)
    public LocalDate f2k3;
    @Parameter(51)
    public Double f2k4;

    @Parameter(52)
    public String f2c1;
    @Parameter(53)
    public Double f2c2;
    @Parameter(54)
    public LocalDate f2c3;
    @Parameter(55)
    public Double f2c4;

    @Parameter(56)
    public String c2f1;
    @Parameter(57)
    public Double c2f2;
    @Parameter(58)
    public LocalDate c2f3;
    @Parameter(59)
    public Double c2f4;

    @Parameter(60)
    public String km2h1;
    @Parameter(61)
    public Double km2h2;
    @Parameter(62)
    public LocalDate km2h3;
    @Parameter(63)
    public Double km2h4;

    @Parameter(64)
    public String h2km1;
    @Parameter(65)
    public Double h2km2;
    @Parameter(66)
    public LocalDate h2km3;
    @Parameter(67)
    public Double h2km4;

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
                        "Feliç St Jordi :)", (val * 180.0 / Math.PI), d,
                        (val * 180.0 / Math.PI),
                        "Feliç St Jordi :)", (val * Math.PI / 180.0), d,
                        (val * Math.PI / 180.0),
                        "Feliç St Jordi :)", (val * 0.621371), d,
                        (val * 0.621371),
                        "Feliç St Jordi :)", (val / 0.621371), d,
                        (val / 0.621371),
                        "Feliç St Jordi :)", (val * 2.20462), d,
                        (val * 2.20462),
                        "Feliç St Jordi :)", (val / 2.20462), d,
                        (val / 2.20462),
                        "Feliç St Jordi :)", (val * 0.264172), d,
                        (val * 0.264172),
                        "Feliç St Jordi :)", (val / 0.264172), d,
                        (val / 0.264172),
                        "Feliç St Jordi :)", (val + 273.15), d,
                        (val + 273.15),
                        "Feliç St Jordi :)", (val - 273.15), d,
                        (val - 273.15),
                        "Feliç St Jordi :)", (1.8 * (val - 273.15) + 32), d,
                        (1.8 * (val - 273.15) + 32),
                        "Feliç St Jordi :)", ((val + 459.67) * 5.0 / 9.0), d,
                        ((val + 459.67) * 5.0 / 9.0),
                        "Feliç St Jordi :)",
                        ((val + 459.67) * 5.0 / 9.0 - 273.15), d,
                        ((val + 459.67) * 5.0 / 9.0 - 273.15),
                        "Feliç St Jordi :)", ( 1.8 * val + 32), d,
                        ( 1.8 * val + 32),
                        "Feliç St Jordi :)", (val*100), d,
                        (val*100),
                        "Feliç St Jordi :)", (val/100), d,
                        (val/100),
                        }};
        return Arrays.asList(data);
    }

    @Test
    public void testConvR2g()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.RAD_GRAUS);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                r2g1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                r2g2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), r2g3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , r2g4);
    }

    @Test
    public void testConvG2r()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.GRAUS_RAD);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                g2r1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                g2r2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), g2r3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , g2r4);
    }

    @Test
    public void testConvKm2m()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.KM_MILLA);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                km2m1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                km2m2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), km2m3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , km2m4);
    }

    @Test
    public void testConvM2km()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.MILLA_KM);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                m2km1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                m2km2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), m2km3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , m2km4);
    }

    @Test
    public void testConvKh2lb()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.KG_LLIURA);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                kg2lb1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                kg2lb2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), kg2lb3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , kg2lb4);
    }

    @Test
    public void testConvLb2kg()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.LLIURA_KG);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                lb2kg1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                lb2kg2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), lb2kg3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , lb2kg4);
    }

    @Test
    public void testConvL2gal()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.LITRE_GALO);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                l2gal1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                l2gal2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), l2gal3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , l2gal4);
    }

    @Test
    public void testConvGal2l()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.GALO_LITRE);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                gal2l1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                gal2l2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), gal2l3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , gal2l4);
    }

    @Test
    public void testConvC2K()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.CELSIUS_KELVIN);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                c2k1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                c2k2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), c2k3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , c2k4);
    }

    @Test
    public void testConvK2c()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.KELVIN_CELSIUS);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                k2c1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                k2c2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), k2c3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , k2c4);
    }

    @Test
    public void testConvK2f()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.KELVIN_FAHRENHEIT);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                k2f1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                k2f2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), k2f3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , k2f4);
    }

    @Test
    public void testConvF2k()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.FAHRENHEIT_KELVIN);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                f2k1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                f2k2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), f2k3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , f2k4);
    }

    @Test
    public void testConvF2c()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.FAHRENHEIT_CELSIUS);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                f2c1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                f2c2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), f2c3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , f2c4);
    }

    @Test
    public void testConvKm2h()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.KM2_HECATAREA);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                km2h1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                km2h2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), km2h3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , km2h4);
    }

    @Test
    public void testConvH2km()
    {
        MatriuCeles mc = new MatriuCeles(2, 2);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        mc.setCela(cela4.getCela(), cela4.getFila(), cela4.getColumna());

        MatriuCeles res = operador.converteixUnitats(mc, ConversioUnitats.HECTAREA_KM2);

        assertEquals(res.getCela(cela1.getFila(), cela1.getColumna()).getText(),
                h2km1);
        assertEquals(res.getCela(cela2.getFila(), cela2.getColumna()).getNum(),
                h2km2);
        assertEquals(res.getCela(cela3.getFila(), cela3.getColumna()).getData(), h2km3);
        assertEquals(res.getCela(cela4.getFila(), cela4.getColumna()).getNum()
                , h2km4);
    }

}
