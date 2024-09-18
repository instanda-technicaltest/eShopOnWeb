using BoDi;
using CodingChallenge.Configurations;
using CodingChallenge.Pages;
using Microsoft.Extensions.Configuration;
using Microsoft.Playwright;
using System.Text.Json;


namespace CodingChallenge.Hooks;

[Binding]
public class Hooks
{
    private readonly IObjectContainer _objectContainer;
    private static string? _authenticatedState;
    private static PlaywrightSettings? _playwrightSettings;
    private IBrowser? _browser;
    private IBrowserContext? _browserContext;
    private readonly ScenarioContext? _scenarioContext;

    public Hooks(IObjectContainer container, ScenarioContext scenarioContext)
    {
        _objectContainer = container;
        LoadPlaywrightSettings();
        _scenarioContext = scenarioContext;
    }

    [BeforeTestRun]
    public static async Task BeforeTestRunAsync() 
    {
        //Fetch Url
        LoadPlaywrightSettings();

        // Load instance of browser
        using var pw = await Playwright.CreateAsync();
        var browser = await pw.Chromium.LaunchAsync(new BrowserTypeLaunchOptions { Headless = false });
        var context = await browser.NewContextAsync(new BrowserNewContextOptions { IgnoreHTTPSErrors = true });
        var page = await context.NewPageAsync();

        //Perform log in
        var loginPage = new LoginPage(page);
        await loginPage.NavigateToAsync();
        await loginPage.Login("demouser@microsoft.com", "Pass@word1");

        //save state to be reused in other tests
            
        var cookie = await page.Context.CookiesAsync();
        _authenticatedState = JsonSerializer.Serialize(cookie);

        await browser.CloseAsync();
           
    }

    [BeforeScenario]
    public async Task BeforeScenario() 
    {
        // Skip browser set up if it's an API test only
        if(_scenarioContext!.ScenarioInfo.Tags.Contains("ApiTest")) { return; }

        //Initialize Browser with config settings
        _browser = await InitializeBrowserAsync();
        _browserContext = await CreateBrowserContextAsync();
        var page = await _browserContext.NewPageAsync();

        // Load Authentication if test is tagged
        if (_scenarioContext!.ScenarioInfo.Tags.Contains("authenticated"))
        {
            var cookies = JsonSerializer.Deserialize<List<Cookie>>(_authenticatedState!);
            await page.Context.AddCookiesAsync(cookies!);
        }

        //Register Page to be used by Steps and Page Objects
        _objectContainer.RegisterInstanceAs(page);
        _objectContainer.RegisterInstanceAs(_playwrightSettings!);
    }

    [AfterScenario]
    public async Task AfterScenario()
    {
        if (_browserContext != null) 
        {
            //Close Page
            var page = _objectContainer.Resolve<IPage>();
            await page.CloseAsync();

            //Close BrowserContext and Browser
            await _browserContext!.CloseAsync();
            await _browser!.CloseAsync();
        }

    }
    private static void LoadPlaywrightSettings()
    {
        var config = new ConfigurationBuilder()
            .SetBasePath(Directory.GetCurrentDirectory())
            .AddJsonFile("appsettings.json")
            .Build();

        _playwrightSettings = config.Get<PlaywrightSettings>()!;
        Config.BaseUrl = _playwrightSettings.BaseUrl;
        Config.ApiUrl = _playwrightSettings.ApiUrl;
    }
    private async Task<IBrowser> InitializeBrowserAsync()
    {
        var playwright = await Playwright.CreateAsync();
        switch (_playwrightSettings!.Browser!.ToLower())
        {
            case "chromium":
                return await playwright.Chromium.LaunchAsync(_playwrightSettings.BrowserTypeLaunchOptions);
            case "firefox":
                return await playwright.Firefox.LaunchAsync(_playwrightSettings.BrowserTypeLaunchOptions);
            case "webkit":
                return await playwright.Webkit.LaunchAsync(_playwrightSettings.BrowserTypeLaunchOptions);
            default:
                throw new ArgumentException("Invalid Browser type selected, update appsettings.json with Valid Browser");
        }
    }

    private async Task<IBrowserContext> CreateBrowserContextAsync() 
    {
        var browserContextOptions = _playwrightSettings?.BrowserContextOptions;

        if (browserContextOptions!.ViewportSize != null &&
            browserContextOptions.ViewportSize.Width > 0 &&
            browserContextOptions.ViewportSize.Height > 0)
            return await _browser!.NewContextAsync(browserContextOptions);
        // Log Invalid Config
        Console.WriteLine("Invalid viewport configuration. Using default values");

        browserContextOptions.ViewportSize = new ViewportSize
        {
            Width = 1280,
            Height = 720
        };
        return await _browser!.NewContextAsync(browserContextOptions);

    }

}
