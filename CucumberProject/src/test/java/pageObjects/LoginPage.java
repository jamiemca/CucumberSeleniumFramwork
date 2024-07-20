package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helpers.BasePage;

public class LoginPage extends BasePage {
	
    private static final String PAGE_BODY_XPATH = "//div[@class='login_container']";
	
	private static final String USERNAME_TEXTBOX_XPATH = PAGE_BODY_XPATH + "//input[@id='user-name']";
	private static final String PASSWORD_TEXTBOX_XPATH = PAGE_BODY_XPATH + "//input[@id='password']";
	private static final String LOGIN_BUTTON_XPATH = PAGE_BODY_XPATH + "//input[@id='login-button']";
	
	private static final String ERROR_MESSAGE_XPATH = PAGE_BODY_XPATH + "//h3[@data-test='error']";
	
	@FindBy (xpath = USERNAME_TEXTBOX_XPATH)
	WebElement usernameField;
	
	@FindBy (xpath = PASSWORD_TEXTBOX_XPATH)
	WebElement passwordField;
	
	@FindBy (xpath = LOGIN_BUTTON_XPATH)
	WebElement loginButton;
	
	@FindBy (xpath = ERROR_MESSAGE_XPATH)
	WebElement errorMessage;
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(String username) {
		waitForElementToAppear(By.xpath(USERNAME_TEXTBOX_XPATH));
		usernameField.clear();
		usernameField.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		waitForElementToAppear(By.xpath(PASSWORD_TEXTBOX_XPATH));
		passwordField.clear();
		passwordField.sendKeys(password);
	}
	
	public void clickLoginButton() {
		waitForElementToAppear(By.xpath(LOGIN_BUTTON_XPATH));
		loginButton.click();
	}
	
	public String getLoginFailedErrorMessage() {
		waitForElementToAppear(By.xpath(ERROR_MESSAGE_XPATH));
		return errorMessage.getText();
	}

}
