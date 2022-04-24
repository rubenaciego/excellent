package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CelaAllTests.class,

        DocumentTest.class,

        FullAllTests.class,

        MatriuCelesAllTests.class,

        ParserDocumentTest.class,
        ParserFullTest.class,

        OperadorAllTests.class,

        ControladorDominiTest.class
})
public class TotsTests {
}
