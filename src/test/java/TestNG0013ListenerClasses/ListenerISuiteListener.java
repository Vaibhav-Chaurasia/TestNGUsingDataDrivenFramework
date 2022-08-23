package TestNG0013ListenerClasses;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class ListenerISuiteListener implements ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		System.out.println("onStart function started of ISuiteListener" + suite.getName());
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("onFinish function started of ISuiteListener" + suite.getName());
	}
}