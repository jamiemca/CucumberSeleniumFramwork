package helpers;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	private static int MAX_TIMEOUT = 120;
	private static int SCROLL_OFFSET = 300;
	
	public BasePage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
//		wait = new WebDriverWait(driver, MAX_TIMEOUT);
		wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIMEOUT));
	}
	
	protected void waitForElementToAppear(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void scrollTo(long position) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(String.format("window.scrollTo(0, %s);", position));
	}
	
	public void scrollToElement(WebElement element) {
		int position = Integer.valueOf(element.getLocation().getY());
		position = position > SCROLL_OFFSET ? position - SCROLL_OFFSET : 0;
		scrollTo(position);
	}
	
	@SuppressWarnings("deprecation")
	public static boolean isElementPresent(By by, WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			driver.findElement(by);
			return true;
			
		} catch(NoSuchElementException e) {
			return false;
			
		} finally {
			driver.manage().timeouts().implicitlyWait(MAX_TIMEOUT, TimeUnit.SECONDS);
		}
	}

}
