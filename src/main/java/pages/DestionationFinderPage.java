package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @version 1.0.0
 * @author Oleksandr Yablonskyi
 */

public class DestionationFinderPage {
	
	WebDriver driver;
	
	public DestionationFinderPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Searching where do you want go
	 */
	@FindBy(xpath=".//*[@id='dsf_search_container']//input[@class='js-autocomplete-locations dsf_search_input']")
	private WebElement whereDoYouWantField;
	
	@FindBy(xpath=".//*[@id='dsf_button']")
	private WebElement searchButton;
	
	@FindBy(xpath=".//*[@id='dsf_search_container']//*[@class='js-autocomplete-suggestions']//*[@data-type='city']")
	private WebElement selectRecommendValues;
	
	/**
	 * Search where do you go
	 * @param city
	 * @throws InterruptedException
	 */
	public void searchWhereDoYouWantGo(String city) throws InterruptedException{
		whereDoYouWantField.sendKeys(city);
		Thread.sleep(1000);
		selectRecommendValues.click();
		Thread.sleep(1000);
		searchButton.click();
	}
	
	/**
	 * Get xpath of list place for staying
	 * @return xpath
	 */
	public String getListOfPlaceForStayingXpath(){
		return ".//*[@id='hotels']//div[@class='dsf_item w33 slick-slide slick-active']";
	}
	
	/**
	 * Get List of places for staying after search
	 * @return count of places that were found according to City
	 */
	public int getCountFoundPlace(){
		List<WebElement> list = driver.findElements(By.xpath(getListOfPlaceForStayingXpath()));
		int count = list.size();
		return count;
	}
	
	
}

