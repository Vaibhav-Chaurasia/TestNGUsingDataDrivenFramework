package TestNG0008AnnotationAttributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class expectedException {

	/*
	 * expectedExceptions: The attribute expectedExceptions is used for exception testing. 
	 * It specifies the type of exceptions that are expected to be thrown by a test method during execution.
	 * 
	 * If the exception thrown by a test method does not match with the exception list entered 
	 * by user, the test method will be marked as failed.
	 * 
	 * TestNG also supports multiple expected exceptions for verification while executing a particular test.
	 * 
	 * Example - @Test(expectedExceptions = {IOException.class, ArithmeticException.class})
	 * */

	public WebDriver driver;

	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
	}

	@Test(expectedExceptions = InterruptedException.class)
	public void waiting() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("Interrupted Exceptions");
		throw new InterruptedException();
	}

	@Test (priority = 1)
	public void enterFirstName() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Vaibhav");
		Thread.sleep(2000);
	}

	@Test (priority = 2)
	public void enterLastName() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(" Chaurasia");
		Thread.sleep(2000);
	}

	@AfterTest
	public void quitDriver() {
		driver.quit();
	}
}