using NUnit.Framework;
using QATaskWeb.PageObjects;

namespace QATaskWeb.StepDefinitions
{
    [Binding]
    public class ForgotPasswordStepDefinitions
    {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();

        [When(@"user clicks forgot your password")]
        public void WhenUserClicksForgotYourPassword()
        {
            forgotPasswordPage.ClickForgotPassword();
        }

        [Then(@"forgot your password page displayed")]
        public void ThenForgotYourPasswordPageDisplayed()
        {
            Assert.IsTrue(forgotPasswordPage.VerifyForgotYourPasswordPage());
        }

        [When(@"user clicks reset password")]
        public void WhenUserClicksResetPassword()
        {
            forgotPasswordPage.ClickResetPassword();
        }
        [Then(@"user should recieve success message")]
        public void ThenUserShouldRecieveSuccessMessage()
        {
            Assert.IsTrue(forgotPasswordPage.VerifyResetPasswordSuccesMessage(), "Please check your email to reset your password.");
        }

        [Then(@"message should displayed ""([^""]*)""")]
        public void ThenMessageShouldDisplayed(string messages)
        {
            Assert.IsFalse(forgotPasswordPage.VerifyMessageDisplayed(messages));
        }

    }
}
