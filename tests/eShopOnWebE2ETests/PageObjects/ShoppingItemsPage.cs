using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;
using OpenQA.Selenium.Support.UI;

namespace eShopOnWebE2ETests.PageObjects
{
    public class ShoppingItemsPage
    {
        private IWebDriver _driver;

        public ShoppingItemsPage(IWebDriver driver)
        {
            _driver = driver;
        }
        private IWebElement ShoppingItemsPageValidator => _driver.FindElement(By.XPath("//section[@class='esh-catalog-hero']"));
        private SelectElement FilterDropdown => new SelectElement(_driver.FindElement(By.XPath("//select[@id='CatalogModel_TypesFilterApplied']")));
        private IWebElement FilterCatalogItemsBtn => _driver.FindElement(By.XPath("//input[@class='esh-catalog-send']"));
        private IReadOnlyCollection<IWebElement> AddToBasketButtons => _driver.FindElements(By.XPath("//div[@class='esh-catalog-items row']/child::div/child::form/input[@class='esh-catalog-button']"));
        public void FilterItemsByTShirt()
        {
            FilterDropdown.SelectByValue("2"); //.SelectByText("T-Shirt");
            FilterCatalogItems();
        }
        private void FilterCatalogItems()
        {
            FilterCatalogItemsBtn.Click();
        }
        public void AddToBasket()
        {
            var element = AddToBasketButtons.First();
            //scrolling down and check item such as T-Shirt as part of navigation
            new Actions(_driver).MoveToElement(element).Perform();
            element.Click();
        }
        public bool VerifyTheShoppingItemsPage()
        {
            try
            {
                return ShoppingItemsPageValidator.Displayed;
            }
            catch (NoSuchElementException)
            {
                return false;
            }
        }
    }
}


