package TestNG0012TestNGXMLOperations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParameterizationUsingDataProviderUsingITextContext {

	//To run this use DataProviderUsingITestContext.xml

	public WebDriver driver;

	@Test (priority = 1, dataProvider = "EmailForm", dataProviderClass = DataProviderWithITestContext.class, groups = "A")
	public void loginFormGroupA(String email, String password) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		String baseUrl = "https://accounts.lambdatest.com/login";
		driver.get(baseUrl);


		//This function enters the text into the input field
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

		Thread.sleep(2000);

		driver.quit();
	}


	@Test (priority = 2, dataProvider = "EmailForm", dataProviderClass = DataProviderWithITestContext.class, groups = "B")
	public void loginFormGroupB(String email) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		String baseUrl = "https://accounts.lambdatest.com/login";
		driver.get(baseUrl);


		//This function enters the text into the input field
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);

		Thread.sleep(2000);

		driver.quit();
	}	
}