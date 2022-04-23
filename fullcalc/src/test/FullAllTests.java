package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        FullBuidaBlocTest.class,
        FullCasosExtrTest.class,
        FullCopiaBlocTest.class,
        FullGetBlocTest.class,
        FullGetBlocZeroOffTest.class,
        FullGetSetTest.class,
        FullMouBlocTest.class
})

public class FullAllTests {
}