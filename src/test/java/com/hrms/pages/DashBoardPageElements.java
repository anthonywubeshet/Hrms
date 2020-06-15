package com.hrms.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.testbase.BaseClass;
import com.hrms.utils.CommonMethods;

public class DashBoardPageElements extends CommonMethods{

	@FindBy(id="welcome")
	public WebElement welcome;
	
	@FindBy(xpath="//div[@id='branding']/a/img")
	public WebElement logo;
	
	@FindBy(xpath="//a[@id='menu_pim_viewPimModule']/b")
	public WebElement pim;
	
	@FindBy(xpath="//input[@id='btnAdd']")
	public WebElement addEmpBtn;
	
	@FindBy(id="menu_pim_viewEmployeeList")
	public WebElement empListPage;
	
	@FindBy(xpath="//div[@class='menu']/ul/li")
	public List<WebElement> dashMenu;
	
	public DashBoardPageElements() {
		PageFactory.initElements(BaseClass.driver, this);
	}
	
	public void navigateToAddEmployee() {
		jsClick(pim);
		jsClick(addEmpBtn);
	}

}
