package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountsPage extends BasePage {

	@FindBy(xpath = "//h1[@class='title']")
	private WebElement pageTitle;

	@FindBy(linkText = "Log Out")
	private WebElement logoutLink;

	public AccountsPage(WebDriver driver) {
		super(driver);
	}

	public String getPageTitle() {
		return getText(pageTitle);
	}

	public boolean isLogoutDisplayed() {
		return isDisplayed(logoutLink);
	}
}