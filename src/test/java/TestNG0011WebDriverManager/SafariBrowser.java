package TestNG0011WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

public class SafariBrowser {
	public WebDriver driver;
	
	@Test
	public void launchBrowser() throws InterruptedException{
		
		//Works only on Mac system
		//SafariDriver requires Safari 10 running on OSX El Capitan or greater.
		driver = new SafariDriver();

		driver.get("http://www.google.com/");
		
		Thread.sleep(2000);
	}
}