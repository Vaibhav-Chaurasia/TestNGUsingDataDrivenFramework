package TestNG0007AnnotationHierarchy;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class WaitMethods extends InitializingAndGettingDetails{

	@BeforeMethod
	public void waitBeforeTest() throws InterruptedException {
		System.out.println("Waiting before Method...");
		Thread.sleep(2000);
	}
	
	@AfterMethod
	public void waitAfterTest() throws InterruptedException {
		System.out.println("Waiting after Method...");
		Thread.sleep(2000);
	}
}