package com.example.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks {
    public static WebDriver driver;
    private static WebDriverWait webDriverWait;
    public final static int TIMEOUT = 5;

    @Before
    public void setUp(Scenario scenario) {

        Boolean isApiAutomation = scenario.getSourceTagNames().toString().toLowerCase().contains("api");

        if(!isApiAutomation){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        webDriverWait =new WebDriverWait(driver, Duration.ofSeconds(30));
        }
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
