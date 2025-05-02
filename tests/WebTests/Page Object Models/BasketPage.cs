using Microsoft.CodeAnalysis.CSharp.Syntax;
using Microsoft.Playwright;

namespace WebTests.Page_Object_Models;

public class BasketPage
{
    private readonly IPage page;
    private ILocator BasketItems => page.Locator("div .esh-catalog-items").Locator("article");
   
    private ILocator BasketTotal => page.Locator(".esh-basket-item--mark").Last;

    private ILocator ContinueShoppingButton => page.Locator("div").GetByText("Continue Shopping", new(){ Exact = false});
    private ILocator UpdateButton => page.Locator("div [name='updatebutton']");
    private ILocator CheckoutButton => page.Locator("div").GetByText("Checkout", new() { Exact = false });

    public BasketPage(IPage page)
    {
        this.page = page;
    }

    public async Task GotoAsync() => await page.GotoAsync("/Basket", new PageGotoOptions{ WaitUntil=WaitUntilState.DOMContentLoaded });
    public async Task<IndexPage> ClickContinueShoppingAsync()
    {
        await Assertions.Expect(ContinueShoppingButton).ToBeAttachedAsync(new() { Timeout = Constants.ExpectTimeoutMs });
        await ContinueShoppingButton.ClickAsync();
        return new IndexPage(page);
    }

    public async Task ClickUpdateAsync() 
    {
        await Assertions.Expect(UpdateButton).ToBeAttachedAsync(new() { Timeout = Constants.ExpectTimeoutMs });
        await UpdateButton.ClickAsync();
    }
    public async Task ClickCheckoutAsync()
    {
        await Assertions.Expect(CheckoutButton).ToBeAttachedAsync(new() { Timeout = Constants.ExpectTimeoutMs });
        await CheckoutButton.ClickAsync();
    }
    public async Task<BasketItemSection[]> GetBasketItemsAsync()
    {
        var all = await BasketItems.AllAsync();
        var count = await BasketItems.CountAsync(); //Skip the last three 'articles'
        return all.Take(count-3).Select(async article => new BasketItemSection(article)).Select(t => t.Result).ToArray(); 

    }
    public async Task<string> GetBasketTotalAsync()
    {
        await Assertions.Expect(BasketTotal).ToBeAttachedAsync(new() { Timeout = Constants.ExpectTimeoutMs});
        return await BasketTotal.InnerTextAsync();
    }
}
