package TestNG0012TestNGXMLOperations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RunFirstProgramFromTestNG {

	//To Run this program go to TestNG File i.e. firstprogram.xml or runMultipleClasses.xml or runMultipleClassesInAnotherStyle.xml
	public WebDriver driver;

	@Test
	public void launchBrowser() throws InterruptedException{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.quit();
	}
}