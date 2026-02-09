package framedesign.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import framedesign.pageobjects.CartPage;
import framedesign.pageobjects.CheckOutPage;
import framedesign.pageobjects.LandingPage;
import framedesign.pageobjects.OrderConfirmationPage;
import framedesign.pageobjects.ProductCatalogue;
import framedesign.testcomponenents.BaseTest;
import framedesign.testcomponenents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginErrorValidationTest extends BaseTest {

	@Test(retryAnalyzer=Retry.class)
	public void invalidUserNameTest() throws IOException {
		landingPage.loginApp("mia@gmail.com", "Mia12345!");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMsg());
		
	}
	
	@Test(groups={"ErrorHandling"})
	public void invalidPasswordTest() throws IOException {
		landingPage.loginApp("mia@hotmail.com", "12345678");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMsg());
		
	}
	
	@Test
	public void emptyUserEmailPassword() {
		
		landingPage.loginApp("", "");
		Assert.assertEquals(landingPage.getInvalidUserEmail(), "*Email is required");
		Assert.assertEquals(landingPage.getInvalidPswd(), "*Password is required");
		
	}
}
