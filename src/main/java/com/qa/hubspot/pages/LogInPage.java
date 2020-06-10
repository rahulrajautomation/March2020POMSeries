package com.qa.hubspot.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class LogInPage extends BasePage {
	
	private WebDriver driver;

By username=By.id("username");
By password=By.id("password");
By loginButton=By.id("loginBtn");
By signupLink=By.linkText("Sign up");

//create constructor 

public LogInPage(WebDriver driver) {
	this.driver=driver;
	
	elementUtil=new ElementUtil(this.driver);
	
}

// Page Actions:

 @Step("get login page title")
 public String getLogInPageTitle() {
	return elementUtil.WaitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 10);
}
 @Step("check signup link on login page")

 public boolean verifySignUpLink() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	return	elementUtil.doisdiplayed(signupLink);
}

 @Step("Login to app with user name: {0} and password: {1}")
public HomePage doLogIn(String username,String Password) {
	
	elementUtil.waitForElementPresent(this.username, 10);
	elementUtil.dosendkeys(this.username, username);
	elementUtil.dosendkeys(this.password, Password);
	elementUtil.doclick(this.loginButton);
	
	return new HomePage(driver);
}
	
}
