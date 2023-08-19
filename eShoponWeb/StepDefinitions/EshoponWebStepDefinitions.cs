using System;
using eShoponWeb.Drivers;
using OpenQA.Selenium;
using TechTalk.SpecFlow;

namespace eShoponWeb.StepDefinitions
{
    [Binding]
    public class EshoponWebStepDefinitions
    {
        IWebDriver driver;
         private readonly ScenarioContext _scenarioContext;

    public BrowserStepDef(ScenarioContext scenariocontext)
    { 
        _scenarioContext = scenariocontext;
    }
    
        [Given(@"user is on landing page")]
        public void GivenUserIsOnLandingPage()
        {
            driver = _scenarioContext.Get<SeleniumDriver>("SeleniumDriver").SetUp();

            driver.Url = "https://localhost:5001/";
        }

        [Given(@"user enter clicks on login button")]
        public void GivenUserEnterClicksOnLoginButton()
        {
            IWebElement Loginbutton = driver.FindElement(By.XPath("/html/body/div/header/div/article/section[2]/div/section/div/a"));
            Loginbutton.Click();  
        }

        [When(@"user enter emailid and password")]
        public void WhenUserEnterEmailidAndPassword()
        {
            IWebElement Emailid = driver.FindElement(By.Id("Input_Email"));
           
            Emailid.SendKeys("demouser@microsoft.com");

            IWebElement Password = driver.FindElement(By.Id("Input_Password"));

            Password.SendKeys("Pass@word1");
        }

        [Then(@"user should be able to login and navigate on home page")]
        public void ThenUserShouldBeAbleToLoginAndNavigateOnHomePage()
        {
            IWebElement Loginbtn = driver.FindElement(By.XPath("/html/body/div/div/div/div/section/form/div[5]/button"));
            Loginbtn.Click();

        }
    }
}
