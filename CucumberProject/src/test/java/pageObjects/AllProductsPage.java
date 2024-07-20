package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import helpers.BasePage;

public class AllProductsPage extends BasePage {
	
    private static final String PAGE_BODY_XPATH = "//div[@class='page_wrapper']";
	
	private static final String HEADER_CONTAINER_XPATH = PAGE_BODY_XPATH + "//div[@id='header_container']";
	private static final String INVENTORY_CONTAINER_XPATH = PAGE_BODY_XPATH + "//div[@id='inventory_container']";
	
	private static final String SHOPPING_CART_BUTTON_XPATH = HEADER_CONTAINER_XPATH + "//a[@class='shopping_cart_link']";
	private static final String HAMBURGER_MENU_BUTTON_XPATH = HEADER_CONTAINER_XPATH + "//button[@id='react-burger-menu-btn']";
	private static final String LOGOUT_BUTTON_XPATH = HEADER_CONTAINER_XPATH + "//a[@id='logout_sidebar_link']";
	
	private static final String PRODUCT_LABEL_XPATH = INVENTORY_CONTAINER_XPATH + "//div[contains(text(),'%s')]";
	private static final String ADD_TO_CART_BUTTON_XPATH = PRODUCT_LABEL_XPATH + "/ancestor::div[@class='inventory_item_description']//button";

	
	@FindBy(xpath = SHOPPING_CART_BUTTON_XPATH)
	WebElement shoppingCartButton;
	
	@FindBy(xpath = HAMBURGER_MENU_BUTTON_XPATH)
	WebElement hamburgerMenuButton;
	
	@FindBy(xpath = LOGOUT_BUTTON_XPATH)
	WebElement logoutButton;
	
	
	public AllProductsPage(WebDriver driver) {
		super(driver);
	}
	
	
	public void addItemToCart(String itemName) {
		WebElement item = driver.findElement(By.xpath(String.format(ADD_TO_CART_BUTTON_XPATH, itemName)));
		wait.until(ExpectedConditions.elementToBeClickable(item));
		item.click();
	}
	
	public ShoppingCartPage viewCart() {
		waitForElementToAppear(By.xpath(SHOPPING_CART_BUTTON_XPATH));
		shoppingCartButton.click();
		return PageFactory.initElements(driver, ShoppingCartPage.class);
	}
	
	public void clickMenuButton() {
		waitForElementToAppear(By.xpath(HAMBURGER_MENU_BUTTON_XPATH));
		hamburgerMenuButton.click();
	}
	
	public void clickLogoutButton() {
		waitForElementToAppear(By.xpath(LOGOUT_BUTTON_XPATH));
		logoutButton.click();
	}

}
