package utility;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;

// Utility class to create reusable wait instances for all page objects
public class WaitHelper {
	
	// Returns FluentWait with custom polling and exception handling
	public static Wait<WebDriver> getFluentWait(WebDriver driver) {
		return new FluentWait<>(driver)
			.withTimeout(Duration.ofSeconds(ConfigReader.getExplicitWait()))
			.pollingEvery(Duration.ofMillis(300))
			.ignoring(StaleElementReferenceException.class)
			.ignoring(ElementNotInteractableException.class)
			.ignoring(NoSuchElementException.class);
	}
}

