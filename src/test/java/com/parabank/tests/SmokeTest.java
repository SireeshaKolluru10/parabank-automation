package com.parabank.tests;

import com.parabank.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test
    public void verifyParabankHomePage() {
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
        Assert.assertTrue(title.contains("ParaBank"), 
            "Home page title does not contain ParaBank");
    }
}