package com.orangehrm.utils;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.factory.DriverFactory;

public class WaitUtils {

	private static final Logger logger = LogManager.getLogger(WaitUtils.class);

	private static final int TIMEOUT = ConfigReader.getExplicitWait();

	private WaitUtils() {

	}

	private static WebDriverWait getWait() {

		return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(TIMEOUT));
	}

	/**
	 * Wait until element visible
	 */
	public static WebElement waitForVisibility(By locator) {

		long startTime = System.currentTimeMillis();

		WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));

		long endTime = System.currentTimeMillis();

		double seconds = (endTime - startTime) / 1000.0;

		System.out.printf("Element [%s] found in %.2f seconds%n", locator, seconds);

		logger.info("Element [{}] found in [{}] seconds", locator, String.format("%.2f", seconds));

		return element;
	}

	/**
	 * Wait until element clickable
	 */
	public static WebElement waitForClickable(By locator) {
		
		long startTime = System.currentTimeMillis();

		WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
		
		long endTime = System.currentTimeMillis();

		double seconds = (endTime - startTime) / 1000.0;

		System.out.printf("Element [%s] found in %.2f seconds%n", locator, seconds);

		logger.info("Element [{}] found in [{}] seconds", locator, String.format("%.2f", seconds));

		return element;

	}

	/**
	 * Wait until element exists
	 */
	public static boolean waitForPresence(By locator) {

		try {

			getWait().until(ExpectedConditions.presenceOfElementLocated(locator));

			return true;

		} catch (Exception e) {

			return false;
		}
	}

	/**
	 * Wait until element disappears
	 */
	public static boolean waitForInvisibility(By locator) {

		return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	/**
	 * Wait for title
	 */
	public static boolean waitForTitle(String title) {

		return getWait().until(ExpectedConditions.titleContains(title));
	}

	public static void waitForPageToLoad(WebDriver driver) {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	public static void click(WebDriver driver, By createLoginDetailsToggle) {
		WebElement toggle = WaitUtils.waitForVisibility(createLoginDetailsToggle);
		new Actions(driver).moveToElement(toggle).click().perform();
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
	}
}