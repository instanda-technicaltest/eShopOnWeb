using Microsoft.Playwright;

namespace Playwright.Tests;
public abstract class BasePlaywrightTest : PageTest
{
    protected IBrowser Browser { get; private set; }

    protected IBrowserContext Context { get; private set; }

    protected IPage Page { get; private set; }

    [SetUp]
    public async Task SetUp()
    {
        Browser = await Playwright.Chromium.LaunchAsync(new() { Headless = true });
        Context = await Browser.NewContextAsync(new() { Locale = "en-GB" });
        Context.SetDefaultTimeout(60000);
        Page = await Context.NewPageAsync();
        Page.SetDefaultTimeout(60000);
    }

    [TearDown]
    public async Task Teardown()
    {
        await Browser.CloseAsync();
    }
}
