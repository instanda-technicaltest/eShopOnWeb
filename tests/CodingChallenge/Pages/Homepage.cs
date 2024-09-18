using CodingChallenge.Configurations;
using Microsoft.Playwright;
using CodingChallenge.StaticContexts;

namespace CodingChallenge.Pages;

public class Homepage
{
    private readonly IPage _page;

    public Homepage(IPage page)
    {
        _page = page;
    }

    //Selector
    private ILocator ItemsPerPage => _page.GetByText("[ ADD TO BASKET ]");
    private ILocator BasketIcon => _page.Locator(".esh-basketstatus-image");
    private ILocator LoginNavLink => _page.GetByRole(AriaRole.Link, new() { NameString = "Login" });



    //Methods

    public async Task NavigateTo()
    {
        await _page.GotoAsync(Config.BaseUrl!);
    }

    public async Task NavigateToLoginPage()
    {
        await LoginNavLink.ClickAsync();
        await _page.WaitForURLAsync($"{Config.BaseUrl}{Routes.PageRoutes.LoginPage}");
    }

    public async Task NavigateToBasketPage()
    {
        await BasketIcon.ClickAsync();
    }

    public async Task AddItemsToBasket(int numberOfItems)
    {
        var itemCount = await ItemsPerPage.CountAsync();

        var random = new Random();
        var selectedItems = Enumerable.Range(0, itemCount).OrderBy(_ => random.Next()).Take(numberOfItems).ToList();

        foreach (var item in selectedItems.Select(itemId => ItemsPerPage.Nth(itemId)))
        {
            //scroll item into view
            await item.ScrollIntoViewIfNeededAsync();
            await item.ClickAsync();

            //navigate back to home page
            var basketPage = new BasketPage(_page);
            await basketPage.ContinueShopping();
        }
    }
}
