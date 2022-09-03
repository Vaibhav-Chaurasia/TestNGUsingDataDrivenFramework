package TestNG0018FrameworkIntegration;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.mortbay.log.Log;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utils.ElementUtils;
import Utils.SpreadsheetReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static SpreadsheetReader readSpreadsheet = new SpreadsheetReader();
	static List<List<Object>> values = null;

	public static Capabilities capability;
	public static String baseURL;

	//Extent Report Declaration
	protected static ExtentSparkReporter htmlReporter;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	public static String configFilePath = "\\src\\main\\resources\\ConfigFiles\\extent-config.xml";

	public static Browser browser;

	public enum Browser {
		CHROME,
		FIREFOX
	}


	@BeforeTest
	public void testBaseSetup() throws Exception {

		values = readSpreadsheet.readCompleteSpreadSheet("BrowserSelection");
		browser = Browser.valueOf(values.get(1).get(0).toString());
		System.out.println("Browser = " + browser);

		values = readSpreadsheet.readCompleteSpreadSheet("LoginScreenData");
		baseURL = values.get(0).get(1).toString();
		System.out.println(baseURL);

		switch (browser) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			capability = ((RemoteWebDriver) driver).getCapabilities(); //Remote Web Driver to get Browser info
			driver.get(baseURL);
			break;

		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			capability = ((RemoteWebDriver) driver).getCapabilities(); //Remote Web Driver to get Browser info
			driver.get(baseURL);
			break;

		default:
			System.out.println("Browser Not On the List");
			break;
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterTest
	public void quitDriver() {
		if(driver!=null) {
			driver.quit();
		}
	}


	/*---------------------------------------Start of Extent Report---------------------------------------------------*/

	//This method helps to access the driver i.e. way to access the various elements and events like click, tap etc.
	public static WebDriver getDriver() {
		return driver;
	}

	//The ExtentHtmlReporter creates a rich standalone HTML file. It allows several configuration options via the config() method.
	public ExtentSparkReporter getHtmlReporter() {
		return htmlReporter;
	}

	//This method helps to set the HTML reporter, so that HTML reporter components will be accessible.
	public void setHtmlReporter(ExtentSparkReporter htmlReporter) {
		BaseClass.htmlReporter = htmlReporter;
	}

	//Extent Reports. Beautifully crafted reports and realtime analytics so you can look at your tests in a totally different way. 
	public static ExtentReports getExtent() {
		return extent;
	}

	//This method helps to set the ExtentReports, so that HTML reporter components will be accessible.
	public static void setExtent(ExtentReports extent) {
		BaseClass.extent = extent;
	}

	//This method is used to set the tests which is executed.
	public static void setTest(ExtentTest test) {
		BaseClass.test = test;
	}



	/*This function is executed before the printing of HTML Report. Basically, it does the basic settings before report printing i.e.
	 * Set the Path
	 * Get Host name
	 * Read Excel Data
	 * Set Report Title
	 * Set Theme for HTML report
	 * Other basic settings before report prints
	 */
	@BeforeSuite(alwaysRun = true)
	public void startReport() throws IOException {

		/*
		 * In case, you want to use extent-config.xml to set report configuration, then uncomment the following code -
		 * htmlReporter.loadXMLConfig(System.getProperty("user.dir") + configFilePath);
		 * 
		 * And comment the following code -
		 * htmlReporter.config().setDocumentTitle("Test Automation Report");
		 * htmlReporter.config().setReportName("Test Report Automation <img style=\"width:15%;\" src='../src/main/resources/Images/DMILOGO.png' />");
		 * */

		//Delete the existing folder where report generated
		ElementUtils.deleteDirectory("ExecutionTestNG");

		// initialize the HtmlReporter
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/ExecutionTestNG/SparkReport.html");

		// Create an object of Extent Reports
		//htmlReporter.loadXMLConfig(System.getProperty("user.dir") + configFilePath);

		//initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		//configuration items to change the look and feel
		//add content, manage tests etc
		extent.setSystemInfo("Host Name", Utils.SystemInfo.getHostName());
		htmlReporter.config().setDocumentTitle("Test Automation Report");
		htmlReporter.config().setReportName("Test Report Automation <img style=\"width:15%;\" src='../src/main/resources/Images/DMILOGO.png' />");
		extent.setSystemInfo("User Name", "Vaibhav Chaurasia");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		extent.setSystemInfo("OS", Utils.SystemInfo.getOperatingSystem());
	}


	/*This method works after the report prints and does the several functions i.e.,
	 * Quit the Driver working for reporting
	 * Print logs if any
	 */
	@AfterTest(alwaysRun = true)
	public static void tearDown() throws Exception {
		if (getDriver() != null) {
			try {
				Log.info("Quiting Driver in @after Test Class TestBase.java");
				Log.info("Extent Report has been flushed Successfully");
				extent.flush();
			} catch (Exception e) {
				Log.info("Unable to Quit Driver");
				TestNG0015Log4j.ListenerITestListenerWithLog4j.Logs.error(e);
			}
		}
	}


	/*This method works after the Basic settings is completed i.e. at the time of reporting. Basically it prints the status of the
	 * result i.e.,
	 * Success and manage the color
	 * Failure and manage the color
	 * Maintain to Skip the test and manage its color
	 * If test fails, then print the cause of failure.
	 * */
	@AfterMethod (alwaysRun = true)
	public void getResult(ITestResult result) throws IOException {
		test = extent.createTest(result.getMethod().getMethodName());

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable(), MediaEntityBuilder
					.createScreenCaptureFromPath(ElementUtils.getScreenShot(driver, result.getName())).build());
		}
		else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		}
		else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
		}
	}


	@AfterSuite
	public void teardown() {
		//To write or update test information to reporter
		//This code is written in aftersuite. So, that it executes only once.
		extent.setSystemInfo("Browser Name", capability.getBrowserName());
		extent.setSystemInfo("Browser Version", capability.getVersion());
		
		extent.flush();
	}
	/*---------------------------------------End of Extent Report---------------------------------------------------*/
}