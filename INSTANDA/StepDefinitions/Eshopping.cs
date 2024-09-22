using NUnit.Framework;
using OpenQA.Selenium.Interactions;
using OpenQA.Selenium;

namespace INSTANDA.StepDefinitions
{
    [Binding]
    public class Eshopping
    {
        IWebDriver _driver;
        HomePage _homePage;

        public Eshopping(IWebDriver driver)
        {
            this._driver = driver;
            _homePage=new HomePage(driver);
        }

        IWebElement AddBasketLink => _driver.FindElement(By.CssSelector("body > div > div > div.esh-catalog-items.row > " +
                                                             "div:nth-child(1) > form > input.esh-catalog-button"));
        IWebElement checkoutbutton => _driver.FindElement(By.CssSelector("body > div > div > form > div > div.row > " +
                                                                        "section.esh-basket-item.col-xs-push-7.col-xs-4 > a"));
        IWebElement Payment => _driver.FindElement(By.XPath("/html/body/div/div/form/div/div[3]/section[2]/input"));

        IWebElement Orderconfirm => _driver.FindElement(By.CssSelector("body > div > div > h1"));

        IWebElement Logged => _driver.FindElement(By.XPath("//*[@id=\"logoutForm\"]/section[1]/div"));

        IWebElement Loggout => _driver.FindElement(By.CssSelector("#logoutForm > section.esh-identity-drop > a:nth-child(3) > div"));

        [Given(@"successfully login to the eshopping site")]        
        internal void GivenSuccessfullyLoginToTheEshoppingSite()
        {
            _homePage.GivenTheEshopURL();
            _homePage.WhenIClickLgoinButton();
            _homePage.ThenIEnterInEmilField("demouser@microsoft.com");
            _homePage.ThenIEnterInPassordField("Pass@word1");
            _homePage.ThenLoginShouldBeSuccess();
        }

        [When(@"I add an item to the basket")]
        internal void WhenIAddAnItemToTheBasket()
        {
            //IJavaScriptExecutor js = (IJavaScriptExecutor)_driver;
            //js.ExecuteScript("arguments[0].scrollIntoView(true);", AddBasketLink);
            Actions actions = new Actions(_driver);
            actions.MoveToElement(AddBasketLink).Perform();
            AddBasketLink.Click();
        }

        [Then(@"I click checkout button")]
        internal void ThenIClickCheckoutButton()
        {
            IJavaScriptExecutor js = (IJavaScriptExecutor)_driver;
            js.ExecuteScript("arguments[0].scrollIntoView(true);", checkoutbutton);
            Actions actions = new Actions(_driver);
            actions.MoveToElement(checkoutbutton).Perform();
            checkoutbutton.Click();
        }

        [Then(@"I click paynow button")]
        internal void ThenIClickPaynowButton()
        {

            // IJavaScriptExecutor js = (IJavaScriptExecutor)_driver;
            // js.ExecuteScript("arguments[0].scrollIntoView(true);", Payment);
            //Thread.Sleep(1000);
            Actions actions = new Actions(_driver);
            actions.MoveToElement(Payment).Perform();
            Payment.Click();

        }

        [Then(@"I should see order success message")]
        internal void ThenIShouldSeeOrderSuccessMessage()
        {
            string Actualmessage = Orderconfirm.Text;

            string Expectedmessage = "Thanks for your Order!";
                        
            Assert.AreEqual(Actualmessage, Expectedmessage,"order error");

            //Actions actions = new Actions(_driver);
            //actions.MoveToElement(Logged).Perform();
            //Loggout.Click();
        }        

        [Then(@"I logout from the site")]
        internal void ThenILogoutFromTheSite()
        {
            _homePage.ThenIShouldLogoutFromTheSite();
        }

    }
}
