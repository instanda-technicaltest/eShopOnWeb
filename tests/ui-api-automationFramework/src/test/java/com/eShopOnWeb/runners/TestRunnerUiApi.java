package com.eShopOnWeb.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Created by Soniya Patel
 */

@CucumberOptions(
        features = {"src/test/resources/features/api",
                "src/test/resources/features/ui"},
        glue = {"com/eShopOnWeb/steps",
                "com/eShopOnWeb/steps/api",
                "com/eShopOnWeb/steps/ui"},
        plugin = {"html:target/cucumber-reports/cucumber-api.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/rerun-ui-api.txt"},
        tags = "@ui or @api"
)
public class TestRunnerUiApi extends AbstractTestNGCucumberTests {
}
