using System;
using eShoponWeb.Drivers;
using Microsoft.VisualStudio.TestPlatform.CommunicationUtilities;
using NUnit.Framework;
using OpenQA.Selenium;
using TechTalk.SpecFlow;
using OpenQA.Selenium.Remote;
using eShoponWeb.StepDefinitions;


namespace eShoponWebLogin.StepDefinitions
{
    [Binding]
    public class EshoponWebStepDefLogin : Base
    {
        public static IWebDriver driver;
        public static string LoginUrl = "https://localhost:5001/";
        LoginPage loginpage = new LoginPage();
       
        private readonly ScenarioContext _scenarioContext;

        public EshoponWebStepDefLogin(ScenarioContext scenariocontext)
        {
            _scenarioContext = scenariocontext;
            //var desiredCapabilities = new ReadOnlyDesiredCapabilities();
            //desiredCapabilities.SetCapability("browserName", "Chrome");
            //desiredCapabilities.SetCapability("platform", "Windows 11");
        }

        [Given(@"user is on landing page")]
        public void GivenUserIsOnLandingPage()
        {
            driver = _scenarioContext.Get<Base>("SeleniumDriver").SetUp();
            driver.Navigate().GoToUrl(driverUrl);
            
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
        
            [When(@"user enter Incorrect emailid or password")]
        public void WhenUserEnterIncorrectEmailidOrPassword()
        {
            Emailid.SendKeys("demo@microsoft.com");
        }

        [Then(@"user should not be able to login and get error message - Invaid credentials")]
        public void ThenUserShouldNotBeAbleToLoginAndGetErrorMessage_InvaidCredentials()
        {
            IWebElement ActualError = driver.FindElement(By.XPath("//li[text()=’Invalid login Attempt’]"));
            Assert.AreEqual(ActualError, "Invalid Login Attempt");
        }

        [When(@"user does not enter emailid or password")]
        public void WhenUserDoesNotEnterEmailidOrPassword()
        {
            Emailid.SendKeys("");
        }

        [Then(@"user should not be able to login and get error message")]
        public void ThenUserShouldNotBeAbleToLoginAndGetErrorMessage()
        {
            IWebElement ActualError = driver.FindElement(By.XPath("//li[text()=’The Email field is required’]"));
            Assert.AreEqual(ActualError, "The Email field is required");
        }


    }
}

