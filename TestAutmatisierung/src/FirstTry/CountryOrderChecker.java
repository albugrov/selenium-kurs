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

public class CountryOrderChecker{
	
	// Task 9
	
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
		CountryOrderChecker countryOrderChecker = new CountryOrderChecker();
		countryOrderChecker.testSortOfCountries();
	}

	    public void testSortOfCountries(){
 	        System.setProperty("webdriver.chrome.driver", "/Utils/chromedriver.exe");
	        webDriver = new ChromeDriver();
	        wait = new WebDriverWait(webDriver,10);
	    	String url = "http://localhost/litecart/admin/?app=countries&doc=countries";
	    	List<WebElement> countries = new ArrayList<WebElement>();
	    	List<WebElement> zones = new ArrayList<WebElement>();
	    	List<String> countryNamesSorted = new ArrayList<String>();
	    	List<String> countryNames = new ArrayList<String>();
	    	List<Integer> countryZonesIndex = new ArrayList<Integer>();
	    	String countryTemp;
	    	Integer zoneTemp;
	    	WebElement countryRow;
	    		    	
	    	// Step 1 "Login"
	        
	        webDriver.get(url);
	        webDriver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("admin");
	        webDriver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys("admin");
	        webDriver.findElement(By.cssSelector("#box-login > form > div.footer > button")).click();
	        
	    	// Step 2 "Go the page"
	        
	    	webDriver.get(url);
	    	
	    	// Step 3 "Check the order of countries"
	    	
	    	countries = webDriver.findElements(By.cssSelector("form[name=countries_form] tr td:nth-child(5)"));
	    	
	    	for(int i = 0; i < countries.size(); i++) {
	    		countryTemp = countries.get(i).getAttribute("innerText");
	    		countryNames.add(countryTemp);
	    		countryNamesSorted.add(countryTemp);
	    	}
	    	countryNamesSorted.sort(String.CASE_INSENSITIVE_ORDER);
	    	
	    	Assert.assertEquals(countryNames,countryNamesSorted);
	    	
	    	countryNames.clear();
	    	countryNamesSorted.clear();
	    	
	    	// Step 4 "Check the order of zones on the the counry page"

	    	for(int i = 2; i < countries.size(); i++) {
	    		countryRow = webDriver.findElement(By.cssSelector("form[name=countries_form]  .row:nth-child(" + i + ")"));
	    		zoneTemp = Integer.valueOf(countryRow.findElement(By.cssSelector("td:nth-child(6)")).getText());
	    		if(zoneTemp>1){
	    			countryZonesIndex.add(i);
	    		}
	    	}
	    	
	    	for(int i = 0; i < countryZonesIndex.size(); i++) {
	    		countryRow = webDriver.findElement(By.cssSelector("form[name=countries_form]  .row:nth-child(" + countryZonesIndex.get(i) + ")"));
	    		System.out.println(countryRow.getText());
	    		countryRow.findElement(By.cssSelector("td:nth-child(5) a[href]")).click();
	    		countries = webDriver.findElements(By.cssSelector("#table-zones tbody tr td input[type='hidden'][name*='name']"));
	    		for(int j = 0; j < countries.size(); j++) {
	    			String co = countries.get(j).getAttribute("defaultValue");
	    			countryNames.add(co);
	    			countryNamesSorted.add(co);
	    		}
	    		countryNamesSorted.sort(String.CASE_INSENSITIVE_ORDER);		    	
		    	Assert.assertEquals(countryNames,countryNamesSorted);
		    	countryNames.clear();
		    	countryNamesSorted.clear();
		    	webDriver.get(url);
		    	
	    	}
	    	
	    	// Step 5 "Check the order of zones on the zone page"

	    	webDriver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
	    	countries = webDriver.findElements(By.cssSelector("form[name='geo_zones_form'] .row"));
	    	
	    	for(int i = 0; i < countries.size(); i++) {
	    		countryRow = webDriver.findElement(By.cssSelector("form[name='geo_zones_form'] .row"));
	    		countryRow.findElement(By.cssSelector("td:nth-child(3) a[href]")).click();

	    		zones = webDriver.findElements(By.cssSelector("#table-zones tr td:nth-child(3) select[name*='zones'] option[selected='selected']"));
	    		for(int j = 0; j < zones.size(); j++) {
	    			String co = zones.get(j).getText();
	    			countryNames.add(co);
	    			countryNamesSorted.add(co);
	    		}
	    		countryNamesSorted.sort(String.CASE_INSENSITIVE_ORDER);		    	
		    	Assert.assertEquals(countryNames,countryNamesSorted);
		    	countryNames.clear();
		    	countryNamesSorted.clear();
		    	webDriver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
		    	
	    	}

	    	
	    	
	    	webDriver.quit();
	        webDriver = null;
	    }
	    
	}



