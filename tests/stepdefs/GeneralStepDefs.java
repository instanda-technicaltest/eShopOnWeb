package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import util.CommonFunctions;
import util.PropertyReaders;
import webdriversupport.DriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GeneralStepDefs {
    private Scenario scenario;
    private static Properties config = new Properties();
    private static InputStream input = null;
    private static String configProperty = System.getProperty("user.dir") + "/resources/properties/config.properties";

    public String getURL() throws IOException {
        try {
            System.out.println("Directory:" + System.getProperty("user.dir"));
            input = new FileInputStream(configProperty);
            config.load(input);
        } catch (IOException e) {
        }
        if (System.getProperty("environment") != null) {
            PropertyReaders.getInstance().writeProperty("ENV", System.getProperty("environment"));
            return String.format(config.getProperty("URL"), config.getProperty("ENVIRONMENT_" + System.getProperty("environment")));
        } else
            return String.format(config.getProperty("URL"), config.getProperty("ENVIRONMENT_" + System.getProperty("ENV")));
    }

    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        this.scenario = scenario;
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        try {
            this.scenario = scenario;
            new CommonFunctions().takeScreenshot(scenario, DriverManager.getDriver());
        } finally {
            DriverManager.closeDriver();
        }
    }
}
