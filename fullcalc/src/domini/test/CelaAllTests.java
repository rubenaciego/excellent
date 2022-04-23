package domini.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        CelaDataTest.class,
        CelaNumTest.class,
        CelaTextTest.class,
        CelaRefTest.class,
})

public class CelaAllTests {
}