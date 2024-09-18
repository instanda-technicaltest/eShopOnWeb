using CodingChallenge.Configurations;
using Microsoft.Playwright;
using CodingChallenge.StaticContexts;

namespace CodingChallenge.Pages;

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
        return errorMessageAppears > 0;
    }
}
