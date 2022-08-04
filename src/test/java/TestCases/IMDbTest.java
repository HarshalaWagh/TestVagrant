package TestCases;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import pojo.BrowserCode;
import pom.IMDBPage;
import utility.Screenshot;

public class IMDbTest {
   WebDriver driver;
   IMDBPage imdbPage;
   String Exp_title="Pushpa: The Rise - Part 1 (2021) - IMDb";
   String Release_Date="December 17, 2021 (India)";
   String Country_of_Origin="India";
   
	@BeforeMethod
	//opening Browser with given url
	public void openBrowser() {
		driver=BrowserCode.openChromeBrowser("https://www.imdb.com/");
	}
	
	@Test//testcase
	public void imdbTest() throws InterruptedException {
		
		imdbPage=new IMDBPage(driver);
		imdbPage.getSearch("Pushpa: the rise",driver);
		Boolean display=imdbPage.scrollToDetailTitle(driver);
		
		String Act_title=driver.getTitle();
		System.out.println(Act_title);
		Assert.assertEquals(Exp_title,Act_title);
		
		SoftAssert soft=new SoftAssert();
		soft.assertTrue(display);
		
		//To get the Date
		String date=imdbPage.getDate();
		
		//For specific movies/known movies add assertions
		soft.assertEquals(Release_Date,date);
		
		//OR
		/*if(date.equals(Release_Date)){
			Reporter.log("Test passed",true);
		}else {
			Reporter.log("Test Failed",true);
		}
		*/
		
		//To get the country
		String country=imdbPage.getCountry();
		soft.assertEquals(Country_of_Origin,country);
		soft.assertAll();
		System.out.println("All assertions passed");
		
		/*if(country.equals(Country_of_Origin)){
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
