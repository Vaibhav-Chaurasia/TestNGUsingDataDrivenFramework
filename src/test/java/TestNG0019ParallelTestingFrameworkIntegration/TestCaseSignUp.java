package TestNG0019ParallelTestingFrameworkIntegration;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCaseSignUp extends BaseClass{
	
	//To run this you can use ParallelTestingAtClassLevel.xml or ParallelTestingAtSuiteLevel.xml or ParallelTestingAtClassLevelWithDifferentBrowser.xml

	protected ScreenRegistrationScreen resistrationScreen;
	protected ScreenLoginScreen loginScreen;
	SoftAssert softAssertion;
	
	@BeforeTest
	public void setUp() {
		resistrationScreen = new ScreenRegistrationScreen(driver);
		loginScreen = new ScreenLoginScreen(driver);
	}

	@Test (priority = 1)
	public void verifyValidationMessageOnBlankFields(){
		softAssertion = new SoftAssert();
		
		loginScreen.clickSignUpLink();

		//Click signup button
		resistrationScreen.clickSignUpButton();
		
		boolean isIAgreeCheckBoxValidationMessageDisplayed = resistrationScreen.isCheckboxValidationMessageDisplayed();
		System.out.println("Is I agree check box message diplayed - " + isIAgreeCheckBoxValidationMessageDisplayed);

		//Asserting true or false
		Assert.assertTrue(isIAgreeCheckBoxValidationMessageDisplayed);

		softAssertion.assertAll();
	}

	@Test (priority = 2)
	public void isIAgreeValidationMessageDisplayed() {
		softAssertion = new SoftAssert();

		//When you run this from "FrameworkImplementationAsWhole.xml" comment "loginScreen.clickSignUpLink();"
		//When you run this from "FrameworkImplementationAsIndividualTestCases.xml" uncomment "loginScreen.clickSignUpLink();"
		loginScreen.clickSignUpLink();
		
		resistrationScreen.clickCheckBox();

		boolean isIAgreeCheckBoxValidationMessageDisplayed = resistrationScreen.isCheckboxValidationMessageDisplayed();
		System.out.println("Is I agree check box message diplayed - " + isIAgreeCheckBoxValidationMessageDisplayed);

		//Asserting true or false
		Assert.assertTrue(isIAgreeCheckBoxValidationMessageDisplayed);

		softAssertion.assertAll();
	}
}