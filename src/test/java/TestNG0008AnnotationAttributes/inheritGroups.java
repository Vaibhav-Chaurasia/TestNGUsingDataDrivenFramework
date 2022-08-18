package TestNG0008AnnotationAttributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * "inheritGroups = true" executes all configurations steps @Before/@After - except @BeforeGroup or @AfterGroup
 * "inheritGroups = true" and @BeforeGroup/@AfterGroup has same value then both @BeforeGroup or @AfterGroup gets executed
 * 
 * If true, this method will belong to groups specified in the @Test annotation at the class level.
 */

@Test(groups = "myGroup")
public class inheritGroups {
	
	public static WebDriver driver;
	static WebElement fullName;
	public static String pageSource;
	
	@BeforeSuite(inheritGroups = true)
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		
		System.out.println("Before Suite Executing....");
	}

	@BeforeTest(inheritGroups = true)
	public void getURL() {
		System.out.println("Before Test Executing....");
		String currentURL = driver.getCurrentUrl();
		System.out.println("The Current URL is - " + currentURL);
	}

	@BeforeGroups(groups = {"myGroup"})
	public void getPageInitialization() {
		System.out.println("Initializing page source in BeforeGroups...");
		pageSource = driver.getPageSource();
	}

	@BeforeGroups(groups = {"myGroup"})
	public void getPageSource() {
		System.out.println("Printing Page source in Before Groups.....");
		System.out.println(pageSource);
	}

	@BeforeClass(inheritGroups = true)
	public void initializeDriver() {
		System.out.println("Before Class Executing....");
		fullName = driver.findElement(By.xpath("//input[@name='name']"));
	}

	@BeforeMethod(inheritGroups = true)
	public void waitBeforeTest() throws InterruptedException {
		System.out.println("Waiting before Method...");
		Thread.sleep(2000);
	}

	@Test (priority = 1)
	public void enterFirstName() {
		System.out.println("First Test Executing....");
		fullName.sendKeys("Vaibhav");
	}
	
	@Test (priority = 2)
	public void enterLastName() {
		System.out.println("Second Test Executing....");
		fullName.sendKeys(" Chaurasia");
	}

	@AfterSuite(inheritGroups = true)
	public void quitDriver() throws InterruptedException {
		System.out.println("After Suite Executing....");
		Thread.sleep(2000);
		driver.quit();
	}

	@AfterTest(inheritGroups = true)
	public void getCurrentTitle() {
		System.out.println("After Test Executing....");
		String currentTitle = driver.getTitle();
		System.out.println("The Current URL is - " + currentTitle);
	}

	@AfterGroups(groups = {"myGroup"})
	public void getPageSorceCompleted() {
		System.out.println("Printing page source is completed in After Groups...");
	}

	@AfterClass(inheritGroups = true)
	public void getFullName() {
		System.out.println("After Class Executing....");
		String getFullName = fullName.getText();
		System.out.println("The Full Name is - " + getFullName);
	}

	@AfterMethod(inheritGroups = true)
	public void waitAfterTest() throws InterruptedException {
		System.out.println("Waiting after Method...");
		Thread.sleep(2000);
	}
}