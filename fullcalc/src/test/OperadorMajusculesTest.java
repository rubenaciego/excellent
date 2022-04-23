package test;

import domini.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class OperadorMajusculesTest {
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

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {(new EntradaMatriuCeles(0, 0, new CelaText("test", "minúscules"))),
                (new EntradaMatriuCeles(1, 1, new CelaText("test", "Una mica de Tot, Sí"))),
                (new EntradaMatriuCeles(2, 2, new CelaText("test",
                        "1234567890'¡!\"·$%&/()=?¿-,.\\<>`+'ç^*Ç{}[]ºª|@#~€¬"))), "MINÚSCULES",
                        "UNA MICA DE TOT, SÍ", "1234567890'¡!\"·$%&/()=?¿-,.\\<>`+'ç^*Ç{}[]ºª|@#~€¬"}};
        return Arrays.asList(data);
    }

    @Test
    public void testMajuscular()
    {
        MatriuCeles mc = new MatriuCeles(3, 3);
        mc.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        mc.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        mc.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());

        MatriuCeles res = operador.converteixMajuscules(mc);

        assertEquals(mc.getCela(cela1.getFila(), cela1.getColumna()).getText(), res1);
        assertEquals(mc.getCela(cela2.getFila(), cela2.getColumna()).getText(), res2);
        assertEquals(mc.getCela(cela3.getFila(), cela3.getColumna()).getText(), res3);
    }
}
