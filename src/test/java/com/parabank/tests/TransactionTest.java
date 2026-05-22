package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.AccountsPage;
import com.parabank.pages.LoginPage;
import com.parabank.pages.TransactionPage;
import com.parabank.utilities.Constants;
import com.parabank.utilities.RetryAnalyser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TransactionTest extends BaseTest {

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
          description = "Verify find transactions " +
                        "by amount returns results",
          retryAnalyzer = RetryAnalyser.class)
    public void findTransactionsByAmountTest() {
        AccountsPage accountsPage =
            loginToApplication();
        accountsPage.clickFindTransactions();

        TransactionPage transactionPage =
            new TransactionPage(driver);

        transactionPage.findTransactionsByAmount(
            Constants.PRIMARY_ACCOUNT_ID,
            Constants.TRANSACTION_AMOUNT);

        Assert.assertTrue(
            transactionPage
                .isTransactionTableDisplayed(),
            "Transaction table not displayed");

        int count = transactionPage
            .getTransactionCount();
        Assert.assertTrue(count > 0,
            "No transactions found for amount "
            + Constants.TRANSACTION_AMOUNT);

        System.out.println(
            "Transactions found: " + count);
    }

    @Test(priority = 2,
          description = "Verify no results for " +
                        "invalid transaction amount",
          retryAnalyzer = RetryAnalyser.class)
    public void findTransactionsInvalidAmountTest() {
        AccountsPage accountsPage =
            loginToApplication();
        accountsPage.clickFindTransactions();

        TransactionPage transactionPage =
            new TransactionPage(driver);

        transactionPage.findTransactionsByAmount(
            Constants.PRIMARY_ACCOUNT_ID,
            "99999.99");

        Assert.assertTrue(
            transactionPage.isTransactionBodyEmpty(),
            "Transactions should not be found " +
            "for invalid amount");

        System.out.println(
            "No transactions found — correct");
    }
}