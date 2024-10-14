using Microsoft.VisualStudio.TestPlatform.CommunicationUtilities;
using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace eShopOnWebE2ETests.PageObjects
{
    public class OrderConfirmationPage
    {
        private IWebDriver _driver;

        public OrderConfirmationPage(IWebDriver driver)
        {
            _driver = driver;
        }
        private IWebElement ConfirmationMessage(string message)
        {
            return _driver.FindElement(By.XPath("//h1[normalize-space()='"+message+"']"));
        } 

        public bool IsConfirmationMessageDisplayed(string message)
        {
            return ConfirmationMessage(message).Displayed;
        }

    }
}
