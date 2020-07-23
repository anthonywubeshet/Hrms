package com.hrms.API.steps.practice;

public class HardcodedConstants {

	
	public static String updateCreatedEmpBody() {
		
		String updateBody = "{\r\n" + 
				"  \"employee_id\": \""+HardcodedExamples.employeeID+"\",\r\n" + 
				"  \"emp_firstname\": \"Roger\",\r\n" + 
				"  \"emp_lastname\": \"Loius\",\r\n" + 
				"  \"emp_middle_name\": \"Orange\",\r\n" + 
				"  \"emp_gender\": \"M\",\r\n" + 
				"  \"emp_birthday\": \"1960-04-12\",\r\n" + 
				"  \"emp_status\": \"Worker\",\r\n" + 
				"  \"emp_job_title\": \"Cloud Architect\"\r\n" + 
				"}";
		
		return updateBody;
	}
}
