package test;

import org.testng.annotations.Test;

import base.baseTest;
import pages.LoginPage;

public class LoginTest extends baseTest {
	
	@Test
	public void testValidLogin() {
	
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
		loginPage.clickLogin();
		System.out.println("Title of the page is : " + driver.getTitle());
	}
	
}
