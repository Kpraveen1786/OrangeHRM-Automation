package com.orangehrm.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.orangehrm.utils.BasePage;

public class AddEmployeePage extends BasePage {

	private static final Logger logger = LogManager.getLogger(AddEmployeePage.class);
	private By pimMenu = By.xpath("//span[text()='PIM']");

	private By addEmployeeMenu = By.xpath("//a[text()='Add Employee']");

	private By firstName = By.name("firstName");

	private By middleName = By.name("middleName");

	private By lastName = By.name("lastName");

	private By employeeId = By.xpath("//label[text()='Employee Id']/../following-sibling::div//input");

	private By saveButton = By.xpath("//button[@type='submit']");

	private By employeeNameHeader = By.xpath("//h6[text()='Personal Details']");
	
	private By createLoginDetailsToggle = By.xpath("//p[text()='Create Login Details']/following::span[contains(@class,'oxd-switch-input')][1]");

	private By userName = By.xpath("//label[text()='Username']/../following-sibling::div//input");

	private By password = By.xpath("//label[text()='Password']/../following-sibling::div//input");

	private By confirmPassword = By.xpath("//label[text()='Confirm Password']/../following-sibling::div//input");

	public AddEmployeePage() {

	}

	public void navigateToAddEmployee() {

		logger.info("Opening Add Employee page");

		click(pimMenu);

		click(addEmployeeMenu);
	}

	public void enterEmployeeDetails(String first, String middle, String last) {

		logger.info("Entering employee details");

		enterText(firstName, first);

		enterText(middleName, middle);

		enterText(lastName, last);
	    
	    enterText(employeeId, String.valueOf(Math.abs(System.nanoTime() % 10_000_000_000L)));

	}
	
	public void enterLoginDetails(String username, String pwd) {

	    logger.info("Creating login details");

	    enterText(userName, username);

	    enterText(password, pwd);

	    enterText(confirmPassword, pwd);
	}
	
		
	public void user_enters_with_login_details(String firstName, String middleName, String lastName, String userName, String password) {
		enterEmployeeDetails(firstName, middleName, lastName);
		
		javaScriptExecutorclick(createLoginDetailsToggle);
		
		enterLoginDetails(userName, password);
	}
	

	public void updateEmployeeId(String id) {

		logger.info("Updating employee id");

		enterText(employeeId, id);

	}

	public void clickSave() throws InterruptedException {

		logger.info("Saving employee details");

		click(saveButton);
		
		waitForPageToLoad();		

	}
	
	public void userTogglesToLoginDetails() {
		logger.info("user toggles User Login Details");
		
		click(createLoginDetailsToggle);
	}

	public boolean verifyEmployeeCreated() {

		logger.info("Verifying employee creation");

		return isDisplayed(employeeNameHeader);

	}

	public EmployeeListPage goToEmployeeList() {

		return new EmployeeListPage();

	}

}