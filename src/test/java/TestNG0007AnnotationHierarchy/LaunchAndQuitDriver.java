package TestNG0007AnnotationHierarchy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchAndQuitDriver {
	
	public static WebDriver driver;
	
	@BeforeSuite
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		
		System.out.println("Before Suite Executing....");
	}
	
	
	@AfterSuite
	public void quitDriver() throws InterruptedException {
		System.out.println("After Suite Executing....");
		Thread.sleep(2000);
		driver.quit();
	}
}