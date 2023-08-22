using System;
using eShoponWeb.Drivers;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Remote;
using TechTalk.SpecFlow;

namespace eShoponWeb.StepDefinitions
{
    [Binding]
    public class EShoponWebCheckOutPageStepDefinitions
    {
        IWebDriver driver;
        String test_url = "https://localhost:44315/Basket";
        

        
            [Given(@"user is on Basket page")]
        public void GivenUserIsOnCheckOutPage()
        {
            
            
            driver = new SeleniumDriver();
            driver.Url = test_url;
            driver.Manage().Window.Maximize();
            System.Threading.Thread.Sleep(2000);
        }

        [When(@"user clicks on Update button")]
        public void WhenUserClicksOnUpdateButton()
        {
            IWebElement Updatebtn = driver.FindElement(By.Name("updatebutton"));
            Updatebtn.Click();
        }

        [Then(@"user should be able to update quantity or make any changes to selected product")]
        public void ThenUserShouldBeAbleToUpdateQuantityOrMakeAnyChangesToSelectedProduct()
        {
            IWebElement Updatequantity = driver.FindElement(By.XPath("//body/div[1]/div[1]/form[1]/div[1]/article[1]/div[1]/section[4]/input[2] "));
            Updatequantity.Click();
        }   

        [When(@"user clicks on Continue Shopping button")]
        public void WhenUserClicksOnContinueShoppingButton()
        {
            IWebElement ContinueShopping = driver.FindElement(By.XPath("//a[contains(text(),'[Continue Shopping]"));
            ContinueShopping.Click();
        }

        [Then(@"user should be able to land on home page and continue shopping")]
        public void ThenUserShouldBeAbleToLandOnHomePageAndContinueShopping()
        {
            String hpage_url = "https://localhost:44315/";

        }

        [When(@"user clicks on PayNow button")]
        public void WhenUserClicksOnPayNowButton()
        {
            IWebElement PayNow = driver.FindElement(By.XPath("//body/div[1]/div[1]/form[1]/div[1]/div[3]/section[2]/input[1]"));
            PayNow.Click();
        }

        [Then(@"user should be able make payment and see thanks message and orders placed")]
        public void ThenUserShouldBeAbleMakePaymentAndSeeThanksMessageAndOrdersPlaced()
        {
            IWebElement Actualmsg = driver.FindElement(By.XPath("//h1[contains(text(),'Thanks for your Order!')]"));
            String expectedmsg = "Thanks for your Order!";
            AssertionExtensions.Equals(Actualmsg, expectedmsg);
        }
    }
}
