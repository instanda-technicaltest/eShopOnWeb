using Microsoft.CodeAnalysis.CSharp.Syntax;
using Microsoft.Playwright;

namespace WebTests.Page_Object_Models;

public class BasketPage
{
    private readonly IPage page;
    private ILocator BasketTotal => page.Locator(".esh-basket-item--mark").Last;
    private ILocator ContinueShoppingButton => page.Locator("div").GetByText("Continue Shopping", new(){ Exact = false});
    private ILocator UpdateButton => page.Locator("div [name='updatebutton']");
    private ILocator CheckoutButton => page.Locator("div").GetByText("Checkout", new() { Exact = false });
    private ILocator BasketItems => page.Locator("div .esh-catalog-items").Locator("article");
    internal static async Task<BasketItemSection[]> WrapBasketItemsAsync(ILocator basketItems)
    {
        var all = await basketItems.AllAsync();
        var count = all.Count(); //Skip the last three 'articles'
        return all
            .Take(count - 3)
            .Select(article => new BasketItemSection(article))
            .ToArray();
    }

    public BasketPage(IPage page)
    {
        this.page = page;
    }

    public async Task GotoAsync() => await page.GotoAsync("/Basket", new PageGotoOptions{ WaitUntil=WaitUntilState.DOMContentLoaded });

    public async Task<IndexPage> ClickContinueShoppingAsync()
    {
        await Assertions.Expect(ContinueShoppingButton).ToBeAttachedAsync();
        await ContinueShoppingButton.ClickAsync();
        return new IndexPage(page);
    }

    public async Task ClickUpdateAsync() 
    {
        await Assertions.Expect(UpdateButton).ToBeAttachedAsync();
        await UpdateButton.ClickAsync();
    }

    public async Task<ReviewPage> ClickCheckoutAsync()
    {
        await Assertions.Expect(CheckoutButton).ToBeAttachedAsync();
        await CheckoutButton.ClickAsync();
        return new ReviewPage(page);
    }

    public async Task<BasketItemSection[]> GetBasketItemsAsync()
    {
        return await WrapBasketItemsAsync(BasketItems);
    }

    public async Task<string> GetBasketTotalAsync()
    {
        await Assertions.Expect(BasketTotal).ToBeAttachedAsync();
        return await BasketTotal.InnerTextAsync();
    }
}
