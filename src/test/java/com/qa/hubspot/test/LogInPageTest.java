package com.qa.hubspot.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.listeners.ExtentReportListener;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@Listeners(ExtentReportListener.class)
//its fine now yeah thank you :)

@Epic("Epic 101 : Design Login page with different features")
@Story("User Story 102 : Design Basic login page with Signup title and login form")
public class LogInPageTest extends BaseTest {

	@Description("Verifying Login page title test.........")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)

	public void verifyLogInPageTitleTest() {
		String title = loginpage.getLogInPageTitle();
		System.out.println("login page title is " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "title not matched");

	}

	@Description("Verifying Sign up link test.........")
	@Severity(SeverityLevel.CRITICAL)

	@Test(priority = 1)

	public void verifySignUpLinkTest() {

		Assert.assertTrue(loginpage.verifySignUpLink(), "Signup not found");
		// Assert.assertTrue("Signup link not found ", loginpage.verifySignUpLink());
	}
	
	@Description("Verifying Login test.........")
	@Severity(SeverityLevel.BLOCKER)

	@Test(priority = 3)

	public void loginTest() {

		loginpage.doLogIn(prop.getProperty("username"), prop.getProperty("password"));
	}

}
