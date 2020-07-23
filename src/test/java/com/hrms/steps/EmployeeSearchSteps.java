package com.hrms.steps;

import org.junit.Assert;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import com.hrms.utils.GlobalVariables;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class EmployeeSearchSteps extends CommonMethods {
	

	@Given("user navigates to employee list page")
	public void user_navigates_to_employee_list_page() {
	    jsClick(dashboard.pim);
	    jsClick(dashboard.empListPage);
	}

	@When("user enters valid employee id {string}")
	public void user_enters_valid_employee_id(String empId) {
	    sendText(viewEmp.empID, empId);
	    GlobalVariables.empID= empId;
	}

	@When("clicks on search button")
	public void clicks_on_search_button() {
	   jsClick(viewEmp.searchBtn);
	}

	@Then("user sees employee information is displayed")
	public void user_sees_employee_information_is_displayed() {
	  System.out.println("Employee is displayed");
	  tearDown();
	}

	@When("user enters valid employee {string} and {string}")
	public void user_enters_valid_employee_name_and_last_name() {
	  sendText(viewEmp.empName, "");
	}
	
	@When("User enter valid username and invalid password")
	public void user_enter_valid_username_and_invalid_password() {
		 sendText(login.username, ConfigsReader.getProperty("username"));
		 sendText(login.password, "123");
	}
	
	@Then("verify table is displayed")
	public void verify_table_is_displayed() {
	    Assert.assertEquals(true, viewEmp.isTableDisplayed());
	}

	@Then("get first name from table")
	public void get_first_name_from_table() {
	   System.out.println(viewEmp.getFirstNameFromTable());
	}
	
	@Then("validate first name from ui against db")
	public void validate_first_name_from_ui_against_db() {
	    Assert.assertEquals(DBSteps.dbData, viewEmp.getFirstNameFromTable());
	}

}
