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
import com.qa.hubspot.utils.OptionsManager;

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
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();

	public static  synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public WebDriver init_driver(Properties prop) {
		
		optionsManager=new OptionsManager(prop);
		String browsername=prop.getProperty("browser");
		if(browsername.equalsIgnoreCase("chrome")) {
		
		WebDriverManager.chromedriver().setup();
		tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
	}
		else if(browsername.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
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
	 * This method is used to initiaize the proprty from cofig.properties file on the basis of given env variable.
	 * @return
	 */
	
	public Properties init_prop() {
		
		String path=null;
		String env=null;
		
		prop= new Properties();
		try {
			
			env=System.getProperty("env");
			
		System.out.println("env value is =====> " +env);
			if(env==null) {
				path=".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
			}else {
				
				switch (env) {
				case "qa":
					path=".\\src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties";
					break;
				case "dev":
					path=".\\src\\main\\java\\com\\qa\\hubspot\\config\\dev.config.properties";
					break;
				case "stage":
					path=".\\src\\main\\java\\com\\qa\\hubspot\\config\\stage.config.properties";
					break;

				default:
					System.out.println("Please pass the correct env value " +env);
					break;
				}
			}
			
			
				
				
			FileInputStream ip=new FileInputStream(path);
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
