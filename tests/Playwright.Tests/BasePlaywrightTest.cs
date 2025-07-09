using Microsoft.Playwright;
using Microsoft.Playwright.NUnit;
using System.Diagnostics.CodeAnalysis;
using static System.Net.Mime.MediaTypeNames;

namespace Playwright.Tests;
public abstract class BasePlaywrightTest : PageTest
{
    protected IBrowser Browser { get; private set; }

    protected IBrowserContext Context { get; private set; }

    protected IPage Page { get; private set; }

    [SetUp]
    public async Task SetUp()
    {
        Browser = await Playwright.Chromium.LaunchAsync(new() { Headless = false });
        Context = await Browser.NewContextAsync(new() { Locale = "en-GB" });
        Context.SetDefaultTimeout(60000);
        Page = await Context.NewPageAsync();
        Page.SetDefaultTimeout(60000);
    }
}
