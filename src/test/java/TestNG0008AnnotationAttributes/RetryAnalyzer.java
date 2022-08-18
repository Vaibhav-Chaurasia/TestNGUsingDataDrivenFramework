package TestNG0008AnnotationAttributes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RetryAnalyzer {
	
	/*
	 * To retry a failed test, we will use the IRetryAnalyzer interface. It reruns the Selenium TestNG 
	 * tests when they are failed. If you work on a test automation project, you’d know that the most 
	 * difficult part of automation is the analysis of test executions. At the end of the execution, 
	 * you need to analyze failed test cases and try to figure out if there’s any false positive/flaky 
	 * situation caused by network glitch, time-out, or some other problem.
	 * 
	 * Also, some tests are dependent on each other and when a test fails maybe we want to run the 
	 * tests before that test, in other words, we want to run the tests inside a test class from 
	 * scratch. Also, I will explain how to rerun tests in a class for TestNG projects. Let’s start 
	 * with how to rerun a failed test with IRetryAnalyzer.
	 * 
	 * First of all, you need to create a separate class that implements this IRetryAnalyzer - "Retry.java" 
	 * in the same folder
	 * 
	 * The next step is to associate your test cases with IRetryAnalyzer, below is the code.
	 * */
	
	public WebDriver driver;
	public static String getCurrentURL;
	
	@Test(priority = 1, retryAnalyzer = Retry.class)
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
	
	@Test(priority = 2, retryAnalyzer = Retry.class)
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