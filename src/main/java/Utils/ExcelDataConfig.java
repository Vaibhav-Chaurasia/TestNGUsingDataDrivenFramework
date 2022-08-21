package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {
	public static XSSFWorkbook workBook ;
	public static XSSFSheet sheetName;

	public  ExcelDataConfig(String excelPath) {
		try {
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			workBook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public String getData(String sheetname, int row, int column) {
		sheetName = workBook.getSheet(sheetname);  //Method help us to read data From Document excel
		String data = sheetName.getRow(row).getCell(column).getStringCellValue();
		return data;
	}

	public int getRowCount(int sheetIndex) {
		int row = workBook.getSheetAt(sheetIndex).getLastRowNum(); //return the index number
		row = row + 1;
		return row;
	}


	public void getCompleteData(int sheetIndex) {
		Sheet sheet = workBook.getSheetAt(sheetIndex);
		
		for (Row row : sheet) {
			for (Cell cell : row) {
				
				CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
		        System.out.print(cellRef.formatAsString());
		        System.out.print(" - ");
		        
				switch (cell.getCellType()) {
				case STRING:
					System.out.println(cell.getRichStringCellValue().getString());
					break;

				case NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						System.out.println(cell.getDateCellValue());
					} else {
						System.out.println(cell.getNumericCellValue());
					}
					break;

				case BOOLEAN:
					System.out.println(cell.getBooleanCellValue());
					break;

				case FORMULA:
					System.out.println(cell.getCellFormula());
					break;

				case BLANK:
					System.out.println();
					break;

				default:
					System.out.println();
				}	
			}

			System.out.println();
		}
	}
	
	public void getSheetName(String sheetname) {
		sheetName = workBook.getSheet(sheetname);
	}
	
	
	public void createCellWithValues(int rowNumber, int columnNumber, String setCellValues, String excelPath) throws IOException {
		Row row = sheetName.createRow(rowNumber);
		Cell cell = row.createCell(columnNumber);
		cell.setCellValue(setCellValues);
		FileOutputStream fos = new FileOutputStream(excelPath);
		ExcelDataConfig.workBook.write(fos);
		fos.close();
	}
}