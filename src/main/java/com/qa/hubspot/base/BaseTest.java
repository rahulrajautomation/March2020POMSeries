package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.hubspot.pages.LogInPage;

public class BaseTest {

	public WebDriver driver;
	
	public BasePage basepage;
	public LogInPage loginpage;
	public Properties prop;
	
	@BeforeTest
	public void setup() {
		basepage=new BasePage();
		prop=basepage.init_prop();
		driver= basepage.init_driver(prop);
		loginpage=new LogInPage(driver);
		

	}
@AfterTest
	
	public void teardown() {
		//driver.quit();
	}
}
