package com.parabank.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, 
                                           String testName) {
        String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss")
            .format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";
        String screenshotPath = "screenshots/" + screenshotName;

        try {
            File source = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }

        return screenshotPath;
    }
}