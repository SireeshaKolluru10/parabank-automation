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

    @FindBy(xpath = "//input[@value='Open New Account']")
    private WebElement openAccountButton;

    @FindBy(id = "newAccountId")
    private WebElement newAccountId;

    @FindBy(xpath = "//h1[@class='title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//p[contains(text()," +
        "'Congratulations')]")
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
        click(openAccountButton);
    }

    public void openNewAccount(String accountType) {
        selectAccountType(accountType);
        clickOpenAccount();
    }

    public boolean isAccountOpenedSuccessfully() {
        try {
            wait.until(ExpectedConditions
                .visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
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