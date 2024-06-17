package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import driverFactory.DriverFactory;

public class SwagLabsLoginPage {

	private WebDriver driver;

	private By userNameField = By.id("user-name");
	private By passwordField = By.id("password");
	private By loginButton = By.id("login-button");
	private By loginErrorMessage = By.xpath("//h3[@data-test='error']");

	public SwagLabsLoginPage() {
		this.driver = DriverFactory.getDriver();
	}

	public void navigateToSwagLabsLoginPage() {
		driver.get("https://www.saucedemo.com/");
	}

	public void enterUserName(String username) {
		driver.findElement(userNameField).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}

	public void clickOnLoginButton() {
		driver.findElement(loginButton).click();
	}
	
	public String getLoginErrorMessage() {
		String errorMessage = driver.findElement(loginErrorMessage).getText().trim();
		return errorMessage;
	}
}
