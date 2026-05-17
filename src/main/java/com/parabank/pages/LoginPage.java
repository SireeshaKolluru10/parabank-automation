package com.parabank.pages;

import com.parabank.base.BasePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

	@FindBy(name = "username")
	private WebElement usernameField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Log In']")
	private WebElement loginButton;

	@FindBy(xpath = "//*[contains(@class,'error') and string-length(text()) > 0]")
	private WebElement errorMessage;

	@FindBy(xpath = "//h1[@class='title']")
	private WebElement pageTitle;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void enterUsername(String username) {
		type(usernameField, username);
	}

	public void enterPassword(String password) {
		type(passwordField, password);
	}

	public void clickLogin() {
		click(loginButton);
	}

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

	public String getErrorMessage() {
		return getText(errorMessage);
	}

	public boolean isErrorDisplayed() {
	    try {
	        WebDriverWait longWait = new WebDriverWait(
	            driver, Duration.ofSeconds(30));
	        longWait.until(ExpectedConditions
	            .presenceOfElementLocated(
	                By.xpath("//*[contains(@class,'error') " +
	                "and string-length(text()) > 0]")));
	        return true;
	    } catch (Exception e) {
	        System.out.println("Error message not found: "
	            + e.getMessage());
	        return false;
	    }
	}
	public String getPageTitle() {
		return getText(pageTitle);
	}
}