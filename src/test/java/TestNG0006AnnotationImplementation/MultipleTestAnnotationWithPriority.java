package TestNG0006AnnotationImplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultipleTestAnnotationWithPriority {

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


/*The disadvantage in using only @Test, that, we have to write to launch browser again and again. So, we will using @Before
 * So, that, it reduces the duplicacy of the code.*/