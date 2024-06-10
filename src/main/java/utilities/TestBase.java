package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import driverFactory.DriverFactory;

public class TestBase {

	@BeforeMethod
	public void init() {
		Properties prop = null;
		try {
			prop = ConfigReader.init("./src/main/resources/config.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String browserName = prop.getProperty("browser");
		DriverFactory.init_driver(browserName);
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.getDriver().quit();
	}

	public void sceenshotForFailedTestCases(String testCaseName) {
		// Take screenshot
		File srcFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

		// Time stamp
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
		String dateTimeStamp = myDateObj.format(dateTimeFormatter);

		// Screenshot name
		String ScreenshotName = testCaseName + "_" + dateTimeStamp;

		// Copy screenshot to destination directory
		try {
			FileUtils.copyFile(srcFile, new File("./test-output/screenshots/" + ScreenshotName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}