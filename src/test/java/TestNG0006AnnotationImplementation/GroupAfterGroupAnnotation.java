package TestNG0006AnnotationImplementation;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupAfterGroupAnnotation extends GroupBeforeGroupAnnotation{
	
	@Test (priority = 1, groups = "User Details")
	public void enterFullName() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Vaibhav");
		Thread.sleep(2000);
	}
	
	@Test (priority = 2, groups = "User Details")
	public void enterLastName() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(" Chaurasia");
		Thread.sleep(2000);
	}
	
	@Test (priority = 3, groups = "Email")
	public void enterEmailID() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("vaicha.oracle");
	}
	
	@Test (priority = 4, groups = "Email")
	public void enterEmailIDAfterDot() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("@gmail.com");
	}
}