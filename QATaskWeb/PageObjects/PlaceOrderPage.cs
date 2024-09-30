using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using QATaskWeb.Hooks;

namespace QATaskWeb.PageObjects
{
    public class PlaceOrderPage
    {

        public IWebDriver driver;

        public PlaceOrderPage()
        {
            driver = BaseTest.driver;
        }

        private By dotNetBlackAndWhiteMug = By.XPath("(//input[@class='esh-catalog-button'])[2]");
        private By productButton = By.XPath("//section[text()='Product']");
        private By continueShopping = By.XPath("//a[@class='btn esh-basket-checkout text-white']");
        private By prismWhiteTShirt = By.XPath("(//input[@type='submit'])[3]");
        private By updateButton = By.XPath("//button[@name='updatebutton']");
        private By reviewPageDisplayed = By.CssSelector("div h1 ");
        private By backButton = By.XPath("//a[text()='[ Back ]']");
        private By blackSweatShirt = By.XPath("(//input[@class='esh-catalog-button'])[1]");
        private By checkoutButton = By.XPath("//a[text()='[ Checkout ]']");
        private By PayNow = By.XPath("//input[@type='submit']");
        private By ThanksForYorOrderPagrDisplayed = By.XPath("//div[h1='Thanks for your Order!']");
        private By brandDropDown = By.Id("CatalogModel_BrandFilterApplied");
        private By typeDropDown = By.Id("CatalogModel_TypesFilterApplied");
        private By forwardArrowButton = By.XPath("//input[@class='esh-catalog-send']");
        private By roslynRedSheetDisplayed = By.XPath("//span[text()='Roslyn Red Sheet']");
        private By downArrow = By.XPath("//input[@type='number']");
        private By basketIsEmptyDisplayed = By.XPath("//h3");
        private By nextButton = By.XPath("(//a[@id='Next'])[1]");
        private By pageTwoOfTwoDisplayed = By.XPath("(//span[@class='esh-pager-item'])[1]");
        private By previousButton = By.XPath("(//a[@id='Previous'])[1]");
        private By pageOneOfTwoDisplayed = By.XPath("(//span[@class='esh-pager-item'])[1]");
        private By basket = By.XPath("//img[@src='/images/cart.png']");
        private By textBox = By.XPath("//input[@name='Items[0].Quantity']");

        public void ClickaddToBasketBlackAndWhiteMug()
        {
            ((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", driver.FindElement(dotNetBlackAndWhiteMug));
        }

        public bool VerifyProductButton()
        {
            return driver.FindElement(productButton).Displayed;
        }
        public void ClickContinueShopping()
        {
            ((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", driver.FindElement(continueShopping));
        }
        public void ClickPrimeWhiteTShirt()
        {
            ((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", driver.FindElement(prismWhiteTShirt));
        }
        public void ClickUpdateButton()
        {
            ((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", driver.FindElement(updateButton));
        }

        public bool VerifyReviewPageDisplayed()
        {
            return driver.FindElement(reviewPageDisplayed).Displayed;
        }
        public void ClickBackButton()
        {
            ((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", driver.FindElement(backButton));
        }

        public void ClickBlackSweatShirt()
        {
            ((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", driver.FindElement(blackSweatShirt));
        }

        public void ClickCheckoutButton()
        {
            ((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", driver.FindElement(checkoutButton));
        }

        public void ClickPayNow()
        {
            ((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", driver.FindElement(PayNow));
        }

        public bool VerifyThanksForYorOrderPagrDisplayed()
        {
            return driver.FindElement(ThanksForYorOrderPagrDisplayed).Displayed;
        }

        public void ClickBrandDropDowns()

        {
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(50));
            SelectElement sel = new SelectElement(driver.FindElement(brandDropDown));
            sel.SelectByValue("5");
        }
        public void ClickTypeDropDown()
        {
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(50));
            SelectElement sel = new SelectElement(driver.FindElement(typeDropDown));
            sel.SelectByValue("3");
        }
        public void ClickForwordArrowButton()
        {
            ((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", driver.FindElement(forwardArrowButton));
            driver.FindElement(forwardArrowButton).Click();
        }
        public bool VerifyRoslynRedSheetDisplayyed()
        {
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(50));
            return driver.FindElement(roslynRedSheetDisplayed).Displayed;
        }

        public void ClickDownArrow()
        {
            ((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", driver.FindElement(downArrow));
            driver.FindElement(downArrow).Clear();
            driver.FindElement(downArrow).SendKeys("0");
        }
        public bool VerifyBasketIsEmpty()
        {
            return driver.FindElement(basketIsEmptyDisplayed).Displayed;
        }
        public void ClickNextButton()
        {
            driver.FindElement(nextButton).Click();
        }
        public bool VerifyPageTwoOfTwoDisplayed()
        {
            return driver.FindElement(pageTwoOfTwoDisplayed).Displayed;
        }

        public void ClickPreviousButton()
        {
            driver.FindElement(previousButton).Click();
        }
        public bool VerifyPageOneOfTwoDisplayed()
        {
            return driver.FindElement(pageOneOfTwoDisplayed).Displayed;
        }

        public void ClickBasket()
        {
            driver.FindElement(basket).Click();
        }
    }

}

