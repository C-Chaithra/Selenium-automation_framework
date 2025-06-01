package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

        driver.get("https://admin-demo.nopcommerce.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        System.out.println("Title is:"+driver.getTitle());
        driver.findElement(By.id("Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.id("Password")).sendKeys("admin");
        WebElement loginbutton = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button"));
        	loginbutton.click();
        	System.out.println(loginbutton.getText());
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
		driver.quit();
	}
}
