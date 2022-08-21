package TestNG0011WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeBrowser {
	public WebDriver driver;
	
	@Test
	public void launchBrowser() throws InterruptedException{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
}