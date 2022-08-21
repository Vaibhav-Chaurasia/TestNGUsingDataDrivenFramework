package TestNG0010DataFromNativeExcelFile;

import org.testng.annotations.Test;

import Utils.ExcelDataConfig;

public class ReadDataUsingUtilFile {
	
	@Test
	public void readData() {
		ExcelDataConfig dataConfig = new ExcelDataConfig("TestData.xlsx");
		
		//Sheet Number i.e. Index of SheetName, Row number, column number
		System.out.println(dataConfig.getData("Scenario", 0, 1));
		System.out.println(dataConfig.getData("Scenario", 1, 1));
		
		System.out.println(dataConfig.getData("Scenario", 0, 0));
	}
}