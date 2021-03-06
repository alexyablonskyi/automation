package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
	WebDriver driver;
	
	public SearchResultPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Message about all room are sold for selected date
	 */
	@FindBy(xpath=".//*[@id='hotellist_inner']//p[@class='simple_av_calendar_no_av']")
	private WebElement hotelRoomAreSoldSection;
	
	/**
	 * Method returns message about all rooms are sold
	 */
	public String getMessageWhenRoomAreSold(){
		return hotelRoomAreSoldSection.getText();
	}
	
	/*
	 * Open hotel page
	 */
	@FindBy(xpath="(.//*[@id='hotellist_inner']//a[@class='hotel_name_link url'])[1]")
	private WebElement openHotelPage;
	
	/**
	 * Open Hotel page
	 */
	public void openHotelPage(){
		openHotelPage.click();
	}
	
}
