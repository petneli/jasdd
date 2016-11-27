package test;

/**
 * Created by Danutz on 26/11/16.
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RestOnlnineShopServiceTestRun {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RestOnlineShopServiceTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}