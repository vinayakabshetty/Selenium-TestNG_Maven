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
import org.testng.annotations.Parameters;

import driverFactory.DriverFactory;

public class TestBase {

	/*
	 * This Method will read the browser name from <Parameter> tag from XML file or
	 * read the browser name from properties file (from
	 * src/main/resources/config.properties) achieve cross browser testing
	 */
	@Parameters(value = { "browserName" })
	@BeforeMethod
	public void init(String browserName) throws IOException {

		Properties prop = null;
		prop = ConfigReader.init("./src/main/resources/config.properties");

		String browserName_FromPropertiesFile = prop.getProperty("browser");
		String browserName_FromXMLFile = browserName;
		String runInDocker = prop.getProperty("runInDocker");

		// Initiate driver
		// Priority 1. XML File 2. Properties file
		if (runInDocker.equalsIgnoreCase("yes") & (!browserName_FromXMLFile.isEmpty())) {
			DriverFactory.init_driver_in_docker(browserName_FromXMLFile);
		} else if (runInDocker.equalsIgnoreCase("no") & !(browserName_FromXMLFile.isEmpty())) {
			DriverFactory.init_driver(browserName_FromXMLFile);
		} else if (runInDocker.equalsIgnoreCase("yes") & (!(browserName_FromPropertiesFile.isEmpty()))) {
			DriverFactory.init_driver_in_docker(browserName_FromPropertiesFile);
		} else if (runInDocker.equalsIgnoreCase("no") & (!(browserName_FromPropertiesFile.isEmpty()))) {
			DriverFactory.init_driver(browserName_FromPropertiesFile);
		} else {
			System.out.println("Please provide browser from either xml file or properties file");
		}
	}

	// This method will close all the open browsers
	@AfterMethod
	public void tearDown() {
		DriverFactory.getDriver().quit();
	}

	/*
	 * This method will take screenshot when test case is failed This method is used
	 * in utilities.Listeners (src/main/java)
	 */
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