package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.HomePage;
import com.parabank.pages.RegistrationPage;
import com.parabank.utilities.Constants;
import com.parabank.utilities.FakerUtil;
import com.parabank.utilities.RetryAnalyser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test(priority = 1,
          description = "Verify successful registration " +
                        "with valid data",
          retryAnalyzer = RetryAnalyser.class)
    public void validRegistrationTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToRegister();

        RegistrationPage registrationPage =
            new RegistrationPage(driver);

        String username = FakerUtil.getUsername();
        String password = FakerUtil.getPassword();

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
            "Registration was not successful");

        System.out.println(
            "Registered successfully: " + username);
    }

    @Test(priority = 2,
    	      description = "Verify errors when form is empty",
    	      retryAnalyzer = RetryAnalyser.class)
    	public void emptyFormRegistrationTest() {
    	    HomePage homePage = new HomePage(driver);
    	    homePage.navigateToRegister();

    	    RegistrationPage registrationPage =
    	        new RegistrationPage(driver);
    	    registrationPage.clickRegister();

    	    // Assert error messages are displayed
    	    Assert.assertTrue(
    	        registrationPage.isFirstNameErrorDisplayed(),
    	        "First name error not displayed");
    	    Assert.assertTrue(
    	        registrationPage.isLastNameErrorDisplayed(),
    	        "Last name error not displayed");
    	    Assert.assertTrue(
    	        registrationPage.isAddressErrorDisplayed(),
    	        "Address error not displayed");
    	    Assert.assertTrue(
    	        registrationPage.isCityErrorDisplayed(),
    	        "City error not displayed");
    	    Assert.assertTrue(
    	        registrationPage.isStateErrorDisplayed(),
    	        "State error not displayed");
    	    Assert.assertTrue(
    	        registrationPage.isZipCodeErrorDisplayed(),
    	        "ZipCode error not displayed");
    	    Assert.assertTrue(
    	        registrationPage.isSSNErrorDisplayed(),
    	        "SSN error not displayed");
    	    Assert.assertTrue(
    	        registrationPage.isUsernameErrorDisplayed(),
    	        "Username error not displayed");
    	    Assert.assertTrue(
    	        registrationPage.isPasswordErrorDisplayed(),
    	        "Password error not displayed");
    	    Assert.assertTrue(
    	        registrationPage.isConfirmPasswordErrorDisplayed(),
    	        "Confirm password error not displayed");

    	    // Assert exact error message text
    	    Assert.assertEquals(
    	        registrationPage.getFirstNameError(),
    	        Constants.FIRST_NAME_REQUIRED,
    	        "First name error text mismatch");
    	    Assert.assertEquals(
    	        registrationPage.getLastNameError(),
    	        Constants.LAST_NAME_REQUIRED,
    	        "Last name error text mismatch");
    	    Assert.assertEquals(
    	        registrationPage.getAddressError(),
    	        Constants.ADDRESS_REQUIRED,
    	        "Address error text mismatch");
    	    Assert.assertEquals(
    	        registrationPage.getCityError(),
    	        Constants.CITY_REQUIRED,
    	        "City error text mismatch");
    	    Assert.assertEquals(
    	        registrationPage.getStateError(),
    	        Constants.STATE_REQUIRED,
    	        "State error text mismatch");
    	    Assert.assertEquals(
    	        registrationPage.getZipCodeError(),
    	        Constants.ZIP_CODE_REQUIRED,
    	        "Zip code error text mismatch");
    	    Assert.assertEquals(
    	        registrationPage.getSSNError(),
    	        Constants.SSN_REQUIRED,
    	        "SSN error text mismatch");
    	    Assert.assertEquals(
    	        registrationPage.getUsernameError(),
    	        Constants.USERNAME_REQUIRED,
    	        "Username error text mismatch");
    	    Assert.assertEquals(
    	        registrationPage.getPasswordError(),
    	        Constants.PASSWORD_REQUIRED,
    	        "Password error text mismatch");
    	    Assert.assertEquals(
    	        registrationPage.getConfirmPasswordError(),
    	        Constants.CONFIRM_PASSWORD_REQUIRED,
    	        "Confirm password error text mismatch");

    	    System.out.println(
    	        "All empty field validations passed");
    	}
    @Test(priority = 3,
          description = "Verify error when username " +
                        "already exists",
          retryAnalyzer = RetryAnalyser.class)
    public void duplicateUsernameTest() {
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
            Constants.VALID_USERNAME,
            FakerUtil.getPassword());

        Assert.assertTrue(
            registrationPage.isUsernameErrorDisplayed(),
            "Duplicate username error not displayed");
        Assert.assertEquals(
            registrationPage.getUsernameError(),
            Constants.USERNAME_TAKEN_ERROR,
            "Username error message does not match");
    }

    @Test(priority = 4,
          description = "Verify error when passwords " +
                        "do not match",
          retryAnalyzer = RetryAnalyser.class)
    public void passwordMismatchTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToRegister();

        RegistrationPage registrationPage =
            new RegistrationPage(driver);

        registrationPage.fillRegistrationForm(
            FakerUtil.getFirstName(),
            FakerUtil.getLastName(),
            FakerUtil.getAddress(),
            FakerUtil.getCity(),
            FakerUtil.getState(),
            FakerUtil.getZipCode(),
            FakerUtil.getPhone(),
            FakerUtil.getSSN(),
            FakerUtil.getUsername(),
            FakerUtil.getPassword());

        // Override confirm password with different value
        registrationPage.enterConfirmPassword(
            FakerUtil.getPassword());

        registrationPage.clickRegister();

        Assert.assertTrue(
            registrationPage
                .isConfirmPasswordErrorDisplayed(),
            "Password mismatch error not displayed");

        System.out.println(
            "Password mismatch test passed");
    }
}