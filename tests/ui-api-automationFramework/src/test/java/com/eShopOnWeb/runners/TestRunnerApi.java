package com.eShopOnWeb.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Created by Soniya Patel
 */

@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = {"com/eShopOnWeb/steps",
                "com/eShopOnWeb/steps/api"},
        plugin = {"html:target/cucumber-reports/cucumber-api.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/rerun-api.txt"},
        tags = "@api"
)
public class TestRunnerApi extends AbstractTestNGCucumberTests {

}
