package TestNG0006AnnotationImplementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GroupBeforeGroupAnnotation {
	
	public WebDriver driver;
	
	@BeforeGroups("User Details")
	public void LaunchBrowser() throws InterruptedException {
		System.out.println("Implementing Before Groups....");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
	
	@AfterGroups("Email")
	public void quitDriver() throws InterruptedException {
		System.out.println("Implementing After Groups....");
		Thread.sleep(2000);
		driver.quit();
	}
}


/* @BeforeGroups
 * TestNG allows the testers to create multiple test cases into a single group through the use of 
 * attribute 'group' in the @Test annotation. We can say that TestNG groups allow you to add similar 
 * functionalities in the same group. For example, student_id, student_name, student_address are the 
 * details of a student, and all these details are added in a same group, i.e., "student details".
 * 
 * @BeforeGroups: The @BeforeGroups annotated method will run only once before all the test methods 
 * belonging to a specified group have been executed.
 * */

/* @AfterGroups
 * The @AfterGroups annotated method will run only once after the execution of all the
 * test methods of a specified group.
 * */