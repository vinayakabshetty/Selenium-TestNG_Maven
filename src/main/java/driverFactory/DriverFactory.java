package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public static ThreadLocal<WebDriver> tl_driver = new ThreadLocal<WebDriver>();

	public static WebDriver init_driver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tl_driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tl_driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			tl_driver.set(new SafariDriver());
		} else {
			System.out.println("Please enter valid browser");
		}

		getDriver().manage().window().maximize();
		return getDriver();
	}

	public static WebDriver getDriver() {
		return tl_driver.get();
	}
}
