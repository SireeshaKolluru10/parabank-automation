package com.parabank.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected static Properties config = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream(
                "src/test/resources/config.properties");
            config.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(
                "config.properties file not found", e);
        }
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts()
            .implicitlyWait(Duration.ofSeconds(
                Integer.parseInt(config.getProperty("implicitWait"))));
        driver.get(config.getProperty("baseUrl"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}