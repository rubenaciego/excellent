package test;
import domini.ExcepcioForaLimits;
import domini.Full;
import org.junit.Test;

public class FullCasosExtrTest {
    Full full;

    /**
     * @brief S'executa buidaBloc definint un bloc que traspassa els limits, per veure que salta l'excepcio.
     */
    @Test(expected = ExcepcioForaLimits.class)
    public void TestBuidaBlocFora() {
        full = new Full(4, 5);
        full.buidaBloc(4, 3, 1, 2);
    }

    /**
     * @brief S'executa copiaBloc definint el bloc origen de manera que surti fora limits, per veure que salta
     * l'excepcio.
     */
    @Test(expected = ExcepcioForaLimits.class)
    public void TestCopiaBlocFora1() {
        full = new Full(4, 5);
        full.copiaBloc(2, 2, 3, 2, 1, 1);
    }

    /**
     * @brief S'executa copiaBloc definint el bloc desti de manera que surti fora limits, per veure que salta
     * l'excepcio.
     */
    @Test(expected = ExcepcioForaLimits.class)
    public void TestCopiaBlocFora2() {
        full = new Full(9, 6);
        full.copiaBloc(2, 2, 3, 2, 7, 2);
    }

    /**
     * @brief S'executa mouBloc definint el bloc origen de manera que surti fora limits, per veure que salta
     * l'excepcio.
     */
    @Test(expected = ExcepcioForaLimits.class)
    public void TestMouBlocFora1() {
        full = new Full(4, 5);
        full.copiaBloc(-1, 0, 3, 2, 1, 1);
    }

    /**
     * @brief S'executa copiaBloc definint el bloc desti de manera que surti fora limits, per veure que salta
     * l'excepcio.
     */
    @Test(expected = ExcepcioForaLimits.class)
    public void TestMouBlocFora2() {
        full = new Full(15, 9);
        full.copiaBloc(0, 0, 10, 2, -5, 1);
    }

    /**
     * @brief S'executa getBloc definint un bloc que no hi cap dins els limits, per veure que salta l'excepcio.
     */
    @Test(expected = ExcepcioForaLimits.class)
    public void TestGetBlocFora1() {
        full = new Full(23, 4);
        full.getBloc(5, 2, 20, 2);
    }

    /**
     * @brief S'executa getBloc definint un surt fora dels limits, per veure que salta l'excepcio.
     */
    @Test(expected = ExcepcioForaLimits.class)
    public void TestGetBlocFora2() {
        full = new Full(2, 2);
        full.getBloc(2, 2, 1, 1);
    }
}
