package com.eShop.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
		features = "src/test/resources/features"
		,glue= {"com.eShop.stepdefs"},
        plugin = {"pretty","summary",
                "html:target/cucumber-reports.html",
                "json:target/json_result.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        dryRun = false
)
public class Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
