package TestNG0009DataFromSpreadsheet;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.SpreadsheetReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RealExampleOfFetchingData {

	public WebDriver driver;
	
	//Create object for spreadsheetreader class
	public static SpreadsheetReader readSpreadSheet = new SpreadsheetReader();

	//Spreadsheet Rows and columns works as a List, so we need to declare values as List.
	static List<List<Object>> values = null;

	@BeforeTest
	public void launchBrowser() throws InterruptedException{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		try {
			values = readSpreadSheet.readCompleteSpreadSheet("Scenario");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void enterFirstName() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(values.get(11).get(1).toString());
		Thread.sleep(3000);
	}

	@AfterTest
	public void quitDriver() {
		driver.quit();
	}
}