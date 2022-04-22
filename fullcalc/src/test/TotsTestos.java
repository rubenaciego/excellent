package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CelaDataTest.class,
        CelaNumTest.class,
        CelaRefTest.class,

        DocumentTest.class,

        FullBuidaBlocTest.class,
        FullCasosExtrTest.class,
        FullGetBlocTest.class,
        FullGetBlocZeroOffTest.class,
        FullGetSetTest.class,

        MatriuCelesAllTests.class,
        MatriuCelesCasosExtrTest.class,
        MatriuCelesElimCol.class,
        MatriuCelesElimFila.class,
        MatriuCelesEntrColTest.class,
        MatriuCelesEntrTest.class,
        MatriuCelesGetSetTest.class
})
public class TotsTestos {
}
