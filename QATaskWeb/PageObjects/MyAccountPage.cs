using OpenQA.Selenium;
using QATaskWeb.Hooks;

namespace QATaskWeb.PageObjects
{
    internal class MyAccountPage
    {

        public IWebDriver driver;

        public MyAccountPage()
        {
            driver = BaseTest.driver;
        }

        private By myAccountButton = By.XPath("//div[text()='My account']");
        private By manageYourAccount = By.XPath("//h2[text()='Manage your account']");


        public void ClickMyAccountButton()
        {
            driver.FindElement(myAccountButton).Click();
        }

        public bool VerifyManageYourAccount()
        {
            return driver.FindElement(manageYourAccount).Displayed;
        }



    }
}
