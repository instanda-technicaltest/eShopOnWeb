using OpenQA.Selenium;

namespace INSTANDA.StepDefinitions
{
    [Binding]
    public class TotalProducts
    {
        IWebDriver _driver;
        HomePage _homePage;

        public TotalProducts(IWebDriver driver)
        { 
            this._driver = driver; 
            _homePage=new HomePage(driver);
        }

        IReadOnlyCollection<IWebElement> Totalproducts => _driver.FindElements(By.CssSelector("body > div > div > div > div"));

        IList<IWebElement> products => _driver.FindElements(By.CssSelector("body > div > div > div > div"));

        [Given(@"Load the URL")]
        public void GivenLoadTheURL()
        {
            _homePage.GivenTheEshopURL();
        }

        [When(@"the home page loads")]
        public void WhenTheHomePageLoads()
        {
            _homePage.ThenThePageShouldLoadFine();
        }

        [Then(@"I should check the total number of products available")]
        public void ThenIShouldCheckTheTotalNumberOfProductsAvailable()
        {
            int totalitems=products.Count();

            Console.WriteLine("Total number of products: " + totalitems);

        }

    }
}
