package com.parabank.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected static Properties config = new Properties();
	static {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			config.load(fis);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,
				Duration.ofSeconds(Integer.parseInt((config.getProperty("explicitWait")))));
		PageFactory.initElements(driver, this);
	}

	// Type into any field
	protected void type(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(text);
	}

	// Click any element
	protected void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	// Get text from any element
	protected String getText(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	// Check if element is displayed
	protected boolean isDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}