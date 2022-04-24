package test.runners;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import test.CelaRefTest;
import test.DocumentTest;

public class DocumentUnitTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DocumentTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful())
            System.out.println("Tots els tests passats");
        else
            System.out.println("Alguns tests no passats");
    }
}
