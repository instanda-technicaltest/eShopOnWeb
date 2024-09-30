using NUnit.Framework;
using QATaskWeb.PageObjects;

namespace QATask.StepDefinitions
{
    [Binding]
    public class MyAccountStepDefinitions
    {
        MyAccountPage myAccountPage = new MyAccountPage();

        [When(@"user clicks my account button")]
        public void WhenUserClicksMyAccountButton()
        {
            myAccountPage.ClickMyAccountButton();
        }

        [Then(@"manage your account page displayed")]
        public void ThenManageYourAccountPageDisplayed()
        {
            Assert.IsTrue(myAccountPage.VerifyManageYourAccount());
        }
    }
}
