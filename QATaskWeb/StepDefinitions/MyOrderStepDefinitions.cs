using NUnit.Framework;
using QATask.PageObjects;
using QATaskWeb.PageObjects;

namespace QATask.StepDefinitions
{
    [Binding]
    public class MyOrdersStepDefinitions
    {
        MyOrdersPage myOrdersPage = new MyOrdersPage();

        [When(@"user clicks my orders button")]
        public void WhenUserClicksMyOrdersButton()
        {
            myOrdersPage.ClickMyOrdersButton();
        }

        [Then(@"my order history page displayed")]
        public void ThenMyOrderHistoryPageDisplayed()
        {
            Assert.IsTrue(myOrdersPage.VerifyMyOrderHistoryPage());
        }

        [When(@"user clicks detail button")]
        public void WhenUserClicksDetailButton()
        {
            myOrdersPage.ClickDetailButton();
        }

        [Then(@"order number should displayed")]
        public void ThenOrderNumberShouldDisplayed()
        {
            Assert.IsTrue(myOrdersPage.VerifyOrderNumberDisplayed());
        }

    }
}
