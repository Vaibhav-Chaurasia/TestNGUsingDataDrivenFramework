package TestNG0006AnnotationImplementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SuiteBeforeAfterSuiteAnnotation {
	
	public static WebDriver driver;
	
	@BeforeSuite
	public void launchBrowser() throws InterruptedException {
		System.out.println("Executing Before Suite");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		
	}
	
	
	@AfterSuite
	public void quitDriver() {
		System.out.println("Executing After Suite");
		driver.quit();
	}
}

/* @BeforeSuite
 * Till now, we read about the @BeforeTest and @AfterTest which have control over the particular folder not on the entire framework.
 *  The tag has control over the whole XML file. The tag is the parent of all the test folders.
 *  The @BeforeSuite annotated method is executed before the execution of all the test cases defined in the folder.
 *  
 *  Generally, @BeforeSuite is used when we have different URLs to run your test cases. Environment variables are set
 *  in a @BeforeSuite annotated method so that before executing all the test cases, you need to load all the environment 
 *  variables for your framework, and then it starts executing your test cases.
 *  
 *  The @BeforeSuite annotated method is given as the first priority, so it is executed before all the other test methods.
 *  */


/* @AfterSuite
 * The @AfterSuite annotated method is executed after the execution of all the test methods in the Suite. 
 * The Suite is basically a testng.xml file so we can say that @AfterSuite annotated method is executed 
 * after the execution of an XML file.
 * 
 * The @BeforeSuite annotation is used to set up or start the selenium drivers while the 
 * @AfterSuite annotation is used to stop the selenium web drivers.
 * */