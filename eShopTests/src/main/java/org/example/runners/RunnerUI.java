package org.example.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features/eShopUI.feature",
        glue = {"org.example.UI"},
        tags = "@regression"
)
public class RunnerUI {
}
