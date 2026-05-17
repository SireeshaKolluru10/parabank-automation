package com.parabank.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}
		return extent;
	}

	private static void createInstance() {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/AutomationReport.html");

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("Parabank Automation Report");
		sparkReporter.config().setReportName("Hybrid Framework Test Results");
		sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Project", "Parabank Automation");
		extent.setSystemInfo("Framework", "Hybrid - POM + Data Driven");
		extent.setSystemInfo("Author", "Sireesha Kolluru");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Browser", "Chrome");
	}

	public static void setTest(ExtentTest extentTest) {
		test.set(extentTest);
	}

	public static ExtentTest getTest() {
		return test.get();
	}

	public static void flushReport() {
		if (extent != null) {
			extent.flush();
		}
	}
}