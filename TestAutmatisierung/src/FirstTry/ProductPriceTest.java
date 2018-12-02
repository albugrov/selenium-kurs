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

public class ProductPriceTest{
	
	// Task 10
	
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
	enum Color 
	{ 
	    RED, GREY, UNKNOW; 
	}
	
	public static Color defineColor(String rgba) {
		String rgbaValue = rgba.substring(5, rgba.length()-1);
		String[] rgbaValues = rgbaValue.split(", ");
		List<Integer> rgbaNumberValues = new ArrayList<Integer>();
		for (int i = 0; i<rgbaValues.length; i++) {
			rgbaNumberValues.add(Integer.parseInt(rgbaValues[i]));
		}
		if (rgbaNumberValues.get(0) > 0 && rgbaNumberValues.get(1) == 0 && rgbaNumberValues.get(2) == 0) {
			return Color.RED;
		}
		if (rgbaNumberValues.get(0) == rgbaNumberValues.get(1) && rgbaNumberValues.get(1) == rgbaNumberValues.get(2)) {
			return Color.GREY;
		}
		return Color.UNKNOW;
	}
	
	public static Double convertFontSize(String fontSize) {
		String temp = fontSize.replace("px", "");
		return Double.parseDouble(temp);
	}

	public static void main(String[] args) {
		ProductPriceTest productPriceTest = new ProductPriceTest();
		productPriceTest.testProductPageTitle();
	}

	    public void testProductPageTitle(){
	        System.setProperty("webdriver.chrome.driver", "/Utils/chromedriver.exe");
	        webDriver = new ChromeDriver();
	        wait = new WebDriverWait(webDriver,10);
	    	String url = "http://localhost/litecart/en/";
	    	List<WebElement> images = new ArrayList<WebElement>();
	    	WebElement product;
	    	String lineThrough = "line-through";
	    	String bold = "700";
	    	
	    	// Step 1 "Collect test data"
	    	
	    	webDriver.get(url);
	    	product = webDriver.findElement(By.cssSelector("#box-campaigns ul .product:nth-child(1) .link"));
	    	
	    	String productNameImage = product.getAttribute("title");
	    	String regularPriceImage = product.findElement(By.cssSelector("s.regular-price")).getAttribute("innerText");
	    	String campaignPriceImage = product.findElement(By.cssSelector("strong.campaign-price")).getAttribute("innerText");
	    	String rgbaCampainPriceImage = webDriver.findElement(By.cssSelector("#box-campaigns strong.campaign-price")).getCssValue("color");
	    	String fontSizeCampainPriceImage = webDriver.findElement(By.cssSelector("#box-campaigns strong.campaign-price")).getCssValue("font-size");
	    	String fontSizeRegularPriceImage = product.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
	    	
	    	product.click();
	    	String productNamePage = webDriver.findElement(By.cssSelector("#box-product .title")).getText();
	    	String regularPriceProductPage = webDriver.findElement(By.cssSelector("#box-product s.regular-price")).getAttribute("innerText");
	    	String campaignPriceProductPage = webDriver.findElement(By.cssSelector("#box-product strong.campaign-price")).getAttribute("innerText");
	    	String rgbaRegularPricePage = webDriver.findElement(By.cssSelector("#box-product s.regular-price")).getCssValue("color");
	    	String decorationLinergbaRegularPricePage = webDriver.findElement(By.cssSelector("#box-product s.regular-price")).getCssValue("text-decoration-line");
	    	String rgbaCampainPricePage = webDriver.findElement(By.cssSelector("#box-product strong.campaign-price")).getCssValue("color");
	    	String fontWeightCampainPricePage = webDriver.findElement(By.cssSelector("#box-product strong.campaign-price")).getCssValue("font-weight");
	    	String fontSizeCampainPricePage = webDriver.findElement(By.cssSelector("#box-product strong.campaign-price")).getCssValue("font-size");
	    	String fontSizeRegularPricePage = webDriver.findElement(By.cssSelector("#box-product s.regular-price")).getCssValue("font-size");
	    	
	    	// Step 2 "Test product name on the product page"
	    	
	    	Assert.assertEquals(productNameImage, productNamePage);
	    	
	    	// Step 3 "Compare prices of image and product page"
	    	
	    	Assert.assertEquals(regularPriceImage, regularPriceProductPage);
	    	Assert.assertEquals(campaignPriceImage, campaignPriceProductPage);
	    	
	    	// Step 4 "Style test of the regular price"
	    	
	    	Assert.assertEquals(decorationLinergbaRegularPricePage, lineThrough);
	    	Assert.assertEquals(defineColor(rgbaRegularPricePage), Color.GREY);
	    	
	    	// Step 5 "Style test of the campaign price"	    	    	
	    	
	    	Assert.assertEquals(defineColor(rgbaCampainPriceImage), Color.RED);
	    	Assert.assertEquals(defineColor(rgbaCampainPricePage), Color.RED);
	    	Assert.assertEquals(fontWeightCampainPricePage, bold);
	    	
	    	// Step 6 "Font-size test"
	    	
	    	Assert.assertTrue(convertFontSize(fontSizeCampainPricePage)>convertFontSize(fontSizeRegularPricePage));
	    	Assert.assertTrue(convertFontSize(fontSizeCampainPriceImage)>convertFontSize(fontSizeRegularPriceImage));
	    	
	    	webDriver.quit();
	        webDriver = null;
	    }
	    
	}



