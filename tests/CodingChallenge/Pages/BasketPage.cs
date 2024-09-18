using CodingChallenge.Configurations;
using CodingChallenge.Utils;
using Microsoft.Playwright;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CodingChallenge.Pages
{
    public class BasketPage
    {
        private readonly IPage _page;

        public BasketPage(IPage page)
        {
            _page = page;
        }

        //Selector
        private ILocator ContinueShoppingButton => _page.GetByRole(AriaRole.Link, new() { NameString = "[ CONTINUE SHOPPING ]" });
        private ILocator Updatebutton => _page.GetByRole(AriaRole.Link, new() { NameString = "[ UPDATE ]" });
        private ILocator CheckOutButton => _page.GetByRole(AriaRole.Link, new() { NameString = "[ CHECKOUT ]" });

        // Checkout page Selectors
        private ILocator PayNowButton => _page.GetByRole(AriaRole.Button, new() { NameString = "[ Pay Now ]" });

        //Success page Selector
        public ILocator ConfirmationMessage => _page.GetByRole(AriaRole.Heading, new() { NameString = "Thanks for your Order!" });



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
}
