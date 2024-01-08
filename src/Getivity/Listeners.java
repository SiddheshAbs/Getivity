package Getivity;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener{

	public WebDriver driver ;
	@Override
	public void onFinish(ITestContext contextFinish) {
	;

	}

	@Override
	public void onStart(ITestContext contextStart) {
	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	

	}

	@Override
	public void onTestFailure(ITestResult result) {
	System.out.println("Test failed " + result.getName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
	System.out.println("Test skipped " + result.getName());

	}

	@Override
	public void onTestStart(ITestResult result) {


	}

	public void onTestSuccess(ITestResult result) {
	System.out.println("Test passed " + result.getName());

	}
	

	

}