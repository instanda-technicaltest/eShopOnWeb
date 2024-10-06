package com.eShopOnWeb.steps;

import com.eShopOnWeb.api.constants.Path;
import com.eShopOnWeb.configreader.ConfigReader;
import com.eShopOnWeb.ui.Utility.UiUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;

/**
 * Created by Soniya Patel
 */

public class Hooks extends UiUtils {

    @Before("@ui")
    public void setUpUI() {
        selectBrowser(ConfigReader.getInstance().getProperty("uiBrowser"));
    }

    @After("@ui")
    public void tearDown(Scenario scenario) {

        if (scenario.getSourceTagNames().contains("@ui")) {
            if (scenario.isFailed()) {
                final byte[] screenshot = getScreenShot();
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
            closeBrowser();
        }

    }

    @Before("@api")
    public static void setUpApi() {
        RestAssured.baseURI = ConfigReader.getInstance().getProperty("apiBaseUrl");
        RestAssured.port = Integer.parseInt(ConfigReader.getInstance().getProperty("apiPort"));
        RestAssured.basePath = Path.ESHOP;
    }

}
