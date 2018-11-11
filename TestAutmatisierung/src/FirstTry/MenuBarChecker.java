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

public class MenuBarChecker{
	
	// Task 7
	
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
		MenuBarChecker menuBarChecker = new MenuBarChecker();
		menuBarChecker.testMenuBar();
	}

	    public void testMenuBar(){
	        System.setProperty("webdriver.chrome.driver", "/Utils/chromedriver.exe");
	        webDriver = new ChromeDriver();
	        wait = new WebDriverWait(webDriver,10);
	    	String url = "http://localhost/litecart/admin/";
	    	List<String> leftColumnTitels = new ArrayList<String>();
	        List<WebElement> leftcolumn = new ArrayList<WebElement>();
	        List<WebElement> temp = new ArrayList<WebElement>();
	        String pageTitel;
	        String name;
	        String cssMenuSelectorTemp;
	        
	        Map<String, String> columnSubTitleTitle= new TreeMap<String, String>();
	        columnSubTitleTitle.put("Catalog","Catalog");
	        columnSubTitleTitle.put("Product Groups","Product Groups");
	        columnSubTitleTitle.put("Option Groups","Option Groups");
	        columnSubTitleTitle.put("Manufacturers","Manufacturers");
	        columnSubTitleTitle.put("Suppliers","Suppliers");
	        columnSubTitleTitle.put("Delivery Statuses","Delivery Statuses");
	        columnSubTitleTitle.put("Sold Out Statuses","Sold Out Statuses");
	        columnSubTitleTitle.put("Quantity Units","Quantity Units");
	        columnSubTitleTitle.put("CSV Import/Export","CSV Import/Export");
	        columnSubTitleTitle.put("countries&']","Countries");
	        columnSubTitleTitle.put("currencies&']","Currencies");
	        columnSubTitleTitle.put("Customers","Customers");
	        columnSubTitleTitle.put("CSV Import/Export","CSV Import/Export");
	        columnSubTitleTitle.put("Newsletter","Newsletter");
	        columnSubTitleTitle.put("geo_zones&']","Geo Zones");
	        columnSubTitleTitle.put("Languages","Languages");
	        columnSubTitleTitle.put("Storage Encoding","Storage Encoding");
	        columnSubTitleTitle.put("Background Jobs","Job Modules");
	        columnSubTitleTitle.put("Customer","Customer Modules");
	        columnSubTitleTitle.put("Shipping","Shipping Modules");
	        columnSubTitleTitle.put("Payment","Payment Modules");
	        columnSubTitleTitle.put("Order Total","Order Total Modules");
	        columnSubTitleTitle.put("Order Success","Order Success Modules");
	        columnSubTitleTitle.put("Order Action","Order Action Modules");
	        columnSubTitleTitle.put("Orders","Orders");
	        columnSubTitleTitle.put("Order Statuses","Order Statuses");
	        columnSubTitleTitle.put("pages&']","Pages");
	        columnSubTitleTitle.put("Monthly Sales","Monthly Sales");
	        columnSubTitleTitle.put("Most Sold Products","Most Sold Products");
	        columnSubTitleTitle.put("Most Shopping Customers","Most Shopping Customers");
	        columnSubTitleTitle.put("Store Info","Settings");
	        columnSubTitleTitle.put("Defaults","Settings");
	        columnSubTitleTitle.put("General","Settings");
	        columnSubTitleTitle.put("Listings","Settings");
	        columnSubTitleTitle.put("Images","Settings");
	        columnSubTitleTitle.put("Checkout","Settings");
	        columnSubTitleTitle.put("Advanced","Settings");
	        columnSubTitleTitle.put("Security","Settings");
	        columnSubTitleTitle.put("slides&']","Slides");
	        columnSubTitleTitle.put("Tax Classes","Tax Classes");
	        columnSubTitleTitle.put("Tax Rates","Tax Rates");
	        columnSubTitleTitle.put("Search Translations","Search Translations");
	        columnSubTitleTitle.put("Scan Files","Scan Files For Translations");
	        columnSubTitleTitle.put("CSV Import/Export","CSV Import/Export");
	        columnSubTitleTitle.put("users&']","Users");
	        columnSubTitleTitle.put("vQmods","vQmods");
	    	
	        // Step "Login"
	        webDriver.get("http://localhost/litecart/admin/");
	        webDriver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("admin");
	        webDriver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys("admin");
	        webDriver.findElement(By.cssSelector("#box-login > form > div.footer > button")).click();
	        
	        //Step "Check fisrt line of menu bar"
        	webDriver.findElement(By.cssSelector("a[href*='http://localhost/litecart/admin/?app=appearance&']")).click();
        	webDriver.findElement(By.cssSelector(".docs li:nth-child(1)")).click();
        	pageTitel = webDriver.findElement(By.cssSelector("#content > h1")).getText();
        	Assert.assertEquals(pageTitel,"Template");
	        webDriver.findElement(By.cssSelector(".docs li:nth-child(2)")).click();
	        pageTitel = webDriver.findElement(By.cssSelector("#content > h1")).getText();
	        Assert.assertEquals(pageTitel, "Logotype");
	        
	        // Step "Check menu bar"
	        leftcolumn = webDriver.findElements(By.cssSelector("#app- >a >span.name"));	        
	        
        	for(int i=1;i<leftcolumn.size();i++) {
        		leftColumnTitels.add(leftcolumn.get(i).getText());
        	}
	   
	        
	        for(int i = 0; i<leftColumnTitels.size(); i++) {
		        name = leftColumnTitels.get(i).toLowerCase().replaceAll("\\s", "_") + "&']";
		        cssMenuSelectorTemp = "a[href*='http://localhost/litecart/admin/?app=" + name;
		        webDriver.findElement(By.cssSelector(cssMenuSelectorTemp)).click();
		        temp = webDriver.findElements(By.cssSelector("#app- > ul > li"));
		        if (temp.size()>0) {
		        	for(int j = 1;j <=temp.size(); j++) {
		        	String columnSubtitleName = webDriver.findElement(By.cssSelector("#app- > ul > li:nth-child(" + j + ")" )).getText();			        	
		        	webDriver.findElement(By.cssSelector("#app- > ul > li:nth-child(" + j + ")" )).click();
		        	pageTitel = webDriver.findElement(By.cssSelector("#content > h1")).getText();
		        	Assert.assertEquals(pageTitel, columnSubTitleTitle.get(columnSubtitleName));
		        	}
		        }
		        else {
		        	pageTitel = webDriver.findElement(By.cssSelector("#content > h1")).getText();
		        	Assert.assertEquals(pageTitel, leftColumnTitels.get(i));
		        }
	        }
	         
	        webDriver.quit();
	        webDriver = null;
	    }
	}



