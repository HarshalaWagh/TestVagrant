package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//POM Class
public class WikiPage {
	
	//Declaration of all the datamembers globally & restricted to private with private access specifier
	@FindBy(xpath="//input[@id='searchInput']")private WebElement searchWiki;
	@FindBy(xpath="//div[@class='suggestions-result']")private List<WebElement> pMovieList;
	@FindBy(xpath="//h1[@id='firstHeading']")private WebElement newPageHeaeding;
	//@FindBy(xpath="//table[@class='infobox vevent']")private WebElement table;
	//@FindBy(xpath="//table[@class='infobox vevent']//tbody//tr")private List<WebElement> rowCount;
	//@FindBy(xpath="//table[@class='infobox vevent']//tbody//tr//th")private List<WebElement> colCount;
	//@FindBy(xpath="(//table//div[@class='plainlist'])[4]")private WebElement date;
	@FindBy(xpath="(//td[@class='infobox-data'])[10]")private WebElement date;
	//@FindBy(xpath="(//table[@class='infobox vevent']/tbody//tr//td)[13]")private WebElement country;
	@FindBy(xpath="(//td[@class='infobox-data'])[12]")private WebElement country;
	WebDriver driver;
	
	/*To initialize the elements in Pagefactory,we have to write this initElements static method of Pagefactory
	inside the constructor*/
	public WikiPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	//Entering the moviename inside the searchbox
	public void searchOnWiki(String MovieName,WebDriver driver) throws InterruptedException {
		searchWiki.sendKeys(MovieName);
		Thread.sleep(2000);
		
		//List of all auto-suggestion movie
		System.out.println(pMovieList.size());
		
		//Iterate the list till we get desired movie
		for(int i=0;i<pMovieList.size();i++) {
			WebElement movie=pMovieList.get(i);
			String moviename=movie.getText();
			
			//Compare the movie with, as we given in movieName
			if(moviename.equalsIgnoreCase(MovieName)) {
				Actions act=new Actions(driver);
				act.moveToElement(movie).perform();
				act.click().perform();
			}
		}
	}
	public String displayTitle(WebDriver driver) {
		return driver.getTitle();
	}
	//For scrolling to details part which contains date and released date
	public void diplayTable(WebDriver driver) {
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,400)");
		/*int rowSize=rowCount.size();
		System.out.println(rowSize);
		int colSize=colCount.size();
		System.out.println(colSize);*/
		
	}
	 //To findout the released date
	public String getDate() {
		String dd=date.getText();//11c,12r
		System.out.println(dd);
		return dd;
	}
	//To findout the origin of country
	public String getCountry() {
		String cc=country.getText();
		System.out.println(cc);
		return cc;
	}

}
