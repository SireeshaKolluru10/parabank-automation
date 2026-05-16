package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.AccountsPage;
import com.parabank.pages.LoginPage;
import com.parabank.utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constants.VALID_USERNAME, Constants.VALID_PASSWORD);

        AccountsPage accountsPage = new AccountsPage(driver);
        Assert.assertTrue(accountsPage.isLogoutDisplayed(),
            "Logout link not displayed — login may have failed");
        Assert.assertEquals(accountsPage.getPageTitle(),
            Constants.ACCOUNTS_PAGE_TITLE,
            "Page title does not match after login");
    }

    @Test(priority = 2, description = "Verify error message with invalid credentials")
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constants.INVALID_USERNAME, Constants.INVALID_PASSWORD);

        Assert.assertTrue(loginPage.isErrorDisplayed(),
            "Error message not displayed for invalid login");
        Assert.assertEquals(loginPage.getErrorMessage(),
            Constants.INVALID_LOGIN_ERROR,
            "Error message text does not match");
    }

    @Test(priority = 3, description = "Verify error message when fields are empty")
    public void emptyFieldsLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
            "Error message not displayed for empty fields");
    }
}