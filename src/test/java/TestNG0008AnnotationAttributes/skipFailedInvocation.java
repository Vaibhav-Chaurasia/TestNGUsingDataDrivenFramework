package TestNG0008AnnotationAttributes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class skipFailedInvocation {
	
	/*
	 * This method skip the failed invocation or threads. It means that program not ends abruptly.
	 * */
	
	public WebDriver driver;
	public static String getCurrentURL;
	
	@Test(priority = 1, retryAnalyzer = TestNG0013ListenerClasses.RetryIRetryAnalyzer.class, skipFailedInvocations = true)
	public void negativeScenario() throws InterruptedException {
		//Negative Scenario
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();

		getCurrentURL = driver.getCurrentUrl();
		System.out.println("Current URL is - " + getCurrentURL);
		
		Assert.assertEquals(getCurrentURL, "https://accounts.lambdatest.com/registe");
		
		driver.quit();
	}
	
	@Test(priority = 2, retryAnalyzer = TestNG0013ListenerClasses.RetryIRetryAnalyzer.class)
	public void positiveScenario() {
		//Postive Scenario
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();

		getCurrentURL = driver.getCurrentUrl();
		System.out.println("Current URL is - " + getCurrentURL);
		
		Assert.assertEquals(getCurrentURL, "https://accounts.lambdatest.com/register");
		
		driver.quit();
	}
}