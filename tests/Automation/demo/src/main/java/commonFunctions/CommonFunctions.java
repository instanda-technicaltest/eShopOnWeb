package commonFunctions;

// Import all packages used in this class

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.time.Duration;
import java.util.Properties;

// All common method are written in this class 
public class CommonFunctions {

    // ***********************************************************************
    // Global Variables
    String filePath; 
    static String mb;

    //***********************************************************************
    // Global declaration Driver
    public static WebDriver driver;  
                
    //***********************************************************************
    //Method to initiate the browser
    public void openBrowse()          
    {
        ChromeOptions options =new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();    //Maximize the browser window

    }
    //********************************************************************
    // Method to close the browser
    public void closeBrowser()          
    {
        driver.quit();                                  //Close the browser
    }
    //***********************************************************************
    // Method for implicit wait
    public void waitForTime(int waitTime)       
    {
        WebDriver.Timeouts b = driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));         //Implicit Wait
    }
    //**********************************************************************
    // Method for Explicit wait
    public void waitForElement(WebElement element, int seconds)  
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));          //Explicit Wait for an element
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (Exception e)
        {
            mb = "Failed Step: "+System.lineSeparator()+System.lineSeparator()+e;

        }

    }
    //***********************************************************************
    //Method for wait until element is not clickable
    public void waitUntilClickable(WebElement element, int seconds)   
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));          //Explicit Wait for an element
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    //***********************************************************************
    //Method  for wait for element and click 
    public void waitAndClick(WebElement element, int waitTimeForElement)  
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeForElement));          //Explicit Wait for an element to click
        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }
    //***********************************************************************
    // send  data
    public void type(WebElement element, String testData)       
    {
        element.sendKeys(testData);
    }
    //**********************************************************************
    //Read Data
    public String readText(WebElement element)                   
    {
        String text = element.getText();
        return text;
    }
    //***********************************************************************
    //clear data
    public void clear(WebElement element)                       
    {
        element.clear();
    }
   
    //********************************************************************
    //Click element
    public void click(WebElement element)                     
    {
        element.click();
    }
    //*********************************************************************
    //Method to read Config files
    public String readConfigFile(String key) throws Exception  
    {
        FileInputStream inputStream = new FileInputStream(FilePaths.configFilePath) ;        //Locate the file using FileInputStream class and Create object for it
        Properties properties = new Properties();               //Create object for property class to handle property file
        properties.load(inputStream);                                                        //load the located file into the properties object

        String value = properties.getProperty(key);                                          //extract data from property file
        return value;
    }
    //*********************************************************************
   
    // Method for force sleep 
    public void sleepTime(int Time) throws Exception
    {
        Thread.sleep(Time);
    }
    //*********************************************************************
    // Method for scroll
    public void jsScroll(WebElement element) throws Exception                                            //Click element using JavaScript
    {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(3000);
        }
        catch (Exception e)
        {
            mb = "Failed Step: "+System.lineSeparator()+System.lineSeparator()+e;
        }

    }
    //**********************************************************************    
    //Select data from dropdown
    public void selectFromDropDown(WebElement element, String testData)                 
    {
        new Select(element).selectByVisibleText(testData);
    }
   
    //*******************************************************************
    public static void log(String message)
    {
        System.out.println(message);
    }
    //******************************************************************
    public static void sleep(long timeOut)
    {
        try {
            Thread.sleep(timeOut);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    //*********************************************************************
    

}
