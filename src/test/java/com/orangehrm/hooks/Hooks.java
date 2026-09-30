package com.orangehrm.hooks;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.orangehrm.factory.DriverFactory;
import com.orangehrm.hooks.utils.ExtentManager;
import com.orangehrm.hooks.utils.ExtentTestManager;
import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.ScreenshotUtils;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.aventstack.extentreports.Status;
import io.cucumber.java.*;
public class Hooks {

	private static final Logger logger = LogManager.getLogger(Hooks.class);

	@Before
	public void setUp(Scenario scenario) {

		logger.info("===== Test Execution Started =====");

		DriverFactory.initDriver(ConfigReader.getBrowser());

		DriverFactory.getDriver().get(ConfigReader.getApplicationUrl());

		logger.info("Application launched successfully");
		
		ExtentTestManager.setTest(
                ExtentManager.getInstance().createTest(scenario.getName()));

	}
	
	@AfterStep
    public void afterStep(Scenario scenario) {

        if (scenario.isFailed()) {
            ExtentTestManager.getTest().log(Status.FAIL, "Step Failed");
        } else {
            ExtentTestManager.getTest().log(Status.PASS, "Step Passed");
        }
    }

	@After
	public void tearDown(Scenario scenario) {

		logger.info("Scenario Status : " + scenario.getStatus());

		if (scenario.isFailed()) {

			logger.error("Scenario Failed. Capturing screenshot");

			byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);

			scenario.attach(screenshot, "image/png", "Failure Screenshot");
			
			String timestamp = new SimpleDateFormat("yyyy_MMM_dd_HH_mm_ss").format(new Date());

			String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/" + scenario.getName() + "_"
					+ timestamp + ".png";
			
			ScreenshotUtils.captureScreenshot(scenario.getName(), screenshotPath);
			
			 ExtentTestManager.getTest()
             .addScreenCaptureFromPath(screenshotPath);

		}

		logger.info("Closing browser : "+scenario.getName());
		ExtentManager.getInstance().flush();

		DriverFactory.quitDriver();

		logger.info("===== Test Execution Completed =====");

	}

}