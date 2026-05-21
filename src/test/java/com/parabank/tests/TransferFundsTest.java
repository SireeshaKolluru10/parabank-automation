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
        		Constants.VALID_TRANSFER_AMOUNT,
        	    Constants.PRIMARY_ACCOUNT_ID,
        	    Constants.SECONDARY_ACCOUNT_ID);

        Assert.assertTrue(
            transferFundsPage.isTransferSuccessful(),
            "Fund transfer was not successful");

        System.out.println("Fund transfer successful — "
            + Constants.VALID_TRANSFER_AMOUNT);
    }

    // Test 2 — Zero amount transfer
    @Test(priority = 2,
    	      description = "BUG-001 — Zero amount transfer " +
    	                    "should be rejected",
    	      retryAnalyzer = RetryAnalyser.class,
    	      enabled = false)
    	public void zeroAmountTransferTest() {
    	    AccountsPage accountsPage = loginToApplication();
    	    accountsPage.clickTransferFunds();

    	    TransferFundsPage transferFundsPage =
    	        new TransferFundsPage(driver);

    	    transferFundsPage.transferFunds(
    	        Constants.ZERO_TRANSFER_AMOUNT,
    	        Constants.PRIMARY_ACCOUNT_ID,
    	        Constants.SECONDARY_ACCOUNT_ID);

    	    // BUG-001 — Zero amount transfer should be
    	    // rejected but Parabank allows it.
    	    // Test intentionally fails to flag this defect.
    	    // Raised in BugReport.md
    	    Assert.assertFalse(
    	        transferFundsPage.isTransferSuccessful(),
    	        "BUG-001: Zero amount transfer should " +
    	        "not be allowed");
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

        transferFundsPage.transferFunds(  "",
        	    Constants.PRIMARY_ACCOUNT_ID,
        	    Constants.SECONDARY_ACCOUNT_ID);

        Assert.assertFalse(
            transferFundsPage.isTransferSuccessful(),
            "Empty amount transfer should not succeed");

        System.out.println(
            "Empty amount transfer test passed");
    }
}