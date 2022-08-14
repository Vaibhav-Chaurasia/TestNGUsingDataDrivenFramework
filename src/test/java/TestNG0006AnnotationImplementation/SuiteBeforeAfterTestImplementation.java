package TestNG0006AnnotationImplementation;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class SuiteBeforeAfterTestImplementation extends SuiteBeforeAfterSuiteAnnotation{
	
	@Test (priority = 1)
	public void enterEmail() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("vaicha.oracle@gmail.com");
		Thread.sleep(2000);
	}
	
	@Test (priority = 2)
	public void enterFullName() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Vaibhav");
		Thread.sleep(2000);
	}
}