package utilsPack;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

	private int retry = 0;
	private final int max = 3;
	
	public boolean retry(ITestResult testResult) {
		
		while(retry< max)
		{
			retry++;
			return true;
		}
		
		
		return false;
	}
	
	
}
