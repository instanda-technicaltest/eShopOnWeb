using CodingChallenge.Pages;
using Microsoft.Playwright;

namespace CodingChallenge.StepDefinitions;

[Binding]
public class LoginSteps
{

    private readonly LoginPage _loginPage;
    public LoginSteps(IPage page)
    {
        _loginPage = new LoginPage(page);
    }

    [Given(@"I Navigate to the login page")]
    public async Task GivenINavigateToTheLoginPage()
    {
        await _loginPage.NavigateToAsync();
    }

    [When(@"I login with a valid username ""([^""]*)"" and password ""([^""]*)""")]
    public async Task WhenIAttemptToLoginWithAValidUsernameAndPassword(string username, string password)
    {
        await _loginPage.Login(username, password);
    }

    [When(@"I login with an invalid username ""([^""]*)"" and password ""([^""]*)""")]
    public async Task WhenIAttemptToLoginWithAnInvalidUsernameAndPassword(string username, string password)
    {
        await _loginPage.Login(username, password);
    }


    [Then(@"I should be logged in successfully ""([^""]*)""")]
    public async Task ThenIShouldBeLoggedInSuccessfully(string username)
    {
        var isUserLoggedIn = await _loginPage.IsUserSignedIn(username);
        isUserLoggedIn.Should().Be(true);
    }

    [Then(@"I should not be logged in ""([^""]*)""")]
    public async Task ThenIShouldNotBeLoggedIn(string username)
    {
        var isUserLoggedIn = await _loginPage.IsUserSignedIn(username);
        isUserLoggedIn.Should().Be(false);
    }


    [Then(@"I should be presented with an error message ""([^""]*)""")]
    public async Task ThenIShouldBePresentedWithAnErrorMessage(string errorMessage)
    {
        var containsErrorMessage =  await _loginPage.ContainsErrorMessage(errorMessage);
        containsErrorMessage.Should().BeTrue();
    }

}
