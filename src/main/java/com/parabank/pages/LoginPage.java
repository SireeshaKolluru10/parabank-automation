package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

	@FindBy(name = "username")
	private WebElement usernameField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Log In']")
	private WebElement loginButton;

	@FindBy(xpath = "//p[@class='error']")
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
	        wait.until(ExpectedConditions
	            .visibilityOf(errorMessage));
	        return errorMessage.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
	public String getPageTitle() {
		return getText(pageTitle);
	}
}