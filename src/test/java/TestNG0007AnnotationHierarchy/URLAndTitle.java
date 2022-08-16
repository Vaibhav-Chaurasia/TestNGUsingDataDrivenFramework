package TestNG0007AnnotationHierarchy;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class URLAndTitle extends LaunchAndQuitDriver{
	
	@BeforeTest
	public void getURL() {
		System.out.println("Before Test Executing....");
		String currentURL = driver.getCurrentUrl();
		System.out.println("The Current URL is - " + currentURL);
	}
	
	@AfterTest
	public void getCurrentTitle() {
		System.out.println("After Test Executing....");
		String currentTitle = driver.getTitle();
		System.out.println("The Current URL is - " + currentTitle);
	}
}