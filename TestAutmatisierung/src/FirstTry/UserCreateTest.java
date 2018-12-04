package FirstTry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;


public class UserCreateTest{
	
	// Task 11
	
    WebDriver webDriver;
    WebDriverWait wait;
    
	private static Function<WebDriver,WebElement> elementLocated(final By locator) {
		return new Function<WebDriver, WebElement>() {
		@Override
		public WebElement apply(WebDriver driver) {
		return driver.findElement(locator);
		}
		};
	}
	
	public void logout() {
		webDriver.findElement(By.cssSelector("a[href=\"http://localhost/litecart/en/logout\"]")).click();		
	}
	
	public void login(String email, String password) {
        webDriver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys(email);
        webDriver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys(password);
        webDriver.findElement(By.cssSelector("button[type=\"submit\"][name=\"login\"]")).click();
        
	}
	
	public static void main(String[] args) {
		UserCreateTest userCreateTest = new UserCreateTest();
		userCreateTest.testUserCreate();
	}

	    public void testUserCreate(){
	        System.setProperty("webdriver.chrome.driver", "/Utils/chromedriver.exe");
	        webDriver = new ChromeDriver();
	        wait = new WebDriverWait(webDriver,10);
	    	String url = "http://localhost/litecart/en/";
	    	
	    	// Step 1 "Go to the create user page"
	    	
	    	webDriver.get(url);
	    	webDriver.findElement(By.cssSelector("#box-account-login a[href*=create_account]")).click();
	    	
	    	// Step 2 "Fill the form"
	        
	    	Long millis = System.currentTimeMillis();
	    	String email = millis.toString() + "@email.ru";
	    	String password = "password";
	    	
	        webDriver.findElement(By.cssSelector("input[name=\"firstname\"]")).sendKeys("Firstname");
	        webDriver.findElement(By.cssSelector("input[name=\"lastname\"]")).sendKeys("Lastname");
	        webDriver.findElement(By.cssSelector("input[name=\"address1\"]")).sendKeys("Address 1");
	        webDriver.findElement(By.cssSelector("input[name=\"postcode\"]")).sendKeys("12345");
	        webDriver.findElement(By.cssSelector("input[name=\"city\"]")).sendKeys("Orlando");
	        webDriver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys(email);
	        webDriver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys("password");
	        webDriver.findElement(By.cssSelector("input[name=\"confirmed_password\"]")).sendKeys(password);
	        webDriver.findElement(By.cssSelector("input[name=\"phone\"]")).click();
	        webDriver.findElement(By.cssSelector("input[name=\"phone\"]")).sendKeys("+17894562133");	        
	        webDriver.findElement(By.cssSelector(".select2-selection__arrow[role=\"presentation\"]")).click();	        	        
	        Select drpCountry = new Select(webDriver.findElement(By.name("country_code")));
			drpCountry.selectByVisibleText("United States");
			webDriver.findElement(By.cssSelector("select[name=zone_code")).click();
			Select drpZone = new Select(webDriver.findElement(By.cssSelector("select[name=zone_code")));
			drpZone.selectByVisibleText("Alabama");
	        webDriver.findElement(By.cssSelector("button[name=create_account]")).click();
	        
	        // Step 3 "Logout and login"
	     
	        logout();
	        login(email, password);
	        logout();
	       	        	    	
	    	webDriver.quit();
	        webDriver = null;
	    }
	    
	}



