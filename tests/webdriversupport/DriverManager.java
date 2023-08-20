package webdriversupport;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.PropertyReaders;

import java.io.IOException;

public class DriverManager {
    static String browser = null;


    static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            if (System.getProperty("browser") != null) {
                browser = System.getProperty("browser");
            } else {
                try {
                    browser = PropertyReaders.getInstance().readProperty("BROWSER").toLowerCase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            switch (browser) {
                case "firefox":
                    return getFireFoxDriver();
                case "chrome":
                    return getChromeDriver();
                default:
                    return getChromeDriver();
            }

        }
    };

    public static WebDriver getDriver() {
        return driver.get();
    }

    private static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");

        WebDriver chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.manage().window().setSize(new Dimension(1208, 1024));
        return chromeDriver;
    }

    private static WebDriver getFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().window().maximize();
        return firefoxDriver;
    }

    public static void closeDriver() {
        driver.get().close();
        driver.remove();
    }
}
