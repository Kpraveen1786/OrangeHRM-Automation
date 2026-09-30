package com.orangehrm.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.orangehrm.factory.DriverFactory;
import com.orangehrm.utils.BasePage;

public class EmployeeListPage extends BasePage {

	private static final Logger logger = LogManager.getLogger(EmployeeListPage.class);
	private By employeeListMenu = By.xpath("//a[text()='Employee List']");

	private By employeeNameInput = By.xpath("//label[text()='Employee Name']" + "/following::input[1]");

	private By searchButton = By.xpath("//button[@type='submit']");

	private By tableRows = By.xpath("//div[@class='oxd-table-card']");

	private By deleteButton = By.xpath("//button[contains(@class,'oxd-table-cell-action-space')][2]");

	private By confirmDeleteButton = By.xpath("//button[text()=' Yes, Delete ']");

	private By tableBody = By.className("oxd-table-body");

	private By tableCard = By.className("oxd-table-card");

	public EmployeeListPage() {

	}

	public void openEmployeeList() {

		logger.info("Opening Employee List");

		click(employeeListMenu);

	}

	public void searchEmployee(String employeeName) {

		logger.info("Searching employee : " + employeeName);

		enterText(employeeNameInput, employeeName);

		click(searchButton);

	}

	public boolean isEmployeeDisplayed() {

		logger.info("Checking employee search result");

		return isDisplayed(tableRows);

	}

	public void deleteEmployee() {

		logger.info("Deleting employee record");

//		scrollToElement(deleteButton);
//
//		click(deleteButton);
//
//		click(confirmDeleteButton);
		WebDriver driver = DriverFactory.getDriver();

		WebElement body = driver.findElement(tableBody);

		List<WebElement> rows = body.findElements(tableCard);
		logger.info("total count : " + rows.size());

		/** To avoid StaleElementReferenceException used for loop. With in the for loop
		 every time taking driver from DriverFactory */
		for (int i = 1; i <= rows.size(); i++) {
			deleteMultipleEmployeeRecordsOneByOne();
		}

	}

	public void deleteMultipleEmployeeRecordsOneByOne() {
		logger.info("Deleting Multiple Employee Records");
		WebDriver driver = DriverFactory.getDriver();

		WebElement body = driver.findElement(tableBody);

		List<WebElement> rows = body.findElements(tableCard);
		logger.info("total count : "+ rows.size());

		for (WebElement row : rows) {

			// Get all columns in the current row
			List<WebElement> columns = row.findElements(By.className("oxd-table-cell"));

			logger.info("Deleting employee record");
			
			waitForPageToLoad();

			scrollToElement(deleteButton);
			
			// Click Delete button in the row
			click(deleteButton);

//			row.findElement(deleteButton).click();
			explicitWait();

			// Confirm Delete
			driver.findElement(confirmDeleteButton).click();
			explicitWait();
			return;
//	        }
		}
	}

	public boolean isEmployeeDeleted() {

		logger.info("Validating employee deletion");

		return !isDisplayed(tableRows);

	}

}