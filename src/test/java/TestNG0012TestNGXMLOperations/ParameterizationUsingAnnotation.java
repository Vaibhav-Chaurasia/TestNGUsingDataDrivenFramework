package TestNG0012TestNGXMLOperations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParameterizationUsingAnnotation {
	
	/* At Test Level
	 * To run this file go to parameterizationUsingAnnotationAtTestLevel.xml
	 * 
	 * or
	 * 
	 * At Suite Level
	 * Go to parameterizationUsingAnnotationAtSuiteLevel.xml
	
*/
	public WebDriver driver;
	
	@Parameters({"email", "password"})
	@Test
	public void loginForm(String email, String password) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		String baseUrl = "https://accounts.lambdatest.com/login";
		driver.get(baseUrl);


		//This function enters the text into the input field
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
	}
}