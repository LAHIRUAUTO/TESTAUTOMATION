package Utilities;

import org.testng.IClass;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log extends TestListenerAdapter {

    @Override
    public void onTestStart(ITestResult tr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm/");
        LocalDateTime now = LocalDateTime.now();
        log("Test Case Execution Started at: " + dtf.format(now));
    }

    @Override
    public void onTestSuccess(ITestResult tr) {

        log("Test Case: " + tr.getName() + " - PASSED");

        // This will print the class name in which the method is present
        //log(tr.getTestClass());

        // This will print the priority of the method.
        // If the priority is not defined it will print the default priority as
        // 'o'
        //log("Priority of this method is " + tr.getMethod().getPriority());

        //System.out.println(".....");
    }

    @Override
    public void onTestFailure(ITestResult tr) {

        log("Test Case: " + tr.getName() + " - FAILED");
        //log("Priority of this method is " + tr.getMethod().getPriority());
        //System.out.println(".....");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log("Test Case: " + tr.getName() + " - SKIPPED");
        //System.out.println(".....");
    }

    private void log(String methodName) {
        System.out.println(methodName);
    }

    private void log(IClass testClass) {
        System.out.println(testClass);
    }
}