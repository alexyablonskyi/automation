package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import pages.AccountPage;
import pages.DestionationFinderPage;
import pages.HomePage;
import pages.HotelPage;
import pages.SearchResultPage;
import values.Global;

/**
 * @version 1.0.0
 * @author Oleksandr Yablonskyi
 */


public class BaseClass {

	protected static WebDriver driver;
	protected HomePage homePage;
	protected AccountPage accountPage;
	protected DestionationFinderPage destinationFinderPage;
	protected SearchResultPage searchResult;
	protected HotelPage hotelPage;
	protected SoftAssert softAssert;

	
	/**
	 * Wait for Page is loaded (All JavaScript completed)
	 */	
	public static void waitForPageLoad(WebDriver driver) {
	    ExpectedCondition <Boolean> pageLoadCondition = new
	        ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	/**
	 * Wait for AJAX request completed
	 */	
    public static void waitUntilAjaxRequestCompletes() {
        new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
            .pollingEvery(1, TimeUnit.SECONDS).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                            JavascriptExecutor jsExec = (JavascriptExecutor) d;
                            return (Boolean) jsExec.executeScript("return jQuery.active == 0;");
                    }
            });
    }
    
    
	 /**
	  * Wait Methods
	  * @param locator
	  */
    public void waitForElementClickableX(WebElement locator){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
		
	public void waitForElementVisibleX(WebElement locator){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}
	
	/**
	 * Random number method
	 * @return number
	 */
    public int randomWithRange(){
        int range = (1000 - 1) + 1;     
        return (int)(Math.random() * range) + 1;
    }
	
	/**
	 * Connection to Database
	 */	
	protected Connection connect = null;
	Statement stmt;	
	PreparedStatement pstmt = null;
	String tableName = "cities" + randomWithRange();
	
	/**
	 * Create db connection
	 * @return connection
	 */
    public Connection createDataBaseConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{	
		try {			
			String dbClass = "com.mysql.jdbc.Driver";
			Class.forName(dbClass).newInstance();			
			connect = DriverManager.getConnection(Global.sqldb_url, Global.sqldb_uname, Global.sqldb_pass);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
    }
    
    /**
     * Close db connection
     */
    public void closeDataBaseConnection(Connection connect) throws SQLException{
    	if (connect != null) {
			connect.close();
		}
    }

    /**
     * Add rows to table
     * @param connect, list
     */   
    public void addNewRowsToTable(Connection connect, ArrayList<String> list){
		try{
			pstmt = connect.prepareStatement("INSERT INTO cities (id, city_name) VALUES (?, ?)");
			for (int i = 0; i < list.size(); i++){
				pstmt.setInt(1, i);
				pstmt.setString(2, list.get(i));
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
	
	/**
	 * Create table in db
	 * @param connect
	 */	
    public void createTable(Connection connect) throws SQLException{
    	String query = "CREATE TABLE "+ tableName +"("
				+ "id int(5) NOT NULL, "
				+ "city_name VARCHAR(30) NOT NULL, "
				+ "PRIMARY KEY (id) "
				+ ")";
    	
    	pstmt = connect.prepareStatement(query);
    	pstmt.executeUpdate();    	
    }
    
    /**
     * Delete all records from table
     * @param connect
     */
    public void deleteAllRowsFromTable(Connection connect) throws SQLException{
    	String query = "delete from cities";
    	
    	pstmt = connect.prepareStatement(query);
    	pstmt.executeUpdate();    	
    }
    
    
    /**
     * Get all elements from table
     * @return arrayList
     * @param connect
     */
    public ArrayList<String> getAllElementsFromTable(Connection connect) throws SQLException{
    	ArrayList<String> list = new ArrayList<String>();
    	stmt = connect.createStatement();	
    	String query = "select * from cities";
			ResultSet res = stmt.executeQuery(query);			
				while (res.next()) {
					list.add(res.getString(2));					
				}
		return list;	
    }
    
    /**
     * Get count of rows from table
     * @param connect
     * @throws SQLException 
     */
    public int getCountRowsInTable(Connection connect) throws SQLException{
    	int count = 0;
    	stmt = connect.createStatement();
    	String query = "SELECT COUNT(city_name) FROM cities";
    	ResultSet res = stmt.executeQuery(query);		
	    	while (res.next()) {
	    		count = res.getInt(1);
	    	}
    	return count;
    }
    
    
    
    /**
     * Verify if element is present
     * @param xpath of element
     */
    public boolean isElementPresent(String xpath) {
		try {
	      driver.findElement(By.xpath(xpath));
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	}
    
    /**
     * Switch between a windows
     */
    public void switchBeetweanWindows(){	
    	for (String winHandle : driver.getWindowHandles()) {
    	    driver.switchTo().window(winHandle);
    	}
    }
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}	


