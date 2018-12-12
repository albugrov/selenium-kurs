package FirstTry;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartTest{
	
	// Task 13
	
    WebDriver webDriver;
    WebDriverWait wait;
    String url = "http://localhost/litecart/en/";
    
	private static Function<WebDriver,WebElement> elementLocated(final By locator) {
		return new Function<WebDriver, WebElement>() {
		@Override
		public WebElement apply(WebDriver driver) {
		return driver.findElement(locator);
		}
		};
	}
	
	public void addProductToCart(String productNumber) {
        webDriver.get(url);
    	webDriver.findElement(By.cssSelector("#box-most-popular ul .product:nth-child(" + productNumber + ") .link")).click();
    	webDriver.findElement(By.name("add_cart_product")).click();
    	wait.until(ExpectedConditions.textToBe(By.cssSelector("#cart .quantity"), productNumber));
		
	}

	public void goToCart() {
    	webDriver.get(url);	    	
    	webDriver.findElement(By.cssSelector("#cart .link")).click();
	}
	
	public void removeProductFromCart(Integer numberOfProducts, boolean clickOnImage) {
		if (clickOnImage) webDriver.findElement(By.cssSelector("#box-checkout-cart > ul > li:nth-child(1) > a")).click();
    	webDriver.findElement(By.name("remove_cart_item")).click();
    	wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#box-checkout-summary td[style=\"text-align: center;\"]"), numberOfProducts));		
	}
	
	public static void main(String[] args) {
		CartTest cartTest = new CartTest();
		cartTest.testProductAdd();
	}
	
	    public void testProductAdd(){
	        System.setProperty("webdriver.chrome.driver", "/Utils/chromedriver.exe");
	        webDriver = new ChromeDriver();
	        wait = new WebDriverWait(webDriver,10);

	        
	        // Step "Add products to cart"	        
	
	        addProductToCart("1");
	        addProductToCart("2");
	        addProductToCart("3");
	        
	        // Step "Go To Cart"
	        
	        goToCart();

	        // Step "Remove three added products from cart"
	        
	        removeProductFromCart(2, true);
	        removeProductFromCart(1, true);
	        removeProductFromCart(0, false);
	    	
	        
	        webDriver.quit();
	        webDriver = null;
	    }
	}



