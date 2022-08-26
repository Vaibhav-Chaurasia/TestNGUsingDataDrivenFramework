package TestNG0018FrameworkIntegration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.ElementUtils;

public class ScreenRegistrationScreen {

	protected final WebDriver driver;

	//Finding xpaths of an elements
	@FindBy(xpath = "//div[@data-testid='errors-i-agree']")
	WebElement checkBoxValidationMessage;
	
	@FindBy(xpath = "//button[@data-testid='signup-button']")
	WebElement signUpButton;
	
	@FindBy(className = "customcheckbox")
	WebElement checkBox;

	

	public ScreenRegistrationScreen(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Creating methods for specific operations	
	public void clickSignUpButton() {
		ElementUtils.sleep();
		ElementUtils.scrollPageToBottom(driver);
		ElementUtils.sleep();
		ElementUtils.clickON(signUpButton, driver, 30);
	}
	
	public boolean isCheckboxValidationMessageDisplayed() {
		ElementUtils.sleep();
		return ElementUtils.isElementDisplayed(checkBoxValidationMessage);
	}
	
	public void clickCheckBox() {
		ElementUtils.sleep();
		ElementUtils.clickON(checkBox, driver, 30);
	}
}