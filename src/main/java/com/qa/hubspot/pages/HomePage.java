package com.qa.hubspot.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class HomePage extends BasePage {
	
	private WebDriver driver;

	By header=By.cssSelector("#uiabstractdropdown-button-5 > span > span.private-dropdown__button-label.uiDropdown__buttonLabel > h1");
	By reportss=By.id("nav-primary-reports-branch");
	
	By primaryContactLink=By.id("nav-primary-contacts-branch");
	By secondaryContactLink=By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver) {
		
		this.driver=driver; 
		elementUtil=new ElementUtil(this.driver);

	}
	
	public String getHomePageTitle() {
		return elementUtil.WaitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);

	}
	
	public String getHomePageHeaderText() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if(elementUtil.doisdiplayed(header)) {
			System.out.println("hoiioioiiiiiiiiiiiiiiiii");
			return elementUtil.dogettext(header);
		}return null;
}
	public String getreporttext() {
		if(elementUtil.doisdiplayed(reportss)) {
			return elementUtil.dogettext(reportss);
		}return null;
}
	
	public ContactPage goToContactPage() {
		clickOnContacts();
		return new ContactPage(driver);
	}
	private void clickOnContacts() {
		elementUtil.waitForElementToBeVisible(primaryContactLink, 10);
		elementUtil.doclick(primaryContactLink);
		elementUtil.waitForElementToBeVisible(primaryContactLink, 5);
		elementUtil.doclick(secondaryContactLink);


	}
}
