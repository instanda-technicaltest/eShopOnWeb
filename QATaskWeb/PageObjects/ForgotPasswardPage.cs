using OpenQA.Selenium;
using QATaskWeb.Hooks;

namespace QATaskWeb.PageObjects
{
    public class ForgotPasswordPage
    {
        public IWebDriver driver;

        public ForgotPasswordPage()
        {
            driver = BaseTest.driver;
        }

        private By forgotYourPassword = By.XPath("//a[text()='Forgot your password?']");
        private By forgotYourPasswordPage = By.XPath("//h1[text()='Forgot your password?']");
        private By email = By.XPath("//input[@id='Input_Email']");
        private By resetPassword = By.XPath("//button[text()='Reset Password']");
        private By ValidEmail = By.XPath("//p[normalize-space()='Please check your email to reset your password.']");
        private By invalidEmail = By.XPath("//span[@id='Input_Email-error']");

        public void ClickForgotPassword()
        {
            driver.FindElement(forgotYourPassword).Click();
        }

        public bool VerifyForgotYourPasswordPage()
        {
            return driver.FindElement(forgotYourPasswordPage).Displayed;
        }

        public void EnterYourEmail(string emailtxt)
        {
            driver.FindElement(email).SendKeys(emailtxt);
        }

        public void ClickResetPassword()
        {
            driver.FindElement(resetPassword).Click();
        }

        public bool VerifyResetPasswordSuccesMessage()
        {
            return driver.FindElement(ValidEmail).Displayed;
        }

        public bool VerifyMessageDisplayed(string emailType)
        {
            switch (emailType)
            {
                case "validEmail":
                    return driver.FindElement(ValidEmail).Displayed;

                case "invalidEmail":
                    return driver.FindElement(invalidEmail).Displayed;


                default:

                    return false;
            }

        }



    }
}
