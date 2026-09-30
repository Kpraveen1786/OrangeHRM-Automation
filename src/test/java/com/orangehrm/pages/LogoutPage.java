package com.orangehrm.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.orangehrm.utils.BasePage;

public class LogoutPage extends BasePage {

	private static final Logger logger = LogManager.getLogger(LogoutPage.class);
	private By profileDropdown = By.xpath("//span[@class='oxd-userdropdown-tab']");

	private By logoutButton = By.xpath("//a[text()='Logout']");

	public LogoutPage() {

	}

	public void logout() {

		logger.info("Logging out from application");

		click(profileDropdown);

		click(logoutButton);

	}

}