package steps;

import java.io.IOException;
import helper.Data;
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
		 Reporter.createReportFile(FeatureName);
		 }
		
//		 String scenarioName = scenario.getName();
		 System.out.println("Scenario is : "+scenarioName);
		 Reporter.addScenarioToReport(scenarioName);	 
	 }
	 
	
	 
	 @After
	 public void after() {
		 Reporter.addStepsToReport();
		 Reporter.details.clear();
//		Driver.driver.close();
		
	 }
	 
	@After("@Last")
	 public void tearDown() {
		 Reporter.writeResults(); 
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

