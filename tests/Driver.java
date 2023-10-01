package tests;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class Driver {

    private Driver() {
    }

    static WebDriver driver;

    public static WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions capability = new ChromeOptions();
        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        driver = new ChromeDriver(capability);
        driver.manage().window().maximize();
        return driver;
    }
}
