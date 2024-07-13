package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager {

	private static ExtentReports extent;
	private static ExtentSparkReporter spark;
	
	public static ExtentReports configExtentReports() {
		
		if(extent ==null)
		{
			extent = new ExtentReports();
			spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\extentreports\\");
			spark.config().setDocumentTitle("This is the report of all the API practice results");
			spark.config().setDocumentTitle("API Results");
			spark.config().setTheme(Theme.DARK);
			spark.config().setEncoding("utf-8");
			extent.attachReporter(spark);
		}
		
		
		
		return extent;
	}
	
	
}
