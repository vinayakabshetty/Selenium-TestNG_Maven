package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class GoogleVerification {
  @Test
  public void f() throws InterruptedException {
	  
	  WebDriver driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://www.google.com");
	  driver.findElement(By.name("q")).sendKeys("gta vice city");
	  driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	  Thread.sleep(5000);
	  driver.quit();
  }
}
