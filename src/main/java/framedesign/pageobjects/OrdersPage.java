package framedesign.pageobjects;


import java.util.List;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framedesign.abstrctcomponents.AbstractComponents;

public class OrdersPage extends AbstractComponents{
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}


	@FindBy(css=".btn-danger")
	private WebElement deleteBtn;
	
	@FindBy(css="tbody th")
	private List<WebElement> orderNbs;
	
	@FindBy(css="[class*='toast-success']")
	private WebElement deleteSucessMsg;
	
	
	
	public void deleteOrder() {
		deleteBtn.click();

	}
	
	public boolean getOrderID(String orderNumber) {
			
		boolean match =  orderNbs.stream().anyMatch(orderNb->orderNb.getText().equalsIgnoreCase(orderNumber.replace("|", "").trim()));
		return match;

	}
	
	public String verifyOrderDeleted() throws InterruptedException {
		waitForWebElementToAppear(deleteSucessMsg);
		Thread.sleep(10);
		return deleteSucessMsg.getText();
	}
	


}
