package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.AccountsPage;
import com.parabank.pages.HomePage;
import com.parabank.pages.LoginPage;
import com.parabank.pages.RegistrationPage;
import com.parabank.pages.TransferFundsPage;
import com.parabank.utilities.APIUtil;
import com.parabank.utilities.Constants;
import com.parabank.utilities.FakerUtil;
import com.parabank.utilities.RetryAnalyser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CrossLayerTest extends BaseTest {

    // Helper method — move to BaseTest if other test classes also need login
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

    // Cross Layer Test 1
    // Register via UI then verify via API
    @Test(priority = 1,
          description = "Register user via UI then verify login via API",
          retryAnalyzer = RetryAnalyser.class)
    public void registerViaUIVerifyViaAPITest() {

        // Step 1 — Generate credentials
        String username = FakerUtil.getUsername();
        String password = FakerUtil.getPassword();

        // Step 2 — Register via UI
        HomePage homePage = new HomePage(driver);
        homePage.navigateToRegister();

        RegistrationPage registrationPage =
            new RegistrationPage(driver);

        registrationPage.registerUser(
            FakerUtil.getFirstName(),
            FakerUtil.getLastName(),
            FakerUtil.getAddress(),
            FakerUtil.getCity(),
            FakerUtil.getState(),
            FakerUtil.getZipCode(),
            FakerUtil.getPhone(),
            FakerUtil.getSSN(),
            username,
            password);

        Assert.assertTrue(
            registrationPage.isRegistrationSuccessful(),
            "UI Registration failed");

        // Step 3 — Verify via API
        Response apiResponse = APIUtil.get(
            "/login/" + username + "/" + password);

        Assert.assertEquals(
            apiResponse.getStatusCode(), 200,
            "API login failed for newly registered user — " +
            "user not found in system after UI registration");
    }

    // Cross Layer Test 2
    // Transfer funds via UI then verify via API
    @Test(priority = 2,
          description = "Transfer funds via UI then verify transaction via API",
          retryAnalyzer = RetryAnalyser.class)
    public void transferViaUIVerifyViaAPITest() {

        // Step 1 — Perform transfer via UI
        AccountsPage accountsPage =
            loginToApplication();
        accountsPage.clickTransferFunds();

        TransferFundsPage transferFundsPage =
            new TransferFundsPage(driver);

        transferFundsPage.transferFunds(
            Constants.VALID_TRANSFER_AMOUNT,
            Constants.PRIMARY_ACCOUNT_ID,
            Constants.BILL_PAY_FROM_ACCOUNT);

        Assert.assertTrue(
            transferFundsPage.isTransferSuccessful(),
            "UI fund transfer failed");

        // Step 2 — Verify transaction exists via API
        Response response = APIUtil.get(
            "/accounts/"
            + Constants.PRIMARY_ACCOUNT_ID
            + "/transactions");

        APIUtil.assertStatusCode(response, 200);

        int transactionCount = response
            .jsonPath()
            .getList("$").size();

        Assert.assertTrue(transactionCount > 0,
            "No transactions found via API after UI transfer");
    }
}