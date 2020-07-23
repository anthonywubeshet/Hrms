Feature: Name validations against DB 

Scenario: First Name validation against DB
  Given user is logged in with valid admin credentials
  And user navigates to employee list page
  When user enters valid employee id "14688"
	And clicks on search button
	Then verify table is displayed 
	And get first name from table
	When get first name from db
	Then validate first name from ui against db