namespace Playwright.Tests;

[Parallelizable(ParallelScope.Self)]
[TestFixture]
public class AdminPurchase : BasePlaywrightTest
{
    private const string BaseUrl = "https://localhost:44315/";

    private string email = "admin@microsoft.com";
    private string password = "Pass@word1";

    [Test]
    public async Task AdminPurchaseSingleItem()
    { 
        await Page.GotoAsync($"{BaseUrl}");
        await TestHelper.SignIn(Page, email, password);

        await Page.ClickAsync("button[type='submit']");

        await TestHelper.Checkout(Page);

        await TestHelper.Logout(Page);
    }

    [Test]
    public async Task AdminPurchaseMultipleItems()
    {
        await Page.GotoAsync($"{BaseUrl}");
        await TestHelper.SignIn(Page, email, password);

        await TestHelper.AddItemToBasket(Page);
        await TestHelper.ContinueShopping(Page);
        await TestHelper.AddItemToBasket(Page);

        await TestHelper.Checkout(Page);

        await TestHelper.Logout(Page);
    }

    [Test]
    public async Task AdminRemovingSingleItem()
    {
        await Page.GotoAsync($"{BaseUrl}");
        await TestHelper.SignIn(Page, email, password);

        await TestHelper.AddItemToBasket(Page);

        await TestHelper.RemoveItemFromCheckout(Page);

        await TestHelper.Logout(Page);
    }
    [Test]
    public async Task AdminFilterProducts()
    {
        await Page.GotoAsync($"{BaseUrl}");
        await TestHelper.SignIn(Page, email, password);

        await TestHelper.FilterItems(Page);

        await TestHelper.Logout(Page);
    }
}

