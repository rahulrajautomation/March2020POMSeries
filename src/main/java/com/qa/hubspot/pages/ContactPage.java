package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class ContactPage extends BasePage{

	private WebDriver driver;

	By header=By.cssSelector("h1.IndexPageRedesignHeader__StyledH1-ljkrr-1.dTXiYC");
	By createContactPrimary=By.xpath("//span[text()='Create contact']");
	By email=By.xpath("//input[@data-field='email']");
	By firstname=By.xpath("//input[@data-field='firstname']");
	By lastname=By.xpath("//input[@data-field='lastname']");
	By jobtitle=By.xpath("//input[@data-field='jobtitle']");
	By createContactSecondary=By.xpath("(//span[text()='Create contact'])[last()]");
	
	By contactsBackLink=By.xpath("(//*[text()='Contacts'])[position()=1]");
	
	public ContactPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(this.driver);

	}
	
	public String getContactPageTitle() {
		return elementUtil.WaitForTitleToBePresent(Constants.CONTACTS_PAGE_TITLE, 10);
	}
	
	public String getContactsPageHeader() {
		elementUtil.waitForElementToBeVisible(header, 5);
		return elementUtil.dogettext(header);
	}

	public void createConatct(String email,String firstname,String lastname,String jobtitle) {
		elementUtil.ClickWhenReady(createContactPrimary, 10);
		
		elementUtil.waitForElementToBeVisible(this.email, 5);
		elementUtil.dosendkeys(this.email, email);
		elementUtil.dosendkeys(this.firstname, firstname);
		elementUtil.dosendkeys(this.lastname, lastname);
		elementUtil.waitForElementToBeVisible(this.jobtitle, 5);
		elementUtil.dosendkeys(this.jobtitle, jobtitle);
		
		elementUtil.waitForElementPresent(createContactSecondary, 10);
		
		elementUtil.doActionclick(createContactSecondary);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		elementUtil.ClickWhenReady(contactsBackLink, 5);
	}
}
