package basePack;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentReportsManager;

public class VishBaseClass {

	
	private ExtentReports extent;
	
	@BeforeSuite
	public void setExtentConfig() {
		System.out.println(System.getProperty("user.dir"));
	
	}
	
	
	
	@AfterSuite(alwaysRun = true)
	public void removeExtentConfig() {
		
	}
	
}
