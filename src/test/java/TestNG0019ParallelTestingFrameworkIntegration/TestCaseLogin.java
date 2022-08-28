package TestNG0019ParallelTestingFrameworkIntegration;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utils.SpreadsheetReader;

public class TestCaseLogin extends BaseClass{
	
	//To run this you can use ParallelTestingAtClassLevel.xml or ParallelTestingAtSuiteLevel.xml or ParallelTestingAtClassLevelWithDifferentBrowser.xml

	protected ScreenLoginScreen loginScreen;
	SoftAssert softAssertion;

	public static SpreadsheetReader readSpreadsheet = new SpreadsheetReader();
	static List<List<Object>> values = null;

	@BeforeTest
	public void setUp() {
		loginScreen = new ScreenLoginScreen(driver);

		try {
			values = readSpreadsheet.readCompleteSpreadSheet("LoginScreenData");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test (priority = 1)
	public void requiredFieldValidation(){
		softAssertion = new SoftAssert();

		//Click on loginButton
		loginScreen.clickLoginButton();

		//Getting actual validation message from website
		String getActualValidationMessage = loginScreen.getValidationMessageForInvalidCredentials();
		System.out.println("The Validation Message is - " + getActualValidationMessage);

		//Getting expected validation message from spreadsheet
		String expectedValidationMessage = values.get(3).get(1).toString();

		//Asserting expected and actual
		Assert.assertEquals(getActualValidationMessage, expectedValidationMessage);

		softAssertion.assertAll();
	}

	@Test (priority = 2)
	public void enterIncorrectFormat() {
		softAssertion = new SoftAssert();

		loginScreen.enterIncorrectFormatEmail(values.get(5).get(1).toString());
		loginScreen.enterIncorrectFormatPassword(values.get(6).get(1).toString());

		//Getting actual validation message from website
		String getActualValidationMessage = loginScreen.getValidationMessageForIncorrectFormat();
		System.out.println("The Validation Message is - " + getActualValidationMessage);

		//Getting expected validation message from spreadsheet
		String expectedValidationMessage = values.get(7).get(1).toString();

		//Asserting expected and actual
		Assert.assertEquals(getActualValidationMessage, expectedValidationMessage);

		softAssertion.assertAll();
	}
}