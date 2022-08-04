package TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import pojo.BrowserCode;
import pom.IMDBPage;
import pom.WikiPage;
import utility.Screenshot;

public class CompareData {
	WebDriver driver;
	
	
	@BeforeMethod
	//opening Browser with given url
	public void openBrowser() {
		driver=BrowserCode.openChromeBrowser("https://www.imdb.com/");
	}
	@Test
	public void getData() throws InterruptedException {
		IMDBPage imdbPage=new IMDBPage(driver);
		imdbPage.getSearch("Pushpa: the rise",driver);
        String date_imdb=imdbPage.getDate();
        String country_imdb=imdbPage.getCountry();
         
        driver.navigate().to("https://en.wikipedia.org/wiki/Main_Page");
        WikiPage wikiPage=new WikiPage(driver);
		wikiPage.searchOnWiki("Pushpa: the rise", driver);
		String date_wiki=wikiPage.getDate(); 
		String country_wiki=wikiPage.getCountry();
       
		/*SoftAssert soft=new SoftAssert();
		soft.assertNotEquals(date_imdb, date_wiki);
		soft.assertEquals(country_imdb, country_wiki);
		soft.assertAll();*/
		if(date_imdb.equals(date_wiki)){
		Reporter.log("Test passed",true);
	    }else {
		Reporter.log("Test Failed",true);
	    }
		if(country_imdb.equals(country_wiki)){
			Reporter.log("Test passed",true);
		    }else 
		    {
			Reporter.log("Test Failed",true);
		 }
		
	
		
		
	}
	@AfterMethod//To close the browser and take the screenshot
	public void closeBrowser() throws IOException {
		Screenshot.screenShotMethod(driver,"Wikipedia");
		driver.close();
		
	}
	
}

