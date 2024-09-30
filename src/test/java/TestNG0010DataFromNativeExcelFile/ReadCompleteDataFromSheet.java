package TestNG0010DataFromNativeExcelFile;

import org.testng.annotations.Test;

import Utils.ExcelDataConfig;

public class ReadCompleteDataFromSheet {
	@Test
	public void readCompleteData() {
		ExcelDataConfig dataConfig = new ExcelDataConfig("TestData.xlsx");
		dataConfig.getCompleteData(0);
	}
}