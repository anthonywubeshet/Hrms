package com.hrms.testbase;

import org.openqa.selenium.By;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;

public class Practice extends CommonMethods {

	public static void main(String[] args) {
		setUp();
		login.login(ConfigsReader.getProperty("username"), ConfigsReader.getProperty("password"));
		
		String actual=dashboard.welcome.getText();
		System.out.println(actual);
		
		//driver.findElement(By.id("welcome")).getText();
		
	}

}
