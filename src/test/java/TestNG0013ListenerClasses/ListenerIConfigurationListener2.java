package TestNG0013ListenerClasses;

import org.testng.IConfigurationListener2;
import org.testng.ITestResult;

public class ListenerIConfigurationListener2 implements IConfigurationListener2{

	@Override
	public void onConfigurationSuccess(ITestResult itr) {
		System.out.println("on configuration success");
	}

	@Override
	public void onConfigurationFailure(ITestResult itr) {
		System.out.println("on configuration failure");
	}

	@Override
	public void onConfigurationSkip(ITestResult itr) {
		System.out.println("on configuration skip");
	}

	@Override
	public void beforeConfiguration(ITestResult tr) {
		System.out.println("called before the configuration method is invoked");
	}
}