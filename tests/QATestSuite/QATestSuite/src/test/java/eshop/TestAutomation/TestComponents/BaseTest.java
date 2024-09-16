package eshop.TestAutomation.TestComponents;

import PageObject.BasePages.CatalogPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
   public WebDriver driver;
    Properties prop;
    public CatalogPage catalogPage;
    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\GobalData.properties");
        prop.load(fis);
        String browsername = prop.getProperty("browser");
        switch (browsername) {
                case "firefox":
                System.out.println("Initial browser SetUp");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                System.out.println("Initial Browser has been SetUp");
                break;
                case "chrome":
                    System.out.println("Initial browser SetUp");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    System.out.println("Initial Browser has been SetUp");
                    break;
                    case "edge":
                System.out.println("Initial browser SetUp");
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                System.out.println("Initial Browser has been SetUp");
                break;
         }
         return driver;

    }

   /* public CatalogPage launchUrl(){
        String url = prop.getProperty("url");
        //driver.get(url);
        CatalogPage cp = new CatalogPage(driver);
        cp.loadUrl(url);
        return cp;
    }*/

    @AfterMethod
    public void quitBrowser(){
        driver.quit();
    }

    public void setUpBrowser() throws IOException {
        driver =initializeDriver();
    }

    @BeforeMethod
    public void loadUrl() throws IOException {

        setUpBrowser();
        String url = prop.getProperty("url");
        catalogPage = new CatalogPage(driver);
        catalogPage.goToUrl(url);
        WebElement brandimage = catalogPage.getBrandImage();
        Assert.assertTrue(brandimage.isDisplayed());
        System.out.println("url is opened");
    }

}
