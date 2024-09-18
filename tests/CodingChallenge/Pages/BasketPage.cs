using Microsoft.Playwright;


namespace CodingChallenge.Pages;

public class BasketPage
{
    private readonly IPage _page;

    public BasketPage(IPage page)
    {
        _page = page;
    }

    //Selector
    private ILocator ContinueShoppingButton => _page.GetByRole(AriaRole.Link, new PageGetByRoleOptions { NameString = "[ CONTINUE SHOPPING ]" });
    private ILocator CheckOutButton => _page.GetByRole(AriaRole.Link, new PageGetByRoleOptions { NameString = "[ CHECKOUT ]" });

    // Checkout page Selectors
    private ILocator PayNowButton => _page.GetByRole(AriaRole.Button, new PageGetByRoleOptions { NameString = "[ Pay Now ]" });

    //Success page Selector
    private ILocator ConfirmationMessage => _page.GetByRole(AriaRole.Heading, new PageGetByRoleOptions { NameString = "Thanks for your Order!" });



    //Methods
    public async Task ContinueShopping()
    {
        await ContinueShoppingButton.ScrollIntoViewIfNeededAsync();
        await ContinueShoppingButton.ClickAsync();
    }

    public async Task SelectCheckout()
    {
        await CheckOutButton.ScrollIntoViewIfNeededAsync();
        await CheckOutButton.ClickAsync();
    }

    public async Task SelectPayNow()
    {
        await PayNowButton.ScrollIntoViewIfNeededAsync();
        await PayNowButton.ClickAsync();
    }

    public async Task<bool> IsOrderProcessedSuccessfully() 
    {
        var successMessage = await ConfirmationMessage.TextContentAsync();

        if (successMessage!.Contains("Thanks for your Order!")) { return true;  }

        return false;
    }
}
