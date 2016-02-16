package automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import automation.base.*;
import automation.pages.*;
import automation.values.*;

public class Tests extends BaseClass {

/*
	Test 1
	1. Enter www.booking.com
	2. Go to Login Page and Login
	3. Check if you First and Last Name appeared in right corner

*/
	@Test(enabled=false, priority=0, description= "Go to booking website and login into the system")
	public void loginIntoBookingSite() throws Exception{
		HomePage homePage = new HomePage(driver);
		homePage.clickSignInButton();
		waitForPageLoad(driver);;
		homePage.loginIntoTheSystem();
		waitUntilAjaxRequestCompletes();
		
		Assert.assertEquals(homePage.getFullNameAfterLogin(), Global.fullNameBookingAccount);
	}

	/*
	Test 2
	1. Enter Montenegro in the search input
	2. Select data from 22 March 2016 till 30 March 2016
	3. Click Search
	4. Check if Montenegro is in the list of popular hotels
*/
	@Test(enabled=true, priority=1, description= "if Montenegro is in the list of popular hotels")
	public void verifyMontenegroInListOfPopularHotels() throws Exception{
		
	}
	
	/*
	Test 3
	0. Do all steps from test 2 except verifications
	1. Select Montenegro Hotel
	2. Check if it has Free Wi-Fi and Free Parking
*/
	/*
	Test 4
	1. Login
	2. Select  "Get destination tips from world travelers"
	3. Click on Discover New Destinations
	4. Enter Lviv in Search and select “LVIV Region,Ukraine” from drop-down
	5. Check if any results a re found
*/
	/*
	Test 5
	1. Enter page
	2. Grab all visible Town Name in Destinations
	3. Save them to DB(it can be sqlLite or Mysql)
	4. Check if The number of entries in DB is the same as number of visible destinations on the page.
*/

}
