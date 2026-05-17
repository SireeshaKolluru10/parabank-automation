package com.parabank.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {

	private static Connection connection;

	// Open DB connection
	public static void openConnection() {
		try {
			Class.forName(Constants.DB_DRIVER);
			connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD);
			System.out.println("DB connection opened successfully");
		} catch (Exception e) {
			System.out.println("DB connection failed: " + e.getMessage());
			throw new RuntimeException("Could not connect to database", e);
		}
	}

	// Close DB connection
	public static void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				System.out.println("DB connection closed");
			}
		} catch (Exception e) {
			System.out.println("Error closing connection: " + e.getMessage());
		}
	}

	// Execute query and return single string value
	public static String getSingleValue(String query, String columnName) {
		String value = null;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				value = rs.getString(columnName);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query failed: " + e.getMessage());
			throw new RuntimeException("DB query failed", e);
		}
		return value;
	}

	// Execute query and check if record exists
	public static boolean recordExists(String query) {
		boolean exists = false;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			exists = rs.next();
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query failed: " + e.getMessage());
			throw new RuntimeException("DB query failed", e);
		}
		return exists;
	}

	// Execute query and return integer value
	public static int getCount(String query) {
		int count = 0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query failed: " + e.getMessage());
			throw new RuntimeException("DB query failed", e);
		}
		return count;
	}
}