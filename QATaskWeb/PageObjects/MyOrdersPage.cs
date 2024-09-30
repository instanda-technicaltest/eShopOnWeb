using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using QATaskWeb.Hooks;

namespace QATask.PageObjects
{
    public class MyOrdersPage
    {
        public IWebDriver driver;

        public MyOrdersPage()
        {
            driver = BaseTest.driver;
        }

        private By myOrdersButton = By.XPath("//div[text()='My orders']");
        private By myOrderHistoryPage = By.XPath("//h1[text()='My Order History']");
        private By detailButton = By.XPath("(//a[@class='esh-orders-link'])[8]");
        private By orderNumber = By.XPath("//section[text()='Order number']");

        public void ClickMyOrdersButton()
        {
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(50));
            driver.FindElement(myOrdersButton).Click();
        }

        public bool VerifyMyOrderHistoryPage()
        {
            return driver.FindElement(myOrderHistoryPage).Displayed;
        }

        public void ClickDetailButton()
        {
            ((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", driver.FindElement(detailButton));
        }

        public bool VerifyOrderNumberDisplayed()
        {
            return driver.FindElement(orderNumber).Displayed;
        }

    }
}
