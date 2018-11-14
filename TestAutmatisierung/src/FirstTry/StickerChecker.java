package FirstTry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StickerChecker{
	
	// Task 8
	
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

	public static void main(String[] args) {
		StickerChecker stickerChecker = new StickerChecker();
		stickerChecker.testStickerNumber();
	}

	    public void testStickerNumber(){
	        System.setProperty("webdriver.chrome.driver", "/Utils/chromedriver.exe");
	        webDriver = new ChromeDriver();
	        wait = new WebDriverWait(webDriver,10);
	    	String url = "http://localhost/litecart/en/";
	    	List<WebElement> images = new ArrayList<WebElement>();
	    	
	    	// Step 1 "Go the page"
	    	webDriver.get(url);
	    	
	    	// Step 2 "Get number of images to check"
	    	images = webDriver.findElements(By.cssSelector(".image-wrapper"));
	    	
	    	// Step 3 "Check the number of stickers of each image"
	    	for(int i = 0; i < images.size(); i++) {
	    		Assert.assertTrue(images.get(i).findElements(By.cssSelector(".sticker")).size()<2, "The image has more than 1 sticker");
	    	}	
	    	
	    	webDriver.quit();
	        webDriver = null;
	    }
	    
	}



