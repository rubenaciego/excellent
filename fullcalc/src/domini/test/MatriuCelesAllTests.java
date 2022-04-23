package domini.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        MatriuCelesElimCol.class,
        MatriuCelesElimFila.class,
        MatriuCelesCasosExtrTest.class,
        MatriuCelesEntrColTest.class,
        MatriuCelesEntrTest.class,
        MatriuCelesGetSetTest.class,
        MatriuCelesGetBlocTest.class })

public class MatriuCelesAllTests {
}
