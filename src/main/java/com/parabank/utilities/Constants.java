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
	public static final String PRIMARY_ACCOUNT_ID = "12345";

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

	// DB connection details — Parabank uses HSQLDB
	public static final String DB_URL = "jdbc:hsqldb:hsql://parabank.parasoft.com/parabank";
	public static final String DB_USERNAME = "sa";
	public static final String DB_PASSWORD = "";
	public static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
}