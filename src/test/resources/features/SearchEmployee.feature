@searchEmployee @sprint13
Feature: Employee Search

  Background: 
    Given user is logged in with valid admin credentials
    And user navigates to employee list page

  @smoke
  Scenario: Search employee by id
    When user enters valid employee id "1453"
    And clicks on search button
    Then user sees employee information is displayed

  @regression
  Scenario: Search employee by name
    When user enters valid employee "John" and "Smith"
    And clicks on search button
    Then user sees employee information is displayed
