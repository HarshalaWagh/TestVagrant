package TestCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pojo.BrowserCode;
import pom.WikiPage;
import utility.Listener;
import utility.ConfigReader;

// Test class to validate movie information on Wikipedia
@Listeners(Listener.class)
public class WikipediaTest {
	private static final Logger logger = LogManager.getLogger(WikipediaTest.class);
	private WebDriver driver;
	
	// Setup method to open browser before each test
	@BeforeMethod(alwaysRun = true)
	public void openBrowser() {
		driver = BrowserCode.openChromeBrowser(ConfigReader.getWikipediaUrl()); 
	}
	
	// Test method to verify movie title, release date and country on Wikipedia
	@Test(description = "Verify movie details on Wikipedia")
	public void verifyMovieDetailsOnWikipedia() {
		SoftAssert softAssert = new SoftAssert();
		WikiPage wikiPage = new WikiPage(driver);
		String movieName = ConfigReader.getMovieName();
		
		logger.info("Searching for movie: {}", movieName);
		wikiPage.searchOnWiki(movieName, driver);
		
		Assert.assertEquals(wikiPage.displayTitle(driver), ConfigReader.getProperty("wikipedia.expected.title"));
		wikiPage.diplayTable(driver);
		
		softAssert.assertEquals(wikiPage.getDate(), ConfigReader.getProperty("wikipedia.expected.date"));
		softAssert.assertEquals(wikiPage.getCountry(), ConfigReader.getProperty("wikipedia.expected.country"));
		softAssert.assertAll();
	}
	
	// Teardown method to close browser after each test
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
