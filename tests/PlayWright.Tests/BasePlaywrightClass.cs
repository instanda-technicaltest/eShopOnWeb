using Microsoft.Playwright;

namespace Playwright.Tests;
public abstract class BasePlaywrightClass : PageTest
{
    protected IBrowser Browser { get; private set; }

    protected IBrowserContext Context { get; private set; }

    protected IPage Page { get; private set; }

    private const string BaseUrl = "https://localhost:44315/";

    [SetUp]
    public async Task SetUp()
    {
        Browser = await Playwright.Chromium.LaunchAsync(new() { Headless = false });
        Context = await Browser.NewContextAsync(new() { Locale = "en-GB" });
        Context.SetDefaultTimeout(60000);
        Page = await Context.NewPageAsync();
        Page.SetDefaultTimeout(60000);
        await Page.GotoAsync($"{BaseUrl}");
    }

    [TearDown]
    public async Task Teardown()
    {
        await Browser.CloseAsync();
    }
}
