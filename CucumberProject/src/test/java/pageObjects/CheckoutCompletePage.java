package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.BasePage;

public class CheckoutCompletePage extends BasePage {
	
    private static final String PAGE_BODY_XPATH = "//div[@class='page_wrapper']";
	
	private static final String ORDER_COMPLETE_LABEL_XPATH = PAGE_BODY_XPATH + "//h2[@class='complete-header']";
	private static final String BACK_HOME_BUTTON_XPATH = PAGE_BODY_XPATH + "//button[@id='back-to-products']";
	
	
	@FindBy(xpath = ORDER_COMPLETE_LABEL_XPATH)
	WebElement orderCompleteLabel;
	
	@FindBy(xpath = BACK_HOME_BUTTON_XPATH)
	WebElement backHomeButton;
	

	public CheckoutCompletePage(WebDriver driver) {
		super(driver);
	}
	
	
	public String getOrderCompleteMessage() {
		waitForElementToAppear(By.xpath(ORDER_COMPLETE_LABEL_XPATH));
		return orderCompleteLabel.getText();
	}
	
	public AllProductsPage clickBackHomeButton() {
		waitForElementToAppear(By.xpath(BACK_HOME_BUTTON_XPATH));
		backHomeButton.click();
		return PageFactory.initElements(driver, AllProductsPage.class);
	}

}
