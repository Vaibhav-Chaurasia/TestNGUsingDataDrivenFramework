package TestNG0011WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OperaBrowser {
	public WebDriver driver;
	
	@Test
	public void launchBrowser() throws InterruptedException{
		WebDriverManager.operadriver().setup();
		driver = new OperaDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
}