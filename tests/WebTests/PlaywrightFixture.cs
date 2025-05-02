using Microsoft.Playwright;

namespace WebTests;

public class PlaywrightFixture : IAsyncLifetime
{
    public IBrowser? Browser { private set; get; }
    public IBrowserContext? BrowserContext { private set; get; }

    private IPlaywright? playwright;

    public async Task InitializeAsync()
    {
        playwright = await Playwright.CreateAsync();
        Browser = await playwright.Chromium.LaunchAsync(new() { Headless = false });
        BrowserContext = await Browser.NewContextAsync(BrowserContextOptions());
    }

    public BrowserNewContextOptions BrowserContextOptions()
    {
        return new BrowserNewContextOptions()
        {
            ColorScheme = ColorScheme.Light,
            ViewportSize = new()
            {
                Width = 1024,
                Height = 640
            },
            BaseURL = Environment.GetEnvironmentVariable("HostTestBaseUrl")
        };
    }

    public async Task DisposeAsync()
    {
        if (Browser != null)
            await Browser.DisposeAsync();

        playwright?.Dispose();
    }
}
