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

    private AccountsPage loginToApplication() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constants.VALID_USERNAME, Constants.VALID_PASSWORD);
        return new AccountsPage(driver);
    }

    @Test(
        priority = 1,
        description = "Verify successful fund transfer between accounts",
        retryAnalyzer = RetryAnalyser.class
    )
    public void validFundTransferTest() {
        AccountsPage accountsPage = loginToApplication();
        accountsPage.clickTransferFunds();

        TransferFundsPage transferFundsPage = new TransferFundsPage(driver);
        transferFundsPage.transferFunds(
            Constants.VALID_TRANSFER_AMOUNT,
            Constants.PRIMARY_ACCOUNT_ID,
            Constants.SECONDARY_ACCOUNT_ID
        );

        Assert.assertTrue(
            transferFundsPage.isTransferSuccessful(),
            "Fund transfer was not successful"
        );
    }

    @Test(
        priority = 2,
        description = "BUG-001 — Zero amount transfer should be rejected",
        retryAnalyzer = RetryAnalyser.class,
        enabled = false
    )
    public void zeroAmountTransferTest() {
        AccountsPage accountsPage = loginToApplication();
        accountsPage.clickTransferFunds();

        TransferFundsPage transferFundsPage = new TransferFundsPage(driver);
        transferFundsPage.transferFunds(
            Constants.ZERO_TRANSFER_AMOUNT,
            Constants.PRIMARY_ACCOUNT_ID,
            Constants.SECONDARY_ACCOUNT_ID
        );

        // BUG-001: Parabank allows zero amount transfers — should be rejected.
        // Disabled until fix is deployed. Tracked in BugReport.md.
        Assert.assertFalse(
            transferFundsPage.isTransferSuccessful(),
            "BUG-001: Zero amount transfer should not be allowed"
        );
    }

    @Test(
        priority = 3,
        description = "Verify error when transfer amount field is empty",
        retryAnalyzer = RetryAnalyser.class
    )
    public void emptyAmountTransferTest() {
        AccountsPage accountsPage = loginToApplication();
        accountsPage.clickTransferFunds();

        TransferFundsPage transferFundsPage = new TransferFundsPage(driver);
        transferFundsPage.transferFunds(
            "",
            Constants.PRIMARY_ACCOUNT_ID,
            Constants.SECONDARY_ACCOUNT_ID
        );

        Assert.assertFalse(
            transferFundsPage.isTransferSuccessful(),
            "Empty amount transfer should not succeed"
        );
    }
}