package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.SwagLabsInventoryPage;
import pages.SwagLabsLoginPage;
import utilities.TestBase;

public class SwagLabs extends TestBase {

	Logger log = LogManager.getLogger("SimpleLog4jDemo.class");

	@Test(priority = 1)
	public void loginToSwagLabs() {
		log.info("Test case started");
		SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage();
		SwagLabsInventoryPage swagLabsInventoryPage = new SwagLabsInventoryPage();
		swagLabsLoginPage.navigateToSwagLabsLoginPage();
		swagLabsLoginPage.enterUserName("standard_user");
		swagLabsLoginPage.enterPassword("secret_sauce");
		swagLabsLoginPage.clickOnLoginButton();
		swagLabsInventoryPage.countItemsInInventory();
		log.info("Test case ended");
	}

	@Test(priority = 2)
	public void loginToSwagLabs_lockedOutUser() {
		log.info("Test case started");
		SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage();
		swagLabsLoginPage.navigateToSwagLabsLoginPage();
		swagLabsLoginPage.enterUserName("locked_out_user");
		swagLabsLoginPage.enterPassword("secret_sauce");
		swagLabsLoginPage.clickOnLoginButton();
		String actualErrorMsg = swagLabsLoginPage.getLoginErrorMessage();
		String expectedErrorMsg = "Epic sadface: Sorry, this user has been locked out.";
		Assert.assertTrue(actualErrorMsg.equals(expectedErrorMsg), "Error message mismatch");
		log.info("Test case ended");
	}
}