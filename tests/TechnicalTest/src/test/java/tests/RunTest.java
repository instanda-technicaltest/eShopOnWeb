package tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import helper.Data;
import helper.Driver;
import helper.OverallReporter;
import helper.OverallUnitTestReporter;
import helper.Reporter;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",
glue = "steps" ,tags = "not @ignore")
public class RunTest {
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("In Junit Before Class");
		OverallUnitTestReporter.createOverallReportFile();
//		mvn clean test -Dtest=RunTest.java
	}
	

	@AfterClass
	public static void afterClass() {
		System.out.println("In Junit Afer Class");
		OverallUnitTestReporter.addTCDetailsToReport();
		OverallUnitTestReporter.writeResults();
		OverallReporter.clearData();
			}

}