using AventStack.ExtentReports;
using AventStack.ExtentReports.Reporter;
using Microsoft.Extensions.Configuration;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.IE;
using Serilog;
using Serilog.Core;
using Serilog.Formatting.Json;
using WebDriverManager.DriverConfigs.Impl;

namespace QATaskWeb.Hooks
{
    [Binding]
    public class BaseTest
    {
        public static IWebDriver driver = null;
        static AventStack.ExtentReports.ExtentReports extent;
        static AventStack.ExtentReports.ExtentTest feature;
        AventStack.ExtentReports.ExtentTest scenario, step;


        static AppSetting config;
        static string appsettingpath = System.IO.Directory.GetParent(@"../../../").FullName
            + Path.DirectorySeparatorChar + "appsetting.json";


        [BeforeTestRun]
        public static void BeforeTestRun()
        {
            config = new AppSetting();
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.AddJsonFile(appsettingpath);
            IConfigurationRoot configuration = builder.Build();
            configuration.Bind(config);

            string reportpath = Path.Combine(Directory.GetParent(@"../../../").FullName, "Result", "ExtentReport.html");

            ExtentHtmlReporter htmlreport = new ExtentHtmlReporter(reportpath);
            extent = new AventStack.ExtentReports.ExtentReports();
            extent.AttachReporter(htmlreport);

            LoggingLevelSwitch levelSwitch = new LoggingLevelSwitch(Serilog.Events.LogEventLevel.Debug);
            Log.Logger = new LoggerConfiguration()
                .MinimumLevel.ControlledBy(levelSwitch)
                .WriteTo.File(new JsonFormatter(), reportpath + @"\Logs").CreateLogger();
        }
        public static string GetBaseUrl()
        {
            return config.Url;
        }

        [BeforeFeature]
        public static void BeforeFeature(FeatureContext context)
        {
            if (context == null || context.FeatureInfo == null)
            {
                Log.Error("FeatureContext or FeatureInfo is null.");
                throw new ArgumentNullException(nameof(context), "FeatureContext or FeatureInfo is null.");
            }

            if (extent == null)
            {
                Log.Error("ExtentReports instance is not initialized.");
                throw new NullReferenceException("ExtentReports instance is null.");
            }

            feature = extent.CreateTest(context.FeatureInfo.Title);
            Log.Information("Selecting Feature file {0} to run", context.FeatureInfo.Title);
        }

        [BeforeScenario]
        public void BeforeScenario()
        {
            string browserName = config.BrowserType;



            switch (browserName.ToUpper())
            {

                case "CHROME":
                    new WebDriverManager.DriverManager().SetUpDriver(new ChromeConfig());
                    var chromeOptions = new ChromeOptions();
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "FIREFOX":
                    new WebDriverManager.DriverManager().SetUpDriver(new FirefoxConfig());
                    driver = new FirefoxDriver();
                    break;

                case "IE":
                    new WebDriverManager.DriverManager().SetUpDriver(new InternetExplorerConfig());
                    driver = new InternetExplorerDriver();
                    break;


                case "EDGE":
                    new WebDriverManager.DriverManager().SetUpDriver(new EdgeConfig());
                    driver = new EdgeDriver();
                    break;

                case "":
                    Console.WriteLine("No browser added. Please check the appsetting.json");
                    break;

                default:
                    Console.WriteLine("Browser" + browserName + "not supported");
                    Console.WriteLine("Please check the appsetting.json");
                    break;

            }
            driver.Manage().Window.Maximize();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(20);
        }

        [BeforeStep]
        public void BeforeStep()
        {
            step = scenario;
        }

        [AfterStep]
        public void AfterStep(ScenarioContext context)
        {

            if (context == null)
            {
                Log.Error("ScenarioContext is null.");
                return;
            }

            if (context.StepContext != null && context.StepContext.StepInfo != null)
            {
                if (context.TestError == null)
                {
                    //step.Log(AventStack.ExtentReports.Status.Pass, context.StepContext.StepInfo.Text);
                }
                else
                {
                    string base64 = GetScreenShot();
                    Log.Error("Test Step Failed | " + context.TestError.Message);
                    step.Log(AventStack.ExtentReports.Status.Fail, context.StepContext.StepInfo.Text,
                             MediaEntityBuilder.CreateScreenCaptureFromBase64String(base64).Build());
                }
            }
            else
            {
                Log.Error("StepContext or StepInfo is null.");
            }

        }

        [AfterScenario]
        public void AfterScenario()
        {
            if (driver != null)
            {
                driver.Quit();
                driver.Dispose();
            }
        }

        [AfterFeature]
        public static void AfterFeature()
        {
            extent.Flush();
        }

        public string GetScreenShot()
        {
            return ((ITakesScreenshot)driver).GetScreenshot().AsBase64EncodedString;
        }

    }
}
