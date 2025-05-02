using Microsoft.Playwright;

namespace WebTests.Page_Object_Models;

public class IndexPage
{
    private readonly IPage page;
    private ILocator LoginButton => page.Locator(@"[href^='/Identity/Account/Login']");
    private ILocator BrandOption => page.Locator("#CatalogModel_BrandFilterApplied");
    private ILocator TypeOption => page.Locator("#CatalogModel_TypesFilterApplied");
    private ILocator BasketButton => page.Locator(@"[href^='/Basket']");

    public IndexPage(IPage page)
    {
        this.page = page;
    }

    private async Task<List<string>> GetOptionsAsync(ILocator option)
    {
        var allOptions = await option.Locator("option").AllAsync();
        return allOptions
            .Select(async option => await option.InnerTextAsync())
            .Select(t => t.Result)
            .ToList();
    }

    public async Task GotoAsync() => await page.GotoAsync("/", new PageGotoOptions{ WaitUntil=WaitUntilState.DOMContentLoaded });
    public async Task<LoginPage> ClickLoginAsync()
    {
        await Assertions.Expect(LoginButton).ToBeAttachedAsync(new() { Timeout = Constants.ExpectTimeoutMs });
        await LoginButton.ClickAsync();
        return new LoginPage(page);
    }

    public async Task<BasketPage> ClickBasketAsync()
    {
        await Assertions.Expect(BasketButton).ToBeAttachedAsync(new() { Timeout = Constants.ExpectTimeoutMs });
        await BasketButton.ClickAsync();
        return new BasketPage(page);
    }

    public async Task<List<string>> GetBrandOptionsAsync()
    {
        await Assertions.Expect(BrandOption).ToBeAttachedAsync(new() { Timeout = Constants.ExpectTimeoutMs });
        return await GetOptionsAsync(BrandOption);
    }
    public async Task SelectBrandOptionAsync(string brandText)
    {
        await Assertions.Expect(BrandOption).ToBeAttachedAsync(new() { Timeout = Constants.ExpectTimeoutMs }); 
        await BrandOption.SelectOptionAsync(new[] { brandText } );
    }
    public async Task<List<string>> GetTypeOptionsAsync()
    {
        await Assertions.Expect(TypeOption).ToBeAttachedAsync(new() { Timeout = Constants.ExpectTimeoutMs }); 
        return await GetOptionsAsync(TypeOption);
    }
    public async Task SelectTypeOptionAsync(string typeText)
    {
        await Assertions.Expect(TypeOption).ToBeAttachedAsync(new() { Timeout = Constants.ExpectTimeoutMs }); 
        await TypeOption.SelectOptionAsync(new[] { typeText });
    }
}
