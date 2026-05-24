package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.AccountsPage;
import com.parabank.pages.LoginPage;
import com.parabank.pages.OpenAccountPage;
import com.parabank.utilities.Constants;
import com.parabank.utilities.RetryAnalyser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountTest extends BaseTest {

    private AccountsPage loginToApplication() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            Constants.VALID_USERNAME,
            Constants.VALID_PASSWORD);
        AccountsPage accountsPage =
            new AccountsPage(driver);
        accountsPage.waitForPageToLoad();
        return accountsPage;
    }

    @Test(priority = 1,
          description = "Verify opening a new " +
                        "checking account",
          retryAnalyzer = RetryAnalyser.class)
    public void openCheckingAccountTest() {
        AccountsPage accountsPage =
            loginToApplication();
        accountsPage.clickOpenNewAccount();

        OpenAccountPage openAccountPage =
            new OpenAccountPage(driver);
        openAccountPage.openNewAccount(
            Constants.CHECKING);

        Assert.assertTrue(
            openAccountPage.isAccountOpenedSuccessfully(),
            "Checking account not opened successfully");

        String newAccountId =
            openAccountPage.getNewAccountId();
        Assert.assertNotNull(newAccountId,
            "New account ID is null");
        Assert.assertFalse(newAccountId.isEmpty(),
            "New account ID is empty");

        System.out.println(
            "New checking account opened: "
            + newAccountId);
    }

    @Test(priority = 2,
          description = "Verify opening a new " +
                        "savings account",
          retryAnalyzer = RetryAnalyser.class)
    public void openSavingsAccountTest() {
        AccountsPage accountsPage =
            loginToApplication();
        accountsPage.clickOpenNewAccount();

        OpenAccountPage openAccountPage =
            new OpenAccountPage(driver);
        openAccountPage.openNewAccount(
            Constants.SAVINGS);

        Assert.assertTrue(
            openAccountPage.isAccountOpenedSuccessfully(),
            "Savings account not opened successfully");

        String newAccountId =
            openAccountPage.getNewAccountId();
        Assert.assertNotNull(newAccountId,
            "New account ID is null");

        System.out.println(
            "New savings account opened: "
            + newAccountId);
    }
}