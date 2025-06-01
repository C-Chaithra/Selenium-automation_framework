package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class baseTest {
protected WebDriver driver;
	
	@BeforeTest
	public void setUp() {

		//Log.info("Starting WebDriver...");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Log.info("Navigating to URL...");
		 driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		driver.get("https://admin-demo.nopcommerce.com/login");
	}
	
	@AfterTest
	public void tearDown() {
		if (driver != null) {
			//Log.info("Closing Browser...");
			driver.quit();
		}
	}
	
}
