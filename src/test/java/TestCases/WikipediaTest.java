package TestCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import pojo.BrowserCode;
import pom.WikiPage;
import utility.Screenshot;

public class WikipediaTest {
	WebDriver driver;
	WikiPage wikiPage;
	String exp_pageTitle="Pushpa: The Rise - Wikipedia";
	String exp_date="17 December 2021";
	String exp_country="India";
	
	@BeforeMethod
	public void openBrowser() {
		driver=BrowserCode.openChromeBrowser("https://en.wikipedia.org/wiki/Main_Page");
	}
	@Test//Testcase
	public void WikiTest() throws InterruptedException {
		wikiPage=new WikiPage(driver);
		wikiPage.searchOnWiki("Pushpa: the rise", driver);
		String act_pageTitle=wikiPage.displayTitle(driver);
		System.out.println(act_pageTitle);
		Assert.assertEquals(act_pageTitle,exp_pageTitle);
		wikiPage.diplayTable(driver);
		
		//To get Date
		String date=wikiPage.getDate(); 
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(exp_date, date);
		
		//To get the country
		String act_country=wikiPage.getCountry();
		soft.assertEquals(act_country, exp_country);
		soft.assertAll();
		System.out.println("All assertions passed");
	}
	@AfterMethod//To close the browser and take the screenshot
	public void closeBrowser() throws IOException {
		Screenshot.screenShotMethod(driver,"Wikipedia");
		driver.close();
		
	}
	
}
