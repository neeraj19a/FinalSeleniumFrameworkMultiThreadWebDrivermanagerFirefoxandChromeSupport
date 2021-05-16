package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener {

    @Override
    public void onFinish(ITestContext testContext) {
        Log.endTestCase("========" + testContext.getName() + "Test Finished==========");
    }

    @Override
    public void onStart(ITestContext arg0) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        Log.info("========" + testResult.getName() + "Test Failed========");
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        Log.info("========" + testResult.getName() + "Test Skipped========");
    }

    @Override
    public void onTestStart(ITestResult testResult) {

        Log.startTestCase(testResult.getName());
        Log.info("========" + testResult.getName() + "Test is starting========");

    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        Log.info("========" + testResult.getName() + "Test completed successfully========");

    }
}