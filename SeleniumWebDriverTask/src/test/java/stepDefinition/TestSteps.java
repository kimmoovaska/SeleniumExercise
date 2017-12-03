package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import cucumber.api.java.Before;
import cucumber.api.java.After;

public class TestSteps {
	public static WebDriver driver;
	
@Before
	public void beforeScenario() {
		// Set path for GeckoDriver
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
		// Create a new instance of the Firefox driver
		driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	}

@After
	public void afterScenario() {
		//Close the browser
		driver.quit();
	}

@Given("^User is on website \"([^\"]*)\"$")
	public void user_opens_target_website(String targetUrl) {
		// Open site from target URL (default: "https://www.amazon.com")
		driver.get(targetUrl);
	}

@When("^User writes \"([^\"]*)\" to search field$")
	public void user_writes_to_search_field(String arg1) {
		// Find the search field element by its name
		WebElement searchField = driver.findElement(By.name("field-keywords"));
		
		// Enter "Nikon" to search for and submit the query
		searchField.sendKeys("Nikon");
		searchField.submit();
	}

@When("^User sorts results from highest to lowest by price$")
	public void user_sorts_results_from_highest_to_lowest_by_price() {
		// Wait until page is loaded and dropdown menu is available
		WebDriverWait wait = new WebDriverWait (driver, 30);
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.name("sort")));
		
		//Select dropdown menu item "Price: High to Low"
		Select sortDropdown = new Select(dropdown);
		sortDropdown.selectByVisibleText("Price: High to Low");
	}

@When("^User wants to see all results$")
	public void user_wants_to_see_all_results() {
		//Click "See all sorted results" -link to list all products (not only relevant products!)
		WebElement allProducts = driver.findElement(By.linkText("See all sorted results."));
		allProducts.click();	
	}

@When("^User selects second product$")
	public void user_selects_second_product() {
		// Select second product and click it for details.
		WebElement productItem = driver.findElement(By.cssSelector("#result_1"));
		WebElement detailLink = productItem.findElement(By.className("s-access-detail-page"));
		detailLink.click();
	}

@Then("^User sees text \"([^\"]*)\" in product topic$")
	public void user_sees_text_in_product_topic(String expectedString) {
		// From details check (verify with assert) that product topic contains text “Nikon D3X”
		// Find topic text from correct field
		WebElement textField = driver.findElement(By.id("centerCol"));
		String topic = textField.getText();
		
		// Assert topic text contain expected string
		Assert.assertTrue(topic.contains(expectedString));
	}
}
