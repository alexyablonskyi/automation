package automationTests;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.*;
import pages.*;
import values.*;

/**
 * @version 1.0.0
 * @author Oleksandr Yablonskyi
 */

public class Tests extends BaseClass {
	
	@Test(enabled=true, priority=0, description= "Go to booking website and login into the system")
	public void loginIntoBookingSite() throws Exception{		
		HomePage homePage = new HomePage(driver);
		AccountPage accountPage = new AccountPage(driver);
			
		homePage.clickSignInButton();
		waitForPageLoad(driver);
		homePage.loginIntoTheSystem(Global.bookingEmail, Global.bookingpassword);	
		waitForElementVisibleX(accountPage.firstNameWebElement());
				
		Assert.assertEquals(accountPage.getFullNameAfterLogin(), Global.fullNameBookingAccount);
	}

	
	@Test(enabled=true, priority=1, description= "Find accomodations in Lviv")
	public void findAccomodationInLviv() throws Exception{		
		HomePage homePage = new HomePage(driver);
		AccountPage accountPage = new AccountPage(driver);
		DestionationFinderPage destinationFinderPage = new DestionationFinderPage(driver);
		
		homePage.clickSignInButton();
		waitForPageLoad(driver);
		homePage.loginIntoTheSystem(Global.bookingEmail, Global.bookingpassword);		
		waitForElementVisibleX(accountPage.firstNameWebElement());
		
		accountPage.clickOnGetDestinationTipsButton();
		accountPage.clickDiscoverNewdestinationButton();			
		destinationFinderPage.searchWhereDoYouWantGo(Global.cityForStay);
		
		Assert.assertEquals(destinationFinderPage.getCountFoundPlace() > 0, true);		
	}
	

	@Test(enabled=true, priority=2, description= "Find all popular cities on website and write them into db, compare result")
	public void findAllPopularCitiesOnSite() throws Exception{
		HomePage homePage = new HomePage(driver);
		
		ArrayList<String> listOfCitiesOnSite = homePage.getNamesOfCities();		
		Connection connect = createDataBaseConnection();
		deleteAllRowsFromTable(connect);
		addNewRowsToTable(connect, listOfCitiesOnSite);	
		
		Assert.assertEquals(listOfCitiesOnSite.size(), getCountRowsInTable(connect));		
	}


	@Test(enabled=true, priority=3, description= "Verify if Hotel has free rooms on appropriete date")
	public void verifyIfHotemIsAvailableOnDate() throws Exception{	
		HomePage homePage = new HomePage(driver);
		SearchResultPage searchResult = new SearchResultPage(driver);
		
		homePage.setDateOfBookingHotel("25", "2016-6", "26", "2016-6");
		homePage.searchHotel(Global.hotelName);
		
		Assert.assertEquals(searchResult.getMessageWhenRoomAreSold(), "We're sold out on your dates: Sat, Jun 25, 2016 - Sun, Jun 26, 2016 (1 night)");
	}
	
	
	@Test(enabled=true, priority=4, description= "Find hotel and check if there are free WiFi and parking")
	public void findHotemAndCheckWiFiAndParkingCosts() throws Exception{
		HomePage homePage = new HomePage(driver);
		SearchResultPage searchResult = new SearchResultPage(driver);
		HotelPage hotelPage = new HotelPage(driver);
		SoftAssert softAssert = new SoftAssert();
		
		homePage.searchHotel(Global.hotelName);	
		searchResult.openHotelPage();
		switchBeetweanWindows();
		
		softAssert.assertEquals(hotelPage.getIsInternetFree(), "Free!", "Internet is not free in hotel");
		softAssert.assertTrue(hotelPage.getIsParkingAvailable().toLowerCase().contains("Parking is available".toLowerCase()), "Parking is not available");
		softAssert.assertAll();
	}
}
