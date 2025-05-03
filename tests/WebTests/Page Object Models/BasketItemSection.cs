using Microsoft.Playwright;

namespace WebTests.Page_Object_Models;
public class BasketItemSection
{
    private readonly ILocator article;

    private ILocator Image => article.Locator("div .esh-basket-image");
    private ILocator Description => article.Locator("div section:nth-child(2)");
    private ILocator Price => article.Locator("div section:nth-child(3)");
    private ILocator Quantity => article.Locator("div section:nth-child(4) input:nth-child(2)");
    private ILocator TotalCost => article.Locator("div section:nth-child(5)");

    public BasketItemSection(ILocator article)
    {
        this.article = article;
    }

    public async Task<string?> GetImageAsync()
    {
        await Assertions.Expect(Image).ToBeAttachedAsync();
        return await Image.GetAttributeAsync("src");
    }

    public async Task<string?> GetDescriptionAsync()
    {
        var look = await article.InnerHTMLAsync();
        await Assertions.Expect(Description).ToBeAttachedAsync();
        return await Description.TextContentAsync();
    }

    public async Task<string?> GetPriceAsync()
    {
        await Assertions.Expect(Price).ToBeAttachedAsync();
        return await Price.TextContentAsync();
    }

    public async Task<string?> GetQuantityAsync()
    {
        await Assertions.Expect(Quantity).ToBeAttachedAsync();
        return await Quantity.GetAttributeAsync("value");
    }

    public async Task<string?> GetTotalCostAsync()
    {
        await Assertions.Expect(TotalCost).ToBeAttachedAsync();
        return await TotalCost.TextContentAsync();
    }

    public async Task SetQuantity(int newQuantity)
    {
        await Assertions.Expect(Quantity).ToBeAttachedAsync();
        await Quantity.FillAsync(newQuantity.ToString());
    }
}
