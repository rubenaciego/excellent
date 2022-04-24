package test;
import domini.CelaNum;
import domini.CelaText;
import domini.MatriuCeles;
import domini.EntradaMatriuCeles;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class MatriuCelesElimFila {

    @Parameter(0)
    public EntradaMatriuCeles cela1;
    @Parameter(1)
    public EntradaMatriuCeles cela2;
    @Parameter(2)
    public EntradaMatriuCeles cela3;
    @Parameter(3)
    public int filaAEliminar;

    MatriuCeles matriu;

    @Before
    public void setUp() {
        matriu = new MatriuCeles(30, 30);
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{(new EntradaMatriuCeles(1, 1, new CelaNum("2.1", 2.1))),
                (new EntradaMatriuCeles(4, 5, new CelaText("Hola"))),
                (new EntradaMatriuCeles(15, 2, new CelaText("foo"))), 4},
                {(new EntradaMatriuCeles(6, 10, new CelaText("bar"))),
                        (new EntradaMatriuCeles(9, 6, new CelaNum("143.0", 143.0))),
                        (new EntradaMatriuCeles(10, 6, new CelaText("Hola"))), 9}};
        return Arrays.asList(data);
    }

    /**
     * @brief Test parametritzat per comprovar que el metode eliminaFila elimina el numero de fila que se li passa.
     * Per fer-ho, s'executa l'operacio i es confirma que les Cela d'un nombre de fila inferior no varien, les d'un
     * numero superior es veuen mogudes cap a un numero de fila inferior, i que les Cela de la fila corresponent
     * deixen d'existir.
     */
    @Test
    public void TestEliminaFila() {
        matriu.setCela(cela1.getCela(), cela1.getFila(), cela1.getColumna());
        matriu.setCela(cela2.getCela(), cela2.getFila(), cela2.getColumna());
        matriu.setCela(cela3.getCela(), cela3.getFila(), cela3.getColumna());
        matriu.eliminaFila(filaAEliminar);

        assertEquals(cela1.getCela(), matriu.getCela(cela1.getFila(), cela1.getColumna()));
        assertEquals(cela3.getCela(), matriu.getCela(cela3.getFila()-1, cela3.getColumna()));
        assertNotEquals(cela2.getCela(), matriu.getCela(cela2.getFila(), cela3.getColumna()));
        assertNotEquals(cela2.getCela(), matriu.getCela(cela2.getFila()-1, cela3.getColumna()));
    }
}