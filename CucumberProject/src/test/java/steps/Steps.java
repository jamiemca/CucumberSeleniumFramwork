package steps;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AllProductsPage;
import pageObjects.CheckoutCompletePage;
import pageObjects.CheckoutInformationPage;
import pageObjects.CheckoutOverviewPage;
import pageObjects.LoginPage;
import pageObjects.ShoppingCartPage;

public class Steps {
	
	protected WebDriver driver;
	protected Faker faker;
	protected LoginPage loginPage;
	protected AllProductsPage allProductsPage;
	protected ShoppingCartPage shoppingCartPage;
	protected CheckoutInformationPage checkoutInformationPage;
	protected CheckoutOverviewPage checkoutOverviewPage;
	protected CheckoutCompletePage checkoutCompletePage;
	protected String firstName;
	protected String lastName;
	protected String zipPostalCode;
	protected String itemPrice;
	protected String url;
	protected String username;
	protected String password;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") 
				+ "\\src\\test\\java\\resources\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		this.driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
	}
	
	@After
	public void teardown() {
		this.driver.manage().deleteAllCookies();
		this.driver.quit();
		this.driver = null;
	}
	
	@Given("^the user has navigated to the website$")
	public void userHasNaviagtedToWebsite() {
		this.url = "https://www.saucedemo.com/";
		driver.get(this.url);
	}
	
	@Given("^the user has logged into the application$")
	public void userHasLoggedIntoApplication() {
		this.url = "https://www.saucedemo.com/";
		this.username = "standard_user";
		this.password = "secret_sauce";
		
		driver.get(this.url);
		
		loginPage = new LoginPage(driver);
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
	}
	
	@When("^a \"([^\"]*)\" is added to my basket$")
	public void addItemToBasket(String item) {
		allProductsPage = new AllProductsPage(driver);
		allProductsPage.addItemToCart(item);
	}
	
	@When("^incorrect login details are entered$")
	public void incorrectLoginDetailsEntered() {
		String invalidUsername = "abcdef";
		String invalidPassword = "123456";
		
		loginPage = new LoginPage(driver);
		loginPage.enterUserName(invalidUsername);
		loginPage.enterPassword(invalidPassword);
		loginPage.clickLoginButton();
		
	}
	
	@When("^the shopping cart is viewed$")
	public void cartIsViewed() {
		allProductsPage = new AllProductsPage(driver);
		shoppingCartPage = allProductsPage.viewCart();
	}
	
	@When("^the item price is stored$")
	public void itemPriceIsStored() {
		this.itemPrice = shoppingCartPage.getItemPrice();
	}
	
	@When("^the checkout information is completed$")
	public void checkoutInformationCompleted() {
		checkoutInformationPage = shoppingCartPage.clickCheckoutButton();
		
		faker = new Faker();
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		zipPostalCode = faker.address().zipCode();
		
		checkoutInformationPage.enterFirstName(firstName);
		checkoutInformationPage.enterLastName(lastName);
		checkoutInformationPage.enterZipPostalCode(zipPostalCode);
		checkoutOverviewPage = checkoutInformationPage.clickContinueButton();
	}
	
	@When("^the item is purchased$")
	public void itemIsPurchased() {
		checkoutInformationPage = shoppingCartPage.clickCheckoutButton();
		
		faker = new Faker();
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		zipPostalCode = faker.address().zipCode();
		
		checkoutInformationPage.enterFirstName(firstName);
		checkoutInformationPage.enterLastName(lastName);
		checkoutInformationPage.enterZipPostalCode(zipPostalCode);
		checkoutOverviewPage = checkoutInformationPage.clickContinueButton();
		checkoutCompletePage = checkoutOverviewPage.clickFinishButton();
	}
	
	@Then("^the correct item price is displayed$")
	public void itemPriceIsCorrect() {
		String itemSubtotal = checkoutOverviewPage.getItemSubtotal();
		itemSubtotal = itemSubtotal.substring(12, itemSubtotal.length());
		assertEquals("Expected message: " + this.itemPrice + " but actual message was: " + itemSubtotal,
				 this.itemPrice, itemSubtotal);
	}
	
	@Then("^the \"([^\"]*)\" message is displayed$")
	public void confirmationMessageIsDisplayed(String expectedMessage) {
		
		if (expectedMessage.contains("Username and password")) {
			
			String errorMessage = loginPage.getLoginFailedErrorMessage();
			errorMessage = errorMessage.substring(14, errorMessage.length());
			assertEquals("Expected error: " + expectedMessage + " but actual error was: " + errorMessage,
					expectedMessage, errorMessage);
			
		} else {
			
			String actualMessage = checkoutCompletePage.getOrderCompleteMessage();
			assertEquals("Expected message: " + expectedMessage + " but actual message was: " + actualMessage,
					expectedMessage, actualMessage);
		}
		

	}
	
	@Then("^the user logs out$")
	public void userLogsOut() {
		
		if (checkoutCompletePage == null) {
			checkoutCompletePage = checkoutOverviewPage.clickFinishButton();
		}
		
		allProductsPage = checkoutCompletePage.clickBackHomeButton();
		allProductsPage.clickMenuButton();
		
	}

}
