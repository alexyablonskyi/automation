package automation.base;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import automation.base.*;
import automation.pages.*;
import automation.values.Global;


public class BaseClass {

	protected static WebDriver driver;
	
	
 	@BeforeMethod
	public void driverSetup(){
 		driver = new FirefoxDriver();
 		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
 		driver.manage().window().maximize();
 		driver.get(Global.baseURL);
 	}

	@AfterMethod
	public void tearDown(){
		driver.quit();	
	} 

	
	/*
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

	/*
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
}

