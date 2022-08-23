package TestNG0015Log4j;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Log4jImplementation {
	public static String configFilePath = "\\src\\main\\resources\\ConfigFiles\\log4j.properties";
	static Logger log = Logger.getLogger(Log4jImplementation.class);
	
	public static void main(String[] args) throws IOException {

		//Get Absolute path of Log4j Property file
		File getConfigFilePath = new File(System.getProperty("user.dir") + configFilePath);
		String getAbsoluteConfigFilePath = getConfigFilePath.getCanonicalPath();
		System.out.println(getAbsoluteConfigFilePath);

		PropertyConfigurator.configure(getAbsoluteConfigFilePath);

		//Launch chrome in Debug mode
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);

		WebDriver driver = new ChromeDriver(options);

		JavascriptExecutor js = (JavascriptExecutor)driver;
		driver.get("https://www.browserstack.com/users/sign_in");

		//Print Log Information before each line.
		log.info("Open browserstack");
		driver.manage().window().maximize();

		log.info("Maximize window size");
		js.executeScript("document.getElementById('user_email_login').value='rbc@xyz.com';");

		log.info("enter username");
		js.executeScript("document.getElementById('user_password').value='password';");

		log.info("enter password");
		js.executeScript("document.getElementById('user_submit').click();");

		log.info("click on login");
		driver.close();
	}
}