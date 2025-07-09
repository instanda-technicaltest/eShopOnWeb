namespace Playwright.Tests;

[Parallelizable(ParallelScope.Self)]
[TestFixture]
public class UserPurchaseTests : BasePlaywrightTest
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

        await TestHelper.Logout(Page);
    }

    [Test]
    public async Task PurchaseMultipleItems()
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
    public async Task RemovingSingleItem()
    {
        await Page.GotoAsync($"{BaseUrl}");
        await TestHelper.SignIn(Page, email, password);

        await TestHelper.AddItemToBasket(Page);

        await TestHelper.RemoveItemFromCheckout(Page);

        await TestHelper.Logout(Page);
    }
    [Test]
    public async Task FilterProducts()
    {
        await Page.GotoAsync($"{BaseUrl}");
        await TestHelper.SignIn(Page, email, password);

        await TestHelper.FilterItems(Page);

        await TestHelper.Logout(Page);
    }

}
