package TestNG0006AnnotationImplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MethodAfterMethodAnnotation {

	public WebDriver driver;
	
	@BeforeClass
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void waitingBeforeMethod() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("waiting before method....");
	}
	
	@AfterMethod
	public void waitingAfterMethod() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("waiting after method....");
	}

	@Test (priority = 1)
	public void enterFullName() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Vaibhav");
	}
	
	@Test (priority = 2)
	public void enterEmailID() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("vaicha.oracle@gmail.com");
	}
	
	
	@AfterClass
	public void quitDriver() {
		driver.quit();
	}
}


/*The @AfterMethod is specific to a class not to an XML file. 
 * The @AfterMethod annotated method will be invoked after the execution of 
 * each test method where the test method is nothing but a test case.
 */