package TestNG0008AnnotationAttributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstAndLastTimeOnly {
	
	/*
	 * If true and the @Test method about to be run anycodings_java has an invocationCount > 1, 
	 * this anycodings_java BeforeMethod will only be invoked once anycodings_java
	 * (before the first test invocation).
	 * */

	public WebDriver driver;
	
	@BeforeClass
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		
	}
	
	//It runs only once
	@BeforeMethod (firstTimeOnly = true)
	public void waitingBeforeMethod() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("waiting before method....");
	}
	
	//It runs only once
	@AfterMethod (lastTimeOnly = true)
	public void waitingAfterMethod() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("waiting after method....");
	}

	@Test (priority = 1)
	public void enterFullName(){
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Vaibhav");
	}
	
	@Test (priority = 2)
	public void enterEmailID() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("vaicha.oracle@gmail.com");
		Thread.sleep(2000);
		driver.quit();
	}
}


/*The @BeforeMethod is specific to a class not to an XML file. 
 * The @BeforeMethod annotated method will be invoked before the execution of 
 * each test method where the test method is nothing but a test case.
 */