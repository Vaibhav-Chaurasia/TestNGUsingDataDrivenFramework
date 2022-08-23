package TestNG0014ListenerClassesExecution;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

//In case anyone wants to run it from here directly i.e. on class level.
@Listeners(TestNG0013ListenerClasses.ListenerIMethodInterceptor.class)

//In case, any one wants to run it from xml file then, use - IMethodInterceptor.xml

public class IMethodInterceptorExecution {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void waitBeforeTest() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	@AfterMethod
	public void waitAfterTest() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	@BeforeTest
	public void launchTheBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		String baseUrl = "https://accounts.lambdatest.com/login";
		driver.get(baseUrl);
	}
	
	@Test(priority = 1)
	public void enterEmail() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("vaicha.oracle@gmail.com");
		Thread.sleep(2000);
	}
	
	@Test(priority = 1)
	public void enterPassword() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test@1234");
		Thread.sleep(2000);
	}
	
	@Test(priority = 2)
	public void clickLoginButton() throws InterruptedException {
		driver.findElement(By.xpath("//button[@id='login-button']")).click();
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void quitDriver() {
		driver.quit();
	}
}