package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class LogInPage extends BasePage {
	
	private WebDriver driver;

By username=By.id("username");
By password=By.id("password");
By loginButton=By.id("loginBtn");
By signupLink=By.linkText("Sign up");

//create constructor 

public LogInPage(WebDriver driver) {
	this.driver=driver;
	
}

// Page Actions:

public String getLogInPageTitle() {
	return driver.getTitle();
}

public boolean verifySignUpLink() {
	return driver.findElement(signupLink).isDisplayed();
}

public HomePage doLogIn(String username,String Password) {
	driver.findElement(this.username).sendKeys(username);
	driver.findElement(this.password).sendKeys(Password);
	driver.findElement(this.loginButton).click();
	
	return new HomePage(driver);
}
	
}
