using Microsoft.Playwright;

namespace WebTests.Page_Object_Models;

public class IndexItemSection
{
    private readonly IPage page;
    private readonly ILocator article;

    private ILocator Image => article.Locator("img");
    private ILocator AddToBasketButton => article.Locator(".esh-catalog-button");
    private ILocator Description => article.Locator(".esh-catalog-name");
    private ILocator Price => article.Locator(".esh-catalog-price");

    public IndexItemSection(IPage page, ILocator article)
    {
        this.page = page;
        this.article = article;
    }

    public async Task<string?> GetImageAsync()
    {
        await Assertions.Expect(Image).ToBeAttachedAsync();
        return await Image.GetAttributeAsync("src");
    }

    public async Task<string?> GetDescriptionAsync()
    {
        await Assertions.Expect(Description).ToBeAttachedAsync();
        return await Description.TextContentAsync();
    }

    public async Task<string?> GetPriceAsync()
    {
        await Assertions.Expect(Price).ToBeAttachedAsync();
        return await Price.TextContentAsync();
    }

    public async Task<BasketPage> ClickAddToBasketAsync()
    {
        await Assertions.Expect(AddToBasketButton).ToBeAttachedAsync();
        await AddToBasketButton.ClickAsync();
        return new BasketPage(page);
    }
}
