package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IMDBPage {
	
	//Declaration of all the datamembers globally & restricted to private with private access specifier
	@FindBy(xpath="//input[@id='suggestion-search']")private WebElement searchIMDb;
	@FindBy(xpath="//a[contains(@class,'const')]")private List<WebElement> movieList;
	@FindBy(xpath="//a[@data-testid='search-result--link']")private WebElement mList;
	@FindBy(xpath="(//h3[@class='ipc-title__text'])[8]")private WebElement details;
	@FindBy(xpath="(//a[@data-testid='search-result--const'])[1]")private WebElement mName;
	@FindBy(xpath="(//a[contains(@href,'/releaseinfo?ref_=tt_dt_rdat')])[2]")private WebElement date;
    @FindBy(xpath="//a[contains(@href,'/search/title/?country_of_origin')]")private WebElement country;
	WebDriver driver;
	
	/*To initialize the elements in Pagefactory,we have to write this initElements static method of Pagefactory
	inside the constructor*/
	public IMDBPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	//Entering the moviename inside the searchbox
     public void getSearch(String movieName,WebDriver driver) throws InterruptedException {
	 searchIMDb.sendKeys(movieName);
	 Thread.sleep(3000);
	//By directly clicking on the film
		 Actions act=new Actions(driver);
		 act.moveToElement(mName).click().build().perform();
	 
	//List of all auto-suggestion movie
	/* System.out.println(movieList.size());
	 
	 //Iterate the list till we get desired movie
	 for(int i=0;i<movieList.size();i++) {
		 WebElement mName=movieList.get(i);
		 String Mname=mName.getText();
		 System.out.println(Mname);
		 
		//Compare the movie with, as we given in movieName
		 if(Mname.equals(movieName)) {
			 Actions act=new Actions(driver);
			 act.moveToElement(mName).perform();
			 act.click().perform();
		 }
	
	 }*/
	 
 }
 //For scrolling to details part which contains date and released date
 public Boolean scrollToDetailTitle(WebDriver driver){
	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",details);
	 return details.isDisplayed();
	 
 }
 //To findout the released date
 public String getDate() {
	 String dd=date.getText();
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
