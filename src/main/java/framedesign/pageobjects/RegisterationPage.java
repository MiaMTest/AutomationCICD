package framedesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import framedesign.abstrctcomponents.AbstractComponents;

public class RegisterationPage extends AbstractComponents {
    WebDriver driver;

    public RegisterationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    private WebElement userFirstName;

    @FindBy(id = "lastName")
    private WebElement userLastName;

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "userPassword")
    private WebElement userPassword;

    @FindBy(id = "confirmPassword")
    private WebElement userConfirmPassword;

    @FindBy(id = "userMobile")
    private WebElement userMobile;

    @FindBy(css = "select[formcontrolname='occupation']")
    private WebElement userOccupation;

    @FindBy(css = "input[value='Male']")
    private WebElement maleRadio;

    @FindBy(css = "input[value='Female']")
    private WebElement femaleRadio;

    @FindBy(css = "input[formcontrolname='required']")
    private WebElement ageCheckbox;

    @FindBy(id = "login")
    private WebElement registerBtn;

    @FindBy(xpath = "//h1[text()='Account Created Successfully']")
    private WebElement successMsg;

    public void completeRegistration(String firstName, String lastName, String email,
                                     String phone, String occupation, String gender,
                                     String password, String confirmPassword) throws InterruptedException {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPhone(phone);
        selectOccupation(occupation);
        selectGender(gender);
        enterPassword(password);
        confirmPassword(confirmPassword);
        checkAgeCheckbox();
        submitRegistration();
        waitForWebElementToAppear(successMsg);
    }

    public void enterFirstName(String firstName) {
        userFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        userLastName.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        userEmail.sendKeys(email);
    }

    public void enterPhone(String phone) {
        userMobile.sendKeys(phone);
    }

    public void enterPassword(String password) {
        userPassword.sendKeys(password);
    }

    public void confirmPassword(String confirmPassword) {
        userConfirmPassword.sendKeys(confirmPassword);
    }

    public void selectOccupation(String occupation) {
        Select select = new Select(userOccupation);
        select.selectByVisibleText(occupation);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            maleRadio.click();
        } else if (gender.equalsIgnoreCase("Female")) {
            femaleRadio.click();
        }
    }

    public void checkAgeCheckbox() {
        if (!ageCheckbox.isSelected()) {
            ageCheckbox.click();
        }
    }

    public void submitRegistration() {
        registerBtn.click();
    }

    public String getSuccessMessage() throws InterruptedException {
        waitForWebElementToAppear(successMsg);
        return successMsg.getText();
    }
}
