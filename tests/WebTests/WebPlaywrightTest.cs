using FluentAssertions;
using Microsoft.Playwright;
using WebTests.Page_Object_Models;
using Xunit.Abstractions;

namespace WebTests;

public class WebPlaywrightTest : IClassFixture<PlaywrightFixture>
{
    private readonly IBrowserContext browserContext;
    private readonly ITestOutputHelper outputHelper;

    public WebPlaywrightTest(PlaywrightFixture playwrightFixture, ITestOutputHelper outputHelper)
    {
        browserContext = playwrightFixture.BrowserContext ?? throw new InvalidOperationException(nameof(playwrightFixture.BrowserContext));
        this.outputHelper = outputHelper;
    }

    [Fact]
    public async Task WIP_POM_DevelopmentTest()
    {
        // NOTE this is a test harness to check my POM selectors
        // This test function does not yet make sense as a test
        // WILL BE DELETED SHORTLY - WIP
        var indexPage = new IndexPage(await browserContext.NewPageAsync());
        outputHelper.WriteLine("Navigating to the page");
        await indexPage.GotoAsync();
        var look = await indexPage.GetBrandOptionsAsync();



        outputHelper.WriteLine("Clicking login");
        var loginPage = await indexPage.ClickLoginAsync();

        outputHelper.WriteLine("Enter email and password");
        await loginPage.EnterEmail(Environment.GetEnvironmentVariable("HostTestUsername"));
        await loginPage.EnterPassword(Environment.GetEnvironmentVariable("HostTestPassword"));
        var switchToIndexPage = await loginPage.ClickLoginAsync();
        switchToIndexPage.Should().NotBeNull("Failed to log in");

        outputHelper.WriteLine("View basket");
        var basketPage = await indexPage.ClickBasketAsync();
        var basketTotal = await basketPage.GetBasketTotalAsync();
        var basketItems = await basketPage.GetBasketItemsAsync();
        basketItems.Should().HaveCount(2);

        outputHelper.WriteLine("Enumerate items");
        foreach (var item in basketItems)
        {
            var description = await item.GetDescriptionAsync();
            var price = await item.GetPriceAsync();
            var quantity = await item.GetQuantityAsync();
            var totalCost = await item.GetTotalCostAsync();
            var image = await item.GetImageAsync();
        }
    }
}
