package framedesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framedesign.abstrctcomponents.AbstractComponents;

public class OrderConfirmationPage extends AbstractComponents {
	
	WebDriver driver;
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);

	}
	
	@FindBy(css = ".hero-primary")
	private WebElement orderMsg;
	
	@FindBy(css = "tr[class*='star-inserted'] label")
	private WebElement orderNb;
	
		
	public String getOrderMsg() {
		return orderMsg.getText();
	}
	
	public String getOrderNb() {
		return orderNb.getText();
	}


}
