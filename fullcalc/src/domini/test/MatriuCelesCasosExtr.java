package domini.test;
import domini.CelaText;
import domini.ExcepcioFilaColumnaInvalida;
import domini.ExcepcioForaLimits;
import domini.MatriuCeles;
import org.junit.Before;
import org.junit.Test;

public class MatriuCelesCasosExtr {

    MatriuCeles matriu;

    @Before
    public void setUp() {
        matriu = new MatriuCeles();
    }

    @Test(expected = ExcepcioForaLimits.class)
    public void TestSetCelaFora1() {
        matriu = new MatriuCeles(4, 5);
        CelaText celaText = new CelaText("Hola");
        matriu.setCela(celaText, 5, 2);
    }

    @Test(expected = ExcepcioForaLimits.class)
    public void TestSetCelaFora2() {
        matriu = new MatriuCeles(4, 5);
        CelaText celaText = new CelaText("Hola");
        matriu.setCela(celaText, -1, 2);
    }

    @Test(expected = ExcepcioForaLimits.class)
    public void TestGetCelaFora1() {
        matriu = new MatriuCeles(10, 6);
        matriu.getCela(1, 7);
    }

    @Test(expected = ExcepcioForaLimits.class)
    public void TestGetCelaFora2() {
        matriu = new MatriuCeles(10, 6);
        matriu.getCela(8, -5);
    }

    @Test(expected = ExcepcioForaLimits.class)
    public void TestEsborraCelaFora1() {
        matriu = new MatriuCeles(1, 2);
        matriu.esborraCela(1, 1);
    }

    @Test(expected = ExcepcioForaLimits.class)
    public void TestEsborraCelaFora2() {
        matriu = new MatriuCeles(3, 3);
        matriu.esborraCela(2, -1);
    }

    @Test(expected = ExcepcioFilaColumnaInvalida.class)
    public void TestGetEntradesColFora1() {
        matriu = new MatriuCeles(4, 3);
        matriu.getEntradesColumna(3);
    }

    @Test(expected = ExcepcioFilaColumnaInvalida.class)
    public void TestGetEntradesColFora2() {
        matriu = new MatriuCeles(8, 9);
        matriu.getEntradesColumna(-6);
    }
}
