package com.eshoponweb.drivermanager;

import com.eshoponweb.propertyreader.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class DriverManager {
    public static WebDriver driver;
    public String baseUrl = PropertyReader.getInstance().getProperty("baseUrl");
    private static final Logger log = LogManager.getLogger(DriverManager.class);

    public DriverManager() {
        PageFactory.initElements(driver, this);
    }

    public void selectBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            log.info("Launching Chrome Browser");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            log.info("Launching Firefox Browser");
            driver = new FirefoxDriver();
        } else {
            log.info("Browser not found");
            System.out.println("Browser not found");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Long.parseLong(PropertyReader.getInstance().getProperty("implicitlyWait"))));
        driver.get(baseUrl);
    }

    public void closeBrowser() {
        log.info("Closing Browser");
        if (driver != null)
            driver.quit();
    }
}
