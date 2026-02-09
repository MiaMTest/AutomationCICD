package framedesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framedesign.abstrctcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	//declare a local object driver
	WebDriver driver;

	//assign life of local driver life TO driver in LandingPage object in test class 
	public LandingPage(WebDriver driver)  {
		super(driver);
		this.driver = driver;
	//using initiElements method in PageFactory class to initialize all Elements by passing driver argument
		PageFactory.initElements(driver, this);
		
	}

	// PageFactory class

	@FindBy(id = "userEmail")
	private WebElement userEmail;

	@FindBy(id = "userPassword")
	private WebElement userPassword;
	
	@FindBy(id="login")
	private WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	private WebElement loginError;
	
	@FindBy(xpath="//*[@id=\"userEmail\"]/parent::div/div")
	private WebElement invalidFeedbackEmail;
	
	@FindBy(xpath="//*[@id=\"userPassword\"]/parent::div/div")
	private WebElement invalidFeedbackPswd;
	
	
	
	public ProductCatalogue loginApp(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();
		return new ProductCatalogue(driver);

	}
	public String getErrorMsg() {
		waitForWebElementToAppear(loginError);
		return loginError.getText();
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getInvalidUserEmail() {
		return invalidFeedbackEmail.getText();
	}

	public String getInvalidPswd() {
		return invalidFeedbackPswd.getText();
	}
	
}
