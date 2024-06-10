package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	public static Properties init(String configFilePath) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(new File(configFilePath));
		prop.load(fis);
		return prop;
	}
}