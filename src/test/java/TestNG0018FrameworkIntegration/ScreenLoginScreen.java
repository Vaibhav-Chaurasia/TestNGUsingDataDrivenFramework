package TestNG0018FrameworkIntegration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactoryFinder;
import org.openqa.selenium.support.locators.RelativeLocator;

import Utils.ElementUtils;
import lombok.experimental.FieldDefaults;

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
	
	@FindBy(id = "userpassword")
	WebElement password;
	
	@FindBy(id = "email")
	WebElement email;
	
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
	
	public void enterPasswordTest(String text) {
		ElementUtils.sleep();
		By password = RelativeLocator.with(By.id("userpassword")).below(By.id("email"));
		ElementUtils.enterText(driver.findElement(password), text);
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