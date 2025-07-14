namespace Playwright.Tests;

[Parallelizable(ParallelScope.Self)]
[TestFixture]
public class AdminPurchase : BasePlaywrightTest
{
    private string email = "admin@microsoft.com";
    private string password = "Pass@word1";

    [Test]
    public async Task AdminPurchaseSingleItem()
    {
        await TestHelper.SignIn(Page, email, password);

        await TestHelper.AddItemToBasket(Page);

        await TestHelper.Checkout(Page);

        await TestHelper.Logout(Page);
    }

    [Test]
    public async Task AdminPurchaseMultipleItems()
    {
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
        await TestHelper.SignIn(Page, email, password);

        await TestHelper.AddItemToBasket(Page);

        await TestHelper.RemoveItemFromCheckout(Page);

        await TestHelper.Logout(Page);
    }
    [Test]
    public async Task AdminFilterProducts()
    {
        await TestHelper.SignIn(Page, email, password);

        await TestHelper.FilterItems(Page);

        await TestHelper.Logout(Page);
    }
}

