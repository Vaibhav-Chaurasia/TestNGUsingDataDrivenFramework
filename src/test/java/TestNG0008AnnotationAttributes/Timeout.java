package TestNG0008AnnotationAttributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Timeout {
	
	/*
	 * timeout: In this attribute, we specify the timeout value for the test method(in milliseconds)
	 * to execute. If the test takes more than the timeout value specified, 
	 * the test terminates and is marked as a fail. By default, the value of the timeout attribute is Zero.
	 * It can be achieved in two ways:
	 * a) At suit level: It will be applicable for all tests in the TestNG suite.
	 * <suite name="TimeOut at Suite level" time-out='500' verbose='1'>
	 * 
	 * b) At each test method level: It will be applicable for each test method. 
	 * It will override the time period if you have configured it at the suite level.
	 * */

	public WebDriver driver;

	@Test (timeOut = 10000)
	public void openPrivacyPolicy() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();

		Thread.sleep(2000);
		driver.findElement(By.linkText("Privacy Policy")).click();
		Thread.sleep(5000);
		
		driver.quit();
	}
}