package com.hrms.steps;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginSteps extends CommonMethods {

	@Given("user navigated to HRMS")
	public void user_navigated_to_HRMS() {
	   setUp();
	   
	}

	@Given("user is logged in with valid admin credentials")
	public void user_is_logged_in_with_valid_admin_credentials() {
	   sendText(login.username, ConfigsReader.getProperty("username"));
	   sendText(login.password, ConfigsReader.getProperty("password"));
	   click(login.loginBtn);
	}
	
	@When("user enters valid admin username and password")
	public void user_enters_valid_admin_username_and_password() {
		sendText(login.username, ConfigsReader.getProperty("username"));
		sendText(login.password, ConfigsReader.getProperty("password"));
	}

	@When("User clicks on login button")
	public void user_clicks_on_login_button() {
		 click(login.loginBtn);
	}

	@Then("admin user is successfully logged in")
	public void admin_user_is_successfully_logged_in() {
	 String expected = "Welcome Admin";
	 String actual = dashboard.welcome.getText();
	 Assert.assertEquals(actual, expected);
	 System.out.println(actual);
	 wait(3);
	 tearDown();
	}

	@When("user enters valid ess username and password")
	public void user_enters_valid_ess_username_and_password() {
		login.login("Gince", "SyntaxHrms123!");
	}

	@Then("ess user is successfully logged in")
	public void ess_user_is_successfully_logged_in() {
		String expected = "Welcome Elvira";
		 String actual = dashboard.welcome.getText();
		 Assert.assertEquals(actual, expected, "Welcome message is not displayed or not correct");
		 System.out.println(actual);
		 tearDown();
	}


	@Then("User sees Invalid Credentials text on login page")
	public void user_sees_Invalid_Credentials_text_on_login_page() {
		String expected= "Invalid credentials";
		String actual=login.errorMsg.getText();
		Assert.assertEquals(expected, actual);
		System.out.println(actual);
		tearDown();
	   }
	
	@When("user enter {string} and {string}")
	public void user_enter_valid_and(String username, String password) {
	   sendText(login.username, username);
	   sendText(login.password, password);
	}
	
	@Then("user see {string}")
	public void user_see(String errorMessage) {
		Assert.assertEquals(errorMessage, login.errorMsg.getText());
	}


	@Then("{string} is successfully logged in")
	public void is_successfully_logged_in(String firstName) {
		String expected = "Welcome "+firstName;
		 String actual = dashboard.welcome.getText();
		 Assert.assertEquals(actual, expected);
		 System.out.println(actual);
	}
	
	@When("I enter invalid username and password and see error message")
	public void i_enter_invalid_username_and_password_and_see_error_message(DataTable invalidCredentials) {
		List<Map<String, String>> list=invalidCredentials.asMaps();
	    for (Map<String, String> map : list) {
			sendText(login.username, map.get("Username"));
			sendText(login.password, map.get("Password"));
			click(login.loginBtn);
			
			Assert.assertEquals(map.get("ErrorMessage"), login.errorMsg.getText());
		}
	}
}
