package com.parabank.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.parabank.base.BaseTest;
import com.parabank.utilities.ExtentReportManager;
import com.parabank.utilities.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	private static ExtentReports extent = ExtentReportManager.getInstance();

	// Runs before each test — creates test node in report
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		ExtentReportManager.setTest(test);
		ExtentReportManager.getTest().log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
	}

	// Runs when test passes
	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReportManager.getTest().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
	}

	// Runs when test fails — captures screenshot automatically
	@Override
	public void onTestFailure(ITestResult result) {
		ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable());

		// Get driver from the test class
		Object testInstance = result.getInstance();
		if (testInstance instanceof BaseTest) {
			org.openqa.selenium.WebDriver driver = ((BaseTest) testInstance).driver;

			if (driver != null) {
				String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
				try {
					ExtentReportManager.getTest().addScreenCaptureFromPath("../" + screenshotPath,
							result.getMethod().getMethodName());
				} catch (Exception e) {
					System.out.println("Could not attach screenshot: " + e.getMessage());
				}
			}
		}
	}

	// Runs when test is skipped
	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReportManager.getTest().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
	}

	// Runs after entire suite — flushes report to disk
	@Override
	public void onFinish(ITestContext context) {
		ExtentReportManager.flushReport();
	}
}