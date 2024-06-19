package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import pages.NavigationPage;
import utilities.TestBase;

public class Navigation_TC extends TestBase {

	@Ignore
	@Test(priority = 1)
	public void navigateToGoogle() {
		NavigationPage page = new NavigationPage();
		page.navigateToGoogle();
		page.verifyTitle();
		Assert.assertTrue(false);
	}

	@Test(priority = 2, dataProvider = "ecommerceWebSiteUrl")
	public void navigateToEcommerceWebsites(String url) {
		NavigationPage page = new NavigationPage();
		page.navigate(url);
	}

	@DataProvider(name = "ecommerceWebSiteUrl", parallel=true)
	public Object[][] dataProviderMethod() {
		return new Object[][] { { "https://www.amazon.in"},{ "https://www.flipkart.com" },
				{ "https://www.snapdeal.com"} };
	}

}