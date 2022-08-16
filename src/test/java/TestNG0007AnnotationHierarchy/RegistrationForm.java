package TestNG0007AnnotationHierarchy;

import org.testng.annotations.Test;

public class RegistrationForm extends WaitMethods{
	
	@Test (priority = 1)
	public void enterFirstName() {
		System.out.println("First Test Executing....");
		fullName.sendKeys("Vaibhav");
	}
	
	
	@Test (priority = 2)
	public void enterLastName() {
		System.out.println("Second Test Executing....");
		fullName.sendKeys(" Chaurasia");
	}
}