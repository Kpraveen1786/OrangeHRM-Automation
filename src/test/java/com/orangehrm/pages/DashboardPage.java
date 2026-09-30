package com.orangehrm.pages;

import org.openqa.selenium.By;

import com.orangehrm.utils.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DashboardPage extends BasePage {

	private static final Logger logger = LogManager.getLogger(DashboardPage.class);
	private By pimMenu = By.xpath("//span[text()='PIM']");

	private By profileIcon = By.xpath("//span[@class='oxd-userdropdown-tab']");

	public DashboardPage() {

	}

	public boolean isDashboardDisplayed() {

		logger.info("Checking dashboard page");

		return isDisplayed(pimMenu);
	}

	public AddEmployeePage navigateToPIM() {

		logger.info("Navigating to PIM module");

		click(pimMenu);

		return new AddEmployeePage();
	}

	public void clickProfile() {

		click(profileIcon);
	}

}