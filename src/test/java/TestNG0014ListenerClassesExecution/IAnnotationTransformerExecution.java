package TestNG0014ListenerClassesExecution;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


//In case anyone wants to run it from here directly i.e. on class level.
@Listeners(TestNG0013ListenerClasses.ListenerIAnnotationTransformer.class)

//In case, any one wants to run it from xml file then, use - IAnnotationTransformer.xml
public class IAnnotationTransformerExecution {
	
	public WebDriver driver;
	
	@BeforeTest
	public void launchTheBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		String baseUrl = "https://accounts.lambdatest.com/login";
		driver.get(baseUrl);
	}
	
	@Test (invocationCount = 5)
	public void enterEmail() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("vaicha.oracle@gmail.com");
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void quitDriver() {
		driver.quit();
	}
}