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

    // Helper method
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
          description = "Register user via UI " +
                        "then verify login via API",
          retryAnalyzer = RetryAnalyser.class)
    public void registerViaUIVerifyViaAPITest()
            throws InterruptedException {

        // Step 1 — Generate credentials
        String username = FakerUtil.getUsername();
        String password = FakerUtil.getPassword();

        System.out.println(
            "Registering user: " + username);

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

        System.out.println(
            "UI registration successful: " + username);

        // Step 3 — Verify via API
        // Small wait for system to process
        Thread.sleep(2000);

        Response apiResponse = APIUtil.get(
            "/login/" + username + "/" + password);

        System.out.println(
            "API login status: "
            + apiResponse.getStatusCode());
        System.out.println(
            "API response: "
            + apiResponse.getBody().asString());

        // Assert user exists in system via API
        Assert.assertEquals(
            apiResponse.getStatusCode(), 200,
            "API login failed for newly " +
            "registered user — user not found " +
            "in system after UI registration");

        System.out.println(
            "Cross layer validation passed — " +
            "user registered via UI verified via API");
    }

    // Cross Layer Test 2
    // Transfer funds via UI then verify via API
    @Test(priority = 2,
    	      description = "Transfer funds via UI " +
    	                    "then verify transaction via API",
    	      retryAnalyzer = RetryAnalyser.class)
    	public void transferViaUIVerifyViaAPITest() {

    	    // Step 1 — Perform transfer via UI
    	    AccountsPage accountsPage =
    	        loginToApplication();
    	    accountsPage.clickTransferFunds();

    	    TransferFundsPage transferFundsPage =
    	        new TransferFundsPage(driver);

    	    // Transfer from 12345 to 13344
    	    // these are different accounts
    	    transferFundsPage.transferFunds(
    	        Constants.VALID_TRANSFER_AMOUNT,
    	        Constants.PRIMARY_ACCOUNT_ID,
    	        Constants.BILL_PAY_FROM_ACCOUNT);

    	    Assert.assertTrue(
    	        transferFundsPage.isTransferSuccessful(),
    	        "UI fund transfer failed");

    	    System.out.println(
    	        "UI transfer successful — "
    	        + Constants.VALID_TRANSFER_AMOUNT);

    	    // Step 2 — Verify transaction exists via API
    	    // Check transactions for FROM account
    	    Response response = APIUtil.get(
    	        "/accounts/"
    	        + Constants.PRIMARY_ACCOUNT_ID
    	        + "/transactions");

    	    APIUtil.assertStatusCode(response, 200);

    	    // Assert transactions list is not empty
    	    int transactionCount = response
    	        .jsonPath()
    	        .getList("$").size();

    	    Assert.assertTrue(transactionCount > 0,
    	        "No transactions found via API " +
    	        "after UI transfer");

    	    System.out.println(
    	        "Cross layer validation passed — "
    	        + transactionCount
    	        + " transactions found via API "
    	        + "after UI transfer");
    	}
}