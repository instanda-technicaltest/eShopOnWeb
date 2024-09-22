using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using NUnit.Framework;
using SeleniumExtras.WaitHelpers;
using OpenQA.Selenium.Interactions;
using INSTANDA.Configuration;

namespace INSTANDA.StepDefinitions
{
    [Binding]    
    public class HomePage
    {
        IWebDriver _driver;
        public HomePage(IWebDriver driver)
        {
            this._driver=driver;
        }
        IWebElement Login => _driver.FindElement(By.CssSelector("body > div > header > div > article >" +
                                              " section.col-lg-4.col-md-5.col-xs-12 > div > section >div > a"));

        IWebElement Email => _driver.FindElement(By.Id("Input_Email"));
        IWebElement passfield => _driver.FindElement(By.Id("Input_Password"));

        IWebElement Loginaccount => _driver.FindElement(By.XPath("html / body / div / div / div / div / section / form / div[5] / button"));

        IWebElement Logged => _driver.FindElement(By.XPath("//*[@id=\"logoutForm\"]/section[1]/div"));


        IWebElement Loggout => _driver.FindElement(By.CssSelector("#logoutForm > section.esh-identity-drop > a:nth-child(3) > div"));

        
        [Given(@"The eshop URL")]       
        public void GivenTheEshopURL()
        {
            //_driver.Navigate().GoToUrl("https://localhost:44315/");
            _driver.Navigate().GoToUrl(SetConfig.WebUrl);
        }

        [When(@"I pass it on browser and hit enter")]
        public void WhenIPassItOnBrowserAndHitEnter()
        {
            WebDriverWait wait=new WebDriverWait(_driver,TimeSpan.FromSeconds(10));
            IWebElement Imagelement = wait.Until(ExpectedConditions.ElementIsVisible(By.CssSelector("body > div > header > div > article > section.col-lg-7.col-md-6.col-xs-12 > a > img")));

        }

        [Then(@"the page should load fine")]
        public void ThenThePageShouldLoadFine()
        {
            string actualtitle = _driver.Title;             
            string expectedTitle = "Catalog - Microsoft.eShopOnWeb";
            Assert.AreEqual(actualtitle, expectedTitle ,"The tile not matching");          

        }

        [When(@"I click Lgoin button")]
        public void WhenIClickLgoinButton()
        {
            Login.Click();
        }

        [Then(@"I enter ""([^""]*)"" in Emil field")]
        public void ThenIEnterInEmilField(string email)
        {
            Email.Clear();
            Email.SendKeys(email);
        }

        [Then(@"I enter ""([^""]*)"" in passord field")]
        public void ThenIEnterInPassordField(string password)
        {
            passfield.Clear();
            passfield.SendKeys(password);
           
        }

        [Then(@"login should be success")]
        public void ThenLoginShouldBeSuccess()
        {
            Loginaccount.Click();
        }

        [Then(@"I should logout from the site")]
        public void ThenIShouldLogoutFromTheSite()
        {
            Actions actions = new Actions(_driver);
            actions.MoveToElement(Logged).Perform();
            Loggout.Click();
        }

    }
}
