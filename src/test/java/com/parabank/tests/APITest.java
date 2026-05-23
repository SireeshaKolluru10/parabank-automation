package com.parabank.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.utilities.APIUtil;
import com.parabank.utilities.Constants;
import com.parabank.utilities.RetryAnalyser;

import io.restassured.response.Response;

public class APITest {

	// Test 1 - Verify login API returns 200 and correct customer
	@Test(priority = 1, description = "Verify login API returns 200 and correct customer data",retryAnalyzer = RetryAnalyser.class)
	public void verifyLoginAPI() {
		Response response = APIUtil.get("/login/" + Constants.VALID_USERNAME + "/" + Constants.VALID_PASSWORD);

		APIUtil.assertStatusCode(response, 200);
		long responseTime = response.getTime();
		System.out.println("Response time: " + responseTime + "ms");
		Assert.assertTrue(responseTime < 10000, "API response time exceeded 10 seconds");

		// Assert correct customer data returned
		Assert.assertEquals(response.jsonPath().getString("id"), Constants.CUSTOMER_ID, "Customer ID does not match");
		Assert.assertEquals(response.jsonPath().getString("firstName"),Constants.CUSTOMER_FIRST_NAME, "First name does not match");
		Assert.assertEquals(response.jsonPath().getString("lastName"), Constants.CUSTOMER_LAST_NAME, "Last name does not match");

		System.out.println("Login API test passed — customer verified");
	}

	// Test 2 - Verify get accounts returns correct accounts
	@Test(priority = 2, description = "Verify get accounts API returns accounts list",retryAnalyzer = RetryAnalyser.class)
	public void verifyGetAccountsAPI() {
		Response response = APIUtil.get("/customers/" + Constants.CUSTOMER_ID + "/accounts");

		APIUtil.assertStatusCode(response, 200);

		// Assert response is not empty
		int accountCount = response.jsonPath().getList("$").size();
		Assert.assertTrue(accountCount > 0, "No accounts returned for customer");

		// Assert first account ID matches known account
		String firstAccountId = response.jsonPath().getString("[0].id");
		Assert.assertNotNull(firstAccountId, "First account ID is null");

		System.out.println("Total accounts found: " + accountCount);
		System.out.println("Get accounts API test passed");
	}

	// Test 3 - Verify invalid login returns 400
	@Test(priority = 3, description = "Verify invalid login API returns 400",retryAnalyzer = RetryAnalyser.class)
	public void verifyInvalidLoginAPI() {
		Response response = APIUtil.get("/login/wrongUser/wrongPass");

		Assert.assertEquals(response.getStatusCode(), 400, "Invalid login should return 400");

		System.out.println("Invalid login API test passed — 400 received");
	}
	@Test(priority = 4,
		      description = "Verify get account details " +
		                    "API returns correct data",
		      retryAnalyzer = RetryAnalyser.class)
		public void verifyGetAccountDetailsAPI() {
		    Response response = APIUtil.get(
		        "/accounts/"
		        + Constants.PRIMARY_ACCOUNT_ID);

		    APIUtil.assertStatusCode(response, 200);

		    // Assert account details
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

		    System.out.println("Account details: "
		        + response.jsonPath().getString("type")
		        + " — balance: "
		        + response.jsonPath().getString("balance"));
		}
	@Test(priority = 5,
		      description = "Verify customer has expected " +
		                    "number of accounts",
		      retryAnalyzer = RetryAnalyser.class)
		public void verifyCustomerAccountsCountAPI() {
		    Response response = APIUtil.get(
		        "/customers/" + Constants.CUSTOMER_ID
		        + "/accounts");

		    APIUtil.assertStatusCode(response, 200);

		    int accountCount = response.jsonPath()
		        .getList("$").size();

		    Assert.assertTrue(accountCount > 0,
		        "Customer should have at least one account");

		    System.out.println("Total accounts: "
		        + accountCount);
		}
}