package utilsPack;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtReporter {

	private static ExtentReports extent;
	private static ExtentSparkReporter spark;
	
	
	private ExtReporter() {
		
	}
	
	
	public static ExtentReports genExtent() {
		
		if(extent == null)
		{
			extent = new ExtentReports();
			spark.config().setDocumentTitle("This is the Complete Test Result");
			spark.config().setTheme(Theme.DARK);
			extent.attachReporter(spark);
		}
		
		return extent;
	}
	
	
}
