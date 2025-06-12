package base;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
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
	// Thread-safe WebDriver using ThreadLocal
	protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	// protected static WebDriver driver ;
	protected static ExtentReports extent;
	protected ExtentTest test;
	//protected ITestContext context;

	@BeforeSuite
	public void setupReport() {
		extent = ExtentReportManager.getReportInstance();
	}

	@AfterSuite
	public void teardownReport() {
		//ExtentReportManager.flushReport();
		extent.flush();
		// String reportPath = ExtentReportManager.reportPath;
		// EmailUtils.sendTestReport(reportPath);
	}

	@BeforeMethod
	public void setUp(ITestContext context) {
		WebDriver webDriver;

		//this.context = context;
		Log.info("Starting WebDriver...");
		webDriver = new ChromeDriver();
		driver.set(webDriver);
		driver.get().manage().window().maximize();

		Log.info("Navigating to URL...");
		driver.get().manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		driver.get().get("https://admin-demo.nopcommerce.com/login");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date());
			String screenshotName = "LoginFailure_" + timestamp + ".html";
			String screenshotPath = ExtentReportManager.captureScreenshot(driver.get(), screenshotName);
			test.fail("Test Failed.. Check Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		if (driver.get() != null) {
			Log.info("Closing Browser...");
			driver.get().quit();
		}
	}

}
