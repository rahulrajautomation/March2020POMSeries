package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class HomePage extends BasePage {
	
	private WebDriver driver;

	By header=By.cssSelector("h1.private-page__title");
	By reportss=By.id("nav-primary-reports-branch");
	
	public HomePage(WebDriver driver) {
		
		this.driver=driver; 
	}
	
	public String getHomePageTitle() {
		return driver.getTitle();
	}
	
	public String getHomePageHeaderText() {
		if(driver.findElement(header).isDisplayed()) {
		return driver.findElement(header).getText();
	}
		return null;
}
	public String getreporttext() {
		if(driver.findElement(reportss).isDisplayed()) {
		return driver.findElement(reportss).getText();
	}
		return null;
}
}
