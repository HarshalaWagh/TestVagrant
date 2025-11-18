package pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import utility.WaitHelper;

// Page Object Model for IMDb website
public class IMDBPage {
	
	// Locator for IMDb search input box
	@FindBy(xpath="//input[@id='suggestion-search' and @name='q']")private WebElement searchIMDb; 
	// Locator for first movie result in search suggestions
	@FindBy(xpath="(//div[@role='presentation']//a[contains(@href,'/title/tt')])[1]")private WebElement mName; 
	// Locator for movie title on details page
	@FindBy(xpath="//h1[@data-testid='hero__pageTitle']//span[1]")private WebElement details;
	// Locator for release date link
	@FindBy(xpath="//a[contains(@href,'releaseinfo') and contains(text(),'(')]")private WebElement date; 
	// Locator for country of origin link
	@FindBy(xpath="//a[contains(@href,'country_of_origin') and not(contains(@href,'sort'))]")private WebElement country; 
	
	// Constructor to initialize page elements
	public IMDBPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	// Search for a movie and navigate to its details page
	public void getSearch(String movieName, WebDriver driver) throws InterruptedException {
		Wait<WebDriver> wait = WaitHelper.getFluentWait(driver);
		wait.until(ExpectedConditions.visibilityOf(searchIMDb));
		searchIMDb.clear();
		searchIMDb.sendKeys(movieName);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(mName)).click();
		wait.until(ExpectedConditions.visibilityOf(details));
	}
	
	// Scroll to movie details section and verify visibility
	public Boolean scrollToDetailTitle(WebDriver driver){
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", details);
		return details.isDisplayed();
	}
	
	// Get release date text from movie details
	public String getDate() {
		return date.getText();
	}
	
	// Get country of origin text from movie details
	public String getCountry() {
		return country.getText();
	}
}
