package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CelaAllTests.class,

        DocumentTest.class,

        FullBuidaBlocTest.class,
        FullCasosExtrTest.class,
        FullGetSetTest.class,

        MatriuCelesAllTests.class,
        MatriuCelesCasosExtrTest.class,
        MatriuCelesElimCol.class,
        MatriuCelesElimFila.class,
        MatriuCelesEntrColTest.class,
        MatriuCelesEntrTest.class,
        MatriuCelesGetSetTest.class,

        OperadorTest.class
})
public class TotsTests {
}
