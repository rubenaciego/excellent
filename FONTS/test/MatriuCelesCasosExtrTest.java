package test;
import domini.CelaText;
import domini.ExcepcioFilaColumnaInvalida;
import domini.ExcepcioForaLimits;
import domini.MatriuCeles;
import org.junit.Test;

public class MatriuCelesCasosExtrTest {

    MatriuCeles matriu;

    /**
     * @brief Es crida a setCela especificant una Cela fora limits, i es comprova que salta l'excepcio
     */
    @Test(expected = ExcepcioForaLimits.class)
    public void TestSetCelaFora1() {
        matriu = new MatriuCeles(4, 5);
        CelaText celaText = new CelaText("Hola");
        matriu.setCela(celaText, 5, 2);
    }

    /**
     * @brief Es crida a setCela especificant una Cela amb indexs negatius, i es comprova que salta l'excepcio
     */
    @Test(expected = ExcepcioForaLimits.class)
    public void TestSetCelaFora2() {
        matriu = new MatriuCeles(4, 5);
        CelaText celaText = new CelaText("Hola");
        matriu.setCela(celaText, -1, 2);
    }

    /**
     * @brief Es crida a getCela especificant una Cela fora limits, i es comprova que salta l'excepcio
     */
    @Test(expected = ExcepcioForaLimits.class)
    public void TestGetCelaFora1() {
        matriu = new MatriuCeles(10, 6);
        matriu.getCela(1, 7);
    }

    /**
     * @brief Es crida a setCela especificant una Cela amb indexs negatius, i es comprova que salta l'excepcio
     */
    @Test(expected = ExcepcioForaLimits.class)
    public void TestGetCelaFora2() {
        matriu = new MatriuCeles(10, 6);
        matriu.getCela(8, -5);
    }

    /**
     * @brief Es crida a esborraCela especificant una Cela fora limits, i es comprova que salta l'excepcio
     */
    @Test(expected = ExcepcioForaLimits.class)
    public void TestEsborraCelaFora1() {
        matriu = new MatriuCeles(1, 2);
        matriu.esborraCela(1, 1);
    }

    /**
     * @brief Es crida a setCela especificant una Cela amb indexs negatius, i es comprova que salta l'excepcio
     */
    @Test(expected = ExcepcioForaLimits.class)
    public void TestEsborraCelaFora2() {
        matriu = new MatriuCeles(3, 3);
        matriu.esborraCela(2, -1);
    }

    /**
     * @brief Es crida a getEntradesColumna especificant una columna fora limits, i es comprova que salta l'excepcio.
     */
    @Test(expected = ExcepcioFilaColumnaInvalida.class)
    public void TestGetEntradesColFora1() {
        matriu = new MatriuCeles(4, 3);
        matriu.getEntradesColumna(3);
    }

    /**
     * @brief Es crida a getEntradesColumna especificant una columna negativa, i es comprova que salta l'excepcio.
     */
    @Test(expected = ExcepcioFilaColumnaInvalida.class)
    public void TestGetEntradesColFora2() {
        matriu = new MatriuCeles(8, 9);
        matriu.getEntradesColumna(-6);
    }

    /**
     * @brief Es crida a eliminaFila especificant una fila no existent, i es comprova que salta l'excepcio.
     */
    @Test(expected = ExcepcioFilaColumnaInvalida.class)
    public void TestEliminaFila() {
        matriu = new MatriuCeles(0, 4);
        matriu.eliminaFila(0);
    }

    /**
     * @brief Es crida a eliminaFila especificant una columna no existent, i es comprova que salta l'excepcio.
     */
    @Test(expected = ExcepcioFilaColumnaInvalida.class)
    public void TestEliminaColumna() {
        matriu = new MatriuCeles(7, 4);
        matriu.eliminaColumna(5);
    }
}