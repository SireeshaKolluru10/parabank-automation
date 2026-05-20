package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(linkText = "Register")
    private WebElement registerLink;

    @FindBy(linkText = "Log In")
    private WebElement loginLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToRegister() {
        click(registerLink);
    }

    public void clickLogin() {
        click(loginLink);
    }
}