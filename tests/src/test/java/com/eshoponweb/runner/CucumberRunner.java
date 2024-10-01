package com.eshoponweb.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
@CucumberOptions(
        features = {"classpath:features"},
        glue = {"com/eshoponweb/steps"},
        plugin = {"html:target/cucumber-reports/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/rerun.txt"},
        tags = ("@productcatalog")
)
public class CucumberRunner extends AbstractTestNGCucumberTests{
}
