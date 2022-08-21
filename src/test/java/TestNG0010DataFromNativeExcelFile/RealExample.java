package TestNG0010DataFromNativeExcelFile;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.ExcelDataConfig;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RealExample {
	public static WebDriver driver;
	String path = "TestData.xlsx";
	ExcelDataConfig dataConfig = new ExcelDataConfig(path);
	

	@BeforeTest
	public void launchBrowser() throws InterruptedException{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	@Test (priority = 1)
	public void enterFirstName() throws InterruptedException {
		WebElement firstName = driver.findElement(By.xpath("//input[@name='name']"));
		firstName.sendKeys(dataConfig.getData("Scenario", 1, 1));
		Thread.sleep(3000);
	}
	
	@Test (priority = 2)
	public void writeToExcelFile() throws InterruptedException, IOException {
		WebElement firstName = driver.findElement(By.xpath("//input[@name='name']"));
		
		dataConfig.getSheetName("STUDENT_DATA");
		dataConfig.createCellWithValues(1, 1, firstName.getAttribute("value"), path);
		
		Thread.sleep(3000);
	}

	@AfterTest
	public void quitDriver() {
		driver.quit();
	}
}