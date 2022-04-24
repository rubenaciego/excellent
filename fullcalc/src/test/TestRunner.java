package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(OperadorTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful())
            System.out.println("Tots els tests passats");
        else
            System.out.println("Alguns tests no passats");
    }
}
