package TestNG0008AnnotationAttributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InvocationCountAndThreadPoolSize {
	
	/*
	 * The threadPoolSize is an attribute of the @Test annotation. 
	 * This attribute informs TestNG how many threads should testng spawn for the 
	 * current @Test annotated test method. This attribute is relevant only when you 
	 * use the invocationCount attribute of the @Test annotation.
	 * 
	 * The threadPoolSize attribute tells TestNG to create a thread pool to run the 
	 * test method via multiple threads. With thread pool, it will greatly 
	 * decrease the running time of the test method.
	 * */

	public WebDriver driver;

	@Test (invocationCount = 3, threadPoolSize = 2)
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
}