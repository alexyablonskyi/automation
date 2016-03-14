package pages;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import values.Global;

/**
 * @version 1.0.0
 * @author Oleksandr Yablonskyi
 */

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Login button Sign in
	 */
	//@FindBy(xpath=".//*[@id='current_account']//span[contains(text(), 'Sign in')]")
	@FindBy(xpath="(.//*[@id='current_account']//span)[2]")
	private WebElement signInButton;
	
	/**
	 * Click on Sign in button method
	 */
	public void clickSignInButton(){
		signInButton.click();
	}
	
	/*
	 * Email and password field for login into the system
	 */
	@FindBy(xpath="(.//*[@id='b2indexPage']//input[@name='username'])[1]")
	private WebElement emailField;
	
	@FindBy(xpath="(.//*[@id='b2indexPage']//input[@name='password'])[1]")
	private WebElement passwordField;
	
	@FindBy(xpath=".//*[@id='b2indexPage']//input[@value='Sign in']")
	private WebElement loginButton;
	
	/**
	 * Login into the system
	 * @param email, password
	 */
	public void loginIntoTheSystem(String email, String password){
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
	}
	
	/**
	 * Get xpath of List of all popular cities
	 * @return xpath
	 */
	public String getPopularDestinationsSectionXpath(){
		return ".//*[@class='b-popular_list lp_endorsements_popular_destinations_container']//li[@class='b-popular_item b-sprite-wrap']//h3/a";
	}
	
	/**
	 * List of all popular cities
	 * @return ArrayList
	 */
	public ArrayList<String> getNamesOfCities(){
		List<WebElement> list = driver.findElements(By.xpath(getPopularDestinationsSectionXpath()));
		
		ArrayList<String> listOfCitiesName = new ArrayList<String>();		
			for (int i = 0; i < list.size(); i++){
				listOfCitiesName.add(i, list.get(i).getText());
			}
		return listOfCitiesName;
	}
	
	
	/*
	 * Find best Deals section, search functionality
	 */
	@FindBy(xpath=".//*[@id='ss']")
	private WebElement searchField;
	
	@FindBy(xpath=".//*[@id='frm']//span[contains(text(), 'Search')]")
	private WebElement searchButton;
	
	@FindBy(xpath=".//*[@id='destinationSearch']//li[1]")
	private WebElement selectSearchSuggestion;
	
	/**
	 * Search method
	 * @param hotelName
	 * @throws Exception 
	 */
	public void searchHotel(String hotelName) throws Exception{
		searchField.sendKeys(hotelName);
		Thread.sleep(500);
		selectSearchSuggestion.click();
		searchButton.click();
	}
	
	/**
	 * Select date for booking hotel
	 * @param dateIn, monthIn, dateOut, monthOut
	 * @throws Exception 
	 */
	public void setDateOfBookingHotel(String dateIn, String monthIn, String dateOut, String monthOut) throws Exception{
		driver.findElement(By.xpath(".//select[@name='checkin_monthday']//option[@value='"+ dateIn +"']")).click();
		driver.findElement(By.xpath(".//select[@name='checkin_year_month']//option[@value='" + monthIn +"']")).click();
		driver.findElement(By.xpath(".//select[@name='checkout_monthday']//option[@value='"+ dateOut +"']")).click();
		driver.findElement(By.xpath(".//select[@name='checkout_year_month']//option[@value='" + monthOut +"']")).click();
	}
	
	
	
}
