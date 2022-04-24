package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ParserDocumentTest.class,
        ParserFullTest.class
})
public class ParserAllTests {
}
