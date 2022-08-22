package TestNG0013ListenerClasses;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class ListenerIHookableListener implements IHookable{

	@Override
	public void run(IHookCallBack callBack, ITestResult testResult) {
		Object[] parameterValues = callBack.getParameters();
		if (parameterValues[0].equals("Vaibhav")) {
			System.out.println("Skip the required parameter");
		} else {
			callBack.runTestMethod(testResult);
		}
	}
}