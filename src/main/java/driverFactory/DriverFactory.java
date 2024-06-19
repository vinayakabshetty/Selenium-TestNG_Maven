package driverFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public static ThreadLocal<WebDriver> tl_driver = new ThreadLocal<WebDriver>();

	public static WebDriver init_driver(String browserName) {
		System.out.println("Browser initiated in local");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tl_driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tl_driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("InternetExplorer")) {
			WebDriverManager.edgedriver().setup();
			tl_driver.set(new EdgeDriver());
		} else {
			System.out.println("Please enter valid browser");
		}

		getDriver().manage().window().maximize();
		return getDriver();
	}

	@SuppressWarnings("deprecation")
	public static WebDriver init_driver_in_docker(String browserName) throws IOException, InterruptedException {
		System.out.println("Browser initiated in docker");

		// Set up grid
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("cmd", "/c", "docker selenium grid up.bat");
		File dir = new File("C:\\Users\\lenovo\\eclipse-workspace\\Selenium-Maven_TestNG\\Docker_Grid_SetUp");
		processBuilder.directory(dir);
		Process process = processBuilder.start();
		
		Thread.sleep(20000);
		// Setting up url
		URL url = new URL("http://localhost:4444/wd/hub");

		// initiate driver in docker
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().browserInDocker().setup();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			tl_driver.set(new RemoteWebDriver(url, cap));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().browserInDocker().setup();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("firefox");
			tl_driver.set(new RemoteWebDriver(url, cap));
		} else if (browserName.equalsIgnoreCase("internetexplorer")) {
			WebDriverManager.edgedriver().browserInDocker().setup();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("MicrosoftEdge");
			tl_driver.set(new RemoteWebDriver(url, cap));
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
