using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.VisualStudio.Services.Profile;
using OpenQA.Selenium;
using OpenQA.Selenium.DevTools.V113.Network;
using SeleniumExtras.PageObjects;

namespace eShoponWeb.StepDefinitions;
public class LoginPage

{
    public LoginPage()
    {

        private IWebDriver driver;
        public LoginPage(IWebDriver driver)
        { 
        this.driver = driver;
        PageFactory.InitELements(driver, this);
        }   
        //PageObject Factory
        [FindsBy(How = How.Id, Using = "Input_Email")]
        private IWebElement Emailid;


        public IWebElement getEmailid()
    {
        return Emailid;
    }





    }

}
