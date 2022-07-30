package TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import graphql.Assert;
import pojo.BrowserCode;
import pom.IMDBPage;
import utility.Screenshot;

public class IMDbTest {
   WebDriver driver;
   IMDBPage imdbPage;
   
	@BeforeMethod
	//opening Browser with given url
	public void openBrowser() {
		driver=BrowserCode.openChromeBrowser("https://www.imdb.com/");
	}
	
	@Test//testcase
	public void imdbTest() throws InterruptedException {
		
		imdbPage=new IMDBPage(driver);
		imdbPage.getSearch("Pushpa",driver);
		imdbPage.scrollToDetailTitle(driver);
		
		//To get the Date
		String date=imdbPage.getDate();
		//For specific movies/known movies add assertions
		/*SoftAssert soft=new SoftAssert();
		soft.assertEquals(Release_Date,date);
		//OR
		if(date.equals(Release_Date)){
			Reporter.log("Test passed",true);
		}else {
			Reporter.log("Test Failed",true);
		}
		*/
		//To get the country
		String country=imdbPage.getCountry();
		/*soft.assertEquals(Country_of_Origin,country);
		soft.assertAll();
		if(country.equals(Country_of_Origin)){
			Reporter.log("Test passed",true);
		}
		else {
			Reporter.log("Test Failed",true);
		}*/
	}
	@AfterMethod//To close the browser and take the screenshot
	public void closeBrowser() throws IOException {
		Screenshot.screenShotMethod(driver,"IMDb");
		driver.close();
		
	}
	
}
