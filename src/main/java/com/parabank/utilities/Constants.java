package com.parabank.utilities;

public class Constants {

	// Parabank credentials
	public static final String VALID_USERNAME = "john";
	public static final String VALID_PASSWORD = "demo";

	// Page titles
	public static final String ACCOUNTS_PAGE_TITLE = "Accounts Overview";

	// Error messages
	public static final String INVALID_LOGIN_ERROR = "The username and password could not be verified.";

	// API base URL
	public static final String API_BASE_URL = "https://parabank.parasoft.com/parabank/services/bank";
	// Customer details from API response
	public static final String CUSTOMER_ID = "12212";
	public static final String PRIMARY_ACCOUNT_ID = "13344";

	// Customer name for assertions
	public static final String CUSTOMER_FIRST_NAME = "John";
	public static final String CUSTOMER_LAST_NAME = "Smith";
	
	// Registration success
	public static final String REGISTRATION_SUCCESS =
			"Your account was created successfully. You are now logged in.";


	// Registration error messages — empty fields
	public static final String FIRST_NAME_REQUIRED =
	    "First name is required.";
	public static final String LAST_NAME_REQUIRED =
	    "Last name is required.";
	public static final String ADDRESS_REQUIRED =
	    "Address is required.";
	public static final String CITY_REQUIRED =
	    "City is required.";
	public static final String STATE_REQUIRED =
	    "State is required.";
	public static final String ZIP_CODE_REQUIRED =
	    "Zip Code is required.";
	public static final String SSN_REQUIRED =
	    "Social Security Number is required.";
	public static final String USERNAME_REQUIRED =
	    "Username is required.";
	public static final String PASSWORD_REQUIRED =
	    "Password is required.";
	public static final String CONFIRM_PASSWORD_REQUIRED =
	    "Password confirmation is required.";

	// Registration duplicate username
	public static final String USERNAME_TAKEN_ERROR =
	    "This username already exists.";
	// Account types
	public static final String CHECKING = "CHECKING";
	public static final String SAVINGS = "SAVINGS";

	// Account open success
	public static final String ACCOUNT_OPENED_SUCCESS =
	    "Congratulations, your account is now open.";

	// Transfer success
	public static final String TRANSFER_SUCCESS =
	    "Transfer Complete!";

	// Account IDs for transfer tests
	public static final String SECONDARY_ACCOUNT_ID
	    = "13344";
	public static final String TERTIARY_ACCOUNT_ID
	    = "12567";
	// Transfer amounts
	public static final String VALID_TRANSFER_AMOUNT
	    = "100.00";
	public static final String ZERO_TRANSFER_AMOUNT
	    = "0.00";
	public static final String NEGATIVE_TRANSFER_AMOUNT
	    = "-100.00";
	// Bill pay
	public static final String BILL_PAY_SUCCESS =
	    "Bill Payment Complete";
	public static final String BILL_PAY_AMOUNT
	    = "50.00";
	public static final String PAYEE_NAME_REQUIRED
	    = "Payee name is required.";
	public static final String AMOUNT_EMPTY_ERROR
	    = "The amount cannot be empty.";
	// Bill pay from account
	public static final String BILL_PAY_FROM_ACCOUNT
	    = "13344";

	// Transaction search
	public static final String TRANSACTION_AMOUNT
	    = "100.00";

	// DB connection details — Parabank uses HSQLDB
	public static final String DB_URL = "jdbc:hsqldb:hsql://parabank.parasoft.com/parabank";
	public static final String DB_USERNAME = "sa";
	public static final String DB_PASSWORD = "";
	public static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
}