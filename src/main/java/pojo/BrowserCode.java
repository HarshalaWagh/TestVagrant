package pojo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ConfigReader;

public class BrowserCode{
	
	// Opens Chrome browser with WebDriver Manager and navigates to the specified URL
	public static WebDriver openChromeBrowser(String url) {
		WebDriverManager.chromedriver().setup();
		
		// Configure Chrome options for stability and to avoid bot detection
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*"); // Allow remote origins
		options.addArguments("--disable-blink-features=AutomationControlled"); // Hide automation flags
		options.addArguments("--start-maximized"); // Maximize the browser
		options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
		options.addArguments("--no-sandbox"); // Bypass OS security model
		options.addArguments("--disable-gpu"); // Disable GPU hardware acceleration
		options.addArguments("--disable-extensions"); // Disable extensions
		options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"); // Set user agent
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); // Remove automation indicator
		options.setExperimentalOption("useAutomationExtension", false); // Disable automation extension
		
		// Initialize ChromeDriver with options
		WebDriver driver = new ChromeDriver(options);
		
		// Set page load timeout only (no implicit wait - using explicit waits instead)
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
		
		// Navigate to URL
		driver.get(url);
		
		return driver; 
	}
}
