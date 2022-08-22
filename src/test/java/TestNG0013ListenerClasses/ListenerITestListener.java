package TestNG0013ListenerClasses;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerITestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Tests Starts - " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Succeeded - "  + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed - " + result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Tests Skipped - " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Tests Succeed with some percentage - " + result.getName() + ITestResult.SUCCESS_PERCENTAGE_FAILURE);
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Process Starts - " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Process Finished - " + context.getName());
	}
}