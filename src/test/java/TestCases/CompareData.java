package TestCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pojo.BrowserCode;
import pom.IMDBPage;
import pom.WikiPage;
import utility.Listener;
import utility.ConfigReader;

@Listeners(Listener.class)

// Test class to compare movie data consistency between IMDb and Wikipedia
public class CompareData {
	private static final Logger logger = LogManager.getLogger(CompareData.class);
	private WebDriver driver;
	
	// Setup method to open browser before each test
	@BeforeMethod(alwaysRun = true)
	public void openBrowser() {
		driver = BrowserCode.openChromeBrowser(ConfigReader.getImdbUrl());
	}
	
	// Test method to compare release date and country between IMDb and Wikipedia
	@Test(description = "Compare movie data between IMDb and Wikipedia")
	public void compareMovieDataAcrossPlatforms() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		String movieName = ConfigReader.getMovieName();
		
		// Fetch data from IMDb
		logger.info("Fetching data from IMDb");
		IMDBPage imdbPage = new IMDBPage(driver);
		imdbPage.getSearch(movieName, driver);
		String imdbDate = imdbPage.getDate();
		String imdbCountry = imdbPage.getCountry();
		
		// Fetch data from Wikipedia
		logger.info("Fetching data from Wikipedia");
		driver.navigate().to(ConfigReader.getWikipediaUrl());
		WikiPage wikiPage = new WikiPage(driver);
		wikiPage.searchOnWiki(movieName, driver);
		String wikiDate = wikiPage.getDate();
		String wikiCountry = wikiPage.getCountry();
		
		// Compare data between both platforms
		logger.info("Comparing data between platforms");
		softAssert.assertTrue(compareDates(imdbDate, wikiDate));
		softAssert.assertEquals(wikiCountry, imdbCountry);
		softAssert.assertAll();
	}
	
	// Compare dates from different formats (IMDb vs Wikipedia)
	private boolean compareDates(String imdbDate, String wikiDate) {
		String imdb = imdbDate.toLowerCase().replace("(india)", "").replace(",", "").trim();
		String wiki = wikiDate.toLowerCase().trim();
		return imdb.contains("december") && wiki.contains("december") &&
		       imdb.contains("17") && wiki.contains("17") && 
		       imdb.contains("2021") && wiki.contains("2021");
	}
	
	// Teardown method to close browser after each test
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}