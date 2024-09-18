using CodingChallenge.Configurations;
using CodingChallenge.Utils;
using Microsoft.Playwright;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace CodingChallenge.Pages
{
    public class LoginPage
    {
        private readonly IPage _page;

        public LoginPage(IPage page)
        {
            _page = page;
        }


        //Selectors 
        private ILocator Username => _page.GetByLabel("Email");
        private ILocator Password => _page.GetByLabel("Password");
        private ILocator LoginButton => _page.GetByRole(AriaRole.Button, new() { NameString = "Log in" });

        private ILocator LoggedInUserName => _page.Locator(".esh-identity-section, esh-identity-name");

        //Methods
        public async Task Login(string username, string password)
        {
            await Username.FillAsync(username);
            await Password.FillAsync(password);
            await LoginButton.ClickAsync();
        }

        public async Task NavigateToAsync()
        {
            await _page.GotoAsync(Config.BaseUrl + Routes.PageRoutes.LoginPage);
        }

        public async Task<bool?> IsUserSignedIn(string username) 
        {

            var name = await LoggedInUserName.TextContentAsync();
            return name!.Contains(username);
        }

        public async Task<bool> ContainsErrorMessage(string errorMessage)
        {
          var errorMessageAppears = await  _page.GetByText(errorMessage).CountAsync();
            if (errorMessageAppears > 0) { return true; }
            return false;
        }
    }
}
