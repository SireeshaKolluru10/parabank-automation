package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class OpenAccountPage extends BasePage {

    @FindBy(id = "type")
    private WebElement accountTypeDropdown;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(xpath = "//input[@type='button']")
    private WebElement openAccountButton;

    @FindBy(xpath = "//a[@id='newAccountId']")
    private WebElement newAccountId;

    @FindBy(xpath = "//h1[@class='title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//p[contains(text(),'now open')]")
    private WebElement successMessage;

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    public void selectAccountType(String accountType) {
        wait.until(ExpectedConditions
            .elementToBeClickable(accountTypeDropdown));
        Select select = new Select(accountTypeDropdown);
        select.selectByVisibleText(accountType);
    }

    public void selectFromAccount(String accountId) {
        wait.until(ExpectedConditions
            .elementToBeClickable(fromAccountDropdown));
        Select select = new Select(fromAccountDropdown);
        select.selectByVisibleText(accountId);
    }

    public void clickOpenAccount() {
        wait.until(ExpectedConditions
            .elementToBeClickable(openAccountButton));
        click(openAccountButton);
    }

    public void openNewAccount(String accountType) {
        selectAccountType(accountType);
        // Small wait for from account dropdown to load
        wait.until(ExpectedConditions
            .elementToBeClickable(fromAccountDropdown));
        clickOpenAccount();
    }

    public boolean isAccountOpenedSuccessfully() {
        try {
            // First wait for success message text
            wait.until(ExpectedConditions
                .visibilityOf(successMessage));

            // Then wait for account ID link
            wait.until(ExpectedConditions
                .visibilityOf(newAccountId));

            return successMessage.isDisplayed();
        } catch (Exception e) {
            System.out.println(
                "Account creation failed: "
                + e.getMessage());
            return false;
        }
    }

    public String getNewAccountId() {
        wait.until(ExpectedConditions
            .visibilityOf(newAccountId));
        return getText(newAccountId);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }
}