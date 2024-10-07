package uitests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static WebDriver createDriver() {
        // Set the path to GeckoDriver based on the operating system
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("mac")) {
            System.setProperty("webdriver.gecko.driver", "/Users/ash/Downloads/EShop/src/main/resources/geckodriver"); // Path for Mac
        } else if (os.contains("win")) {
            System.setProperty("webdriver.gecko.driver", "C:\\path\\to\\geckodriver.exe"); // Path for Windows
        } else if (os.contains("linux")) {
            System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver"); // Path for Linux
        }

        // Initialize and return Firefox WebDriver
        return new FirefoxDriver();
    }
}
