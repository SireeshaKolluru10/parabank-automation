package com.parabank.tests;

import com.parabank.utilities.APIUtil;
import com.parabank.utilities.Constants;
import com.parabank.utilities.RetryAnalyser;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest {

    private static final Logger log =
        LoggerFactory.getLogger(APITest.class);

    // Test 1 — Verify login API returns 200 and correct customer
    @Test(priority = 1,
          description = "Verify login API returns 200 and correct customer data",
          retryAnalyzer = RetryAnalyser.class)
    public void verifyLoginAPI() {
        Response response = APIUtil.get(
            "/login/"
            + Constants.VALID_USERNAME + "/"
            + Constants.VALID_PASSWORD);

        APIUtil.assertStatusCode(response, 200);

        long responseTime = response.getTime();
        Assert.assertTrue(responseTime < 10000,
            "API response time exceeded 10 seconds");

        Assert.assertEquals(
            response.jsonPath().getString("id"),
            Constants.CUSTOMER_ID,
            "Customer ID does not match");
        Assert.assertEquals(
            response.jsonPath().getString("firstName"),
            Constants.CUSTOMER_FIRST_NAME,
            "First name does not match");
        Assert.assertEquals(
            response.jsonPath().getString("lastName"),
            Constants.CUSTOMER_LAST_NAME,
            "Last name does not match");
    }

    // Test 2 — Verify get accounts returns correct accounts
    @Test(priority = 2,
          description = "Verify get accounts API returns accounts list",
          retryAnalyzer = RetryAnalyser.class)
    public void verifyGetAccountsAPI() {
        Response response = APIUtil.get(
            "/customers/"
            + Constants.CUSTOMER_ID
            + "/accounts");

        APIUtil.assertStatusCode(response, 200);

        int accountCount = response.jsonPath()
            .getList("$").size();
        Assert.assertTrue(accountCount > 0,
            "No accounts returned for customer");

        log.info("Total accounts found: {}", accountCount);

        String firstAccountId =
            response.jsonPath().getString("[0].id");
        Assert.assertNotNull(firstAccountId,
            "First account ID is null");
    }

    // Test 3 — Verify invalid login returns 400
    @Test(priority = 3,
          description = "Verify invalid login API returns 400",
          retryAnalyzer = RetryAnalyser.class)
    public void verifyInvalidLoginAPI() {
        Response response = APIUtil.get(
            "/login/wrongUser/wrongPass");

        Assert.assertEquals(
            response.getStatusCode(), 400,
            "Invalid login should return 400");
    }

    // Test 4 — Verify get account details API returns correct data
    @Test(priority = 4,
          description = "Verify get account details API returns correct data",
          retryAnalyzer = RetryAnalyser.class)
    public void verifyGetAccountDetailsAPI() {
        Response response = APIUtil.get(
            "/accounts/"
            + Constants.PRIMARY_ACCOUNT_ID);

        APIUtil.assertStatusCode(response, 200);

        Assert.assertEquals(
            response.jsonPath().getString("id"),
            Constants.PRIMARY_ACCOUNT_ID,
            "Account ID does not match");
        Assert.assertEquals(
            response.jsonPath().getString("customerId"),
            Constants.CUSTOMER_ID,
            "Customer ID does not match");
        Assert.assertNotNull(
            response.jsonPath().getString("type"),
            "Account type is null");
        Assert.assertNotNull(
            response.jsonPath().getString("balance"),
            "Balance is null");

        log.info("Account details — type: {}, balance: {}",
            response.jsonPath().getString("type"),
            response.jsonPath().getString("balance"));
    }

    // Test 5 — Verify customer has expected number of accounts
    @Test(priority = 5,
          description = "Verify customer has expected number of accounts",
          retryAnalyzer = RetryAnalyser.class)
    public void verifyCustomerAccountsCountAPI() {
        Response response = APIUtil.get(
            "/customers/"
            + Constants.CUSTOMER_ID
            + "/accounts");

        APIUtil.assertStatusCode(response, 200);

        int accountCount = response.jsonPath()
            .getList("$").size();

        Assert.assertTrue(accountCount > 0,
            "Customer should have at least one account");
    }
}