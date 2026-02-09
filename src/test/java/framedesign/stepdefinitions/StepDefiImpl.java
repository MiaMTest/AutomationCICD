package framedesign.stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import framedesign.pageobjects.CartPage;
import framedesign.pageobjects.CheckOutPage;
import framedesign.pageobjects.LandingPage;
import framedesign.pageobjects.OrderConfirmationPage;
import framedesign.pageobjects.ProductCatalogue;
import framedesign.testcomponenents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefiImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckOutPage checkoutPage;
	public OrderConfirmationPage orderConfirmPage;

	@Given("I landed on Ecommcerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = lanchApplication();

	}

	// (.+) regex, matches any sequence of one(.) or more(+) of any character
	@Given("^Logged in with username (.+) and password (.+)$") // regex of String,starting ^,ending $
	public void loggered_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApp(username, password);
	}

	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws InterruptedException{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);

	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_productName_and_submit_the_order(String productName) {
		cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyItemInCart(productName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goCheckOutPage();
		checkoutPage.selectCountry("India");
		orderConfirmPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void thankyou_message_is_displayed_on_ConfirmationPage(String string) {

		Assert.assertTrue(orderConfirmPage.getOrderMsg().equalsIgnoreCase(string));
		driver.close();
	}
	
	 
	  @Then("{string} message is displayed")
	  public void message_is_displayed(String string) {
			Assert.assertEquals(string,landingPage.getErrorMsg());
			driver.close();
	  }
}
