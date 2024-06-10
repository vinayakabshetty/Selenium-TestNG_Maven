package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import driverFactory.DriverFactory;

public class NavigationPage {
	private WebDriver driver;

	public NavigationPage() {
		this.driver = DriverFactory.getDriver();
	}

	public void navigateToGoogle() {
		driver.get("https://www.google.com");
	}

	public void navigate(String url) {
		driver.get(url);
	}

	public void verifyTitle() {
		String expectedTitle = "Google";
		String actualTitle = driver.getTitle().trim();
		Assert.assertEquals(actualTitle, expectedTitle, "Navigation unsuccessfull");
	}

}