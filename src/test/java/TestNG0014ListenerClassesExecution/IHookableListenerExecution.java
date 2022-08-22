package TestNG0014ListenerClassesExecution;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

//In case anyone wants to run it from here directly i.e. on class level.
@Listeners(TestNG0013ListenerClasses.ListenerIHookableListener.class)

//In case, any one wants to run it from xml file then, use - IExecutionListener.xml

public class IHookableListenerExecution {
	
	//To run this use - DataProviderInSameClass.xml
	
public WebDriver driver;
	
	@Test (dataProvider = "EmailForm")
	public void loginForm(String email) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		String baseUrl = "https://accounts.lambdatest.com/login";
		driver.get(baseUrl);


		//This function enters the text into the input field
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		Thread.sleep(2000);
		
		driver.quit();
	}
	
    @DataProvider (name="EmailForm")
    public Object[][] getDataFromDataprovider(){
    return new Object[][] 
    	{
            {"Vaibhav"},
            {"Krishna"},
            {"Bhupesh"}
        };
    }
}