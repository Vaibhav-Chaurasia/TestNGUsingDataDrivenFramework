package TestNG0008AnnotationAttributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ignoreMissingDependencies {
	
	/*
	 * There are two types of dependencies in TestNG:
	 * 1. Hard Dependency : All the methods you depend on must have run and succeeded for you to run. 
	 * If at least one failure occurred in your dependencies, you will not be invoked and marked as a SKIP in the report.
	 * 
	 * 2. Soft Dependency : You will always be run after the methods you depend on, even if some of them have failed. 
	 * This is useful when you just want to make sure that your test methods are run in a certain order but their 
	 * success doesn’t really depend on the success of others. A soft dependency is obtained by adding “alwaysRun=true” 
	 * in your @Test annotation.
	 * 
	 * ignoreMissingDependencies: In case, we have soft dependency on any module, then we can ignore that testcase and run
	 * directly the second one to save time and resources.
	 * */

	public WebDriver driver;
	static WebElement enterFirstName;
	
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
		System.out.println("This method is disabled");
		Thread.sleep(2000);
	}
	
	@Test (priority = 2, dependsOnMethods = "enterFirstName", ignoreMissingDependencies = true)
	public void enterLastName() throws InterruptedException {
		enterFirstName = driver.findElement(By.xpath("//input[@name='name']"));
		enterFirstName.sendKeys("Vaibhav");
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void quitDriver() {
		driver.quit();
	}
}