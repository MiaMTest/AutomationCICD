package framedesign.abstrctcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framedesign.pageobjects.CartPage;
import framedesign.pageobjects.OrdersPage;

public class AbstractComponents {
	WebDriver driver;
	//catch driver from child class
	public AbstractComponents(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}

	
	@FindBy(css="[routerlink='/dashboard/']")
	WebElement homeHeader;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	@FindBy(css="[routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForTextToBePresent(By findBy, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(findBy, text));
	
	}
	
	public CartPage goToCartPage() {
	    cartHeader.click();
	    //when a method directs user to a new Page
	    //return the page object with driver (instead of creating new object in test file)
	    return new CartPage(driver);
	}
	
	public OrdersPage goToOrderPage() {
		orderHeader.click();
		return new OrdersPage(driver);
	}
	


}
