package com.hrms.API.steps.practice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/**
 * Rest assured static packages
 */

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
/**
 * importing Junit packages
 */
import org.junit.Assert;
import org.junit.Test;

import org.junit.*;
import org.junit.runners.MethodSorters;
/**
 * We may use below - please comment out for now
 *
 */
//import org.apache

/**
 * This @FixMethodOrder(MethodSorters.NAME_ASCENDING) will execute @Test annotation in ascending 
 * alphabetical order
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardcodedExamples {

	
	/**
	 * Rest Assured 
	 * 
	 * given - prepare your request
	 * when - you are making the call to the endpoint
	 * then - validating
	 * 
	 * @param args
	 */
	
	static String basedURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
	static String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTUzOTAyNTQsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTQzMzQ1NCwidXNlcklkIjoiODEwIn0.NmWfTEy9BOJXD7bf3m73_o31etMUk31faD0eKssWjy0";
	static String employeeID;
	
	public void sampleTestNotes() {
		

			/**
			 * BaseURI for all calls
			 */
			RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
			
			/**
			 * JWT required for all calls - expires every 12 hours
			 */
			String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTUyOTM1NjEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTMzNjc2MSwidXNlcklkIjoiODEwIn0.5VG-JRw2Ba6NicHuCPtg_A0P7WItWWqVmkpArjpMmUo";
			
			/**
			 * Preparing /getOneEmployee.php request
			 */
			RequestSpecification getOneEmployeeRequest = given().header("Content-Type","application/json")
					.header("Authorization" , token).queryParam("employee_id", "19049A").log().all();
			
			/**
			 * Storing response
			 */
			Response getOneEmployeeResponse =getOneEmployeeRequest.when().get("/getOneEmployee.php");
			
			/**
			 * Two ways to print response
			 * prettyPrint() method converts JSON object into String and prints - no need to SOP
			 */
			getOneEmployeeResponse.prettyPrint();
			//String response = getOneEmployeeResponse.body().asString();
			
			getOneEmployeeResponse.then().assertThat().statusCode(200);
	}

	
	@Test
	public void aPostcreateEmployee() {
		
		/**
		 * Preparing request for /createEmployee.php
		 */
		
		RequestSpecification createEmployeeRequest = given().header("Content-Type","application/json").header("Authorization", token).body("{\r\n" + 
				"  \"emp_firstname\": \"Bat\",\r\n" + 
				"  \"emp_lastname\": \"Sam\",\r\n" + 
				"  \"emp_middle_name\": \"Man\",\r\n" + 
				"  \"emp_gender\": \"M\",\r\n" + 
				"  \"emp_birthday\": \"1989-07-12\",\r\n" + 
				"  \"emp_status\": \"Worker\",\r\n" + 
				"  \"emp_job_title\": \"Cloud Architect\"\r\n" + 
				"}").log().all();
		
		/**
		 * Storing response intro createEmployeeResonse
		 */
		Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");
		
		/**
		 * Printing response using prettyPrint() method
		 */
		createEmployeeResponse.prettyPrint();

		/**
		 * jsonPath() to view response body which lets us get the employee ID
		 * We store employee ID as a global variable so that we may use it 
		 * with our other calls  
		 * 
		 */
	    employeeID = createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");
		
	    /** 
	     * optional to print employee ID
	     */
		System.out.println(employeeID);
		
		/**
		 * Verifying response status code is 201 
		 */
		createEmployeeResponse.then().assertThat().statusCode(201);
		
		/**
		 * Verifying message using equalTo() method - manually import static package
		 * import static org.hamcrest.Matchers.*;
		 */
		createEmployeeResponse.then().assertThat().body("Message", equalTo("Entry Created"));
		
		/**
		 * Verifying created first name
		 */
		createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname", equalTo("Bat"));
		
		/**
		 * Verifying server using then().header()
		 */
		createEmployeeResponse.then().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
		
		/**
		 * Verifying Content-Type using then().header()
		 */
		createEmployeeResponse.then().header("Content-Type", "application/json");
		
		/**
		 * Verifying Access-Control-Allow-Methods using then().header()
		 */
		createEmployeeResponse.then().header("Access-Control-Allow-Methods", "POST");
		
		
	}

	@Test
	public void bbGetcreatedEmployee() {
		/**
		 * Preparing request for /getOneEmployee.php
		 * Using log().all() to see all information being sent with request
		 */
		RequestSpecification getCreatedEmployeeRequest = given().header("Content-Type","application/json")
		.header("Authorization", token).queryParam("employee_id", employeeID).log().all();
		
		/**
		 * Making call to retrieve created employee
		 */
		Response getCreatedEmployeeResponse = getCreatedEmployeeRequest.when().get("/getOneEmployee.php");
		
		/**
		 * Printing response using prettyPrint()
		 */
		String response = getCreatedEmployeeResponse.prettyPrint();
		
		/**
		 * Storing response employeeID into empID which will be used for verification
		 * against stored gloval employee ID
		 */
		String empID = getCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");
		
		/**
		 * matching exact employeeID's
		 */
		boolean verifyingEmployeeIDsMatch = empID.equalsIgnoreCase(employeeID);
		System.out.println("Employee ID's match: "+ verifyingEmployeeIDsMatch);
		
		/**
		 * Asserting employee ID's match
		 */
		Assert.assertTrue(verifyingEmployeeIDsMatch);
		
		/*
		 * Verifying status code is 200
		 */
		getCreatedEmployeeResponse.then().assertThat().statusCode(200);
		
		/*
		 * Using JsongPath class to retrieve values from response as a String
		 */
		JsonPath js = new JsonPath(response);
		
		String emplID = js.getString("employee[0].employee_id");
		String firstname =js.getString("employee[0].emp_firstname");
		String middlename =js.getString("employee[0].emp_middle_name");
		String lastname = js.getString("employee[0].emp_lastname");
		String emp_bday = js.getString("employee[0].emp_birthday");
		String gender = js.getString("employee[0].emp_gender");
		String jobTitle= js.getString("employee[0].emp_job_title");
		String empStatus = js.getString("employee[0].emp_status");
		
		System.out.println(emplID);
		System.out.println(firstname);
		System.out.println(middlename);
		System.out.println(lastname);
		System.out.println(emp_bday);
		System.out.println(gender);
		System.out.println(jobTitle);
		System.out.println(empStatus);
		
		/**
		 * Verifying employees ID's match
		 */
		Assert.assertTrue(emplID.contentEquals(employeeID));
		//Second way of asserting 
		Assert.assertEquals(employeeID, emplID);
	
		/**
		 * Verifying expected first name matches actual first name
		 */
		Assert.assertTrue(firstname.contentEquals("Bat"));
		/**Verifying expected middle name matches actual middle name*/
		Assert.assertTrue(middlename.contentEquals("Man"));
		/**Verifying expected last name matches actual last name*/
		Assert.assertTrue(lastname.contentEquals("Sam"));
		/**Verifying expected birthday matches actual birthday*/
		Assert.assertTrue(emp_bday.contentEquals("1989-07-12"));
		/**Verifying expected gender matches actual gender*/
		Assert.assertTrue(gender.contentEquals("Male"));
		/**Verifying expected jobTitle matches actual jobTitle*/
		Assert.assertTrue(jobTitle.contentEquals("Cloud Architect"));
		/**Verifying expected employee status matches actual employee status*/
		Assert.assertTrue(empStatus.contentEquals("Worker"));
		
		
	}

	@Test
	public void cGetAllEmployees() {
		/**
		 * Preparing /getAllEmployees.php request
		 */
		RequestSpecification getAllEmployees = given().header("Content-Type","application/json").header("Authorization", token);
		
		/**
		 * Storing response into getAllEmployeesResponse
		 */
		Response getAllEmployeesResponse = getAllEmployees.when().get("/getAllEmployees.php");
		
		/**
		 * Printing all employees
		 */
		//getAllEmployeesResponse.prettyPrint();
		
		String allEmployees = getAllEmployeesResponse.body().asString();
		
		/**
		 * The below will pass but incorrect
		 */
		//allEmployees.contains(employeeID);
		/**
		 * Do reasearch
		 */
		//allEmployees.matches(employeeID);
		
		JsonPath js = new JsonPath(allEmployees);
		
		int sizeOfList =js.getInt("Employees.size()");
		
		System.out.println(sizeOfList);
		
		/**
		 * Print out all Employtee ID's
		 *
		 */
		
		//for(int i = 0; i < sizeOfList; i++) {
			
			/**
			 * Printing all employee ID's
			 */
			//String allEmployeeIDs = js.getString("Employees["+ i +"].employee_id");
			//System.out.println(allEmployeeIDs);
			
			/**
			 * if statement inside for loop to find stored employee ID and break the loop
			 * when foind
			 */
			
			//if(allEmployeeIDs.contentEquals(employeeID)) {
			//	System.out.println("Employee ID: " + employeeID +" is present in body");
			//	String employeeFirstName = js.getString("Employees["+ i +"].emp_firstname");
			//	System.out.println(employeeFirstName);
				
			//	break;
			//}
		//}
		
		
		
	}

	@Test
	public void dPutupdateCreatedEmployee() {
		
	RequestSpecification updatedCreatedEmployeeRequest =given().header("Content-Type","application/json")
			.header("Authorization", token).body(HardcodedConstants.updateCreatedEmpBody());
		
	Response updatedCreatedEmployeeResponse =updatedCreatedEmployeeRequest.when().put("/updateEmployee.php");
	
	String response = updatedCreatedEmployeeResponse.prettyPrint();
	}
}
