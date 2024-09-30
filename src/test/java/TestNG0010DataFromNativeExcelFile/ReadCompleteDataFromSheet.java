package TestNG0010DataFromNativeExcelFile;

import org.testng.annotations.Test;

import Utils.ExcelDataConfig;

public class ReadCompleteDataOfSheet {
	@Test
	public void readCompleteData() {
		ExcelDataConfig dataConfig = new ExcelDataConfig("TestData.xlsx");
		
		//Sheet index number to be filled in parameters
		dataConfig.getCompleteData(0);
	}
}
