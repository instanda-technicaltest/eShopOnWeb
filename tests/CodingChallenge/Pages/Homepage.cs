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

        public async Task NaviagateTo()
        {
            await _page.GotoAsync(Config.BaseUrl!);
        }

        public async Task NavigateToLoginPage()
        {
            await LoginNavLink.ClickAsync();
            await _page.WaitForURLAsync($"{Config.BaseUrl}{Routes.PageRoutes.LoginPage}");
        }

        public async Task NaviagateToBasketPage()
        {
            await BasketIcon.ClickAsync();
        }

        public async Task AddItemsToBasket(int numberOfItems)
        {
            var itemCount = await ItemsPerPage.CountAsync();

            var random = new Random();
            var selectedItems = Enumerable.Range(0, itemCount).OrderBy(x => random.Next()).Take(numberOfItems).ToList();

            foreach (var itemID in selectedItems)
            {
                var item = ItemsPerPage.Nth(itemID);

                //scroll item into view
                await item.ScrollIntoViewIfNeededAsync();
                await item.ClickAsync();

                //navigate back to home page
                var basketPage = new BasketPage(_page);
                await basketPage.ContinueShopping();
            }
        }
    }
}
