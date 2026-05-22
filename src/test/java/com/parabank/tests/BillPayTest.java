package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.AccountsPage;
import com.parabank.pages.BillPayPage;
import com.parabank.pages.LoginPage;
import com.parabank.utilities.Constants;
import com.parabank.utilities.FakerUtil;
import com.parabank.utilities.RetryAnalyser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BillPayTest extends BaseTest {

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
          description = "Verify successful bill payment",
          retryAnalyzer = RetryAnalyser.class)
    public void validBillPayTest() {
        AccountsPage accountsPage =
            loginToApplication();
        accountsPage.clickBillPay();

        BillPayPage billPayPage =
            new BillPayPage(driver);

        billPayPage.payBill(
        		FakerUtil.getFirstName()
                + " " + FakerUtil.getLastName(),
            FakerUtil.getAddress(),
            FakerUtil.getCity(),
            FakerUtil.getState(),
            FakerUtil.getZipCode(),
            FakerUtil.getPhone(),
            Constants.SECONDARY_ACCOUNT_ID,
            Constants.BILL_PAY_AMOUNT,
            Constants.BILL_PAY_FROM_ACCOUNT);

        Assert.assertTrue(
            billPayPage.isBillPaySuccessful(),
            "Bill payment was not successful");

        System.out.println(
            "Bill payment successful — "
            + Constants.BILL_PAY_AMOUNT);
    }

    @Test(priority = 2,
          description = "Verify errors for empty " +
                        "bill pay form",
          retryAnalyzer = RetryAnalyser.class)
    public void emptyBillPayTest() {
        AccountsPage accountsPage =
            loginToApplication();
        accountsPage.clickBillPay();

        BillPayPage billPayPage =
            new BillPayPage(driver);
        billPayPage.clickSendPayment();

        Assert.assertTrue(
            billPayPage.isPayeeNameErrorDisplayed(),
            "Payee name error not displayed");
        Assert.assertEquals(
            billPayPage.getPayeeNameError(),
            Constants.PAYEE_NAME_REQUIRED,
            "Payee name error text mismatch");

        System.out.println(
            "Empty bill pay validation passed");
    }
}