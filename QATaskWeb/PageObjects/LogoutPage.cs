using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;
using OpenQA.Selenium.Support.UI;
using QATaskWeb.Hooks;

namespace QATaskWeb.PageObjects
{
    public class LogoutPage
    {
        public IWebDriver driver;

        public LogoutPage()
        {
            driver = BaseTest.driver;
        }

        private By loginEmail = By.XPath("//div[text()='admin@microsoft.com']");
        private By logoutButton = By.XPath("(//div[@class='esh-identity-name esh-identity-name--upper'])[4]");
        private By loginPage = By.XPath("//div//a[normalize-space()='Login']");


        public void HoverLoginEmail()
        {
            IWebElement hoverElement = driver.FindElement(loginEmail);
            Actions actions = new Actions(driver);
            actions.MoveToElement(hoverElement).Perform();
        }

        public void ClickLogoutButton()
        {
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(50));
            driver.FindElement(logoutButton).Click();
        }
        public bool VerifyLoginButtonDisplayed()
        {
            return driver.FindElement(loginPage).Displayed;
        }


    }
}
