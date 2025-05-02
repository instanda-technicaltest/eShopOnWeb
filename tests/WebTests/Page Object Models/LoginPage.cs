using Microsoft.Playwright;

namespace WebTests.Page_Object_Models;

public class LoginPage 
{
    private readonly IPage page;
    private ILocator LoginButton => page.Locator("button").GetByText("Log in");
    private ILocator RegisterButton => page.Locator("a").GetByText("Register as a new user");
    private ILocator EmailInput => page.Locator("#Input_Email");
    private ILocator PasswordInput => page.Locator("#Input_Password");

    public LoginPage(IPage page)
    {
        this.page = page;
    }

    public async Task GotoAsync() => await page.GotoAsync("/Login", new PageGotoOptions { WaitUntil = WaitUntilState.DOMContentLoaded });

    public async Task<IndexPage?> ClickLoginAsync()
    {
        await Assertions.Expect(LoginButton).ToBeAttachedAsync();
        await LoginButton.ClickAsync();
        var stillOnLoginPage = await EmailInput.IsVisibleAsync();
        if (!stillOnLoginPage)
            return new IndexPage(page); 
        else
            return null;
    }
    public async Task<IndexPage?> ClickRegisterAsync()
    {
        await Assertions.Expect(RegisterButton).ToBeAttachedAsync();
        await RegisterButton.ClickAsync();
        return null; //WIP: new RegisterPage(page);
    }

    public async Task EnterEmail(string email)
    {
        await Assertions.Expect(EmailInput).ToBeAttachedAsync();
        await EmailInput.FillAsync(email);
    }
    public async Task EnterPassword(string password)
    {
        await Assertions.Expect(PasswordInput).ToBeAttachedAsync();
        await PasswordInput.FillAsync(password);
    } 
}
