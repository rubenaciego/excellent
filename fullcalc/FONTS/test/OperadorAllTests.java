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
        OperadorOrdenaTest.class,
        OperadorCercaTest.class,
        OperadorLongitudTest.class,
        OperadorConversioUnitatsTest.class,
        OperadorExtreuDatesTest.class,
        OperadorExtreuLongitudTextTest.class,
        OperadorExtreuDiesStringTest.class,
        OperadorOpAritmeticaTest.class,
        OperadorTruncaTest.class
})
public class OperadorAllTests {
}
