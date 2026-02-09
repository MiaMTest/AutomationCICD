package framedesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framedesign.abstrctcomponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		//send driver to parent class
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css=".mb-3")
	private List<WebElement> products;
	
	@FindBy(css=".cardbody button:last-of-type")
	private WebElement addToCartBtn;
	
	@FindBy(css="[routerlink='/dashboard/cart']")
	private WebElement cart;
	
	
	
	private By productsLocator = By.cssSelector(".mb-3");
	private By addToCart = By.cssSelector("button:last-of-type");
	private By toastMsg = By.id("toast-container");
	private By SelectedQty = By.cssSelector("[routerlink*='cart'] label");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsLocator);
		return products;
	}
	
//	public WebElement getProductByName(String productName) {
//		WebElement selectedProduct = products.stream()
//				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
//				.findFirst().orElse(null);
//		return selectedProduct;
//	}
	
	public void addProductToCart(String productName) {
		//WebElement selectedProduct = getProductByName(productName);
		WebElement selectedProduct = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		selectedProduct.findElement(addToCart).click();
		waitForElementToAppear(toastMsg);
		waitForTextToBePresent(SelectedQty, "1");
	
						
	}
	
}
