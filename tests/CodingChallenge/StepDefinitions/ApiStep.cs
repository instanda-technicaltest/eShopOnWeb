using CodingChallenge.Api.Builders.Http;
using CodingChallenge.Configurations;
using CodingChallenge.Models;
using Newtonsoft.Json;
using System.Text;
using CodingChallenge.StaticContexts;


namespace CodingChallenge.StepDefinitions;

[Binding]
public class ApiStep
{
    private HttpResponseMessage? _response;
    private string? _apiPath;

    [Given(@"I make a valid login request to the Api ""([^""]*)"" ""([^""]*)""")]
    public async Task GivenIMakeAValidLoginRequestToTheApi(string user, string pass)
    {
        _apiPath = Routes.ApiRoutes.Authentication;
        var payload = GetLoginPayload(user, pass);
        _response = await HttpRequestFactory.PostAsync(
            $"{Config.ApiUrl!}", 
            $"{_apiPath!}",
            payload);

    }

    [Given(@"I make a invalid login request to the Api ""([^""]*)"" ""([^""]*)""")]
    public async Task GivenIMakeAInvalidLoginRequestToTheApi(string user, string pass)
    {
        var payload = GetLoginPayload(user, pass);
        _response = await HttpRequestFactory.PostAsync(
            $"{Config.ApiUrl!}",
            $"{_apiPath!}",
            payload);
    }

    [Then(@"the request should be successful")]
    public  void ThenTheRequestShouldBeSuccessful()
    {
        _response!.IsSuccessStatusCode.Should().BeTrue();
    }



    [Then(@"the request should be not successful")]
    public void ThenTheRequestShouldBeNotSuccessful()
    {
        _response?.IsSuccessStatusCode.Should().BeFalse();
    }


    private static StringContent GetLoginPayload(string user, string pass)
    {
        var userObject = new LoginApiModel
        {
            Username = user,
            Password = pass
        };

        var payload = JsonConvert.SerializeObject(userObject);

        return new StringContent(payload, Encoding.UTF8, "application/json");
    }
}
