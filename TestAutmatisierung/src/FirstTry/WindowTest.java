package FirstTry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WindowTest{
	
	// Task 14
	
    WebDriver webDriver;
    WebDriverWait wait;
    String url = "http://localhost/litecart/admin/?app=countries&doc=countries";
    
	private static Function<WebDriver,WebElement> elementLocated(final By locator) {
		return new Function<WebDriver, WebElement>() {
		@Override
		public WebElement apply(WebDriver driver) {
		return driver.findElement(locator);
		}
		};
	}
	
	public void login() {
		webDriver.get(url);
        webDriver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("admin");
        webDriver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys("admin");
        webDriver.findElement(By.cssSelector("#box-login > form > div.footer > button")).click();
	}
	public void openAndCloseNewTab(WebElement el) {
		el.click();
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));    	
    	ArrayList<String> tabs2 = new ArrayList<String> (webDriver.getWindowHandles());
    	webDriver.switchTo().window(tabs2.get(1));
    	webDriver.close();
    	webDriver.switchTo().window(tabs2.get(0));
		
	}

	public static void main(String[] args) {
		WindowTest windowTest = new WindowTest();
		windowTest.testOpenWindow();
	}

	    public void testOpenWindow(){
 	        System.setProperty("webdriver.chrome.driver", "/Utils/chromedriver.exe");
	        webDriver = new ChromeDriver();
	        wait = new WebDriverWait(webDriver,10);
	    	List<WebElement> links = new ArrayList<WebElement>();
	    		    	
	    	// Step 1 "Login"
	        
	        login();
	        
	    	// Step 2 "Go to edit page"
	        
	    	webDriver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
	    	webDriver.findElement(By.cssSelector(".button[href*=\"edit_country\"]")).click();
	    	
	    	// Step 3 "Go to external page"
	    	
            links = webDriver.findElements(By.cssSelector("a[target=\"_blank\"] .fa-external-link"));
	    	for (WebElement el:links) {
	    		openAndCloseNewTab(el);
	    	}
	    	
	    	webDriver.quit();
	        webDriver = null;
	    }
	    
	}



