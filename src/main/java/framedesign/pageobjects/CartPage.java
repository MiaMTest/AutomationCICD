package framedesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framedesign.abstrctcomponents.AbstractComponents;


public class CartPage extends AbstractComponents{
	WebDriver driver;

	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	
	@FindBy(css=".items")
	private List<WebElement> cartItems;
	
	@FindBy(css = ".totalRow button")
	private WebElement checkOutBtn;
	
	private By itemDescript = By.cssSelector("h3");
	
	

	
	public boolean verifyItemInCart(String productName) {
		boolean match = cartItems.stream().anyMatch(cartItem->cartItem.findElement(itemDescript).getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goCheckOutPage() {

		checkOutBtn.click();
		return new CheckOutPage(driver);

	}
	
	
	
	
	
}


