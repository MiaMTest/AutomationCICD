package framedesign.testcomponenents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import framedesign.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	// initialize driver
	public WebDriver driver;
	// initialize public variable to allow access for child class
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		// create instance for properties class
		Properties prop = new Properties();
		// use FileInputStream class to convert .properties file to inputStream
		// hard coded path:
		// E:\JavaProject\QAAutomationRahulShetty\seleniumframeworkdesign\src\main\java\framedesign\ressources\GlobalData.properties
		// generate path dynamically: system.getProperty("user.dir") to get project path

		// FileInputStream fis=new
		// FileInputStream("E://JavaProject//QAAutomationRahulShetty//seleniumframeworkdesign//src//main//java");

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//framedesign//ressources//GlobalData.properties");
		prop.load(fis);
		// ternary operator syntax, variable = (condition)?expressTrue:expressionFalse
		//check if having browser value sent from command,if yes->run as it ; if no->take from .properties file;
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// get browser's value on .properties file


		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080", "--no-sandbox");
//			options.addArguments("--headless=new");
//			options.addArguments("--window-size=1440,900");//full screen

			}
			driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> convertJsonDataToMap(String filePath) throws IOException {
		// read Json to String by using FileUtils class
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to Map, import Jackson Databind Library dependency
		// using ObjectMapper class for converting between Java Objects and JSON data

		ObjectMapper mapper = new ObjectMapper();
		// readValue from String content to HashMap with TypeReference <...>(){}
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;// {{map},{map1}}

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		// give life to driver which is handled in Listeners
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File targetFile = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, targetFile);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	// alwaysRun=true, test run anyway with or without groups annotation
	@BeforeMethod(alwaysRun = true)
	public LandingPage lanchApplication() throws IOException {
		// call the initialized driver
		driver = initializeDriver();
		// give variable life by creating object
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
