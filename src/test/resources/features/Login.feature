#Author
Feature: Login
	@te
  Scenario Outline: valid admin and ess login
    When user enter "<Username>" and "<Password>"
    And User clicks on login button
    Then "<FirstName>" is successfully logged in

    Examples: 
      | Username | Password       | FirstName |
      | Admi    | Hum@nhrm123    | Admin     |
      | Gince    | SyntaxHrms123! | Zuhaira   |

  Scenario Outline: Error message validation
      When user enter "<Username>" and "<Password>"
    And User clicks on login button
    Then user see "<ErrorMessage>"
    
    Examples:
      | Username | Password   | ErrorMessage             |
      | Admin    | Admin123   | Invalid credentials      |
      | Hello    | Syntax123! | Invalid credentials      |
      | Admin    |            | Password cannot be empty |
      |          | Syntax123! | Username cannot be empty |
