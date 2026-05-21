package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.AccountsPage;
import com.parabank.pages.LoginPage;
import com.parabank.pages.TransferFundsPage;
import com.parabank.utilities.Constants;
import com.parabank.utilities.RetryAnalyser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TransferFundsTest extends BaseTest {

    // Helper method — login before each test
    private AccountsPage loginToApplication() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            Constants.VALID_USERNAME,
            Constants.VALID_PASSWORD);
        return new AccountsPage(driver);
    }

    // Test 1 — Valid fund transfer
    @Test(priority = 1,
          description = "Verify successful fund " +
                        "transfer between accounts",
          retryAnalyzer = RetryAnalyser.class)
    public void validFundTransferTest() {
        AccountsPage accountsPage = loginToApplication();
        accountsPage.clickTransferFunds();

        TransferFundsPage transferFundsPage =
            new TransferFundsPage(driver);

        transferFundsPage.transferFunds(
            Constants.VALID_TRANSFER_AMOUNT);

        Assert.assertTrue(
            transferFundsPage.isTransferSuccessful(),
            "Fund transfer was not successful");

        System.out.println("Fund transfer successful — "
            + Constants.VALID_TRANSFER_AMOUNT);
    }

    // Test 2 — Zero amount transfer
    @Test(priority = 2,
          description = "Verify error for zero " +
                        "amount transfer",
          retryAnalyzer = RetryAnalyser.class)
    public void zeroAmountTransferTest() {
        AccountsPage accountsPage = loginToApplication();
        accountsPage.clickTransferFunds();

        TransferFundsPage transferFundsPage =
            new TransferFundsPage(driver);

        transferFundsPage.transferFunds(
            Constants.ZERO_TRANSFER_AMOUNT);

        Assert.assertFalse(
            transferFundsPage.isTransferSuccessful(),
            "Zero amount transfer should not succeed");

        System.out.println(
            "Zero amount transfer test passed");
    }

    // Test 3 — Empty amount transfer
    @Test(priority = 3,
          description = "Verify error for empty " +
                        "amount field",
          retryAnalyzer = RetryAnalyser.class)
    public void emptyAmountTransferTest() {
        AccountsPage accountsPage = loginToApplication();
        accountsPage.clickTransferFunds();

        TransferFundsPage transferFundsPage =
            new TransferFundsPage(driver);

        transferFundsPage.transferFunds("");

        Assert.assertFalse(
            transferFundsPage.isTransferSuccessful(),
            "Empty amount transfer should not succeed");

        System.out.println(
            "Empty amount transfer test passed");
    }
}