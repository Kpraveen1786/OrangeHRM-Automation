package com.orangehrm.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.orangehrm.factory.DriverFactory;

public class ScreenshotUtils {

	private ScreenshotUtils() {

	}

	/**
	 * Capture screenshot and return file path
	 */
	public static String captureScreenshot(String testName, String screenshotPath) {

//		String timestamp = new SimpleDateFormat("yyyy_MMM_dd_HH_mm_ss").format(new Date());

//		String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/" + testName + "_"
//				+ timestamp + ".png";

		File source = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

		File destination = new File(screenshotPath);

		try {

			FileUtils.copyFile(source, destination);

		} catch (IOException e) {

			throw new RuntimeException("Screenshot capture failed", e);
		}

		return screenshotPath;
	}
}