package TestNG0009DataFromSpreadsheet;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.SpreadsheetReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RealExampleOfWritingData {
	
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

		try {
			values = readSpreadSheet.readCompleteSpreadSheet("Scenario");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void waiting() throws InterruptedException {
		Thread.sleep(2000);
	}

	@Test (priority = 1)
	public void enterFirstName() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(values.get(11).get(1).toString());
	}
	
	@Test (priority = 2)
	public void writeValueInDifferentSheet() throws Exception {
		WebElement firstName = driver.findElement(By.xpath("//input[@name='name']"));
		
		Thread.sleep(2000);
		
		//Writing data
		List<Object> writeValue = new ArrayList<Object>();
		writeValue.add(firstName.getAttribute("value"));
		
		/* To Print in an individual cell
		 * Mention Sheet name with Cell number.
		*/
		readSpreadSheet.writeSheet(writeValue, "More" + "!C1");
		readSpreadSheet.writeSheet(writeValue, "More" + "!D1");
		
		
		/* To Print data in an complete sheet
		 * Mention Sheet name and in this, method print data starting from cell A1 and overwrites previous data
		 * if inside these cells.
		*/
		readSpreadSheet.writeSheet(writeValue, "Scenario1");
	}
	
	

	@AfterTest
	public void quitDriver() {
		driver.quit();
	}
}