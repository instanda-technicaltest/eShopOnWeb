using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;
using System.Xml.Linq;

namespace eShopOnWebE2ETests.PageObjects
{
    public class CheckoutPage
    {
        private IWebDriver _driver;

        public CheckoutPage(IWebDriver driver)
        {
            _driver = driver;
        }
        private IWebElement ItemCountValidator => _driver.FindElement(By.XPath("//div[@class='esh-basketstatus-badge']"));
        private IWebElement PayNowButton => _driver.FindElement(By.XPath("//input[@value='[ Pay Now ]']"));
        
        public int ValidateCartItemCount()
        {
            return int.Parse(ItemCountValidator.Text);
        }
        public void ClickPayNow()
        {
            new Actions(_driver).MoveToElement(PayNowButton).Perform();
            PayNowButton.Click();
        }

    }
}
