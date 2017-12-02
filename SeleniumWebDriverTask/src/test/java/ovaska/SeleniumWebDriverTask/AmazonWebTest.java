package ovaska.SeleniumWebDriverTask;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class AmazonWebTest {

	public static void main(String[] args) {
		String targetUrl = args[0];
		// Set path for GeckoDriver
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");

        // Create a new instance of the Firefox driver
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        
        // Open site "https://www.amazon.com"
        driver.get(targetUrl);
        
        // Find the search field element by its name
        WebElement searchField = driver.findElement(By.name("field-keywords"));

        // Enter "Nikon" to search for and submit the query
        searchField.sendKeys("Nikon");
        searchField.submit();
        
        // Sort results from highest price to lowest
        // Wait until page is loaded and dropdown menu is available
        WebDriverWait wait = new WebDriverWait (driver, 30);
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.name("sort")));
        
        //Select dropdown menu item "Price: High to Low"
        Select sortDropdown = new Select(dropdown);
        sortDropdown.selectByVisibleText("Price: High to Low");
        
        // Select second product and click it for details.
        // Selection is made by absolute xpath --> This is not a very clever solution, find better way to sort things out later.
        String xpath1 = "/html/body/div[1]/div[2]/div/div[3]/div[2]/div/div[4]/div[1]/div/ul/li[2]/div/div/div/div[2]/div[2]/div[1]/div[1]/a";
        driver.findElement(By.xpath(xpath1)).click();
        
        // From details check (verify with assert) that product topic contains text “Nikon D3X”
        // Find topic text from correct field
        WebElement textField = driver.findElement(By.cssSelector("#title_feature_div"));
        String topic = textField.getText();
        System.out.println(topic);
        
        // Assert topic text contain expected string
        String expectedString1 = "Nikon D4S";
        String expectedString2 = "Nikon D3X";
        
        // Added try-catch structure to avoid assertion error blocking browser closing.
        
        try {
        	Assert.assertTrue(topic.contains(expectedString1));
        } catch (Throwable e) {
        	System.out.println(e + ": Expected string \"" + expectedString1 + "\" not found!");
        }
        
        try {
        	Assert.assertTrue(topic.contains(expectedString2));
        } catch (Throwable e) {
        	System.out.println(e + ": Expected string \"" + expectedString2 + "\" not found!");
        }

        //Close the browser
        driver.quit();
	}
}
