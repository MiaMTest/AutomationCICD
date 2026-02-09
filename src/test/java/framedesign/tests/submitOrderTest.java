package framedesign.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framedesign.pageobjects.CartPage;
import framedesign.pageobjects.CheckOutPage;
import framedesign.pageobjects.OrderConfirmationPage;
import framedesign.pageobjects.ProductCatalogue;
import framedesign.pageobjects.OrdersPage;
import framedesign.testcomponenents.BaseTest;

public class submitOrderTest extends BaseTest {
	public OrderConfirmationPage orderConfirmPage;
	public String orderNumber;

	@Test(dataProvider = "getData", groups = { "Purchase", "Smoke" })
	public void submitOrder(HashMap<String, String> input) throws IOException { // receive parameter HashMap input
		// String productName = "ZARA COAT 3";
		String CountryName = "India";

		// catch langingPage object returned
		ProductCatalogue productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));// retrieve
																											// HashMap
																											// value
		// ProductCatalogue productCatalogue = landingPage.loginApp(email, password);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		// catch the object returned from goToCartPage() method
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyItemInCart(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goCheckOutPage();

		checkoutPage.selectCountry(CountryName);
		// submit order direct to confirmation page
		OrderConfirmationPage orderConfirmPage = checkoutPage.submitOrder();

		Assert.assertTrue(orderConfirmPage.getOrderMsg().equalsIgnoreCase("Thankyou for the order."));
		//String orderNumber = orderConfirmPage.getOrderNb();

	}

	@Test(dependsOnMethods = "submitOrder")
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApp("mia@hotmail.com", "Mia12345!");
		OrdersPage ordersPage = landingPage.goToOrderPage();
		Assert.assertTrue(ordersPage.getOrderID(orderNumber));

	}

	@Test(enabled = false)
	public void deleteOrderTest() throws InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApp("mia@hotmail.com", "Mia12345!");
		OrdersPage ordersPage = landingPage.goToOrderPage();
		ordersPage.deleteOrder();
		Assert.assertEquals(ordersPage.verifyOrderDeleted(), "Orders Deleted Successfully");

	}
	


	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = convertJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\framedesign\\data\\purchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	// create HashMap object with data type argument Object (could instore any value
	// type)
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "Amail@example.com");
//		map.put("password", "1mail@example.COM");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "mia@hotmail.com");
//		map1.put("password", "Mia12345!");
//		map1.put("productName", "ADIDAS ORIGINAL");

	// return new Object[][] {{map},{map1}};

//	@DataProvider
//	public Object[][] getData(){
//		
//		//create a 2 dimensional arrays, ex with 2 data sets {}
//		return new Object[][] {{"Amail@example.com","1mail@example.COM","ZARA COAT 3"},{"mia@hotmail.com", "Mia12345!","ADIDAS ORIGINAL"}};
//	}

}
