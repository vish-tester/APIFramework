package com.ui.tests;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.pages.GoogleSearchPage;

import testNGBasePack.BaseClass;

public class UIGoogleTests extends BaseClass {


	WebDriver driver;
	GoogleSearchPage googleSearchPage =new GoogleSearchPage();
	String textToSearch = properties.getProperty("textToSearch");
	
	@BeforeTest
	public void setUp() {
		logger.info("Creating the chrome driver");
		driver = instantiateDriver();
	}
	
	  @Test
	    public void testMethod() {
		  logger.info("Opening google chrome");
	        driver.get(properties.getProperty("baseUrl"));
	        driver.manage().window().maximize();
	        
	        Assert.assertTrue(driver.getTitle().equals("Google"));
	    }
	  
	  @Test(dependsOnMethods = "testMethod" )
	  public void openSearchResults() {
		  
		
		  
		 
		  googleSearchPage.getSearchResult(textToSearch);
		  try {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		  wait.until(ExpectedConditions.titleIs(textToSearch+" - Google Search"));
		  System.out.println(driver.getTitle()); }
		  catch(Exception e) {
			  logger.error("Title was not validated ");
			  e.printStackTrace();
		  }
		  
		  Assert.assertTrue(driver.getTitle().equals(textToSearch+" - Google Search"));
		  
		  
	  }
	  
	  @Test(dependsOnMethods = "openSearchResults" )
	  public void getBrokenLinksTest() {
		  try {
			  logger.info("Inside the test case to fetch all broken links");
		  googleSearchPage.getBrokenLinks();
		  }
		  catch (IOException e) {
			  logger.info("IO Exception occured");
			  e.printStackTrace();
		  }
	  }
	  
	  @AfterTest(alwaysRun = true)
		public void cleanUp() {
			logger.info("Closing browser");
			quitBrowser();
		}
}
