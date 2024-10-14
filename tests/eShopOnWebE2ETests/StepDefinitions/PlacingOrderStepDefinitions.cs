using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using TechTalk.SpecFlow;
using eShopOnWebE2ETests.PageObjects;

namespace eShopOnWebE2ETests.StepDefinitions
{
    [Binding]
    public class PlacingOrderStepDefinitions
    {
        private IWebDriver driver;
        private LoginPage loginPage;
        private ShoppingItemsPage shoppingItemsPage;
        private ShoppingCartPage cartPage;
        private CheckoutPage checkoutPage;
        private OrderConfirmationPage orderConfirmationPage;

        private string username = System.Environment.GetEnvironmentVariable("USERNAME");
        private string password = System.Environment.GetEnvironmentVariable("PASSWORD");


        [Given(@"eShopOnWeb application available at ""(.*)""")]
        public void GivenEShopOnWebApplicationAvailableAt(string url)
        {
            driver = new ChromeDriver();
            driver.Manage().Window.Maximize();
            driver.Navigate().GoToUrl(url);
            
            loginPage = new LoginPage(driver);
            shoppingItemsPage = new ShoppingItemsPage(driver);
            cartPage = new ShoppingCartPage(driver);
            checkoutPage = new CheckoutPage(driver);
            orderConfirmationPage = new OrderConfirmationPage(driver);

        }

        [When(@"I click login")]
        public void WhenIClickLogin()
        {
            loginPage.ClickLogin();
        }

        [Then(@"I should land on Log in page")]
        public void ThenIShouldLandOnLogInPage()
        {
            Assert.AreEqual(loginPage.GetThePageTitle(), "Log in");
        }

        [When(@"I enter valid user credentials")]
        public void WhenIEnterValidUserCredentials()
        {
            /*loginPage.EnterUsername(username);
            loginPage.EnterPassword(password);*/

            loginPage.EnterUsername("demouser@microsoft.com");
            loginPage.EnterPassword("Pass@word1");
        }

        [When(@"I click Login button")]
        public void WhenIClickLoginButton()
        {
            loginPage.SubmitLogin();
        }

        [Then(@"I should land on shopping items page")]
        public void ThenIShouldLandOnShoppingItemsPage()
        {
            Assert.IsTrue(shoppingItemsPage.VerifyTheShoppingItemsPage(), "Not on shopping items page");
        }

        [When(@"I filter shopping items for T-Shirt type")]
        public void WhenIFilterShoppingItemsForT_ShirtType()
        {
            shoppingItemsPage.FilterItemsByTShirt();
        }

        [When(@"click on ""([^""]*)"" button")]
        public void WhenClickOnButton(string buttonName)
        {
            if (buttonName == "ADD TO BASKET")
            {
                shoppingItemsPage.AddToBasket();
            }
            else if (buttonName == "UPDATE")
            {
                cartPage.ClickUpdate();
            }
            else if (buttonName == "CHECKOUT")
            {
                cartPage.ClickCheckout();
            }
            else if (buttonName == "PAY NOW")
            {
                checkoutPage.ClickPayNow();
            }
        }

        [Then(@"I should see shopping cart page with T-Shirt item")]
        public void ThenIShouldSeeShoppingCartPageWithT_ShirtItem()
        {
            Assert.IsTrue(cartPage.VerifyCartHasItems(), "Shopping cart does not contain T-Shirt");
        }

        [When(@"I change the item count to (.*)")]
        public void WhenIChangeTheItemCountTo(int count)
        {
            cartPage.ChangeItemCount(count);
        }

        [Then(@"I should see shopping cart page with (.*) T-Shirt items")]
        public void ThenIShouldSeeShoppingCartPageWithT_ShirtItems(int count)
        {
            Assert.AreEqual(count, cartPage.GetItemCount(), "Item count is incorrect");
        }

        [Then(@"I should see cart review page T-Shirt items")]
        public void ThenIShouldSeeCartReviewPageT_ShirtItems()
        {
            Assert.AreEqual(checkoutPage.ValidateCartItemCount(),2);
        }

        [Then(@"I should see ""(.*)"" confirmation message")]
        public void ThenIShouldSeeConfirmationMessage(string confirmationMessage)
        {
            Assert.IsTrue(orderConfirmationPage.IsConfirmationMessageDisplayed(confirmationMessage), "Order confirmation message not displayed");
            driver.Quit();
        }
    }
}
