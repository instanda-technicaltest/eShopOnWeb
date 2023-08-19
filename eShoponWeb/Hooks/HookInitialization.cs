using eShoponWeb.Drivers;
using OpenQA.Selenium;
using TechTalk.SpecFlow;

namespace eShoponWeb.Hooks;
[Binding]
public sealed class HookInitialization
{
    private readonly ScenarioContext _scenarioContext;

    public HookInitialization(ScenarioContext scenarioContext) => _scenarioContext = scenarioContext;

    [BeforeScenario("@tag1")]
    
    public void BeforeScenarioWithTag()
    {
        SeleniumDriver seleniumDriver = new SeleniumDriver(_scenarioContext);
        _scenarioContext.Set(seleniumDriver, "SeleniumDriver");
    }

   

    [AfterScenario]
    public void AfterScenario()
    {
        Console.WriteLine("Selenium Webdriver quit");
        _scenarioContext.Get<IWebDriver>("Webdriver").Quit();
        
    }
}
