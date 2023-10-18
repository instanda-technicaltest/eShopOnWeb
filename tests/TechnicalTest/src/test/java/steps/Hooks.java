package steps;

import java.io.IOException;

import flows.ProductSelectionFlow;
import helper.Data;
import helper.Driver;
import helper.OverallReporter;
import helper.OverallUnitTestReporter;
import helper.Reporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	
	int stepNo = 0;
	static String FeatureName="";
	

	 @Before
	 public void initiateTest(Scenario scenario) {
		 
		 String scenarioName = scenario.getName();
		 String featureName = getFeatureFileName(scenario);
		 System.out.println("feature Name is : "+featureName);
		 
		
			
		 if(!FeatureName.equals(featureName)) {
		 FeatureName = featureName;
		 OverallUnitTestReporter.featureName = featureName;
		 Reporter.createReportFile(FeatureName);
		 }
		
//		 String scenarioName = scenario.getName();
		 System.out.println("Scenario is : "+scenarioName);
		 Reporter.addScenarioToReport(scenarioName);
		 
		 OverallReporter.TCStatus ="Pass";
		 OverallReporter.TCCount = OverallReporter.TCCount+1;	
		 OverallReporter.TCName = featureName;
	 }
	 
	
	 
	 @After
	 public void after() {
		 Reporter.addStepsToReport();
		 Reporter.details.clear(); 
		 ProductSelectionFlow.clearStaticValues();
		 
//		Driver.driver.close();
		
	 }
	 
	@After("@Last")
	 public void tearDown() {
		 Reporter.writeResults(); 
		 
		    OverallUnitTestReporter.tcDetailsReport(OverallReporter.TCCount+"", OverallUnitTestReporter.featureName, OverallReporter.TCStatus, Reporter.reportPath);
			 if(OverallReporter.TCStatus.equals("Fail")) {
				 OverallReporter.FailedTCCount = OverallReporter.FailedTCCount+1;
				 OverallUnitTestReporter.failedTCDetails(OverallReporter.FailedTCCount+"", OverallUnitTestReporter.featureName, OverallReporter.reasonForFailure, OverallReporter.failureScreen); 
			 }else {
				 OverallReporter.PassedTCCount = OverallReporter.PassedTCCount+1;
			 }
			 try {
				 OverallReporter.failureFlag="";
				 OverallReporter.addFailureRows();
				 OverallReporter.failureCount=1;
				 OverallReporter.failureMap.clear();
//			Driver.driver.close();
			 }catch(Exception e1) {
				 System.out.println("Driver is already closed");
			 }
			 Data.ClearData();
	 }
	
	private String getFeatureFileName(Scenario scenario) {
	    try {
	    	System.out.println(scenario.getUri().toString());
	    String rawFeatureName = scenario.getUri().toString().split("features/")[1].replace(".feature","");
	    System.out.println(rawFeatureName);
	    return rawFeatureName;
	    }catch(Exception e) {
	    	 String rawFeatureName = scenario.getUri().toString().split("Features/")[1].replace(".feature","");
	 	    System.out.println(rawFeatureName);
	 
		    return rawFeatureName;
	    }	
	    

	}
	 
	 public void setData(String scenarioName) {
		 	String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\data";
			Data data = new Data();
			try {
				data.setData(filePath,"TestData.xls","Data",scenarioName);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	 
	 public void setData(String sheetName, String scenarioName) {
		 	String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\data";
			Data data = new Data();
			try {
				data.setData(filePath,"TestData.xls",sheetName,scenarioName);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}

