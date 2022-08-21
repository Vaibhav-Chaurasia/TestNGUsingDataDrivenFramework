package TestNG0011WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InternetExplorerBrowser {
	public WebDriver driver;
	
	@Test
	public void launchBrowser() throws InterruptedException{
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
}