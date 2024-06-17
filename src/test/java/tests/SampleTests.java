package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class SampleTests {
	@Test
	public void f() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\lenovo\\.cache\\selenium\\chromedriver\\win64\\116.0.5797.0\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("116.0.5797.0");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.lenovo.com");
	}
}
