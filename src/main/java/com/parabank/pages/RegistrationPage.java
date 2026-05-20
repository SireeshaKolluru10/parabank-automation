package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {
	  // Constructor
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Form fields
    @FindBy(id = "customer.firstName")
    private WebElement firstNameField;

    @FindBy(id = "customer.lastName")
    private WebElement lastNameField;

    @FindBy(id = "customer.address.street")
    private WebElement addressField;

    @FindBy(id = "customer.address.city")
    private WebElement cityField;

    @FindBy(id = "customer.address.state")
    private WebElement stateField;

    @FindBy(id = "customer.address.zipCode")
    private WebElement zipCodeField;

    @FindBy(id = "customer.phoneNumber")
    private WebElement phoneField;

    @FindBy(id = "customer.ssn")
    private WebElement ssnField;

    @FindBy(id = "customer.username")
    private WebElement usernameField;

    @FindBy(id = "customer.password")
    private WebElement passwordField;

    @FindBy(id = "repeatedPassword")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@value='Register']")
    private WebElement registerButton;

    // Success message
    @FindBy(xpath =
        "//p[contains(text(),'Your account was created successfully. You are now logged in.')]")
    private WebElement successMessage;

    // Error messages
    @FindBy(id = "customer.firstName.errors")
    private WebElement firstNameError;

    @FindBy(id = "customer.lastName.errors")
    private WebElement lastNameError;

    @FindBy(id = "customer.address.street.errors")
    private WebElement addressError;

    @FindBy(id = "customer.address.city.errors")
    private WebElement cityError;

    @FindBy(id = "customer.address.state.errors")
    private WebElement stateError;

    @FindBy(id = "customer.address.zipCode.errors")
    private WebElement zipCodeError;

    @FindBy(id = "customer.ssn.errors")
    private WebElement ssnError;

    @FindBy(id = "customer.username.errors")
    private WebElement usernameError;

    @FindBy(id = "customer.password.errors")
    private WebElement passwordError;

    @FindBy(id = "repeatedPassword.errors")
    private WebElement confirmPasswordError;

    // Actions
    public void fillRegistrationForm(
            String firstName, String lastName,
            String address, String city,
            String state, String zipCode,
            String phone, String ssn,
            String username, String password) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(addressField, address);
        type(cityField, city);
        type(stateField, state);
        type(zipCodeField, zipCode);
        type(phoneField, phone);
        type(ssnField, ssn);
        type(usernameField, username);
        type(passwordField, password);
        type(confirmPasswordField, password);
    }

    public void enterConfirmPassword(String password) {
        type(confirmPasswordField, password);
    }

    public void clickRegister() {
        click(registerButton);
    }

    public void registerUser(
            String firstName, String lastName,
            String address, String city,
            String state, String zipCode,
            String phone, String ssn,
            String username, String password) {
        fillRegistrationForm(
            firstName, lastName, address,
            city, state, zipCode,
            phone, ssn, username, password);
        clickRegister();
    }

    // Verification methods
    public boolean isRegistrationSuccessful() {
        try {
            wait.until(ExpectedConditions
                .visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    // Error display methods
    public boolean isFirstNameErrorDisplayed() {
        return isDisplayed(firstNameError);
    }

    public boolean isLastNameErrorDisplayed() {
        return isDisplayed(lastNameError);
    }

    public boolean isAddressErrorDisplayed() {
        return isDisplayed(addressError);
    }

    public boolean isCityErrorDisplayed() {
        return isDisplayed(cityError);
    }

    public boolean isStateErrorDisplayed() {
        return isDisplayed(stateError);
    }

    public boolean isZipCodeErrorDisplayed() {
        return isDisplayed(zipCodeError);
    }

    public boolean isSSNErrorDisplayed() {
        return isDisplayed(ssnError);
    }

    public boolean isUsernameErrorDisplayed() {
        return isDisplayed(usernameError);
    }

    public boolean isPasswordErrorDisplayed() {
        return isDisplayed(passwordError);
    }

    public boolean isConfirmPasswordErrorDisplayed() {
        return isDisplayed(confirmPasswordError);
    }

    // Error text methods
    public String getFirstNameError() {
        return getText(firstNameError);
    }

    public String getLastNameError() {
        return getText(lastNameError);
    }

    public String getAddressError() {
        return getText(addressError);
    }

    public String getCityError() {
        return getText(cityError);
    }

    public String getStateError() {
        return getText(stateError);
    }

    public String getZipCodeError() {
        return getText(zipCodeError);
    }

    public String getSSNError() {
        return getText(ssnError);
    }

    public String getUsernameError() {
        return getText(usernameError);
    }

    public String getPasswordError() {
        return getText(passwordError);
    }
  
    public String getConfirmPasswordError() {
        return getText(confirmPasswordError);
    }
}