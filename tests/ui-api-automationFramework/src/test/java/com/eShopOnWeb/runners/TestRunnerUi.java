package com.eShopOnWeb.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Created by Soniya Patel
 */

@CucumberOptions(
        features = "src/test/resources/features/ui",
        glue = {"com/eShopOnWeb/steps",
                "com/eShopOnWeb/steps/ui"},
        plugin = {"html:target/cucumber-reports/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/rerun.txt"},
        tags = "@ui"
)
public class TestRunnerUi extends AbstractTestNGCucumberTests {
}
