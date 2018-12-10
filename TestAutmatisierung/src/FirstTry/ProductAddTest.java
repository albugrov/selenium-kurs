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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductAddTest{
	
	// Task 12
	
    WebDriver webDriver;
    WebDriverWait wait;
    private String absolutePath = new File("").getAbsolutePath();
    Double random = Math.random();
    String code = random.toString();
    
	private static Function<WebDriver,WebElement> elementLocated(final By locator) {
		return new Function<WebDriver, WebElement>() {
		@Override
		public WebElement apply(WebDriver driver) {
		return driver.findElement(locator);
		}
		};
	}

	
	public void saveForm() {
		webDriver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
	}
	
	public void pause(Integer milliseconds){
	    try {
	        TimeUnit.MILLISECONDS.sleep(milliseconds);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

	public static void main(String[] args) {
		String classpath = System.getProperty("java.class.path");
		System.out.println(new File("").getAbsolutePath());
		ProductAddTest productAddTest = new ProductAddTest();
		productAddTest.testProductAdd();
	}
	

	    public void testProductAdd(){
	        System.setProperty("webdriver.chrome.driver", "/Utils/chromedriver.exe");
	        webDriver = new ChromeDriver();
	        wait = new WebDriverWait(webDriver,10);
	    	String url = "http://localhost/litecart/admin/";
	        String name = code + " test duck";
	        List<WebElement> products = new ArrayList<WebElement>();
	        
	        // Step "Login"
	        
	        webDriver.get(url);
	        webDriver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("admin");
	        webDriver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys("admin");
	        webDriver.findElement(By.cssSelector("#box-login > form > div.footer > button")).click();
	        
	        //Step "Go to add product"
	        
	        webDriver.findElement(By.cssSelector("#app- a[href=\"http://localhost/litecart/admin/?app=catalog&doc=catalog\"]")).click();
	        webDriver.findElement(By.cssSelector(".button[href*=edit_product]")).click();
	        pause(1000);
		    
		    // Step "Fill Information"
		    
		    webDriver.findElement(By.cssSelector("a[href=\"#tab-information\"]")).click();
		    pause(1000);
		    Select manufacrturer = new Select(webDriver.findElement(By.name("manufacturer_id")));
		    manufacrturer.selectByVisibleText("ACME Corp.");
		    webDriver.findElement(By.name("keywords")).sendKeys("product_test");
		    webDriver.findElement(By.name("short_description[en]")).sendKeys("short_description_test");
		    webDriver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("extended_description_test");
		    webDriver.findElement(By.name("meta_description[en]")).sendKeys("meta_description_test");
		    //saveForm();
		    
		   // Step "Fill Price"
		    
		    webDriver.findElement(By.cssSelector("a[href=\"#tab-prices\"]")).click();
		    pause(1000);
		    webDriver.findElement(By.name("purchase_price")).clear();
		    webDriver.findElement(By.name("purchase_price")).sendKeys("100");
		    Select currency = new Select(webDriver.findElement(By.name("purchase_price_currency_code")));
		    currency.selectByVisibleText("Euros");
		    webDriver.findElement(By.name("gross_prices[USD]")).clear();
		    webDriver.findElement(By.name("gross_prices[USD]")).sendKeys("90");		    
		    webDriver.findElement(By.name("gross_prices[EUR]")).clear();
		    webDriver.findElement(By.name("gross_prices[EUR]")).sendKeys("85");
		    //saveForm();
		    
		    // Step "Fill General"
		    
		    webDriver.findElement(By.cssSelector("a[href=\"#tab-general\"]")).click();
		    pause(1000);
	        webDriver.findElement(By.cssSelector("input[type=\"radio\"][value=\"1\"]")).click();
	        webDriver.findElement(By.cssSelector("input[name=\"name\\[en\\]\"]")).sendKeys(name);
	        webDriver.findElement(By.cssSelector("input[name=\"code\"]")).sendKeys(code);
	        webDriver.findElement(By.cssSelector("input[type=\"checkbox\"][value=\"1\"]")).click();
		    webDriver.findElement(By.cssSelector("input[type=\"checkbox\"][value=\"1-3\"]")).click();
		    webDriver.findElement(By.cssSelector("input[type=\"number\"][name=\"quantity\"]")).clear();
		    webDriver.findElement(By.cssSelector("input[type=\"number\"][name=\"quantity\"]")).click();
		    webDriver.findElement(By.cssSelector("input[type=\"number\"][name=\"quantity\"]")).sendKeys("10");
		    webDriver.findElement(By.cssSelector("input[type=\"date\"][name=\"date_valid_from\"]")).sendKeys("01.01.2019");
		    webDriver.findElement(By.cssSelector("input[type=\"date\"][name=\"date_valid_from\"]")).sendKeys("01.01.2019");
		    webDriver.findElement(By.cssSelector("input[type=\"date\"][name=\"date_valid_to\"]")).sendKeys("12.01.2019");
		    webDriver.findElement(By.name("new_images[]")).sendKeys(absolutePath + "//reccources//duck.jpg");		    
		    saveForm();
		    
		    // Step "Test if product was successfully added "
		    
		    products = webDriver.findElements(By.cssSelector(".row td:nth-child(3) a"));
		    
		    boolean productadded = false;
		    for (WebElement el : products) {
		    	if (el.getAttribute("innerText").equals(name)) {
		    		productadded = true;
		    	}
		    }
		    
		    Assert.assertTrue(productadded, "the product wasn't added");

	         
	        webDriver.quit();
	        webDriver = null;
	    }
	}



