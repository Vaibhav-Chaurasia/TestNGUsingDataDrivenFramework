package TestNG0013ListenerClasses;

import org.testng.IExecutionListener;

public class ListenerIExecutionListener implements IExecutionListener{

	@Override
	public void onExecutionStart() {
		long startTime= System.currentTimeMillis();
		System.out.println("Inform all the suite have started execution at - " + startTime);
	}

	@Override
	public void onExecutionFinish() {
		long endTime= System.currentTimeMillis();
		System.out.println("Inform all the suite have finished execution at - " + endTime);
	}
}