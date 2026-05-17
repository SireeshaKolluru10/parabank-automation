package com.parabank.base;

import com.parabank.utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest {

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {
	    WebDriverManager.chromedriver().setup();
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("--start-maximized");

	    // Run headless on CI pipeline
	    String headless = System.getProperty("headless", "false");
	    if (headless.equals("true")) {
	        options.addArguments("--headless");
	        options.addArguments("--no-sandbox");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--window-size=1920,1080");
	    }

	    driver = new ChromeDriver(options);
	    driver.manage().timeouts()
	        .implicitlyWait(Duration.ofSeconds(
	            ConfigReader.getInt("implicitWait")));
	    driver.get(ConfigReader.get("baseUrl"));
	}
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}