package eshop.TestAutomation.TestComponents;

import PageObject.BasePages.CatalogPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eshop.TestAutomation.Test.loginTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
   public WebDriver driver;
    public Properties prop;
    public CatalogPage catalogPage;
    public static Logger log = LogManager.getLogger(BaseTest.class.getName());
    public WebDriver initializeDriver() throws IOException {

        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\GlobalData.properties");
        prop.load(fis);
        String browsername = prop.getProperty("browser");
        switch (browsername) {
                case "firefox":
                log.info("Initial browser SetUp");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                log.info("Initial Browser has been SetUp");
                break;
                case "chrome":
                    System.out.println("Initial browser SetUp");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    log.info("Initial Browser has been SetUp");
                    break;
                    case "edge":
                System.out.println("Initial browser SetUp");
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                log.info("Initial Browser has been SetUp");
                break;
         }
         return driver;

    }

    public List<HashMap<String, String>> getJsonToMap(String FilePath) throws IOException {
        String JsonContent = FileUtils.readFileToString(new File(FilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data =mapper.readValue(JsonContent, new TypeReference<List<HashMap<String ,String>>>() {
        });
        return data;
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
        log.info("url is opened");
    }

    public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
    {
        TakesScreenshot ts=(TakesScreenshot) driver;
        File source =ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
        FileUtils.copyFile(source,new File(destinationFile));
        return destinationFile;


    }
}
