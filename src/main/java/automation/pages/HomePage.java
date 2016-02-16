package automation.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.values.Global;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Login button Sign in
	 */
	@FindBy(xpath=".//*[@id='current_account']//span[contains(text(), 'Sign in')]")
	private WebElement signInButton;
	
	/*
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
	
	/*
	 * Login into the system
	 */
	public void loginIntoTheSystem(){
		emailField.sendKeys(Global.bookingEmail);
		passwordField.sendKeys(Global.bookingpassword);
		loginButton.click();
	}
	
	/*
	 * First and last name xpath after login into the system
	 */
	@FindBy(xpath=".//*[@id='current_account']//*[@class='header_name user_firstname']")
	private WebElement firstName;
	
	@FindBy(xpath=".//*[@id='current_account']//*[@class='header_name user_lastname']")
	private WebElement lastName;
	
	
	/*
	 * Return Full name of user after login
	 */
	
	public String getFullNameAfterLogin(){
		String fullName = firstName.getText() + " " + lastName.getText();
		return fullName;
	}
	
	
	
	
	
	
}
