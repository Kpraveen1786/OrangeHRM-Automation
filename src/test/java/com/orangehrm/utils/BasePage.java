package com.orangehrm.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.orangehrm.factory.DriverFactory;

public class BasePage {
	private static final Logger logger = LogManager.getLogger(BasePage.class);

	protected BasePage() {
	}

	/**
	 * Get current WebDriver instance
	 */
	protected WebDriver getDriver() {
		return DriverFactory.getDriver();
	}

	/**
	 * Click element with wait
	 */
	protected void click(By locator) {

//		WaitUtils.waitForClickable(locator).click();
		for (int i = 0; i < 3; i++) {
			try {
				WebElement element = WaitUtils.waitForClickable(locator);

				highlight(element);

				element.click();
				return;
			} catch (StaleElementReferenceException e) {
				logger.error("Stale element. Retrying click...");
			}
		}

		explicitWait();

		throw new RuntimeException("Unable to click element: " + locator);
	}

	/**
	 * Click element with wait
	 */
	protected void click(WebElement element) {

//		WaitUtils.waitForClickable(locator).click();
		for (int i = 0; i < 3; i++) {
			try {
				highlight(element);
				element.click();
				return;
			} catch (StaleElementReferenceException e) {
				logger.warn("Stale element. Retrying click...");
			}
		}

		explicitWait();

		throw new RuntimeException("Unable to click element: " + element);
	}

	/**
	 * Enter text into textbox
	 */
	protected void enterText(By locator, String text) {

		WebElement element = WaitUtils.waitForVisibility(locator);
		highlight(element);
		element.clear();

		// Some times text box value is not getting cleared. To avoid this issue used
		// Keys
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element.sendKeys(Keys.DELETE);

		element.sendKeys(text);
		explicitWait();
	}

	protected void explicitWait() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void clearText(By locator) {
		WebElement element = WaitUtils.waitForVisibility(locator);

		highlight(element);

		element.clear();

		explicitWait();
	}

	/**
	 * Get element text
	 */
	protected String getText(By locator) {

		return WaitUtils.waitForVisibility(locator).getText();
	}

	/**
	 * Check element displayed
	 */
	protected boolean isDisplayed(By locator) {

		try {

			return WaitUtils.waitForVisibility(locator).isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}

	/**
	 * Select dropdown by visible text
	 */
	protected void selectByVisibleText(By locator, String text) {

		WebElement dropdown = WaitUtils.waitForVisibility(locator);

		Select select = new Select(dropdown);

		select.selectByVisibleText(text);
	}

	/**
	 * Scroll to element
	 */
	protected void scrollToElement(By locator) {

		WebElement element = WaitUtils.waitForVisibility(locator);

		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		js.executeScript("arguments[0].scrollIntoView(true);", element);

		highlight(element);

		explicitWait();
	}

	/**
	 * JavaScript click
	 */
	protected void javaScriptClick(By locator) {

		WebElement element = WaitUtils.waitForVisibility(locator);

		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * Get current URL
	 */
	protected String getCurrentURL() {

		return getDriver().getCurrentUrl();
	}

	protected void waitForPageToLoad() {
		WaitUtils.waitForPageToLoad(getDriver());
	}

	protected void javaScriptExecutorclick(By createLoginDetailsToggle) {
		WaitUtils.click(getDriver(), createLoginDetailsToggle);
		explicitWait();
	}

	public void highlight(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		js.executeScript("arguments[0].style.outline='1px solid green';", element);
	}
}