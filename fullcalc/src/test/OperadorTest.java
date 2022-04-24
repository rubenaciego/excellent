package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        OperadorEstadistica1Test.class,
        OperadorEstadistica2Test.class,
        OperadorMajusculesTest.class,
        OperadorMinusculesTest.class,
        OperadorTransposaTest.class,
        OperadorReemplacaTest.class,
        OperadorOrdenaTest.class
})

public class OperadorTest {
}
