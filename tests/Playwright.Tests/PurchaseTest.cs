using System.Diagnostics.CodeAnalysis;
using Microsoft.Playwright;

namespace Playwright.Tests;

[Parallelizable(ParallelScope.Self)]
[TestFixture]
public class PurchaseTest : BasePlaywrightTest
{
    private const string BaseUrl = "https://localhost:44315/";
    private string email = "demouser@microsoft.com";
    private string password = "Pass@word1";

    [Test]
    public async Task PurchaseSingleItem()
    {
        await Page.GotoAsync($"{BaseUrl}");
        await TestHelper.SignIn(Page, email, password);

        await TestHelper.AddItemToBasket(Page);

        await TestHelper.Checkout(Page);
    }

    public async Task RemovingSingleItem()
    {
        await Page.GotoAsync($"{BaseUrl}");
        await TestHelper.SignIn(Page, email, password);

        await TestHelper.AddItemToBasket(Page);

        await TestHelper.RemoveItemFromCheckout(Page);
    }

}
