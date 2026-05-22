package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountsPage extends BasePage {

    @FindBy(xpath = "//h1[@class='title']")
    private WebElement pageTitle;

    @FindBy(linkText = "Log Out")
    private WebElement logoutLink;

    @FindBy(linkText = "Open New Account")
    private WebElement openNewAccountLink;

    @FindBy(linkText = "Transfer Funds")
    private WebElement transferFundsLink;

    @FindBy(linkText = "Bill Pay")
    private WebElement billPayLink;

    @FindBy(linkText = "Find Transactions")
    private WebElement findTransactionsLink;

    @FindBy(linkText = "Account Activity")
    private WebElement accountActivityLink;

    public AccountsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isLogoutDisplayed() {
        return isDisplayed(logoutLink);
    }

    public void clickOpenNewAccount() {
        click(openNewAccountLink);
    }

    public void clickTransferFunds() {
        click(transferFundsLink);
    }

    public void clickBillPay() {
        click(billPayLink);
    }

    public void clickFindTransactions() {
        click(findTransactionsLink);
    }

    public void clickAccountActivity() {
        click(accountActivityLink);
    }

    public void logout() {
        click(logoutLink);
    }
    public void waitForPageToLoad() {
        wait.until(ExpectedConditions
            .visibilityOf(pageTitle));
        wait.until(ExpectedConditions
            .visibilityOf(openNewAccountLink));
    }
}