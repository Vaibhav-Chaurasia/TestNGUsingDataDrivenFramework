package TestNG0012TestNGXMLOperations;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utils.ExcelDataConfig;

import org.apache.poi.ss.usermodel.CellType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataProviderUsingExcel {

	//To run this use - DataProviderInSameClass.xml

	public WebDriver driver;

	@Test(dataProvider = "EmailForm", dataProviderClass = DataProviderUsingExcel.class)
	public void loginForm(String username, String password) throws InterruptedException {
		
		System.out.println(password);
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		String baseUrl = "https://accounts.lambdatest.com/login";
		driver.get(baseUrl);


		//This function enters the text into the input field
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

		Thread.sleep(2000);

		driver.quit();
	}

	
//	public static Object[][] readData(String sheetName) throws IOException {
//
//		FileInputStream fis = new FileInputStream("TestData.xlsx");
//		System.out.println("FIS - " + fis);
//
//		XSSFWorkbook workbook = new XSSFWorkbook(fis);
//		XSSFSheet sheet = workbook.getSheet(sheetName);
//		System.out.println("Sheet - " + sheet);
//
//		//I have added test data in the cell A2 as "Scenarios"
//		//Cell A2 = row 0 and column 1. It reads first row as 0 and Column A as 1.
//		int rows = sheet.getLastRowNum();
//		int cols = sheet.getRow(0).getLastCellNum();
//
//		Object[][] data = new Object[rows][cols];
//
//		for(int i = 0; i < rows; i++) {
//			XSSFRow row  = sheet.getRow(i + 1);
//
//			for(int j = 0; j < cols; j++) {
//				XSSFCell cell = row.getCell(j);
//				CellType cellType = cell.getCellType();
//
//				switch (cellType) {
//				case STRING:
//					data[i][j] = cell.getStringCellValue();
//					System.out.println(data[i][j]);
//					break;
//
//				case NUMERIC:
//					data[i][j] = NumberToTextConverter.toText(cell.getNumericCellValue());
//					System.out.println(data[i][j]);
//					break;
//
//				case BOOLEAN:
//					data[i][j] = cell.getBooleanCellValue();
//					System.out.println(data[i][j]);
//					break;
//
//				case FORMULA:
//					data[i][j] = cell.getCellFormula();
//					System.out.println(data[i][j]);
//					break;
//
//				case BLANK:
//					System.out.println();
//					break;
//
//				default:
//					System.out.println("Failed Case");
//				}
//			}
//		}
//		return data;
//	}

	@DataProvider (name="EmailForm")
	public Object[][] getDataFromDataprovider() throws Exception{
		Object[][] data = ExcelDataConfig.readDataFromExcel("TestData.xlsx", "Scenario");
		return (data);
	}
}