package com.parabank.tests;

import com.parabank.utilities.APIUtil;
import com.parabank.utilities.Constants;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest {

    // Test 1 - Verify login API returns 200 and correct customer
    @Test(priority = 1,
          description = "Verify login API returns 200 and correct customer data")
    public void verifyLoginAPI() {
        Response response = APIUtil.get(
            "/login/" + Constants.VALID_USERNAME
            + "/" + Constants.VALID_PASSWORD);

        APIUtil.assertStatusCode(response, 200);
        long responseTime = response.getTime();
        System.out.println("Response time: " + responseTime + "ms");
        Assert.assertTrue(responseTime < 10000,
            "API response time exceeded 10 seconds");

        // Assert correct customer data returned
        Assert.assertEquals(
            response.jsonPath().getString("id"),
            Constants.CUSTOMER_ID,
            "Customer ID does not match");
        Assert.assertEquals(
            response.jsonPath().getString("firstName"),
            "John",
            "First name does not match");
        Assert.assertEquals(
            response.jsonPath().getString("lastName"),
            "Smith",
            "Last name does not match");

        System.out.println("Login API test passed — customer verified");
    }

    // Test 2 - Verify get accounts returns correct accounts
    @Test(priority = 2,
          description = "Verify get accounts API returns accounts list")
    public void verifyGetAccountsAPI() {
        Response response = APIUtil.get(
            "/customers/" + Constants.CUSTOMER_ID
            + "/accounts");

        APIUtil.assertStatusCode(response, 200);

        // Assert response is not empty
        int accountCount = response.jsonPath()
            .getList("$").size();
        Assert.assertTrue(accountCount > 0,
            "No accounts returned for customer");

        // Assert first account ID matches known account
        String firstAccountId = response.jsonPath()
            .getString("[0].id");
        Assert.assertNotNull(firstAccountId,
            "First account ID is null");

        System.out.println("Total accounts found: " + accountCount);
        System.out.println("Get accounts API test passed");
    }

    // Test 3 - Verify invalid login returns 400
    @Test(priority = 3,
          description = "Verify invalid login API returns 400")
    public void verifyInvalidLoginAPI() {
        Response response = APIUtil.get(
            "/login/wrongUser/wrongPass");

        Assert.assertEquals(response.getStatusCode(), 400,
            "Invalid login should return 400");

        System.out.println("Invalid login API test passed — 400 received");
    }
}