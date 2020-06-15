Feature: Login

  @smoke
  Scenario: valid admin login
    When user enters valid admin username and password
    And User clicks on login button
    Then admin user is successfully logged in

  #@smoke
  #Scenario: valid ess login
  # When user enters valid ess username and password
  # And User clicks on login button
  # Then ess user is successfully logged in
  Scenario Outline: 
    When user enter "<Username>" and "<Password>"
    And User clicks on login button
    Then "<FirstName>" is successfully logged in

    Examples: 
      | Username | Password       | FirstName |
      | Admin    | Hum@nhrm123    | Admin     |
      | Gince    | SyntaxHrms123! | Zuhaira   |

  @regression
  Scenario: Login with valid username and invalid password
    When User enter valid username and invalid password
    And User clicks on login button
    Then User sees Invalid Credentials text on login page

  @temo
  Scenario: Login with invalid credentials
    When I enter invalid username and password and see error message
      | Username | Password   | ErrorMessage        |
      | Admin    | Admin123   | Invalid credentials |
      | Hello    | Syntax123! | Invalid credentials |
