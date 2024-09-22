using BoDi;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using Microsoft.Extensions.Configuration;
using INSTANDA.Configuration;


namespace INSTANDA.Hooks
{
    [Binding]
    public sealed class Hooks
    {
        private readonly IObjectContainer _container;

        public Hooks(IObjectContainer container)

        {
            _container = container;
            LoadConfiguration();
        }
            

        //[BeforeScenario("@tag1")]
        //public void BeforeScenarioWithTag()
        //{
        //    LoadConfiguration();
        //}

        [BeforeTestRun]
        public static void BeforeTestRun(IObjectContainer _container)
        {
            IWebDriver driver = new ChromeDriver();
            driver.Manage().Window.Maximize();
            _container.RegisterInstanceAs<IWebDriver>(driver);
            LoadConfiguration();
        }

        [AfterTestRun]
        public static void AfterTestRun(IObjectContainer _container)
        {
          var driver=_container.Resolve<IWebDriver>();
            if (driver != null)
            {
                driver.Quit();
            }            
        }

        private static void LoadConfiguration()
        {
            var config = new ConfigurationBuilder()
                .SetBasePath(Directory.GetCurrentDirectory())
                .AddJsonFile("appsettings.Environment.json")
                .Build();

            var _environmentConfig = config.Get<EnvironmentConfig>();
            SetConfig.WebUrl = _environmentConfig.WebUrl;
            SetConfig.ApiUrl = _environmentConfig.ApiUrl;
        }
    }
}