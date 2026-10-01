Feature: Employee Lifecycle Management in OrangeHRM

  Background:
    Given user launches OrangeHRM application
    
    When user logs into application with valid credentials
	
	

  @AddEmployee
  Scenario: Create search and delete employee successfully
  
	Then dashboard page should be displayed

    When user navigates to PIM module

    And user creates a new employee with following details
      | firstName | middleName | lastName |
      | John      | Test       | Automation |

    Then employee profile should be created successfully


    When user searches employee from Employee List


    Then created employee record should be displayed


    When user deletes the employee record


    Then employee record should not be displayed


    When user logs out from application


    Then application should logout successfully
    
    
    #@AddEmplooyeeWithLoginDetails
  Scenario: Create with Login Details search and delete employee successfully

    
    Then dashboard page should be displayed


    When user navigates to PIM module
	
	When user enters login Details
	| firstName | middleName | lastName 	|	userName	|	password|
	| John      | Test       | Automation 	|	John.Test		|	Admin12345|
	
    
    Then employee profile should be created successfully


    When user searches employee from Employee List


    Then created employee record should be displayed


    When user logs out from application


    Then application should logout successfully
    
    When user login with existing user details
    |	userName	|	password|
    |	John.Test		|	Admin12345|
    
    When user logs out from application
    
    Then application should logout successfully
    
    When user logs into application with valid credentials

    Then dashboard page should be displayed
    
    When user navigates to PIM module
    
    When user searches employee from Employee List


    Then created employee record should be displayed


    When user deletes the employee record
    
    Then employee record should not be displayed


    When user logs out from application


    Then application should logout successfully
    
    