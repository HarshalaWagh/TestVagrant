package utility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import io.qameta.allure.Allure;

// Utility class for capturing screenshots
public class Screenshot {
	
	private static final Logger logger = LogManager.getLogger(Screenshot.class);
	
	// Capture screenshot and save to file and Allure report
	public static void screenShotMethod(WebDriver driver, String name) throws IOException {
		try {
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
			String screenshotDir = System.getProperty("user.dir") + File.separator + "Screenshots";
			
			// Create Screenshots directory if not exists
			new File(screenshotDir).mkdirs();
			
			// Capture screenshot
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			
			// Attach to Allure report
			Allure.addAttachment(name + " - " + timestamp, "image/png", 
				new java.io.ByteArrayInputStream(screenshot), ".png");
			
			// Save to file
			File dest = new File(screenshotDir, name + timestamp + ".png");
			FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), dest);
			
			logger.info("Screenshot saved: {}", dest.getAbsolutePath());
			
		} catch (IOException e) {
			logger.error("Failed to capture screenshot: {}", e.getMessage());
			throw e;
		}
	}
}
