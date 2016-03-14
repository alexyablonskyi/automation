package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @version 1.0.0
 * @author Oleksandr Yablonskyi
 */

public class AccountPage {
	
	WebDriver driver;
	
	
	public AccountPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * First and last name xpath after login into the system
	 */
	@FindBy(xpath=".//*[@id='current_account']//*[@class='header_name user_firstname']")
	private WebElement firstName;
	
	@FindBy(xpath=".//*[@id='current_account']//*[@class='header_name user_lastname']")
	private WebElement lastName;
	
	/**
	 * Return WebElement first Name
	 * @return firstName
	 */
	public WebElement firstNameWebElement(){
		return this.firstName;
	}
	
	/**
	 * Return Full name of user after login
	 * @return fullName
	 */	
	public String getFullNameAfterLogin(){
		String fullName = firstName.getText() + " " + lastName.getText();
		return fullName;
	}
	
	/*
	 * "Get destination tips from world travelers" button and click on button
	 */
	@FindBy(xpath=".//*[@data-title='Get destination tips from world travelers']")
	private WebElement getDistinationTipsButton;
	
	/**
	 * Click on Get destination tip button
	 */
	public void clickOnGetDestinationTipsButton(){
		getDistinationTipsButton.click();			
	}
	
	
	/*
	 * Discover New Destinations button and click on button
	 */
	@FindBy(xpath=".//*[@class='dsf_banner_awareness_index_cta']")
	private WebElement discoverNewDestinationsButton;
	
	/**
	 * Click on New Destination button
	 */
	public void clickDiscoverNewdestinationButton(){
		discoverNewDestinationsButton.click();
	}
	
	
	
	
	
	
}
