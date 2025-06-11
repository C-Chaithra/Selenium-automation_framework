package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	private static ExtentTest test;
	public static String reportPath;

	public static ExtentReports getReportInstance() {

		if (extent == null) {

			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			reportPath = "reports/ExtentReport_" + timestamp + ".html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

			reporter.config().setDocumentTitle("Selenium Automation Test Report");
			reporter.config().setReportName("Test Execution Report");

			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}

		return extent;
	}

	public static ExtentTest createTest(String testName, ITestContext context) {

		String testNgTestName = testName + " - " + context.getName();
		test = getReportInstance().createTest(testNgTestName);
		extentTest.set(test);
		return test;
	}

	public static ExtentTest getTest() {
		return extentTest.get();
	}

	public static void flushReport() {
		if (extent != null) {
			extent.flush();
		}
	}

	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		try {

			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
			System.out.println("Path for screenshot is : " + path);
			FileUtils.copyFile(src, new File(path));
			return path;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
