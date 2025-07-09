namespace Playwright.Tests;

[Parallelizable(ParallelScope.Self)]
[TestFixture]
public class AdminPurchase : BasePlaywrightTest
{
    private const string BaseUrl = "https://localhost:44315/";

    [Test]
    public async Task AdminPurchaseSingleItem()
    {

        var email = "admin@microsoft.com";
        var password = "Pass@word1";

        await Page.GotoAsync($"{BaseUrl}");
        await TestHelper.SignIn(Page, email, password);

        await Page.ClickAsync("button[type='submit']");

        await TestHelper.Checkout(Page);
    }
}

