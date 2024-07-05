package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AWS_Tests {
	@Test
	public void f() throws MalformedURLException {

		@SuppressWarnings("deprecation")
		URL url = new URL("http://65.2.131.63:4444/wd/hub");

		WebDriverManager.chromedriver().setup();
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("chrome");
		
		WebDriver driver = new RemoteWebDriver(url, cap);
		driver.get("https://www.google.com");
		driver.quit();

	}
}
