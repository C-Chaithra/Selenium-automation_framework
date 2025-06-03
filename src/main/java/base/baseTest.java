package base;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.ExtentReportManager;
import utils.Log;

public class baseTest {
	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;

	@BeforeSuite
	public void setupReport() {
		extent = ExtentReportManager.getReportInstance();
	}

	@AfterSuite
	public void teardownReport() {
		extent.flush();
		// String reportPath = ExtentReportManager.reportPath;
		// EmailUtils.sendTestReport(reportPath);
	}

	@BeforeMethod
	public void setUp() {

		Log.info("Starting WebDriver...");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Log.info("Navigating to URL...");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		driver.get("https://admin-demo.nopcommerce.com/login");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			String screenshotName = "LoginFailure_"+timestamp+".html";
		     
			String screenshotPath = ExtentReportManager.captureScreenshot(driver, screenshotName);
			test.fail("Test Failed.. Check Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		if (driver != null) {
			Log.info("Closing Browser...");
			driver.quit();
		}
	}
	

}
