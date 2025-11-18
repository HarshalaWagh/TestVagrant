package utility;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

// TestNG Listener class to capture screenshots on test failure
public class Listener implements ITestListener {
	
	// Logger instance for Listener class
	private static final Logger logger = LogManager.getLogger(Listener.class);
	
	// Method called when test starts
	@Override
	public void onTestStart(ITestResult result) {
		logger.info("Test Started: {}", result.getName());
	}
	
	// Method called when test fails
	@Override
	public void onTestFailure(ITestResult result) {
		logger.error("Test Failed: {}", result.getName()); 
		
		// Get the test class instance from result
		Object testClass = result.getInstance();
		
		// Try to get the WebDriver from the test class using reflection
		try {
			// Use reflection to access private driver field from test class
			java.lang.reflect.Field driverField = testClass.getClass().getDeclaredField("driver");
			driverField.setAccessible(true); 
			WebDriver driver = (WebDriver) driverField.get(testClass); 
			
			if (driver != null) { 
				// Capture screenshot on failure with test name
				String testName = result.getName();
				Screenshot.screenShotMethod(driver, testName + "_FAILED_");
			}
		} catch (NoSuchFieldException e) {
			logger.error("Driver field not found: {}", e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error("Cannot access driver field: {}", e.getMessage());
		} catch (IOException e) {
			logger.error("Failed to capture screenshot: {}", e.getMessage());
		} catch (Exception e) {
			logger.error("Error in onTestFailure: {}", e.getMessage());
		}
	}
	
	// Method called when test is skipped
	@Override
	public void onTestSkipped(ITestResult result) {
		logger.warn("Test Skipped: {}", result.getName());
	}
	
	// Method called when test passes successfully
	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("Test Passed: {}", result.getName());
	}
}

