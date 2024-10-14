using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;
using System.Xml.Linq;

namespace eShopOnWebE2ETests.PageObjects
{
    public class ShoppingCartPage
    {
        private IWebDriver _driver;

        public ShoppingCartPage(IWebDriver driver)
        {
            _driver = driver;
        }

        private IWebElement CartItem => _driver.FindElement(By.XPath("//img[@class='esh-basket-image']"));
        private IWebElement QuantityInput =>   _driver.FindElement(By.XPath("//input[@class='esh-basket-input']"));
        private IWebElement UpdateButton => _driver.FindElement(By.XPath("//button[normalize-space()='[ Update ]']"));
        private IWebElement CheckoutButton => _driver.FindElement(By.XPath("//a[normalize-space()='[ Checkout ]']"));

        public void ChangeItemCount(int count)
        {
            QuantityInput.Clear();
            QuantityInput.SendKeys(count.ToString());
        }

        public void ClickUpdate()
        {
            new Actions(_driver).MoveToElement(UpdateButton).Perform();
            UpdateButton.Click();
        }

        public void ClickCheckout()
        {
            new Actions(_driver).MoveToElement(CheckoutButton).Perform();
            CheckoutButton.Click();
        }
        public int GetItemCount()
        {
            try
            {
                return int.Parse(QuantityInput.GetAttribute("value"));
            }
            catch (NoSuchElementException)
            {
                return 0;
            }
        }
        public bool VerifyCartHasItems()
        {
            try
            {
                return CartItem.Displayed;
            }
            catch (NoSuchElementException)
            {
                return false;
            }
        }
    }
}