package com.orangehrm.pages;

import org.openqa.selenium.By;

import com.orangehrm.utils.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage extends BasePage {
	private static final Logger logger = LogManager.getLogger(LoginPage.class);

	private By usernameField = By.name("username");

	private By passwordField = By.name("password");

	private By loginButton = By.xpath("//button[@type='submit']");

	private By dashboardText = By.xpath("//h6[text()='Dashboard']");

	public LoginPage() {

	}

	public void enterUsername(String username) {

		logger.info("Entering username");

		enterText(usernameField, username);
	}

	public void enterPassword(String password) {

		logger.info("Entering password");

		enterText(passwordField, password);
	}

	public DashboardPage clickLogin() {

		logger.info("Clicking login button");

		click(loginButton);
		waitForPageToLoad();

		return new DashboardPage();
	}

	public DashboardPage login(String username, String password) {

		enterUsername(username);

		enterPassword(password);

		return clickLogin();
	}

	public boolean isLoginSuccessful() {

		return isDisplayed(dashboardText);
	}
}