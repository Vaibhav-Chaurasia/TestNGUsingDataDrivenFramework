package TestNG0008AnnotationAttributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InvocationCount {
	
	/*
	 * invocationCount: This attribute is used to execute a method the number of times. It acts as a loop.
	 * */

	public WebDriver driver;

	@Test (invocationCount = 2)
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