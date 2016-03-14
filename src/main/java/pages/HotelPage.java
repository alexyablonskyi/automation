package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelPage {
	WebDriver driver;
	
	public HotelPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Internet section
	 */
	@FindBy(xpath=".//*[@id='hp_facilities_box']//h5[contains(text(), 'Internet')]/following-sibling::ul//strong")
	private WebElement internetSection;
	
	/**
	 * Found out if Free Internet is available
	 * @return String
	 */
	public String getIsInternetFree(){
		return internetSection.getText();
	}
	
	/*
	 * Parking section
	 */
	@FindBy(xpath=".//*[@id='hp_facilities_box']//h5[contains(text(), 'Parking')]/following-sibling::ul//p")
	private WebElement parkingSection;
	
	/**
	 * Found out if Parking is available
	 * @return String
	 */
	public String getIsParkingAvailable(){
		return parkingSection.getText();
	}
}
