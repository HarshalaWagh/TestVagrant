package pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementNotInteractableException;
import utility.WaitHelper;

// Page Object Model for Wikipedia website
public class WikiPage {
	
	// Locator for Wikipedia search input box
	@FindBy(xpath="//input[@name='search' and @type='search']")private WebElement searchWiki;
	// Locator for release date in movie infobox
	@FindBy(xpath="//*[contains(text(),'Release date')]/parent::*/following-sibling::*[1]")private WebElement date;
	// Locator for country in movie infobox
	@FindBy(xpath="//table[contains(@class,'infobox')]//tr[.//th[contains(normalize-space(),'Country')]]/td")private WebElement country;
	// Locator for specific search result containing 'The Rise'
	@FindBy(xpath="//div[contains(@class,'mw-search-result-heading')]//a[contains(@title,'The Rise') or contains(text(),'The Rise')]")private WebElement specificSearchResult;
	// Locator for first search result as fallback
	@FindBy(xpath="(//div[contains(@class,'mw-search-result-heading')]//a)[1]")private WebElement firstSearchResult;
	
	// Constructor to initialize page elements
	public WikiPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	// Search for a movie on Wikipedia and handle search results page
	public void searchOnWiki(String movieName, WebDriver driver) {
		Wait<WebDriver> wait = WaitHelper.getFluentWait(driver);
		String currentUrl = driver.getCurrentUrl();
		
		wait.until(driver1 -> {
			try {
				searchWiki.clear();
				searchWiki.sendKeys(movieName);
				searchWiki.sendKeys(Keys.ENTER);
				return true;
			} catch (StaleElementReferenceException | ElementNotInteractableException e) {
				return false;
			}
		});
		
		wait.until(driver1 -> !driver.getCurrentUrl().equals(currentUrl));
		
		if (driver.getTitle().contains("Search results")) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(specificSearchResult)).click();
			} catch (Exception e) {
				wait.until(ExpectedConditions.elementToBeClickable(firstSearchResult)).click();
			}
		}
	}
	
	// Get current page title
	public String displayTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	// Scroll down to view movie details table
	public void diplayTable(WebDriver driver) {
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,400)");
	}
	
	// Get release date text from infobox
	public String getDate() {
		return date.getText();
	}
	
	// Get country text from infobox
	public String getCountry() {
		return country.getText();
	}
}
