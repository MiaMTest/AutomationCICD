package framedesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framedesign.abstrctcomponents.AbstractComponents;


public class CheckOutPage extends AbstractComponents {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	

	@FindBy(css = "input[placeholder*='Country']")
	private WebElement countryInput;
	
	@FindBy(css = ".list-group-item span")
	private List<WebElement> countriesResults;
	
	@FindBy(css = ".actions a")
	private WebElement placeOrderBtn;
	



	public void selectCountry(String CountryName) {

		countryInput.sendKeys(CountryName);

		WebElement countrySearched = countriesResults.stream()
				.filter(country -> country.getText().equalsIgnoreCase(CountryName)).findFirst().orElse(null);
		countrySearched.click();
	}

	public OrderConfirmationPage submitOrder() {
		placeOrderBtn.click();
		return new OrderConfirmationPage(driver);
		
	}
	

}
