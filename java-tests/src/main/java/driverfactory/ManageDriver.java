package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ManageDriver {
    public static WebDriver driver;

    public void selectBrowser(){
        driver=new ChromeDriver();
    }

    public void closeBrowser(){
        driver.quit();
    }
}
