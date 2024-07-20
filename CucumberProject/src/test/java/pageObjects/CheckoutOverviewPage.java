package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.BasePage;

public class CheckoutOverviewPage extends BasePage {
	
    private static final String PAGE_BODY_XPATH = "//div[@class='page_wrapper']";
	
	private static final String CART_LIST_XPATH = PAGE_BODY_XPATH + "//div[@class='cart_list']";
	private static final String SUMMARY_INFO_XPATH = PAGE_BODY_XPATH + "//div[@class='summary_info']";
	
	private static final String ITEM_SUBTOTAL_XPATH = SUMMARY_INFO_XPATH + "//div[@class='summary_subtotal_label']";
	private static final String CANCEL_BUTTON_XPATH = SUMMARY_INFO_XPATH + "//button[@id='cancel']";
	private static final String FINISH_BUTTON_XPATH = SUMMARY_INFO_XPATH + "//button[@id='finish']";
	
	
	@FindBy(xpath = CANCEL_BUTTON_XPATH)
	WebElement cancelButton;
	
	@FindBy(xpath = FINISH_BUTTON_XPATH)
	WebElement finishButton;
	
	@FindBy(xpath = ITEM_SUBTOTAL_XPATH)
	WebElement itemSubTotal;
	

	public CheckoutOverviewPage(WebDriver driver) {
		super(driver);
	}
	
	
	public void clickCancelButton() {
		waitForElementToAppear(By.xpath(CANCEL_BUTTON_XPATH));
		cancelButton.click();
	}
	
	public CheckoutCompletePage clickFinishButton() {
		waitForElementToAppear(By.xpath(FINISH_BUTTON_XPATH));
		scrollToElement(finishButton);
		finishButton.click();
		return PageFactory.initElements(driver, CheckoutCompletePage.class);
	}
	
	public String getItemSubtotal() {
		waitForElementToAppear(By.xpath(ITEM_SUBTOTAL_XPATH));
		return itemSubTotal.getText();
	}

}
