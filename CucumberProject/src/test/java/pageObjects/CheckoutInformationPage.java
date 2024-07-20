package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.BasePage;

public class CheckoutInformationPage extends BasePage {
	
    private static final String PAGE_BODY_XPATH = "//div[@class='page_wrapper']";
	
	private static final String CHECKOUT_INFO_XPATH = PAGE_BODY_XPATH + "//div[@class='checkout_info']";
	private static final String CHECKOUT_BUTTONS_XPATH = PAGE_BODY_XPATH + "//div[@class='checkout_buttons']";
	
	private static final String FIRST_NAME_FIELD_XPATH = CHECKOUT_INFO_XPATH + "//input[@id='first-name']";
	private static final String LAST_NAME_FIELD_XPATH = CHECKOUT_INFO_XPATH + "//input[@id='last-name']";
	private static final String ZIP_POSTAL_CODE_FIELD_XPATH = CHECKOUT_INFO_XPATH + "//input[@id='postal-code']";
	
	private static final String CANCEL_BUTTON_XPATH = CHECKOUT_BUTTONS_XPATH + "//button[@id='cancel']";
	private static final String CONTINUE_BUTTON_XPATH = CHECKOUT_BUTTONS_XPATH + "//input[@id='continue']";
	
	
	@FindBy(xpath = FIRST_NAME_FIELD_XPATH)
	WebElement firstNameField;
	
	@FindBy(xpath = LAST_NAME_FIELD_XPATH)
	WebElement lastNameField;
	
	@FindBy(xpath = ZIP_POSTAL_CODE_FIELD_XPATH)
	WebElement zipPostalCodeField;
	
	@FindBy(xpath = CANCEL_BUTTON_XPATH)
	WebElement cancelButton;
	
	@FindBy(xpath = CONTINUE_BUTTON_XPATH)
	WebElement continueButton;
	

	public CheckoutInformationPage(WebDriver driver) {
		super(driver);
	}
	
	
	public void enterFirstName(String firstName) {
		waitForElementToAppear(By.xpath(FIRST_NAME_FIELD_XPATH));
		firstNameField.clear();
		firstNameField.sendKeys(firstName);
	}
	
	public void enterLastName(String secondName) {
		waitForElementToAppear(By.xpath(LAST_NAME_FIELD_XPATH));
		lastNameField.clear();
		lastNameField.sendKeys(secondName);
	}
	
	public void enterZipPostalCode(String zipPostalCode) {
		waitForElementToAppear(By.xpath(ZIP_POSTAL_CODE_FIELD_XPATH));
		zipPostalCodeField.clear();
		zipPostalCodeField.sendKeys(zipPostalCode);
	}
	
	public void clickCancelButton() {
		waitForElementToAppear(By.xpath(CANCEL_BUTTON_XPATH));
		cancelButton.click();
	}
	
	public CheckoutOverviewPage clickContinueButton() {
		waitForElementToAppear(By.xpath(CONTINUE_BUTTON_XPATH));
		continueButton.click();
		return PageFactory.initElements(driver, CheckoutOverviewPage.class);
	}

}
