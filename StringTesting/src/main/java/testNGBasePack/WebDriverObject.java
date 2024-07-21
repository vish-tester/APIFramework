package testNGBasePack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverObject {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	private WebDriverObject () {
		/*
		 * making it private to restrict
		 * calling constructor from outside the class
		 * 
		 * */
	}
	 
	public static WebDriver getDriver() {
		
		if(driver.get()==null)
			synchronized (WebDriverObject.class) {
				if(driver.get()==null)
				{
					driver.set(new ChromeDriver());;
				}
			}
		
		
		return driver.get();
	}
	 
	  
	  
	  
	 
}
