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
    public async Task LoggedInUserPurchasesItemsOk()
    {
        var indexPage = new IndexPage(await browserContext.NewPageAsync());
        await indexPage.GotoAsync();

        // Log in
        outputHelper.WriteLine("Log in");
        var loginPage = await indexPage.ClickLoginAsync();

        await loginPage.EnterEmailAndPassword(
            Environment.GetEnvironmentVariable("HostTestUsername") ?? throw new NullReferenceException("HostTestUsername"),
            Environment.GetEnvironmentVariable("HostTestPassword") ?? throw new NullReferenceException("HostTestPassword"));

        var switchToIndexPage = await loginPage.ClickLoginAsync();
        switchToIndexPage.Should().NotBeNull("Failed to log in");

        await ClearBasket(indexPage);

        // Add items to the basket
        outputHelper.WriteLine("Add items to the basket");
        var expectedItems = new[] { "Kudu Purple Sweatshirt", "Roslyn Red Sheet" };
        BasketPage? basketPage = null;
        var needRedirect = false;
        foreach (var item in expectedItems)
        { 
            if (needRedirect)
                await indexPage.GotoAsync();

            var itemsDict = await indexPage.GetItemsDictionaryAsync();
            itemsDict.Should().ContainKeys(item);
            basketPage = await itemsDict[item].ClickAddToBasketAsync();
            needRedirect = true;
        }
        basketPage.Should().NotBeNull($"Failed to add items to the basket");

        // Checkout items
        outputHelper.WriteLine("Checkout items");
        const string expectedPrice = "$ 17.00";

        var basketTotal = await basketPage.GetBasketTotalAsync();
        basketTotal.Should().Be(expectedPrice);

        var basketItems = await basketPage.GetBasketItemsAsync();
        basketItems.Should().HaveCount(2);
        basketItems
            .Select(async s => await s.GetDescriptionAsync()).Select(s => s.Result)
            .Should().BeEquivalentTo(expectedItems);

        // Review order
        var reviewPage = await basketPage.ClickCheckoutAsync();
        reviewPage.Should().NotBeNull("Failed to checkout items");

        var reviewTotal = await reviewPage.GetReviewTotalAsync();
        reviewTotal.Should().Be(expectedPrice);

        // Expect success
        var successPage = await reviewPage.ClickPayNowAsync();
        successPage.Should().NotBeNull("Failed to pay for items");
        var isSuccessMessage = await successPage.IsThanksForYourOrderAsync();
        isSuccessMessage.Should().BeTrue("Failed to pay for items");
    }

    private async Task ClearBasket(IndexPage indexPage)
    {
        outputHelper.WriteLine("Clear the basket");
        var basketPage = await indexPage.ClickBasketAsync();
        var basketItems = await basketPage.GetBasketItemsAsync();
        if (basketItems.Length > 0)
        { 
            foreach (var basketItem in basketItems)
                await basketItem.SetQuantity(0);

            await basketPage.ClickUpdateAsync();
        }
        await indexPage.GotoAsync();
    }
}
