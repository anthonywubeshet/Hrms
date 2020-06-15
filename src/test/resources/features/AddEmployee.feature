#Author: syntaxteam
@sprint6 @addEmployee
Feature: Add new Employee

  Background: 
    Given user is logged in with valid admin credentials
    And user navigates to AddEmployeePage

  Scenario: Add Employee with first and lastname
    When user enters employees "jonny" and "smitthy"
    And user clicks save button
    Then "jonny smitthy" is added successfully

  Scenario: Add Employee without employee id
    When user enters employees first name and last name
    And user deletes employee id
    And user clicks save button
    Then employee is added successfully

  Scenario: Add Employee and create Login Credentials
    When user enters employees first name and last name
    And user clicks on create login checkbox
    And user enters login credentials
    And user clicks save button
    Then employee is added successfully

  #to perform DDT in cucumber we use scenario outline with examples
  Scenario Outline: Adding multiple employees
    When user enters employee "<FirstName>", "<MiddleName>" and "<LastName>"
    And user clicks save button
    Then "<FirstName>", "<MiddleName>" and "<LastName>" is added successfully

    Examples: 
      | FirstName | MiddleName | LastName |
      | Jame      | L          | Smithy   |
      | Loius     | V          | Same     |
      | Lauren    | W          | Walrus   |
      | Yonder    | E          | James    |

  #adding multiple employees using Cucumber DataTable
  Scenario: 
    When user enters employee details and click on save 
      | FirstName | MiddleName | LastName |
      | John      | f          | Smith    |
      | Janes     | R          | Smith    |
     Then employee is added 
     
    @excel
   Scenario: Adding multiple employees from excel
   When user enters employee data from "Employee" excel sheet then employee is added
   

