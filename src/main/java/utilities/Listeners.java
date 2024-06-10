package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements IRetryAnalyzer, ITestListener {

	int counter = 0;
	int limit = 1;

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test case started : " + context.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test case failed : " + result.getName());
		TestBase testbase = new TestBase();
		testbase.sceenshotForFailedTestCases(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test case passed : " + result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test case completed : " + context.getName());
	}

	@Override
	public boolean retry(ITestResult result) {
		if (counter < limit) {
			counter++;
			return true;
		}
		return false;
	}

}