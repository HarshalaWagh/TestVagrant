package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Utility class to read configuration from properties file
public class ConfigReader {
	
	// Logger instance for ConfigReader class
	private static final Logger logger = LogManager.getLogger(ConfigReader.class);
	// Properties object to store configuration key-value pairs
	private static Properties properties;
	// Path to configuration file
	private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";
	
	// Static block to load properties file when class is loaded
	static {
		try {
			properties = new Properties(); 
			FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
			properties.load(fis);
			fis.close(); 
			logger.info("Configuration file loaded successfully from: {}", CONFIG_FILE_PATH);
		} catch (IOException e) {
			logger.error("Failed to load config.properties file: {}", e.getMessage(), e);
		}
	}
	
	// Method to get property value by key
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	// Method to get Wikipedia base URL
	public static String getWikipediaUrl() {
		return properties.getProperty("wikipedia.url");
	}
	
	// Method to get IMDb base URL
	public static String getImdbUrl() {
		return properties.getProperty("imdb.url");
	}
	
	// Method to get movie name for testing
	public static String getMovieName() {
		return properties.getProperty("movie.name");
	}
	
	// Method to get explicit wait timeout in seconds for WebDriverWait/FluentWait
	public static int getExplicitWait() {
		return Integer.parseInt(properties.getProperty("browser.explicit.wait"));
	}
	
	// Method to get browser page load timeout in seconds
	public static int getPageLoadTimeout() {
		return Integer.parseInt(properties.getProperty("browser.page.load.timeout"));
	}
}

