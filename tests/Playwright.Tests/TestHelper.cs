using Microsoft.Playwright;
using System.Diagnostics.CodeAnalysis;


namespace Playwright.Tests;
public static class TestHelper
{
    public static async Task SignIn(IPage page, string email, string password)
    {
        await page.GetByText("Login").ClickAsync();
        await page.Locator("[id='Input_Email']").FillAsync(email);
        await page.Locator("[id='Input_Password']").FillAsync(password);
        await page.ClickAsync("button[type='submit']");
    }

    public static async Task AddItemToBasket(IPage page)
    {
        await page.Locator("input[type='submit']").Nth(1).ClickAsync();
    }

    public static async Task ContinueShopping(IPage page)
    {
        await page.ClickAsync(".btn.esh-basket-checkout.text-white");
        //await page.GetByRole(AriaRole.Link, new() { Name = "[ Continue Shopping ]" }).ClickAsync();
    }

    public static async Task Checkout(IPage page)
    {
        await page.GetByRole(AriaRole.Link, new() { NameString = "[ Checkout ]" }).ClickAsync();
        await page.GetByRole(AriaRole.Button, new() { NameString = "[ Pay Now ]" }).ClickAsync();
        await Assertions.Expect(page.Locator("text=Thanks for your Order!")).ToBeVisibleAsync();
    }

    public static async Task RemoveItemFromCheckout(IPage page)
    {
        await page.Locator("input[name=\"Items\\[0\\]\\.Quantity\"]").ClickAsync();
        await page.Locator("input[name=\"Items\\[0\\]\\.Quantity\"]").FillAsync("0");
        await page.GetByRole(AriaRole.Button, new() { NameString = "[ Update ]" }).ClickAsync();
        await page.GetByRole(AriaRole.Heading, new() { NameString = "Basket is empty." }).ClickAsync();
        await page.GetByRole(AriaRole.Link, new() { NameString = "[ Continue Shopping ]" }).ClickAsync();

    }
}

