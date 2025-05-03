using System.Runtime.CompilerServices;
using Microsoft.CodeAnalysis.CSharp.Syntax;
using Microsoft.Playwright;

namespace WebTests.Page_Object_Models;

public class IndexPage
{
    private readonly IPage page;
    private ILocator LoginButton => page.Locator(@"[href^='/Identity/Account/Login']");
    private ILocator BrandOption => page.Locator("#CatalogModel_BrandFilterApplied");
    private ILocator TypeOption => page.Locator("#CatalogModel_TypesFilterApplied");
    private ILocator BasketButton => page.Locator(@"[href^='/Basket']");
    private ILocator Items => page.Locator(".esh-catalog-item").Locator("form");
    private async Task<IndexItemSection[]> WrapItemsAsync()
    {
        var allResult = await Items.AllAsync();

        return allResult.Select(item => new IndexItemSection(page, item)).ToArray();
    }

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
        await Assertions.Expect(LoginButton).ToBeAttachedAsync();
        await LoginButton.ClickAsync();
        return new LoginPage(page);
    }

    public async Task<BasketPage> ClickBasketAsync()
    {
        await Assertions.Expect(BasketButton).ToBeAttachedAsync();
        await BasketButton.ClickAsync();
        return new BasketPage(page);
    }

    public async Task<List<string>> GetBrandOptionsAsync()
    {
        await Assertions.Expect(BrandOption).ToBeAttachedAsync();
        return await GetOptionsAsync(BrandOption);
    }

    public async Task SelectBrandOptionAsync(string brandText)
    {
        await Assertions.Expect(BrandOption).ToBeAttachedAsync(); 
        await BrandOption.SelectOptionAsync(new[] { brandText } );
    }

    public async Task<List<string>> GetTypeOptionsAsync()
    {
        await Assertions.Expect(TypeOption).ToBeAttachedAsync(); 
        return await GetOptionsAsync(TypeOption);
    }

    public async Task SelectTypeOptionAsync(string typeText)
    {
        await Assertions.Expect(TypeOption).ToBeAttachedAsync(); 
        await TypeOption.SelectOptionAsync(new[] { typeText });
    }

    public async Task<IndexItemSection[]> GetItems()
    {
        return await WrapItemsAsync();
    }

    /// <returns>Dictionary of item descriptions against <see cref="IndexItemSection"/> instances</returns>
    public async Task<Dictionary<string, IndexItemSection>> GetItemsDictionaryAsync()
    {
        var shownItems = (await GetItems()).ToList();
        var itemsDict = shownItems
            .Select(async s => new { Key = await s.GetDescriptionAsync(), Value = s })
            .Select(r => r.Result)
            .Where(w => w.Key != null)
            .ToDictionary(k => k.Key!.Trim(), v => v.Value);
        return itemsDict;
    }
}
