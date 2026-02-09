package framedesign.testcomponenents;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import framedesign.ressources.ExtentReporterNG;

//implements: a class adopts the contracts defined by an interface
public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTestMap = new ThreadLocal<>();// makes thread safe
	HashMap<String, ExtentTest> testMap = new HashMap<>();

	@Override
	public void onTestStart(ITestResult result) {
		// using variable result to grab test method name
		String methodName = result.getMethod().getMethodName();
		ExtentTest existing = testMap.get(methodName);
		// check if the ExtentTest for the current thread/test already exists
		if (existing == null) {
			test = extent.createTest(methodName);// create report entry for test
			extentTestMap.set(test);// push the test entry in ThreadLocal, give unique thread
									// ID(ErrorValidationTest)->Test
		} else {
			extentTestMap.set(existing);
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTestMap.get().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

			extentTestMap.get().fail(result.getThrowable());// get() to extract
 

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extentTestMap.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Not commonly used, handle as needed
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// Not commonly used, handle as needed
	}

	@Override
	public void onStart(ITestContext context) {
		// Not commonly used, handle as needed
	}

	@Override
	public void onFinish(ITestContext context) {
		// flush the report at the end of execution
		extent.flush();
	}

}
