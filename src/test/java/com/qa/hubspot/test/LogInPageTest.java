package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LogInPage;
import com.qa.hubspot.utils.Constants;

public class LogInPageTest {
	
	BasePage basepage;
	WebDriver driver;
	LogInPage loginpage;
	Properties prop;
	
	@BeforeTest
	public void setup() {
		basepage=new BasePage();
		prop=basepage.init_prop();
		driver= basepage.init_driver(prop);
		loginpage=new LogInPage(driver);
	}
	
	@Test(priority=2)
	
	public void verifyLogInPageTitleTest()
	{
		String title=loginpage.getLogInPageTitle();
		System.out.println("login page title is " +title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE,"title not matched");
	
	}
	
	@Test(priority=1)
	
	public void verifySignUpLinkTest() {
		
		Assert.assertTrue(loginpage.verifySignUpLink(), "Signup not found");
		//Assert.assertTrue("Signup link not found ", loginpage.verifySignUpLink());
	}
	
	@Test(priority=3)
	
	public void loginTest() {
		
		loginpage.doLogIn(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@AfterTest
	
	public void teardown() {
		driver.quit();
	}

}
