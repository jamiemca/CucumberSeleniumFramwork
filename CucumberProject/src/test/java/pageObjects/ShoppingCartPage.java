package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.BasePage;

public class ShoppingCartPage extends BasePage {
	
    private static final String PAGE_BODY_XPATH = "//div[@class='page_wrapper']";
	
	private static final String CART_LIST_XPATH = PAGE_BODY_XPATH + "//div[@class='cart_list']";
	private static final String CART_FOOTER_XPATH = PAGE_BODY_XPATH + "//div[@class='cart_footer']";
	
	private static final String ITEM_PRICE_XPATH = CART_LIST_XPATH + "//div[@class='inventory_item_price']";
	
	private static final String CHECKOUT_BUTTON_XPATH = CART_FOOTER_XPATH + "//button[@id='checkout']";
	
	
	@FindBy(xpath = CHECKOUT_BUTTON_XPATH)
	WebElement checkoutButton;
	
	@FindBy(xpath = ITEM_PRICE_XPATH)
	WebElement itemPriceLabel;
	

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}
	
	
	public CheckoutInformationPage clickCheckoutButton() {
		waitForElementToAppear(By.xpath(CHECKOUT_BUTTON_XPATH));
		checkoutButton.click();
		return PageFactory.initElements(driver, CheckoutInformationPage.class);
	}
	
	public String getItemPrice() {
		waitForElementToAppear(By.xpath(ITEM_PRICE_XPATH));
		return itemPriceLabel.getText();
	}

}
