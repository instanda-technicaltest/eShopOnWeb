
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Net;
using Castle.Core.Internal;
namespace Tests;

[TestClass]
public class IntegrationTestsHTTP
{
    public const string apiUrl = "https://localhost:5099";
    private HttpClient _httpClient;

    [TestInitialize]
    public void TestInitialize()
    {
        _httpClient = new HttpClient
        {
            BaseAddress = new Uri(apiUrl)
        };
    }

    [TestMethod]
    public async Task GetCatalogItems_ReturnsOkResponse()
    {
        // Act: Send the request to API
        var response = await _httpClient.GetAsync("api/catalog-items");
        var responseBody = await response.Content.ReadAsStringAsync();

        // Assert: Check the HTTP status code
        Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
        Assert.IsNotNull(responseBody);
    }

    [TestMethod]
    public async Task GetCatalogItemById_ValidId_ReturnsOk()
    {
        // Arrange
        int catalogItemId = 1;

        // Act
        var response = await _httpClient.GetAsync($"api/catalog-items/{catalogItemId}");
        var responseBody = await response.Content.ReadAsStringAsync();

        // Assert
        Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
        Assert.IsTrue(responseBody.Contains(catalogItemId.ToString()));
    }

    [TestMethod]
    public async Task GetCatalogItemById_InvalidId_ReturnsNotFound()
    {
        // Arrange
        int catalogItemId = -1;

        // Act
        var response = await _httpClient.GetAsync($"api/catalog-items/{catalogItemId}");
        var responseBody = await response.Content.ReadAsStringAsync();
        
        // Assert
        Assert.AreEqual(HttpStatusCode.NotFound, response.StatusCode);
        Assert.IsTrue(responseBody.IsNullOrEmpty());
    }
}