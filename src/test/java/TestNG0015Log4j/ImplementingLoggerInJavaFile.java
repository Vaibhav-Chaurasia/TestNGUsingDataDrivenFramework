package TestNG0015Log4j;

import java.io.IOException;

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

//This is the listener class with Log4j integrated. The output you can view in LoggingInfo folder in root.
@Listeners(TestNG0015Log4j.ListenerITestListenerWithLog4j.class)

public class ImplementingLoggerInJavaFile {

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
	public void launchTheBrowser() throws IOException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		String baseUrl = "https://accounts.lambdatest.com/login";
		driver.get(baseUrl);

		TestNG0015Log4j.Logs.startTestCase("launchTheBrowser");
	}

	@Test(priority = 1)
	public void enterEmail() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("vaicha.oracle@gmail.com");
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void enterPassword() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test@1234");
		Thread.sleep(2000);
	}

	@Test(priority = 3)
	public void clickLoginButton() throws InterruptedException {
		driver.findElement(By.xpath("//button[@id='login-butto']")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 4)
	public void waitForSometime() throws InterruptedException {
		Thread.sleep(2000);
	}

	@AfterTest
	public void quitDriver() {
		driver.quit();
	}
}