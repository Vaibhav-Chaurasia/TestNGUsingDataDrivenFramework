package TestNG0009DataFromSpreadsheet;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.ExcelDataConfig;
import Utils.SpreadsheetReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PrintListOfData {

	public static WebDriver driver;
	String path = "TestData.xlsx";
	ExcelDataConfig dataConfig = new ExcelDataConfig(path);
	public static SpreadsheetReader readSpreadSheet = new SpreadsheetReader();

	//Spreadsheet Rows and columns works as a List, so we need to declare values as List.
	static List<List<Object>> values = null;

	@BeforeTest
	public void launchBrowser() throws InterruptedException{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		try {
			values = readSpreadSheet.readCompleteSpreadSheet("Scenario");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void printListOfData() throws Exception {

		List<WebElement> columns = driver.findElements(By.xpath("//*[@id='customers']/tbody/tr"));
		int columnSize = columns.size();
		System.out.println("Total Number of Columns are - " + columnSize);

		String beforeXpath1stColumn = "//*[@id='customers']/tbody/tr[";
		String afterXpath2ndColumn = "]/td[1]";

		//Writing data
		List<Object> writeValue = new ArrayList<Object>();



		for(int i = 2; i <= columnSize; i++) {
			String actualXpath = beforeXpath1stColumn + i + afterXpath2ndColumn;
			WebElement element = driver.findElement(By.xpath(actualXpath));
			System.out.println(element.getText());
			writeValue.add(element.getText());
			readSpreadSheet.writeSheet(writeValue, "List" + "!A2");
		}
	}

	@AfterTest
	public void quitDriver() {
		driver.quit();
	}
}