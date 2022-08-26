package TestNG0018FrameworkIntegration;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCaseSignUp extends BaseClass{

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

		//Enter Email, password and click on login button.
		resistrationScreen.clickSignUpButton();
		
		boolean isIAgreeCheckBoxValidationMessageDisplayed = resistrationScreen.isCheckboxValidationMessageDisplayed();
		System.out.println("Is I agree check box message diplayed - " + isIAgreeCheckBoxValidationMessageDisplayed);

		//Asserting true or false
		Assert.assertTrue(isIAgreeCheckBoxValidationMessageDisplayed);

		softAssertion.assertAll();
	}

	@Test (priority = 2)
	public void enterIncorrectFormat() {
		softAssertion = new SoftAssert();

		resistrationScreen.clickCheckBox();

		boolean isIAgreeCheckBoxValidationMessageDisplayed = resistrationScreen.isCheckboxValidationMessageDisplayed();
		System.out.println("Is I agree check box message diplayed - " + isIAgreeCheckBoxValidationMessageDisplayed);

		//Asserting true or false
		Assert.assertTrue(isIAgreeCheckBoxValidationMessageDisplayed);

		softAssertion.assertAll();
	}
}