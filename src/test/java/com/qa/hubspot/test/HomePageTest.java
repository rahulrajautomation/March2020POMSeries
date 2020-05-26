package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LogInPage;
import com.qa.hubspot.utils.Constants;


public class HomePageTest {
	
	WebDriver driver;

	BasePage basepage;
	LogInPage loginpage;
	HomePage homepage;
	Properties prop;
	
	@BeforeTest
	
	public void setup() {
		basepage=new BasePage();
		prop=basepage.init_prop();
		driver= basepage.init_driver(prop);
		loginpage=new LogInPage(driver);
		
		homepage=loginpage.doLogIn(prop.getProperty("username"),prop.getProperty("password"));

		
	}
	
	@Test(priority=3)
	
	public void verifyHomepageTitle() {
		
		String title=homepage.getHomePageTitle();
		System.out.println("Home page title is " +title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE,"Home page title is not matched");
		
	}
	
@Test(priority=1)
	
	public void verifyHomepageHeader() {
		
		String header=homepage.getHomePageHeaderText();
		System.out.println("Home page Header is " +header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER,"Home page Header is not matched");
		
	}

@Test(priority=2)
public void verifyreporttext() {
	String reporttext=homepage.getreporttext();
	
	System.out.println("report text is " +reporttext);
	Assert.assertEquals(reporttext, "Reports");
}
	
	@AfterTest
	
	public void teardown() {
		driver.quit();
	}
	
	

}
