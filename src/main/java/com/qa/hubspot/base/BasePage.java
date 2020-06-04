package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.ElementUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * this method is used to initialize the driver on basis of browser
 * @author Rahul
 *
 */

public class BasePage {

	WebDriver driver;
	public Properties prop;
	public ElementUtil elementUtil;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();

	public static  synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public WebDriver init_driver(Properties prop) {
		
		String browsername=prop.getProperty("browser");
		if(browsername.equalsIgnoreCase("chrome")) {
		
		WebDriverManager.chromedriver().setup();
		//driver=new ChromeDriver();
		tlDriver.set(new ChromeDriver());
	}
		else if(browsername.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		}
		else if(browsername.equalsIgnoreCase("safari")) {
			
			WebDriverManager.getInstance(SafariDriver.class).setup();
			tlDriver.set(new SafariDriver());
		}
		
		/*getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();*/
		//driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
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

	/**
	 * This method will take the screenshot
	 * @return 
	 */
	
	public String getScreenshot() {
		File src=((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		return path;
	}
}
