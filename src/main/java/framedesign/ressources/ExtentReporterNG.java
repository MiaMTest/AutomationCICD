package framedesign.ressources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	//define method static, to make it accessible directly with class name
	public static ExtentReports getReportObject() {
		String path= System.getProperty("user.dir")+"\\Reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test results");
		reporter.config().setReportName("Web Automation Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("tester", "Mia M");
		return extent;
		
		
	}

}
