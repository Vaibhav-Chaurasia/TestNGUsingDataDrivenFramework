package TestNG0018FrameworkIntegration;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utils.SpreadsheetReader;

public class TestCaseLogin extends BaseClass{
	
	//To Run this test case you can use FrameworkImplementationAsIndividualTestCases.xml or FrameworkImplementationAsWhole.xml

	protected ScreenLoginScreen loginScreen;
	SoftAssert softAssertion;

	public static SpreadsheetReader readSpreadsheet = new SpreadsheetReader();
	static List<List<Object>> values = null;

	@BeforeTest
	public void setUpLogin() {
		loginScreen = new ScreenLoginScreen(driver);

		try {
			values = readSpreadsheet.readCompleteSpreadSheet("LoginScreenData");
		} catch (Exception e) {
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