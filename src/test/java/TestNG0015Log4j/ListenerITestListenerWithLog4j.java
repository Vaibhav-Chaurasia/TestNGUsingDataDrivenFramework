package TestNG0015Log4j;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerITestListenerWithLog4j implements ITestListener {

	public static String configFilePath = "\\src\\main\\resources\\ConfigFiles\\log4j.properties";
	public static String getAbsoluteConfigFilePath;
	public static Exception e;
	Logs logging = new Logs();

	//This method is used to get Logs.
	public static Logger Logs = Logger.getLogger(Logs.class);

	@Override
	public void onTestStart(ITestResult result){
		//Get Absolute path of Log4j Property file
		File getConfigFilePath = new File(System.getProperty("user.dir") + configFilePath);

		try {
			getAbsoluteConfigFilePath = getConfigFilePath.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(getAbsoluteConfigFilePath);
		PropertyConfigurator.configure(getAbsoluteConfigFilePath);

		TestNG0015Log4j.Logs.startTestCase("Test Case Started - " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		TestNG0015Log4j.Logs.information("Test Case Succeed - " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		TestNG0015Log4j.Logs.fatal("Test Case Failed - " + result.getName());
		Logs.error(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		TestNG0015Log4j.Logs.information("Test Case Skipped - " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		TestNG0015Log4j.Logs.warn("Test Case Partially failed - " + result.getName() + ITestResult.SUCCESS_PERCENTAGE_FAILURE);
	}

	@Override
	public void onStart(ITestContext context) {
		TestNG0015Log4j.Logs.startTestCase("Test Case Started - " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		TestNG0015Log4j.Logs.endTestCase("Test Case Ends - " + context.getName());
	}
}