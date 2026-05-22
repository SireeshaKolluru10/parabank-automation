package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class BillPayPage extends BasePage {

    @FindBy(name = "payee.name")
    private WebElement payeeNameField;

    @FindBy(name = "payee.address.street")
    private WebElement addressField;

    @FindBy(name = "payee.address.city")
    private WebElement cityField;

    @FindBy(name = "payee.address.state")
    private WebElement stateField;

    @FindBy(name = "payee.address.zipCode")
    private WebElement zipCodeField;

    @FindBy(name = "payee.phoneNumber")
    private WebElement phoneField;

    @FindBy(name = "payee.accountNumber")
    private WebElement accountNumberField;

    @FindBy(name = "verifyAccount")
    private WebElement verifyAccountField;

    @FindBy(name = "amount")
    private WebElement amountField;

    @FindBy(name = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(xpath = "//input[@value='Send Payment']")
    private WebElement sendPaymentButton;

    @FindBy(xpath =
        "//h1[contains(text(),'Bill Payment Complete')]")
    private WebElement successMessage;

    @FindBy(id = "validationModel-name")
    private WebElement payeeNameError;

    @FindBy(id = "validationModel-amount-empty")
    private WebElement amountError;

    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    public void enterPayeeName(String name) {
        type(payeeNameField, name);
    }

    public void enterAddress(String address) {
        type(addressField, address);
    }

    public void enterCity(String city) {
        type(cityField, city);
    }

    public void enterState(String state) {
        type(stateField, state);
    }

    public void enterZipCode(String zipCode) {
        type(zipCodeField, zipCode);
    }

    public void enterPhone(String phone) {
        type(phoneField, phone);
    }

    public void enterAccountNumber(String account) {
        type(accountNumberField, account);
        type(verifyAccountField, account);
    }

    public void enterAmount(String amount) {
        type(amountField, amount);
    }

    public void selectFromAccount(String accountId) {
        wait.until(ExpectedConditions
            .elementToBeClickable(fromAccountDropdown));
        Select select = new Select(fromAccountDropdown);
        select.selectByValue(accountId);
    }

    public void clickSendPayment() {
        click(sendPaymentButton);
    }

    public void payBill(
            String payeeName, String address,
            String city, String state,
            String zipCode, String phone,
            String accountNumber, String amount,
            String fromAccount) {
        enterPayeeName(payeeName);
        enterAddress(address);
        enterCity(city);
        enterState(state);
        enterZipCode(zipCode);
        enterPhone(phone);
        enterAccountNumber(accountNumber);
        enterAmount(amount);
        selectFromAccount(fromAccount);
        clickSendPayment();
    }

    public boolean isBillPaySuccessful() {
        try {
            wait.until(ExpectedConditions
                .visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            System.out.println(
                "Bill pay success not found: "
                + e.getMessage());
            return false;
        }
    }

    public boolean isPayeeNameErrorDisplayed() {
        return isDisplayed(payeeNameError);
    }

    public boolean isAmountErrorDisplayed() {
        return isDisplayed(amountError);
    }

    public String getPayeeNameError() {
        return getText(payeeNameError);
    }

    public String getAmountError() {
        return getText(amountError);
    }


}