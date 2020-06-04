package com.qa.hubspot.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage{
	
	WebDriver driver;
	JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
		jsUtil=new JavaScriptUtil(this.driver);
	}
	
	public List<WebElement> getElements(By locator){
		List<WebElement> elementList=driver.findElements(locator);
		return elementList;
	}
	public WebElement getElement(By locator){
		WebElement element=null;
		try{
			System.out.println("locator is " +locator);
			element=driver.findElement(locator);
			if(prop.getProperty("highlight").equalsIgnoreCase("yes")) {
			jsUtil.flash(element);}
			System.out.println("Web element is created successfully " + locator);
}catch (Exception e) {
	System.out.println("some exception got occured" + locator);
}
		
		return element;
	}
	
	public void dosendkeys(By locator,String value)
	{
		getElement(locator).sendKeys(value);;
	}
	
	public void doclick(By locator)
	{
		getElement(locator).click();
	}
	
	public String dogettext(By locator)
	{
		String str=getElement(locator).getText();
		return str;
	}
	
	public boolean doisdiplayed(By locator)
	{
		return getElement(locator).isDisplayed();
		
	}
	
	//*****************DropDown Utils************************
	
	public  void doselectbyvisibletext(By locator, String value)
	{
		Select select=new Select(getElement(locator));
		select.selectByVisibleText(value);	
	}

	public  void doselectbyindex(By locator, int index)
	{
		Select select=new Select(getElement(locator));
		select.selectByIndex(index);	
	}
	public  void doselectbyvalue(By locator, String value)
	{
		Select select=new Select(getElement(locator));
		select.selectByValue(value);	
	}
	public int dodropdownoptioncount(By locator){
		return doGetDropDownOptions(locator).size();
	}
		public ArrayList<String> doGetDropDownOptions(By locator){
			
			ArrayList<String> ar=new ArrayList<String>();
			Select select =new Select(getElement(locator));
			List<WebElement> Optionlist=select.getOptions();
			
			//System.out.println(Optionlist.size());
			
			for(int i=0;i<Optionlist.size();i++)
			{
				String text=Optionlist.get(i).getText();
				ar.add(text);
				//System.out.println(text);
			
		}return ar;
	}
		public void doselectdropdownvalue(By locator,String value){
			
			Select selectday =new Select(getElement(locator));
			List<WebElement> optionslist=selectday.getOptions();
			
			//System.out.println(dayslist.size());
			
			for(int i=0;i<optionslist.size();i++)
			{
				String text=optionslist.get(i).getText();
				//System.out.println(text);
				
				if(text.equals(value)){
					optionslist.get(i).click();break;
				}
			}
		}
		
		public void doSelectDropDownValueWithoutSelect(By locator,String value)
		{
			List<WebElement> optionslist =getElements(locator);
			for(int i=0;i<optionslist.size();i++)
			{
				String text=optionslist.get(i).getText();
				System.out.println(text);
				if(text.equals(value)){
					optionslist.get(i).click();break;
				}
					
			}
		}
		
		public void selectChoiceValues(By locator,String... value){
			
			//List<WebElement> choiceList =driver.findElements(By.cssSelector("span.comboTreeItemTitle"));
			
			List<WebElement> choiceList =getElements(locator);

				if(! value[0].equalsIgnoreCase("all")){
					
					for(int i=0;i<choiceList.size();i++)
					{
						String st=choiceList.get(i).getText();
						
						System.out.println(st);
						
						for(int k=0;k<value.length;k++)
						{if(st.equals(value[k])){
							choiceList.get(i).click();break;}
							
							
						}
						
						}
					}
					
				else{
					try{
					for(int all=0;all<choiceList.size();all++)
					{
						choiceList.get(all).click();
					}
				}
					catch(Exception e){
						
					}
				}

			}
		//*****************Actions class Utils************************

		public void doDragAndDrop(By source,By target)
		{
			Actions action=new Actions(driver);
			WebElement sourceEle=getElement(source);
			WebElement tarEle=getElement(target);

			action.dragAndDrop(sourceEle, tarEle).build().perform();;

		}
		
		public void doActionsendkeys(By locator,String value)
		{
			Actions action=new Actions(driver);
			action.sendKeys(getElement(locator),value).build().perform();
		}
		
		public void doActionclick(By locator)
		{
			Actions action=new Actions(driver);
			action.click(getElement(locator)).build().perform();
		}
		
		//********************************Wait Utils******************
		
		public List<WebElement> visiblityOfAllElemets(By locator,int timeout){
			WebDriverWait wait=new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		
		
		public WebElement waitForElementPresent(By locator,int timeout){
			WebDriverWait wait=new WebDriverWait(driver, timeout);
			WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return element;

		}
		
		public WebElement waitForElementToBeVisible(By locator,int timeout){
			WebElement element=getElement(locator);
			WebDriverWait wait=new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOf(element));
			return element;
		
		}

		public WebElement waitForElementToBeClickable(By locator,int timeout){
			WebDriverWait wait=new WebDriverWait(driver, timeout);
			WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
			return element;

		}
		public Boolean waitForURL(String url,int timeout){
			WebDriverWait wait=new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.urlContains(url));

		}
		public Alert waitForAlertToBePresent(int timeout){
			WebDriverWait wait=new WebDriverWait(driver, timeout);
			Alert alert= wait.until(ExpectedConditions.alertIsPresent());
			return alert;

		}
		
		//ClickWhenReady

		public void ClickWhenReady(By locator,int timeout){
			WebDriverWait wait=new WebDriverWait(driver, timeout);
			WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();
		}
		public String WaitForTitleToBePresent(String title,int timeout){
			WebDriverWait wait=new WebDriverWait(driver, timeout);
			 wait.until(ExpectedConditions.titleContains(title));
			 return driver.getTitle();
		}


}
