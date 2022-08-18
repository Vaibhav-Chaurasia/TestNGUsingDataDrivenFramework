package TestNG0008AnnotationAttributes;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class expectedExceptionsMessageRegExp {

	public WebDriver driver;
	String message = "Manisha";
	
	@Test(expectedExceptions = ArithmeticException.class)
	public void testPrintMessage() {
		System.out.println("Inside testPrintMessage()");
	}

	@Test(expectedExceptions = { IOException.class }, expectedExceptionsMessageRegExp = "Pass Message test")
	public void exceptionTestOne() throws Exception {
		throw new IOException("Pass Message test");
	}

	@Test(expectedExceptions = { IOException.class }, expectedExceptionsMessageRegExp = ".* Message .*")
	public void exceptionTestTwo() throws Exception {
		throw new IOException("Pass Message test");
	}

	@Test(expectedExceptions = { IOException.class }, expectedExceptionsMessageRegExp = "Pass Message test")
	public void exceptionTestThree() throws Exception {
		throw new IOException("Fail Message test"); //Fails
	}
}