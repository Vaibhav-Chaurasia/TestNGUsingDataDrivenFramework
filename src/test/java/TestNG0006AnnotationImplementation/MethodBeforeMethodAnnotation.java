package TestNG0006AnnotationImplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MethodBeforeMethodAnnotation {

	public WebDriver driver;
	
	@BeforeClass
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod
	public void waiting() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("waiting....");
	}

	@Test (priority = 1)
	public void enterFullName() throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Vaibhav");
		Thread.sleep(2000);
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