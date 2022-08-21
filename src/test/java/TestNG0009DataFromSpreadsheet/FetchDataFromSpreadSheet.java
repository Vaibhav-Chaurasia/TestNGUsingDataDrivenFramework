package TestNG0009DataFromSpreadsheet;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Utils.SpreadsheetReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FetchDataFromSpreadSheet {
	
	public WebDriver driver;
	
	//Create object for spreadsheetreader class
	public static SpreadsheetReader readSpreadSheet = new SpreadsheetReader();
	
	//Spreadsheet Rows and columns works as a List, so we need to declare values as List.
	static List<List<Object>> values = null;
	
	@Test
	public void readData() throws Exception {
		
		//We need to give the SheetName to fetch values
		values = SpreadsheetReader.readSpreadSheet("Scenario");
		
		//Printing value having index 2nd row and 1st column. Always index starts from 0.
		System.out.println(values.get(2).get(1).toString());
		
		//Launch browser directly from URL given in spreadsheet
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(values.get(11).get(1).toString());
	}
}