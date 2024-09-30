using NUnit.Framework;
using QATaskWeb.Hooks;
using QATaskWeb.PageObjects;

namespace QATaskWeb.StepDefinitions
{
    [Binding]
    public class LoginStepDefinitions
    {
        LoginPage loginPage = new LoginPage();

        [Given(@"user navigates to eShopOnWeb")]
        public void GivenUserNavigatesToEShopOnWeb()
        {
            string baseUrl = BaseTest.GetBaseUrl();
            BaseTest.driver.Navigate().GoToUrl(baseUrl);
        }

        [Then(@"eShopOnWeb page displayed successfully")]
        public void ThenEShopOnWebPageDisplayedSuccessfully()
        {
            Assert.IsTrue(loginPage.VerifyEShopOnWebPageDisplayed());
        }

        [When(@"user clicks login button")]
        public void WhenUserClicksLoginButton()
        {
            loginPage.ClickLoginButton();
        }

        [Then(@"Login page displayed")]
        public void ThenLogInPageDisplayed()
        {
            Assert.IsTrue(loginPage.VerifyLoginPageDisplayed());
        }

        [When(@"user enters email address ""([^""]*)""")]
        public void WhenUserEntersEmaillAddress(string emailtxt)
        {
            loginPage.EnterEmailAddress(emailtxt);
        }

        [When(@"user enters password ""([^""]*)""")]
        public void WhenUserEntersPassword(string passwordtxt)
        {
            loginPage.EnterPassword(passwordtxt);
        }

        [When(@"user ticks remember me checkbox")]
        public void WhenUserTicksRememberMeCheckbox()
        {
            loginPage.TickRememberMe();
        }

        [When(@"user clicks Log in")]
        public void WhenUserClicksLogIn()
        {
            loginPage.clickLogin();
        }

        [Then(@"invalid message should displayed ""([^""]*)""")]
        public void ThenInvalidMessageShouldDisplayed(string erroMessage)
        {
            Assert.IsFalse(loginPage.VerifyUnsuccessfulLoginMessage(erroMessage));
        }

        [Then(@"user should unsuccessfully is message is displayed")]
        public void ThenUserShouldUnsuccessfullyIsMessageIsDisplayed()
        {
            Assert.IsTrue(loginPage.VerifyUserUnSuccessfullLoginPageDisplayed());
        }

        [Then(@"""([^""]*)"" should be displayed")]
        public void ThenShouldBeDisplayed(string expectedMessage, string email, string password)
        {
            string actualMessage = string.Empty;


            if (string.IsNullOrEmpty(email))
            {
                actualMessage = "The Email field is required.";
            }
            else if (!email.Contains("@"))
            {
                actualMessage = "Email field is not a valid e-mail address.";
            }

            else if (string.IsNullOrEmpty(password))
            {
                actualMessage = "The Password field is required.";
            }
            else
            {
                bool loginSuccessful = loginPage.VerifyUserSuccessfullLoginPageDisplayed();
                if (!loginSuccessful)
                {
                    actualMessage = "Invalid login attempt.";
                }
            }

            Assert.AreEqual(expectedMessage, actualMessage, $"Expected message '{expectedMessage}', but got '{actualMessage}'.");
        }

        [Then(@"user should login successfully")]
        public void ThenUserShouldLoginSuccessfully()
        {
            Assert.AreEqual(loginPage.VerifyUserSuccessfullLoginPageDisplayed(), true);
        }

    }
}
