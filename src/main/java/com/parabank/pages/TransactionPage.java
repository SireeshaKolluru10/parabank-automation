package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class TransactionPage extends BasePage {

    @FindBy(id = "accountId")
    private WebElement accountDropdown;

    @FindBy(id = "amount")
    private WebElement amountField;

    @FindBy(id = "findByAmount")
    private WebElement findByAmountButton;

    @FindBy(id = "transactionTable")
    private WebElement transactionTable;

    @FindBy(xpath =
        "//tbody[@id='transactionBody']/tr")
    private List<WebElement> transactionRows;

    @FindBy(xpath = "//h1[@class='title']")
    private WebElement pageTitle;

       @FindBy(xpath = "//p[contains(text(),'No transactions found')]")
    private WebElement noResultsMessage;
       public TransactionPage(WebDriver driver) {
           super(driver);
       }

    public void selectAccount(String accountId) {
        wait.until(driver -> new Select(accountDropdown).getOptions().size() > 0);
        new Select(accountDropdown).selectByValue(accountId);
    }

    public void enterAmount(String amount) {
        type(amountField, amount);
    }

    public void clickFindByAmount() {
        click(findByAmountButton);
    }

    public void findTransactionsByAmount(
            String accountId, String amount) {
        selectAccount(accountId);
        enterAmount(amount);
        clickFindByAmount();
    }

    public boolean isTransactionTableDisplayed() {
        try {
            wait.until(ExpectedConditions
                .visibilityOf(transactionTable));
            return transactionTable.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getTransactionCount() {
        try {
            wait.until(ExpectedConditions
                .visibilityOfAllElements(
                    transactionRows));
            return transactionRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isTransactionBodyEmpty() {
        try {
            // Wait for EITHER rows to load OR the no-results message
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfAllElements(transactionRows),
                ExpectedConditions.visibilityOf(noResultsMessage)
            ));
            return transactionRows.isEmpty();
        } catch (Exception e) {
            return true;
        }
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }
}