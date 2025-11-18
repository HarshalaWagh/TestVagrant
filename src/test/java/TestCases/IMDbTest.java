package TestCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pojo.BrowserCode;
import pom.IMDBPage;
import utility.Listener;
import utility.ConfigReader;

// Test class to validate movie information on IMDb
@Listeners(Listener.class)
public class IMDbTest {
	private static final Logger logger = LogManager.getLogger(IMDbTest.class);
	private WebDriver driver;
	
	// Setup method to open browser before each test
	@BeforeMethod(alwaysRun = true)
	public void openBrowser() {
		driver = BrowserCode.openChromeBrowser(ConfigReader.getImdbUrl());
	}
	
	// Test method to verify movie title, release date and country on IMDb
	@Test(description = "Verify movie details on IMDb")
	public void verifyMovieDetailsOnIMDb() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		IMDBPage imdbPage = new IMDBPage(driver);
		String movieName = ConfigReader.getMovieName();
		
		logger.info("Searching for movie: {}", movieName);
		imdbPage.getSearch(movieName, driver);
		
		Assert.assertEquals(driver.getTitle(), ConfigReader.getProperty("imdb.expected.title"));
		softAssert.assertTrue(imdbPage.scrollToDetailTitle(driver));
		softAssert.assertEquals(imdbPage.getDate(), ConfigReader.getProperty("imdb.expected.date"));
		softAssert.assertEquals(imdbPage.getCountry(), ConfigReader.getProperty("imdb.expected.country"));
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
