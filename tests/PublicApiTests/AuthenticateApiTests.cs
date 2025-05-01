using System.Dynamic;
using System.Net.Http.Json;
using FluentAssertions;
using Newtonsoft.Json;

namespace PublicApiTests;

public class AuthenticateApiTests : IClassFixture<HttpServiceFixture>
{
    private readonly HttpServiceFixture httpService;


    public AuthenticateApiTests(HttpServiceFixture httpService)
    {
        this.httpService = httpService;
    }

    [Fact]
    public async Task Authenticate_Post_Ok()
    {
        var content = HttpServiceFixture.GetTestAuthRequestContent();

        // Act
        var response = await httpService.HttpClient.PostAsync("authenticate", content);

        // Assert
        response.IsSuccessStatusCode.Should().BeTrue();

        var stringContent = await response.Content.ReadAsStringAsync();
        var contentDict = JsonConvert.DeserializeObject<ExpandoObject>(stringContent) as IDictionary<string, object>;

        contentDict.Should().ContainKey("token");
        contentDict["token"].Should().BeOfType<string>();
        var actualToken = (string)contentDict["token"];
        actualToken.Should().NotBeNullOrEmpty();

        contentDict.Should().ContainKey("result");
        contentDict["result"].Should().BeAssignableTo<bool>();
        var actualResult = (bool)contentDict["result"];
        actualResult.Should().BeTrue();
    }

    [Theory]
    [InlineData("unknown@email", null)]
    [InlineData(null, "incorrect password")]
    [InlineData("unknown@email", "incorrect password")]
    public async Task Authenticate_Post_Unauthorised(string? username, string? password)
    {
        // Arrange
        var content = HttpServiceFixture.GetTestAuthRequestContent(username, password);

        // Act
        var response = await httpService.HttpClient.PostAsync("authenticate", content);

        // Assert
        response.IsSuccessStatusCode.Should().BeTrue();

        var stringContent = await response.Content.ReadAsStringAsync();
        var contentDict = JsonConvert.DeserializeObject<ExpandoObject>(stringContent) as IDictionary<string, object>;

        contentDict.Should().ContainKey("token");
        contentDict["token"].Should().BeOfType<string>();
        var actualToken = (string)contentDict["token"];
        actualToken.Should().BeEmpty();

        contentDict.Should().ContainKey("result");
        contentDict["result"].Should().BeAssignableTo<bool>();
        var actualResult = (bool)contentDict["result"];
        actualResult.Should().BeFalse();
    }

    [Fact]
    public async Task CatalogBrands_Get_Ok()
    {
        var response = await httpService.HttpClient.GetAsync("catalog-brands");

        response.IsSuccessStatusCode.Should().BeTrue();

        var stringContent = await response.Content.ReadAsStringAsync();
        var content = JsonConvert.DeserializeObject<ExpandoObject>(stringContent);
    }
}
