using System.Dynamic;
using FluentAssertions;
using Newtonsoft.Json;
using Xunit.Abstractions;

namespace PublicApiTests;

public class AuthenticateApiTests : IClassFixture<HttpServiceFixture>
{
    private readonly HttpServiceFixture httpService;
    private readonly ITestOutputHelper outputHelper;

    public AuthenticateApiTests(HttpServiceFixture httpService, ITestOutputHelper outputHelper)
    {
        this.httpService = httpService;
        this.outputHelper = outputHelper;
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
        outputHelper.WriteLine($"Actual token:{actualToken}");

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
        // Arrange
        const string expectedItemsString = @"{
  ""catalogBrands"": [
    {
      ""id"": 1,
      ""name"": ""Azure""
    },
    {
      ""id"": 2,
      ""name"": "".NET""
    },
    {
      ""id"": 3,
      ""name"": ""Visual Studio""
    },
    {
      ""id"": 4,
      ""name"": ""SQL Server""
    },
    {
      ""id"": 5,
      ""name"": ""Other""
    }
  ]
}";
        var expectedDict = JsonConvert.DeserializeObject<ExpandoObject>(expectedItemsString) as IDictionary<string, object>;

        // Act
        var response = await httpService.HttpClient.GetAsync("catalog-brands");

        // Assert
        response.IsSuccessStatusCode.Should().BeTrue();

        var stringContent = await response.Content.ReadAsStringAsync();
        var contentDict = JsonConvert.DeserializeObject<ExpandoObject>(stringContent) as IDictionary<string, object>;
        contentDict.Should().BeEquivalentTo(expectedDict);
    }
}
