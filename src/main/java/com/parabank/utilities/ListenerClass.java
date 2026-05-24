package com.parabank.utilities;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ListenerClass implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== Suite started: " + context.getName() + " ===");
        ExtentReportManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = ExtentReportManager.getInstance()
            .createTest(result.getMethod().getMethodName(),
                        result.getMethod().getDescription());
        ExtentReportManager.setTest(extentTest);
        ExtentReportManager.getTest().log(Status.INFO, "Test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportManager.getTest().log(Status.FAIL,
            "Test failed: " + result.getThrowable().getMessage());

        // Capture screenshot and embed as Base64
        Object testInstance = result.getInstance();
        try {
            WebDriver driver = (WebDriver)
                testInstance.getClass().getField("driver").get(testInstance);

            if (driver instanceof TakesScreenshot) {
                byte[] screenshotBytes =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                String base64 = Base64.getEncoder().encodeToString(screenshotBytes);
                ExtentReportManager.getTest().addScreenCaptureFromBase64String(
                    base64, "Failure screenshot");
            }
        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.WARNING,
                "Screenshot capture failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP,
            "Test skipped: " + result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReport();
        System.out.println("Report generated at: reports/ExtentReport.html");
    }
}