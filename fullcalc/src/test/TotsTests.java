package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CelaAllTests.class,

        DocumentTest.class,

        FullAllTests.class,

        MatriuCelesAllTests.class,
        MatriuCelesCasosExtrTest.class,
        MatriuCelesElimCol.class,
        MatriuCelesElimFila.class,
        MatriuCelesEntrColTest.class,
        MatriuCelesEntrTest.class,
        MatriuCelesGetSetTest.class,

        ParserDocumentTest.class,
        ParserFullTest.class,

        OperadorTest.class,

        ControladorDominiTest.class
})
public class TotsTests {
}
