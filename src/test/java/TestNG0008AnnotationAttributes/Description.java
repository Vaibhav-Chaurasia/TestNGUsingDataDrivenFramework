package TestNG0008AnnotationAttributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Description {
	
	/*
	 * It is the description of this method.
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

	@Test (priority = 1, alwaysRun = false, description = "Enter First Name with incorrect xpath")
	public void enterFirstName() throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@name='nam']")).sendKeys("Vaibhav"); //incorrect xpath
		Thread.sleep(2000);
	}
	
	@Test (priority = 2, alwaysRun = true, description = "Enter Last Name with correct xpath")
	public void enterLastName() throws InterruptedException {
		
		//inspite of incorrect xpath in first method, this will run.
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(" Chaurasia");
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void quitDriver() {
		driver.quit();
	}
}