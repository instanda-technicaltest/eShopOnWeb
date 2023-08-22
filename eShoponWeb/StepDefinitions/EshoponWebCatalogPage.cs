using System;
using OpenQA.Selenium.Support.UI;
using OpenQA.Selenium;
using TechTalk.SpecFlow;
using TechTalk.SpecFlow.Assist;
using NUnit.Framework;

namespace eShoponWeb.StepDefinitions
{
    [Binding]
    public class EShoponWebAddtoCart
    {
        public static IWebDriver driver;

        public static string gridURL = "https://localhost:44315/";



        private readonly ScenarioContext _scenarioContext;
        [Given(@"user is on home page")]
        public void GivenUserIsOnHomePage()
        {
            //Assert.IsTrue("https://localhost:44315/", gridURL);
        }

        [Given(@"user selects products from dropdown like brand and type")]
        public void GivenUserSelectsProductsFromDropdownLikeBrandAndType()
        {
            SelectElement Brand = new SelectElement(driver.FindElement(By.Id("CatalogModel_BrandFilterApplied")));

            SelectElement Type = new SelectElement(driver.FindElement(By.XPath("/html/body/div/section[2]/div/form/label[2]")));

            IWebElement Arrow = driver.FindElement(By.XPath("/html/body/div/section[2]/div/form/input"));
            Brand.SelectByText(".NET");
            Type.SelectByText("Shirt");
        }

        [When(@"user clicks on Arrow button to cart for filtered products")]
        public void WhenUserClicksOnAddToCartForFilteredProducts()
        {
            IWebElement ArrowButton = driver.FindElement(By.XPath("/html/body/div/div/div[2]/div[2]/form/input[1]"));
            ArrowButton.Click();
        }

        [Then(@"User is able to see one filtered product")]
        public void ThenUserLandsOnCartAndPaymentPage()
        {
            IWebElement NoofProducts = driver.FindElement(By.ClassName("esh-pager-item"));
            Assert.AreEqual(NoofProducts, "1");
        }



        [Then(@"User is able to Add Product to the Cart")]
        public void ThenUserIsAbleToAddProductToTheCart()
        {
            IWebElement AddtoBasketbtn = driver.FindElement(By.XPath("//button[@class='esh-catalog-button'][.//input[contains(text(), 'ADD TO BASKET')]]"));
            AddtoBasketbtn.Click();
        }

        [Given(@"user selects dropdown where user mail id is mentioned")]
        public void GivenUserSelectsDropdownWhereUserMailIdIsMentioned()
        {
            IWebElement Dropdown = driver.FindElement(By.ClassName("esh-identity-drop")); 
            Dropdown.Click();
        }

        [When(@"user clicks on My Account")]
        public void WhenUserClicksOnMyAccount()
        {
            SelectElement MyAccount = new SelectElement(driver.FindElement(By.ClassName("esh-identity-name esh-identity-name—upper ")));
            MyAccount.SelectByText("My Account");       
        }

        [Then(@"User is able to check All Account details")]
        public void ThenUserIsAbleToCheckAllAccountDetails()
        {
            IWebElement text = driver.FindElement(By.XPath("//*[@class='esh-orders-titles-row']/h1[.//h1[contains(text(), 'Order Details')]]"));

          Assert.AreEqual(text,"Order Details");
        }


    }
}

