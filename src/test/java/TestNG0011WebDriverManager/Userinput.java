package TestNG0011WebDriverManager;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Userinput {

	WebDriver driver;

	@Test
	public void userInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the name of the browser to launch (Chrome/Firefox/Edge)");

		String browserName = scanner.nextLine();

		if (browserName.equalsIgnoreCase("Chrome")) {
			// set the path of the chrome driver executable
			WebDriverManager.chromedriver().setup();

			// create an instance of the ChromeDriver
			driver = new ChromeDriver();
		} 

		else if (browserName.equalsIgnoreCase("Firefox")) {
			// set the path of the Firefox driver executable
			WebDriverManager.firefoxdriver().setup();

			// create an instance of the FirefoxDriver
			driver = new FirefoxDriver();
		} 

		else if(browserName.equalsIgnoreCase("Edge")){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		else {
			System.out.println("Chosen Incorrect Browser");
		}

		// navigate to a URL
		driver.get("https:www.google.com");

		// close the browser
		driver.quit(); 
	}
}