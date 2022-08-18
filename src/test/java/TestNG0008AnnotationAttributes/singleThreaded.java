package TestNG0008AnnotationAttributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class singleThreaded {
	
	/*
	 * When we set invocationCount and invocationTimeOut on a test method, 
	 * invocationTimeOut is the maximum time period TestNG will wait for 
	 * all the invocations of the test method specified in the attribute 
	 * invocationCount.
	 * */

	public WebDriver driver;
	

	@Test (invocationCount = 2, singleThreaded = true)
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