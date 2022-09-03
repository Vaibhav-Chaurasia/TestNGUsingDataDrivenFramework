package TestNG0009DataFromSpreadsheet;

import java.util.ArrayList;
import java.util.List;

import Utils.SpreadsheetReader;

public class SpreadSheetOperations {

	public static SpreadsheetReader readSpreadSheet = new SpreadsheetReader();

	//Spreadsheet Rows and columns works as a List, so we need to declare values as List.
	static List<List<Object>> values = null;

	public static void main(String[] args) throws Exception {

		List<Object> fruits = new ArrayList<Object>();
		
		//Adding elements in the List  
		fruits.add("Mango");
		fruits.add("Apple");
		fruits.add("Banana");
		fruits.add("Grapes");
		
		List<Object> people = new ArrayList<Object>();
		//Adding elements in the List  
		people.add("Ram");
		people.add("Shyam");
		people.add("Bunty");
		people.add("Vaibhav");

		//Get Total Count of Rows
		System.out.println(SpreadsheetReader.getTotalNumberOfRows("Scenario"));

		//Create new sheet within the spreadsheet - This also checks existing sheet with existing name
		readSpreadSheet.createNewSheet(SpreadsheetReader.SPREADSHEET_ID, "TestData");

		Thread.sleep(2000);

		//Create New Sheet and Columns
		//readSpreadSheet.createSheetAndColumn("TestData1", fruits);
		
		Thread.sleep(2000);
		
		//Write data to the spreadsheet and it works in existing sheets.
		readSpreadSheet.writeSheet(people, "TestData" + "!B1");
		readSpreadSheet.writeSheet(fruits, "TestData" + "!B2");
	}
}