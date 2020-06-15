package com.hrms.steps;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.Constants;
import com.hrms.utils.ExcelUtility;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddEmployeeSteps extends CommonMethods {

	@Given("user navigates to AddEmployeePage")
	public void user_navigates_to_AddEmployeePage() {
		dashboard.navigateToAddEmployee();
	}

	// this method has hardcoded values
	@When("user enters employees first name and last name")
	public void user_enters_employees_first_name_and_last_name() {
		sendText(addEmp.firstName, "James");
		sendText(addEmp.lastName, "Smith");
	}

	// this method is enhanced method and paramater values are supplied from feature
	// file
	@When("user enters employees {string} and {string}")
	public void user_enters_employees_first_name_and_last_name(String firstName, String lastName) {
		sendText(addEmp.firstName, firstName);
		sendText(addEmp.lastName, lastName);
	}

	@When("user clicks save button")
	public void user_clicks_save_button() {
		click(addEmp.saveBtn);
	}

	// this method has hardcoded values
	@Then("employee is added successfully")
	public void employee_is_added_successfully() {
		String actual = pdetails.personalDetails.getText();// check personalDetails
		String expectedName = "James Smeith";
		Assert.assertEquals(expectedName, actual);
	}

	// this method is enhanced method and paramater values are supplied from feature
	// file
	@Then("{string} is added successfully")
	public void employee_is_added_successfully(String expectedName) {
		String actual = pdetails.personalDetails.getText();// check personalDetails
		Assert.assertEquals(expectedName, actual);
	}

	@When("user deletes employee id")
	public void user_deletes_employee_id() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("user clicks on create login checkbox")
	public void user_clicks_on_create_login_checkbox() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("user enters login credentials")
	public void user_enters_login_credentials() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("user enters employee {string}, {string} and {string}")
	public void user_enters_employee_and(String fName, String mName, String lName) {
		sendText(addEmp.firstName, fName);
		sendText(addEmp.middleName, mName);
		sendText(addEmp.lastName, lName);
	}

	@Then("{string}, {string} and {string} is added successfully")
	public void and_is_added_successfully(String string, String string2, String string3) {
		System.out.println("is added");
	}

	@When("user enters employee details and click on save")
	public void user_enters_employee_details_and_click_on_save(DataTable dataTable) {
		List<Map<String, String>> addEmployeeList = dataTable.asMaps();

		for (Map<String, String> map : addEmployeeList) {
			
			String fname=map.get("FirstName");
			String mname= map.get("MiddleName");
			String lname=map.get("LastName");
			
			sendText(addEmp.firstName, fname);
			sendText(addEmp.middleName, mname);
			sendText(addEmp.lastName,lname);
			
			String actual= pdetails.personalDetails.getText();
			String expected=fname+" "+mname+" "+lname;
			click(addEmp.saveBtn);
			//Assert.assertEquals(expected,actual);
			jsClick(dashboard.addEmpBtn);
	}
}
	@Then("employee is added")
	public void employee_is_added() {
	   System.out.println("-----Employee is added using datatable");
	}
	
	@When("user enters employee data from {string} excel sheet then employee is added")
	public void user_enters_employee_data_from_excel_sheet_then_employee_is_added(String sheetName) {
	   List<Map<String, String>> excelList=ExcelUtility.excelIntoListOfMaps(Constants.TESTDATA_FILEPATH, sheetName);
	   for(Map<String, String> data:excelList) {
		   String fname=data.get("FirstName");
		   String mname=data.get("MiddleName");
		   String lname=data.get("LastName");
		   
		   sendText(addEmp.firstName, fname);
		   sendText(addEmp.middleName, mname);
		   sendText(addEmp.lastName, lname);
		   click(addEmp.saveBtn);
		   
		   String actual=pdetails.personalDetails.getText();
		   String expected=fname+" "+mname+" "+lname;
		   Assert.assertEquals(expected, actual);
		   jsClick(dashboard.addEmpBtn);
	   }
	}
}
