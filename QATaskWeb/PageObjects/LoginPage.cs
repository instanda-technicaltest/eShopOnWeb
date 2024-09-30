using OpenQA.Selenium;
using QATaskWeb.Hooks;

namespace QATaskWeb.PageObjects
{
    public class LoginPage
    {
        public IWebDriver driver;

        public LoginPage()
        {
            driver = BaseTest.driver;
        }

        private By loginButton = By.XPath("//div//a[normalize-space()='Login']");
        private By loginPageDisplayed = By.XPath("//div//h2[text()='Log in']");
        private By emailAddress = By.XPath("//input[@id='Input_Email']");
        private By password = By.XPath("//input[@id='Input_Password']");
        private By rememberMeCheckbox = By.XPath("//input[@id='Input_RememberMe']");
        private By login = By.XPath("//button[@type='submit']");
        private By successfulLogin = By.XPath("//div[text()='admin@microsoft.com']");
        private By inValidEmail = By.XPath("//span[text()='The Email field is not a valid e-mail address.']");
        private By inValidPassword = By.XPath("//li[text()='Invalid login attempt.']");
        private By emptyEmail = By.XPath("//span[@id='Input_Email-error']");
        private By emptyPassword = By.XPath("//span[@id='Input_Password-error']");

        public void Navigate(string url)
        {
            driver.Navigate().GoToUrl(url);
        }

        public bool VerifyEShopOnWebPageDisplayed()
        {
            return driver.Url.Contains("localhost:5106/");
        }

        public void ClickLoginButton()
        {
            driver.FindElement(loginButton).Click();
        }

        public bool VerifyLoginPageDisplayed()
        {
            return driver.FindElement(loginPageDisplayed).Displayed;
        }

        public void EnterEmailAddress(string emailtxt)
        {
            driver.FindElement(emailAddress).SendKeys(emailtxt);
        }

        public void EnterPassword(string passwordtxt)
        {
            driver.FindElement(password).SendKeys(passwordtxt);
        }

        public void TickRememberMe()
        {
            driver.FindElement(rememberMeCheckbox).Click();
        }

        public void clickLogin()
        {
            driver.FindElement(login).Click();
        }

        public bool VerifyUserSuccessfullLoginPageDisplayed()
        {

            return driver.FindElement(successfulLogin).Displayed;
        }
        public bool VerifyUserUnSuccessfullLoginPageDisplayed()
        {

            return driver.FindElement(inValidEmail).Displayed;
        }

        public bool VerifyUnsuccessfulLoginMessage(string displayType)
        {

            switch (displayType)
            {
                case "invalidEmail":
                    return driver.FindElement(inValidEmail).Displayed;

                case "invalidPassword":
                    return driver.FindElement(inValidPassword).Displayed;

                case "emptyEmail":
                    return driver.FindElement(emptyEmail).Displayed;

                case "emptyPassword":
                    return driver.FindElement(emptyPassword).Displayed;

                default:

                    return false;
            }

        }
    }
}
