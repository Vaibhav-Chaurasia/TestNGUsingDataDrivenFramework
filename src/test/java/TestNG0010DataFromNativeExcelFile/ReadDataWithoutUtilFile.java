package TestNG0010DataFromNativeExcelFile;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadDataWithoutUtilFile {
	
	@Test
	public void readData() throws IOException {
		FileInputStream fis = new FileInputStream("TestData.xlsx");
		
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//I have added test data in the cell A2 as "Scenarios"
		//Cell A2 = row 0 and column 1. It reads first row as 0 and Column A as 1.
		Row row = sheet.getRow(0);
		
		//It prints value individually for cell
		Cell cell = row.getCell(1);
		System.out.println(cell);
		
		//It prints value of the cell but considering index of Rows
		System.out.println(sheet.getRow(1).getCell(1));
	}
}