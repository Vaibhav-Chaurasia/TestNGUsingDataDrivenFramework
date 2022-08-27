package TestNG0019ParallelTestingFrameworkIntegration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.ElementUtils;

public class ScreenLoginScreen {

	protected final WebDriver driver;

	//Finding xpaths of an elements
	@FindBy(id = "email")
	WebElement emailField;

	@FindBy(name = "password")
	WebElement passwordField;

	@FindBy(xpath = "//button[@id='login-button']")
	WebElement loginButton;

	@FindBy(xpath = "//p[@data-testid='errors-email']")
	WebElement errorEmailValidation;
	
	@FindBy(xpath = "//p[contains(text(),'Invalid email address')]")
	WebElement incorrectFormatMessage;
	
	@FindBy(xpath = "//a[contains(text(),'Sign up')]")
	WebElement clickSignupLink;
	
	public ScreenLoginScreen(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Creating methods for specific operations
	public void enterInvalidEmail(String Email) {
		ElementUtils.sleep();
		ElementUtils.enterText(emailField, Email);
	}

	public void enterInvalidPassword(String Password) {
		ElementUtils.sleep();
		ElementUtils.enterText(passwordField, Password);
	}

	public void clickLoginButton() {
		ElementUtils.sleep();
		ElementUtils.clickON(loginButton, driver, 30);
	}

	public void enterIncorrectFormatEmail(String Email) {
		ElementUtils.sleep();
		ElementUtils.enterText(emailField, Email);
	}

	public void enterIncorrectFormatPassword(String Password) {
		ElementUtils.sleep();
		ElementUtils.enterText(passwordField, Password);
	}

	public String getValidationMessageForInvalidCredentials() {
		ElementUtils.sleep();
		ElementUtils.sleep();
		return ElementUtils.getText(errorEmailValidation);
	}

	public String getValidationMessageForIncorrectFormat() {
		ElementUtils.sleep();
		return ElementUtils.getText(errorEmailValidation);
	}
	
	public void clickSignUpLink() {
		ElementUtils.sleep();
		ElementUtils.clickON(clickSignupLink, driver, 30);
	}
}