package TestCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.*;

import pojo.BrowserCode;
import pom.WikiPage;
import utility.Screenshot;

public class WikipediaTest {
	WebDriver driver;
	WikiPage wikiPage;
	String Exp_PageTitle="Pushpa: The Rise - Wikipedia";
	
	@BeforeMethod
	public void openBrowser() {
		driver=BrowserCode.openChromeBrowser("https://en.wikipedia.org/wiki/Main_Page");
	}
	@Test//Testcase
	public void WikiTest() throws InterruptedException {
		wikiPage=new WikiPage(driver);
		wikiPage.searchOnWiki("anek", driver);
		String Act_PageTitle=wikiPage.displayTitle(driver);
		System.out.println(Act_PageTitle);
		//Assert.assertEquals(Act_PageTitle,Exp_PageTitle);
		wikiPage.diplayTable(driver);
		//To get Date
		wikiPage.getDate(); 
		
		//To get the country
		wikiPage.getCountry();
	}
	@AfterMethod//To close the browser and take the screenshot
	public void closeBrowser() throws IOException {
		Screenshot.screenShotMethod(driver,"Wikipedia");
		driver.close();
		
	}
	
}
