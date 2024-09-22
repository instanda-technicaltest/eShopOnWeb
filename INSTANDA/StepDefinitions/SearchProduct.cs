using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;
using SeleniumExtras.WaitHelpers;
using OpenQA.Selenium.Support.UI;

namespace INSTANDA.StepDefinitions
{
    [Binding]
    public class SearchProduct
    {
        IWebDriver _driver;

        HomePage _homePage;

        public SearchProduct(IWebDriver driver)

        {
            this._driver = driver;
            _homePage = new HomePage(driver);
        }

        IWebElement productfilter => _driver.FindElement(By.Id("CatalogModel_TypesFilterApplied"));

        IList<IWebElement> selectproducts => _driver.FindElements(By.CssSelector("#CatalogModel_TypesFilterApplied > option"));

        IWebElement searchbutton => _driver.FindElement(By.XPath("/html/body/div/section[2]/div/form/input"));

        IWebElement searchresult => _driver.FindElement(By.XPath("/html/body/div/div/div[1]/div/article/nav/div[2]/span"));

        [Given(@"Load the home page")]
        public void GivenLoadTheHomePage()
        {
            _homePage.GivenTheEshopURL();
            _homePage.ThenThePageShouldLoadFine();

        }

        [When(@"I select product filter")]
        public void WhenISelectProductFilter()
        {
            Actions actions = new Actions(_driver);
            actions.MoveToElement(productfilter).Perform();
            productfilter.Click();
        }

        [Then(@"I select specific product")]
        public void ThenISelectSpecificProduct()
        {
            foreach (var product in selectproducts)
            {
                if (product.Text.Contains("T-Shirt"))
                {
                    product.Click();
                }
            }
        }

        [Then(@"It should show result")]
        public void ThenItShouldShowResult()
        {
            searchbutton.Click();

            WebDriverWait wait=new WebDriverWait(_driver,TimeSpan.FromSeconds(10));

            wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("/html/body/div/div/div[1]/div/article/nav/div[2]/span")));
            string searchoutput = searchresult.Text;
            Console.WriteLine("the search result is"+ searchoutput);
        }

    }
}
