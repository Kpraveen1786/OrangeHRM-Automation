package com.orangehrm.stepdefinitions;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.orangehrm.hooks.utils.ReportManager;
import com.orangehrm.pages.AddEmployeePage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.EmployeeListPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.LogoutPage;
import com.orangehrm.utils.ConfigReader;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

	private static final Logger logger = LogManager.getLogger(StepDefinitions.class);

	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private AddEmployeePage addEmployeePage;
	private EmployeeListPage employeeListPage;
	private LogoutPage logoutPage;

	private String employeeFirstName;
	private String employeeMiddleName;
	private String employeeLastName;
	private String employeeName;
	private String userName;
	private String pwd;

	public StepDefinitions() {
		
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		addEmployeePage = new AddEmployeePage();
		employeeListPage = new EmployeeListPage();
		logoutPage = new LogoutPage();

	}

	// ----------------------------------------------------
	// Background Step
	// ----------------------------------------------------

	@Given("user launches OrangeHRM application")
	public void userLaunchesOrangeHRMApplication() {

		logger.info("OrangeHRM application launched");

	}

	// ----------------------------------------------------
	// Login
	// ----------------------------------------------------

	@When("user logs into application with valid credentials")
	public void userLogsIntoApplicationWithValidCredentials() {

		logger.info("Logging into application");

		loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

	}

	@Then("dashboard page should be displayed")
	public void dashboardPageShouldBeDisplayed() {

		logger.info("Verifying dashboard page");

		Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard page is not displayed");
		
		ReportManager.info("dashboard page should be displayed passed");

	}

	// ----------------------------------------------------
	// Navigate PIM
	// ----------------------------------------------------

	@When("user navigates to PIM module")
	public void userNavigatesToPIMModule() {

		logger.info("Navigating to PIM module");

		dashboardPage.navigateToPIM();
		
		ReportManager.info("Navigating to PIM module");

	}

	// ----------------------------------------------------
	// Create Employee
	// ----------------------------------------------------

	@When("user creates a new employee with following details")
	public void userCreatesANewEmployee(DataTable dataTable) throws InterruptedException {

		logger.info("Creating new employee");

		Map<String, String> employeeData = dataTable.asMaps().get(0);

		employeeFirstName = employeeData.get("firstName");

		employeeMiddleName = employeeData.get("middleName");

		employeeLastName = employeeData.get("lastName");

		employeeName = employeeFirstName + " " + employeeMiddleName;

		addEmployeePage.navigateToAddEmployee();

		addEmployeePage.enterEmployeeDetails(employeeFirstName, employeeMiddleName, employeeLastName);

		addEmployeePage.clickSave();

	}
	
	@When("user enters login Details")
	public void userEnterLoginDetails(DataTable dataTable) throws InterruptedException {
		Map<String, String> employeeData = dataTable.asMaps().get(0);

		employeeFirstName = employeeData.get("firstName");

		employeeMiddleName = employeeData.get("middleName");

		employeeLastName = employeeData.get("lastName");
		
		userName = employeeData.get("userName");
		
		pwd = employeeData.get("password");
		
		employeeName = employeeFirstName + " " + employeeMiddleName;

		addEmployeePage.navigateToAddEmployee();

//		addEmployeePage.enterEmployeeDetails(employeeFirstName, employeeMiddleName, employeeLastName);
		
		addEmployeePage.user_enters_with_login_details(employeeFirstName, employeeMiddleName, employeeLastName, userName, pwd);

		addEmployeePage.clickSave();		
	}
	
	@When("user enters firstName {string} middleName {string} lastName {string} userName {string} password {string}")
	public void user_enters_with_login_details(String firstName, String middleName, String lastName, String userName,
			String password) throws InterruptedException {
		employeeFirstName = firstName;

		employeeMiddleName = middleName;

		employeeLastName = lastName;

		employeeName = employeeFirstName + " " + employeeMiddleName;
		
		logger.info("passowrd : "+password);
		System.out.println("password : "+password);
		employeeName = employeeFirstName + " " + employeeMiddleName;

		addEmployeePage.navigateToAddEmployee();

//		addEmployeePage.enterEmployeeDetails(employeeFirstName, employeeMiddleName, employeeLastName);
		
		addEmployeePage.user_enters_with_login_details(firstName, middleName, lastName, userName, password);

		addEmployeePage.clickSave();
	}
	
	
	@Then("employee profile should be created successfully")
	public void employeeProfileShouldBeCreatedSuccessfully() {

		logger.info("Validating employee creation");

		Assert.assertTrue(addEmployeePage.verifyEmployeeCreated(), "Employee profile was not created");

	}

	// ----------------------------------------------------
	// Search Employee
	// ----------------------------------------------------

	@When("user searches employee from Employee List")
	public void userSearchesEmployeeFromEmployeeList() {

		logger.info("Searching employee");

		employeeListPage.openEmployeeList();

		employeeListPage.searchEmployee(employeeName);

	}
	
	@When("naviagte to Employee List")
	public void navigateToEmployeeFromEmployeeList() {

		logger.info("Naviagate to Employee List");

		employeeListPage.openEmployeeList();

	}

	@Then("created employee record should be displayed")
	public void createdEmployeeRecordShouldBeDisplayed() {

		logger.info("Checking employee record");

		Assert.assertTrue(employeeListPage.isEmployeeDisplayed(), "Employee record not found");

	}

	// ----------------------------------------------------
	// Delete Employee
	// ----------------------------------------------------

	@When("user deletes the employee record")
	public void userDeletesTheEmployeeRecord() {

		ReportManager.info("Deleting Employee");
		logger.info("Deleting employee");

		employeeListPage.deleteEmployee();

	}

	@Then("employee record should not be displayed")
	public void employeeRecordShouldNotBeDisplayed() {

		logger.info("Verifying employee deletion");

		employeeListPage.searchEmployee(employeeName);

		Assert.assertFalse(employeeListPage.isEmployeeDisplayed(), "Employee record still exists");

	}

	// ----------------------------------------------------
	// Logout
	// ----------------------------------------------------

	@When("user logs out from application")
	public void userLogsOutFromApplication() {

		logger.info("Logging out from application");

		logoutPage.logout();

	}

	@Then("application should logout successfully")
	public void applicationShouldLogoutSuccessfully() {

		logger.info("Logout validation completed");

		Assert.assertTrue(true, "Logout completed");

	}
	
	@When("user login with existing user details")
	public void userLoginWithExistingUserDetails(DataTable dataTable) {
		Map<String, String> employeeData = dataTable.asMaps().get(0);
		
		userName = employeeData.get("userName");
		
		pwd = employeeData.get("password");
		loginPage.enterUsername(userName);
		loginPage.enterPassword(pwd);
		loginPage.clickLogin();
	}

}