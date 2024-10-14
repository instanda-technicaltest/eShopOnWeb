using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;

namespace eShopOnWebE2ETests.PageObjects
{
    public class LoginPage
    {
        private IWebDriver _driver;

        public LoginPage(IWebDriver driver)
        {
            _driver = driver;
        }
        private IWebElement LoginButton => _driver.FindElement(By.XPath("//a[contains(text(),'Login')]"));
        private IWebElement UserNameInput => _driver.FindElement(By.XPath("//input[@id='Input_Email']"));
        private IWebElement PasswordInput => _driver.FindElement(By.XPath("//input[@id='Input_Password']"));
        private IWebElement SubmitLoginButton => _driver.FindElement(By.XPath("//button[normalize-space()='Log in']"));
        private IWebElement LoginPageTitle => _driver.FindElement(By.XPath("//h2[normalize-space()='Log in']"));
        public void EnterUsername(string username)
        {
            UserNameInput.SendKeys(username);
        }
        public void EnterPassword(string password)
        {
            PasswordInput.SendKeys(password);
        }
        public void ClickLogin()
        {
            LoginButton.Click();
        }
        public string GetThePageTitle()
        {
            return LoginPageTitle.Text;
        }
        public void SubmitLogin()
        {
            SubmitLoginButton.Click();
        }
    }
}
