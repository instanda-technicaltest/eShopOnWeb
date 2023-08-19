using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;

namespace eShoponWeb.Drivers;
public class SeleniumDriver
{
    private IWebDriver driver;

    private readonly ScenarioContext _scenarioContext;

    public SeleniumDriver(ScenarioContext scenarioContext)=> _scenarioContext = scenarioContext;
    public IWebDriver SetUp()

    {
        //Launch ChromeBrowser
        var ChromeOptions = new ChromeOptions();
        driver = new RemoteWebDriver(new Uri("https://localhost:5001/"), ChromeOptions.ToCapabilities());

        //Set Driver
        _scenarioContext.Set(driver, "Webdriver");

        driver.Manage().Window.Maximize();

        return driver;

    }

}
