using Microsoft.eShopWeb.ApplicationCore.Entities.OrderAggregate;
using Microsoft.Playwright;

namespace WebTests.Page_Object_Models;

public class SuccessPage 
{
    private readonly IPage page;
    private ILocator ThanksForYourOrderText => page.Locator("h1").GetByText("Thanks for your Order", new() { Exact = false });
    private ILocator ContinueShoppingButton => page.Locator("a").GetByText("Continue Shopping", new() { Exact = false });
    public SuccessPage(IPage page)
    {
        this.page = page;
    }

    public async Task<IndexPage> ClickContinueShoppingAsync()
    {
        await Assertions.Expect(ContinueShoppingButton).ToBeAttachedAsync();
        await ContinueShoppingButton.ClickAsync();
        return new IndexPage(page); 
    }

    public async Task<bool> IsThanksForYourOrderAsync()
    {
        return (await ThanksForYourOrderText.TextContentAsync()) != null;
    }
}
