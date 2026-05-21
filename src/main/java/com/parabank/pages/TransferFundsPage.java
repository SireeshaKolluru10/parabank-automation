package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsPage extends BasePage {

    @FindBy(id = "amount")
    private WebElement amountField;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(id = "toAccountId")
    private WebElement toAccountDropdown;

    @FindBy(xpath = "//input[@value='Transfer']")
    private WebElement transferButton;

    @FindBy(xpath = "//h1[@class='title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//h1[contains(text()," +
        "'Transfer Complete')]")
    private WebElement successMessage;

    @FindBy(xpath = "//p[@class='error']")
    private WebElement errorMessage;

    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    public void enterAmount(String amount) {
        type(amountField, amount);
    }

    public void selectFromAccount(String accountId) {
        wait.until(ExpectedConditions
            .elementToBeClickable(fromAccountDropdown));
        Select select = new Select(fromAccountDropdown);
        select.selectByVisibleText(accountId);
    }

    public void selectToAccount(String accountId) {
        wait.until(ExpectedConditions
            .elementToBeClickable(toAccountDropdown));
        Select select = new Select(toAccountDropdown);
        select.selectByVisibleText(accountId);
    }

    public void clickTransfer() {
        click(transferButton);
    }

    public void transferFunds(
            String amount,
            String fromAccount,
            String toAccount) {
        enterAmount(amount);
        selectFromAccount(fromAccount);
        selectToAccount(toAccount);
        clickTransfer();
    }

    public boolean isTransferSuccessful() {
        try {
            wait.until(ExpectedConditions
                .visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }
}