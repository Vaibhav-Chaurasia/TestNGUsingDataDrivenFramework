package TestNG0008AnnotationAttributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Enabled {
	
	/*
	 * Disabling a test can be achieved in 2 ways. One is from testng.xml configuration file 
	 * and the second is from the Test annotation property/attribute (i.e. enabled). 
	 * These will disable the said test method from being executed as part of the test suite. 
	 * If this attribute is set to false for the Test annotation at class level, all the public 
	 * methods inside the class will be disabled.
	 * */

	public WebDriver driver;
	
	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	@Test (priority = 1, enabled = false)
	public void enterFirstName() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Vaibhav");
		Thread.sleep(2000);
	}
	
	@Test (priority = 2, enabled = true)
	public void enterLastName() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(" Chaurasia");
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void quitDriver() {
		driver.quit();
	}
}