package tests;


import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import flows.ProductSelectionFlow;
import helper.Data;
import helper.OverallReporter;
import helper.Reporter;

public class BaseConfig {
	WebDriver driver;
	Data data = new Data();
	SoftAssert softAssert = new SoftAssert();
	String className = this.getClass().getName().replace("tests.","");

	@BeforeSuite
	public void beforeSuite() {
		OverallReporter.createOverallReportFile();
	}
	
	

	
	@BeforeClass
	public void setUp() {
		data.setData(className);	
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//resources//Driver//chromedriver.exe");
		ChromeOptions handlingSSL = new ChromeOptions();
		handlingSSL.setAcceptInsecureCerts(true);
		driver = new ChromeDriver(handlingSSL);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(data.getData("lauchURL"));
		
//		String className = this.getClass().getName();
		 Reporter.createReportFile(className);
		 OverallReporter.TCStatus ="Pass";
		 OverallReporter.TCCount = OverallReporter.TCCount+1;	
		 OverallReporter.TCName = className;
	}
	
	public static void failTC(String reason) {
		Assert.fail(reason);
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
//		if(OverallReporter.failureFlag!="Failed")
		Reporter.addScenarioToReport(method.getName());
	}
	
	@AfterMethod
	public void afterMethod() {
		Reporter.addStepsToReport();
		 Reporter.details.clear();
	}
	

	@AfterClass
	public void tearDown() {
		 Reporter.writeResults();
		 OverallReporter.tcDetailsReport(OverallReporter.TCCount+"", className, OverallReporter.TCStatus, Reporter.reportPath);
		 if(OverallReporter.TCStatus.equals("Fail")) {
			 OverallReporter.FailedTCCount = OverallReporter.FailedTCCount+1;
			 OverallReporter.failedTCDetails(OverallReporter.FailedTCCount+"", className, OverallReporter.reasonForFailure, OverallReporter.failureScreen); 
		 }else {
			 OverallReporter.PassedTCCount = OverallReporter.PassedTCCount+1;
		 }
		 try {
			 OverallReporter.failureFlag="";
			 OverallReporter.addFailureRows();
			 OverallReporter.failureCount=1;
			 OverallReporter.failureMap.clear();
		driver.close();
		 }catch(Exception e) {
			 System.out.println("Driver is already closed");
		 }
		 Data.ClearData();
	}
	
	@AfterClass
	public void clearStaticValues() {
		ProductSelectionFlow.clearStaticValues();
		
	}
	
	@AfterSuite
	public void afterSuite() {
		OverallReporter.addTCDetailsToReport();
		OverallReporter.writeResults();
		OverallReporter.clearData();
		System.out.println(OverallReporter.reportPath);
	}
	
}
