using NUnit.Framework;
using QATaskWeb.PageObjects;

namespace QATask.StepDefinitions
{
    [Binding]
    public class LogoutStepDefinitions
    {
        LogoutPage logoutPage = new LogoutPage();

        [When(@"user hovers on login email on the top menu")]
        public void WhenUserHoversOnLoginEmailOnTheTopMenu()
        {
            logoutPage.HoverLoginEmail();
        }

        [When(@"user clicks logout button")]
        public void WhenUserClicksLogoutButton()
        {
            logoutPage.ClickLogoutButton();
        }

        [Then(@"login page displayed successfully")]
        public void ThenLoginPageDisplayedSuccessfully()
        {
            Assert.IsTrue(logoutPage.VerifyLoginButtonDisplayed());
        }

    }
}
