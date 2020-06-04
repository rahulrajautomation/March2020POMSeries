package com.qa.hubspot.test;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.ContactPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactPageTest extends BaseTest{
	
	HomePage homePage;
	ContactPage contactpage;

	@BeforeClass
	
	public void contactsSetUp() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homePage=loginpage.doLogIn(prop.getProperty("username"),prop.getProperty("password"));
		contactpage=homePage.goToContactPage();	
	}
	
	@Test(priority=1)
	
	public void verifyContactsPageTitleTest() {
		String title=contactpage.getContactPageTitle();
		System.out.println("Contacts page title is " + title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}
	
	@DataProvider
	
	public Object[][] getContactsTestData() {
		Object data[][]=ExcelUtil.getTestData(Constants.CONTACT_SHEET_NAME);
		return data;
	}
	
@Test(priority=2,dataProvider="getContactsTestData")
	
	public void createContactTest(String email,String firstName,String lastName,String jobTitle) {
		contactpage.createConatct(email,firstName,lastName,jobTitle);
		
	}
	
	
}
