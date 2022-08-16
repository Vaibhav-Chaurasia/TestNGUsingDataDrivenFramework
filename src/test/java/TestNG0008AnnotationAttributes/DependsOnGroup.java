package TestNG0008AnnotationAttributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DependsOnGroup {
	
	/*
	 * dependsOnGroups: This attribute is used to make test methods depend on a particular group. 
	 * We can also specify a list of groups this method depends on.
	 * 
	 * All of the methods of these groups are executed first before this method. 
	 * If any test method belonging to a particular group is failed, the dependent 
	 * test method will not be run and will be flagged as a SKIP.
	 * 
	 * It specifies the list of groups this method or class belongs to.
	 * 
	 *  Example @Test(dependsOnGroups = {"m3", "m1"})
	 * */

	public WebDriver driver;
	
	@BeforeTest
	public void launchBrowser() throws InterruptedException{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	@Test (groups = "EnterData")
	public void enterFirstName() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Vaibhav");
		Thread.sleep(2000);
	}
	
	@Test  (dependsOnGroups = "EnterData")
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