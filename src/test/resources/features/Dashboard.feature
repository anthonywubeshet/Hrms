#Author: anthony@syntaxtechs.com
Feature: Dashboard
	@tag
  Scenario: Dashboard menu view for admin
    Given user is logged in with valid admin credentials
    Then user sees dashboard menu is displayed
      | Admin | PIM | Leave | Time | Recruitment | Performance | Dashboard | Directory |
