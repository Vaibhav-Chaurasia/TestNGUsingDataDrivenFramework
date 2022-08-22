package TestNG0012TestNGXMLOperations;

import org.testng.annotations.DataProvider;

public class DataProviderSimple {
	@DataProvider (name="EmailForm")
	public Object[][] getDataFromDataprovider(){
		return new Object[][] 
				{
					{"Vaibhav", "India"},
					{"Krishna", "UK"},
					{"Bhupesh", "USA"}
				};
	}
}