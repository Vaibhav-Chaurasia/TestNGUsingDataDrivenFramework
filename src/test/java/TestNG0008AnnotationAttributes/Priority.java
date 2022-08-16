package TestNG0008AnnotationAttributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Priority {
	
	/*
	 * This attribute sets the priority at the test method level. 
	 * It tells TestNG which priority order has to follow to run the test method.
	 * */

	public WebDriver driver;

	@Test (priority = 1)
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
	
	@Test (priority = 2)
	public void navigateToGoogle() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();

		Thread.sleep(2000);
		driver.navigate().to("https://www.google.com");
		Thread.sleep(5000);
		
		driver.quit();
	}
}