package TestNG0012TestNGXMLOperations;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataProviderWithITestContext {

	@DataProvider(name="EmailForm")
	public Object[][] getDataFromDataprovider(ITestContext c){
		Object[][] groupArray = null;
		for (String group : c.getIncludedGroups()) {
			if(group.equalsIgnoreCase("A")){
				groupArray = new Object[][] { 
					{"Vaibhav", "India"}, 
					{"Krishna", "UK"}, 
					{"Bhupesh", "USA"} 
				};
				break;	
			}
			else if(group.equalsIgnoreCase("B"))
			{
				groupArray = new Object[][] { 
					{"Canada"}, 
					{"Russia"}, 
					{"Japan"} 
				};
			}
			break;
		}
		return groupArray;		
	}
}