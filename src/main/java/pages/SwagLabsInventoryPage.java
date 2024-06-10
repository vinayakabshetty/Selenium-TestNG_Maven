package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import driverFactory.DriverFactory;

public class SwagLabsInventoryPage {
	private WebDriver driver;

	private By inventoryItems = By.className("inventory_item");

	public SwagLabsInventoryPage() {
		this.driver = DriverFactory.getDriver();
	}

	public int countItemsInInventory() {
		List<WebElement> inventoryCount = driver.findElements(inventoryItems);
		System.out.println("The number of items in inventory is " + inventoryCount.size());
		return inventoryCount.size();
	}
}