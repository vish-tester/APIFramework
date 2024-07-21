package com.google.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testNGBasePack.BaseClass;

public class GoogleSearchPage extends BaseClass {

	@FindBy(xpath = "//textarea[@class='gLFyf']")
	WebElement searchText;

	@FindBy(xpath = "//div[contains(@class, 'FPdoL')]/descendant::input[@class='gNO89b']")
	WebElement searchButton;

	@FindBy(xpath = "//div[@class='s6JM6d']/descendant::a")
	List<WebElement> allVisibleLinks;

	public GoogleSearchPage() {
		PageFactory.initElements(instantiateDriver(), this);
	}

	public void getSearchResult(String text) {
		logger.info("Inside method getSearchResult");

		logger.info("Check if we are home page of google.com");
		if (instantiateDriver().getTitle().equals("Google")) {
			logger.info("Enter the text in search area " + text);
			searchText.sendKeys(text);

			logger.info("Click on search");
			searchButton.click();

		}
	}

	public void getBrokenLinks() throws IOException {
		logger.info("Inside method getBrokenLinks");
		for(int i = 0; i< allVisibleLinks.size(); i++)
		{
			String link = allVisibleLinks.get(i).getAttribute("href");
			
			URL url = new URL(link);
			HttpURLConnection connect = (HttpURLConnection)url.openConnection();
			connect.setConnectTimeout(2000);
			connect.connect();
			
			if(connect.getResponseCode()>=400)
			{
				logger.error("Link "+allVisibleLinks.get(i).getText()+" is broken with error code: "+connect.getResponseCode());
			}
			else
			{
				logger.info("correct Link: "+connect.getResponseCode());
			}
			
		}
	}
}
