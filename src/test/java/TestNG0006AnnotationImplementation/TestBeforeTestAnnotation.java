package TestNG0006AnnotationImplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBeforeTestAnnotation {

	public WebDriver driver;
	
	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();

		Thread.sleep(2000);
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


/*@BeforeTest is the method which executes before the @Test*/