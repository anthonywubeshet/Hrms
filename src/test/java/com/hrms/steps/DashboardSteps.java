package com.hrms.steps;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.hrms.utils.CommonMethods;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;


public class DashboardSteps extends CommonMethods{
	@Then("user sees dashboard menu is displayed")
	public void user_sees_dashboard_menu_is_displayed(DataTable dashboardMenu) {
		List<String> expectedMenu=dashboardMenu.asList();
		List<String> actualMenu=new ArrayList<String>();
		
		for(WebElement el:dashboard.dashMenu) {
			actualMenu.add(el.getText());
		}
		
		System.out.println(expectedMenu);
		System.out.println(actualMenu);
		
		Assert.assertTrue(actualMenu.equals(expectedMenu));
	}
	

}
