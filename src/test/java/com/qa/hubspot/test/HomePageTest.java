package com.qa.hubspot.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utils.Constants;


public class HomePageTest extends BaseTest {
	
	HomePage homePage;
	
		@BeforeClass
		public void homeSetUp() {
			homePage=loginpage.doLogIn(prop.getProperty("username"),prop.getProperty("password"));

		}
		
	@Test(priority=3)
	
	public void verifyHomepageTitle() {
		
		String title=homePage.getHomePageTitle();
		System.out.println("Home page title is " +title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE,"Home page title is not matched");
		
	}
	
@Test(priority=1)
	
	public void verifyHomepageHeader() {
		
	
		String header=homePage.getHomePageHeaderText();
		System.out.println("Home page Header is " +header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER,"Home page Header is not matched");
		
	}

@Test(priority=2)
public void verifyreporttext() {
	String reporttext=homePage.getreporttext();
	
	System.out.println("report text is " +reporttext);
	Assert.assertEquals(reporttext, "Reports");
}
	
	
	
	

}
