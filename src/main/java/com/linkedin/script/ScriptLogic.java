package com.linkedin.script;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Script that navigates through search result, takes companies' urls and writes .csv file with the results
 *
 */
public class ScriptLogic 
{
	private static String username = "alice.start123@gmail.com";
	private static String password = "Install_new!";
	private static String linkedInUrl = "https://www.linkedin.com/";
	
    public static void main( String[] args ) throws InterruptedException, AWTException
    {
    	WebDriver driver = new FirefoxDriver();
    	driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
    	driver.get(linkedInUrl);
    	
    	//User log in
    	WebElement loginField = driver.findElement(By.id("login-email"));
    	loginField.click();
    	loginField.sendKeys(username);
    	WebElement passwordField = driver.findElement(By.id("login-password"));
    	passwordField.click();
    	passwordField.sendKeys(password);
    	WebElement signInButton = driver.findElement(By.xpath("//input[@name='submit']"));
    	signInButton.click();
    	
    	WebElement searchButton = driver.findElement(By.cssSelector(".search-button"));
    	searchButton.click();
    	Thread.sleep(1000);
    	
    	//Search by countnry
    	/*SearchData searchData = new SearchData();
    	Thread.sleep(2000);
    	ArrayList<String> locationsList = searchData.locationsList();
    	for (int i = 0; i < locationsList.size(); i++ ) {
    		//Click on add location button
    		driver.findElement(By.cssSelector("#facet-CCR .add-facet-button")).click();
    		WebElement addLocation = driver.findElement(By.cssSelector("#facet-CCR .facet-values-container .add-facet-typeahead .facet-typeahead"));
    		addLocation.click();
    		addLocation.sendKeys(locationsList.get(i));
    		  Thread.sleep(2000);
    		  //addLocation.click();
    		  //addLocation.sendKeys(Keys.DOWN);
    		  Thread.sleep(2000);
    		addLocation.sendKeys(Keys.RETURN);
    		Thread.sleep(1000);
    	} */
    	
    	//Select company type
    	driver.findElement(By.cssSelector("div #search-types li:nth-child(4)")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.id("facet-CS")).click();
    	//Company size 1-10
    	driver.findElement(By.id("B-CS-ffs")).click();
    	Thread.sleep(2000);
    	//Company size 11-50
    	driver.findElement(By.id("C-CS-ffs")).click();
    	Thread.sleep(2000);
    	//Company size 51-200
    	driver.findElement(By.id("D-CS-ffs")).click();
    	Thread.sleep(2000);
    	//Company size 201-500
    	driver.findElement(By.id("E-CS-ffs")).click();
    	Thread.sleep(2000);
    	//Company size 501-1000
    	driver.findElement(By.id("F-CS-ffs")).click();
    	Thread.sleep(2000);
    	
    	//Form array with companies links
    	Map<String, String> companyNameWithUrl = new HashMap<String, String>();
    	List<WebElement> ompaniesURL =  new ArrayList<WebElement>();
    	ompaniesURL = driver.findElements(By.cssSelector(".main-headline"));
    	for (int i = 0; i < ompaniesURL.size(); i++) {
    		
    		String newUrl = ompaniesURL.get(i).getAttribute("href");
    		String companyName = ompaniesURL.get(i).getText();   		
    		companyNameWithUrl.put(companyName, newUrl);
    		List<WebElement> ex = driver.findElements(By.cssSelector(".next"));   		
    		if (i == 9 & ex.size() != 0){
    			WebElement nextButton = driver.findElement(By.cssSelector(".next"));
    			nextButton.click();
    			Thread.sleep(5000);
    			i=0;
    			ompaniesURL = driver.findElements(By.cssSelector(".main-headline"));
    		}
    	}
    	
    	//Write .csv file and put it in root directory
    	CSVFileWriter csvWriter = new CSVFileWriter();
    	csvWriter.writeCSVFile("companies.csv", companyNameWithUrl);
    	//driver.close();
    }
}
