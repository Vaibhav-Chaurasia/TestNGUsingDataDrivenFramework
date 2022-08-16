package TestNG0007AnnotationHierarchy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class InitializingAndGettingDetails extends URLAndTitle{

	static WebElement fullName; 
	
	@BeforeClass
	public void initializeDriver() {
		System.out.println("Before Class Executing....");
		fullName = driver.findElement(By.xpath("//input[@name='name']"));
	}
	
	
	@AfterClass
	public void getFullName() {
		System.out.println("After Class Executing....");
		String getFullName = fullName.getText();
		System.out.println("The Full Name is - " + getFullName);
	}
}