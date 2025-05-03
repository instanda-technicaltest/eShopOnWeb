using Microsoft.CodeAnalysis.CSharp.Syntax;
using Microsoft.Playwright;

namespace WebTests.Page_Object_Models;

public class ReviewPage
{
    private readonly IPage page;
    private ILocator ReviewTotal => page.Locator(".esh-basket-item--mark").Last;
    private ILocator ReviewTitle => page.Locator("h1").GetByText("Review");
    private ILocator PayNowButton => page.Locator("input").GetByText("Pay Now", new() { Exact = false });
    private ILocator ReviewItems => page.Locator("div .esh-catalog-items").Locator("article");
   

    public ReviewPage(IPage page)
    {
        this.page = page;    
    }

    public async Task GotoAsync() 
    {
        await page.GotoAsync("/Basket", new PageGotoOptions{ WaitUntil=WaitUntilState.DOMContentLoaded });
        await Assertions.Expect(ReviewTitle).ToBeAttachedAsync();
    }   

    public async Task<BasketItemSection[]> GetReviewItemsAsync()
    {
        return await BasketPage.WrapBasketItemsAsync(ReviewItems);
    }

    public async Task<string> GetReviewTotalAsync()
    {
        await Assertions.Expect(ReviewTotal).ToBeAttachedAsync();
        return await ReviewTotal.InnerTextAsync();
    }

    public async Task<SuccessPage?> ClickPayNowAsync()
    {
        await Assertions.Expect(PayNowButton).ToBeAttachedAsync();
        await PayNowButton.ClickAsync();

        var isPageChange = page.Url.EndsWith("success", StringComparison.InvariantCultureIgnoreCase);
        if (isPageChange)
            return new SuccessPage(page);
        else
            return null;
    }
}
