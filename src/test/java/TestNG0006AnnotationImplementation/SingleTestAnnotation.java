package TestNG0006AnnotationImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SingleTestAnnotation {

	public WebDriver driver;

	@Test
	public void ac() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.lambdatest.com/register");
		driver.manage().window().maximize();

		Thread.sleep(2000);
		driver.findElement(By.linkText("Privacy Policy")).click();
	}
}

/*@Test acts same as main function in java. Each and every code executes inside @Test*/