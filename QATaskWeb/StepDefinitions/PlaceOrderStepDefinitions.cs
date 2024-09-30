using NUnit.Framework;
using QATaskWeb.PageObjects;

namespace QATask.StepDefinitions
{
    [Binding]
    public class PlaceOrderStepDefinitions
    {
        PlaceOrderPage placeOrderPage = new PlaceOrderPage();

        [When(@"user clicks add to basket to add dot net black & white mug")]
        public void WhenUserClicksAddToBasketToAddDotNetBlackWhiteMug()
        {
            placeOrderPage.ClickaddToBasketBlackAndWhiteMug();
        }

        [When(@"user clicks update button")]
        public void WhenUserClicksUpdateButton()
        {
            placeOrderPage.ClickUpdateButton();
        }

        [When(@"user clicks continue shopping")]
        public void WhenUserClicksContinueShopping()
        {
            placeOrderPage.ClickContinueShopping();
        }

        [When(@"user clicks add to basket to add prism white T-Shirt")]
        public void WhenUserClicksAddToBasketToAddPrismWhiteT_Shirt()
        {
            placeOrderPage.ClickPrimeWhiteTShirt();
        }

        [When(@"user clicks add to basket to dot net black sweatshirt")]
        public void WhenUserClicksAddToBasketToDotNetBlackSweatshirt()
        {
            placeOrderPage.ClickBlackSweatShirt();
        }

        [Then(@"review page displayed")]
        public void ThenReviewPageDisplayed()
        {
            Assert.IsTrue(placeOrderPage.VerifyReviewPageDisplayed());
        }

        [When(@"user clicks back button to add more items")]
        public void WhenUserClicksBackButtonToAddMoreItems()
        {

            placeOrderPage.ClickBackButton();
        }

        [When(@"user clicks checkout button")]
        public void WhenUserClicksCheckoutButton()
        {
            placeOrderPage.ClickCheckoutButton();
        }

        [When(@"user clicks back button")]
        public void WhenUserClicksBackButton()
        {
            placeOrderPage.ClickBackButton();
        }

        [When(@"user clicks pay now")]
        public void WhenUserClicksPayNow()
        {
            placeOrderPage.ClickPayNow();
        }

        [Then(@"Thanks for your order! displayed")]
        public void ThenThanksForYourOrderDisplayed()
        {
            Assert.IsTrue(placeOrderPage.VerifyThanksForYorOrderPagrDisplayed());
        }

        [When(@"user clicks on brand dropdown to select other")]
        public void WhenUserClicksOnBrandDropdownToSelectOther()
        {
            Thread.Sleep(5000);
            placeOrderPage.ClickBrandDropDowns();
        }

        [When(@"user clicks on type dropdown to select sheet")]
        public void WhenUserClicksOnTypeDropdownToSelectSheet()
        {
            Thread.Sleep(5000);
            placeOrderPage.ClickTypeDropDown();
        }

        [When(@"user clicks on forward arrow button")]
        public void WhenUserClicksOnForwardArrowButton()
        {
            Thread.Sleep(5000);
            placeOrderPage.ClickForwordArrowButton();
        }

        [Then(@"roslyn red sheet should display")]
        public void ThenRoslynRedSheetShouldDisplay()
        {
            Assert.IsTrue(placeOrderPage.VerifyRoslynRedSheetDisplayyed());
        }

        [Then(@"basket is empty displayed")]
        public void ThenBasketIsEmptyDisplayed()
        {
            Assert.IsTrue(placeOrderPage.VerifyBasketIsEmpty());
        }

        [When(@"user clicks next button")]
        public void WhenUserClicksNextButton()
        {
            placeOrderPage.ClickNextButton();
        }

        [Then(@"product displayed")]
        public void ThenProductDisplayed()
        {
            Assert.IsTrue(placeOrderPage.VerifyProductButton());
        }

        [Then(@"page two to two should displayed")]
        public void ThenPageTwoToTwoShouldDisplayed()
        {
            Assert.IsTrue(placeOrderPage.VerifyPageTwoOfTwoDisplayed());
        }

        [When(@"user clicks previous button")]
        public void WhenUserClicksPreviousButton()
        {
            placeOrderPage.ClickPreviousButton();
        }

        [Then(@"page one to two should diaplayed")]
        public void ThenPageOneToTwoShouldDiaplayed()
        {
            Assert.IsTrue(placeOrderPage.VerifyPageOneOfTwoDisplayed());
        }

        [When(@"user clicks basket to remove an item Roslyn Red T-Shirt")]
        public void WhenUserClicksBasketToRemoveAnItemRoslynRedT_Shirt()
        {
            placeOrderPage.ClickBasket();
        }

        [When(@"user clicks basket to remove an item")]
        public void WhenUserClicksBasketToRemoveAnItem()
        {
            placeOrderPage.ClickBasket();
        }

        [When(@"user clicks on the down arrow to remove a product")]
        public void WhenUserClicksOnTheDownArrowToRemoveAProduct()
        {
            placeOrderPage.ClickDownArrow();
        }

    }
}
