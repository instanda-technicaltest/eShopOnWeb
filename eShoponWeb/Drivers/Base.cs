using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.DevTools.V114.Runtime;
using OpenQA.Selenium.Remote;

namespace eShoponWeb.Drivers;

[Binding]
public class Base
{
    public IWebDriver driver;
    [SetUp]
    private readonly ScenarioContext _scenarioContext;

    public void StartBrowser(ScenarioContext scenarioContext) => _scenarioContext = scenarioContext;
    {
        //Configuration
        String browserName = ConfigurationManager.AppSettings("browser");
        InitBrowser(browserName);
        driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds();

        driver.Manage().Window().Maximize();
        driver.Url = "https://localhost:44315/Identity/Account/Login";
    }
public IWebdriver getdriver()
{
    return driver;
}


}
    
    
