package TestNG0008AnnotationAttributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Groups {
	
	/*
	 * 
	 * It specifies the list of groups this method or class belongs to.
	 * If, we are executing groups, then each annotation, which is linked to the 
	 * @test annotation, should also have an attribute "group".
	 * 
	 * To run this, go to testng.xml file and write code over there to run.
	 * For this example we have file named as - 'GroupExecution.xml' in root folder
	 * */

	public WebDriver driver;
	
	@BeforeTest (groups = {"EnterData", "Launch"})
	public void launchBrowser() throws InterruptedException{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	//To View the result go to TestNG
	@Test (groups = {"EnterData"})
	public void enterFirstName() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Vaibhav");
		Thread.sleep(2000);
	}
	
	@Test (groups = {"EnterData"})
	public void enterLastName() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(" Chaurasia");
		Thread.sleep(2000);
	}
	
	@Test
	public void enterEmailID() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("vaicha.oracle@gmail.com");
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void quitDriver() {
		driver.quit();
	}
}