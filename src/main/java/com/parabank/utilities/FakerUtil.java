package com.parabank.utilities;

import com.github.javafaker.Faker;

public class FakerUtil {

	private static final Faker faker = new Faker();

	// Name
	public static String getFirstName() {
		return faker.name().firstName();
	}

	public static String getLastName() {
		return faker.name().lastName();
	}

	// Contact
	public static String getEmail() {
		return faker.internet().emailAddress();
	}

	public static String getPhone() {
		return faker.numerify("##########");
	}

	// Address
	public static String getAddress() {
		return faker.address().streetAddress();
	}

	public static String getCity() {
		return faker.address().city();
	}

	public static String getState() {
		return faker.address().stateAbbr();
	}

	public static String getZipCode() {
		return faker.address().zipCode().substring(0, 5);
	}

	// Credentials
	public static String getUsername() {
		return faker.name().username().replaceAll("[^a-zA-Z0-9]", "").substring(0, 8);
	}

	public static String getPassword() {
		return "Test@" + faker.numerify("####");
	}

	// Numbers
	public static String getSSN() {
		return faker.numerify("###-##-####");
	}

	public static double getAmount(double min, double max) {
		return Math.round((min + Math.random() * (max - min)) * 100.0) / 100.0;
	}
}