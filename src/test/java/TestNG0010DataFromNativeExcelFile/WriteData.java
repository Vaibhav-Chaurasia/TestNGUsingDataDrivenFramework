package TestNG0010DataFromNativeExcelFile;

import java.io.IOException;

import org.testng.annotations.Test;

import Utils.ExcelDataConfig;

public class WriteData {
	
	@Test
	public void writeData() throws IOException {
		
		String path = "TestData.xlsx";
		ExcelDataConfig dataConfig = new ExcelDataConfig(path);
		
		dataConfig.getSheetName("STUDENT_DATA");
		
		dataConfig.createCellWithValues(1, 1, "Vaibhav", path);
	}
}