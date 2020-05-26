package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * this method is used to initialize the driver on basis of browser
 * @author Rahul
 *
 */

public class BasePage {

	WebDriver driver;
	Properties prop;
	
	public WebDriver init_driver(Properties prop) {
		
		String browsername=prop.getProperty("browser");
		if(browsername.equalsIgnoreCase("chrome")) {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
	}
		else if(browsername.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.chromedriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browsername.equalsIgnoreCase("safari")) {
			
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver=new SafariDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	/**
	 * This method is used to initiaize the proprty from cofig.properties file
	 * @return
	 */
	
	public Properties init_prop() {
		
		prop= new Properties();
		try {
			FileInputStream ip=new FileInputStream(".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
