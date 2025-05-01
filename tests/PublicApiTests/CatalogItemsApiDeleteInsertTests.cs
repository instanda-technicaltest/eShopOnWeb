using System.Dynamic;
using System.Net.Http.Json;
using FluentAssertions;
using Newtonsoft.Json;

namespace PublicApiTests;

public class CatalogItemsApiDeleteInsertTests : IClassFixture<HttpServiceFixture>, IDisposable
{
    private readonly HttpServiceFixture httpService;
    private long testItemId;
    private string? adminToken;

    public CatalogItemsApiDeleteInsertTests(HttpServiceFixture httpService)
    {
        this.httpService = httpService;
    }

    private async Task<long> InsertTestItemToDeleteLaterAsync(string name)
    {
        adminToken = await httpService.GetTestAuthTokenAsync(true);
        adminToken.Should().NotBeNull("Test malfunction!, unable to get admin token.");

        var testItem = new
        {
            catalogBrandId = int.MaxValue,
            catalogTypeId = int.MaxValue-1,
            description = "Test item",
            name = name,
            price = 999,
            pictureUri = "https://dog.ceo/api/breeds/image/random"
        };
        var request = new HttpRequestMessage(HttpMethod.Post, "catalog-items");
        request.Headers.Add("Authorization", "Bearer " + adminToken);
        request.Content = JsonContent.Create(testItem);

        var response = await httpService.HttpClient.SendAsync(request);
        response.IsSuccessStatusCode.Should().BeTrue("Test malfunction!, unable to create test item for delete api tests.");

        var contentString = await response.Content.ReadAsStringAsync();
        var contentObj = (dynamic) JsonConvert.DeserializeObject<ExpandoObject>(contentString);
        return contentObj.catalogItem.id;
    }

    private void DeleteTestCatalogItem(long testItemId)
    {
        var request = new HttpRequestMessage(HttpMethod.Delete, "catalog-items/" + testItemId);
        request.Headers.Add("Authorization", "Bearer " + adminToken);
        var response = httpService.HttpClient.Send(request);
    }

    private async Task<bool> IsCatalogIdDeleted(long catalogId)
    {
        var response = await httpService.HttpClient.GetAsync(@$"catalog-items/{catalogId}");
        return response.StatusCode == System.Net.HttpStatusCode.NotFound;
    }

    [Fact]
    public async Task CatalogItem_Post_Ok()
    {
        /* Try to insert/POST an item.
           Then check the response is ok.
           Then get the item and assert it's the one we just inserted. */

        Assert.Fail("Test not written yet");
    }

    [Fact]
    public async Task CatalogItem_Put_Ok()
    {
        /* Insert an test item.
           Alter all properties.
           Make a PUT request.
           Then get the item and assert it's the details we just updated. */

        Assert.Fail("Test not written yet");
    }

    [Fact]
    public async Task CatalogItems_Single_Delete_Ok()
    {
        // Arrange        
        testItemId = await InsertTestItemToDeleteLaterAsync(Guid.NewGuid().ToString());

        var deleteRequest = new HttpRequestMessage(HttpMethod.Delete, "catalog-items/" + testItemId);
        deleteRequest.Headers.Add("Authorization", "Bearer " + adminToken);

        // Act
        var actualResponse = httpService.HttpClient.Send(deleteRequest);

        // Assert
        actualResponse.IsSuccessStatusCode.Should().BeTrue();
        var isDeleted = await IsCatalogIdDeleted(testItemId);
        isDeleted.Should().BeTrue();
    }

    [Fact]
    public async Task CatalogItems_Single_Delete_Unauthorized()
    {
        // Arrange
        var itemName = Guid.NewGuid().ToString();
        testItemId = await InsertTestItemToDeleteLaterAsync(itemName);

        /*

          \     |     /
                
        --  No token  --
                      
          /     |     \

        */

        // Act
        var actualResponse = await httpService.HttpClient.DeleteAsync("catalog-items/" + testItemId);

        // Assert
        actualResponse.StatusCode.Should().Be(System.Net.HttpStatusCode.Unauthorized);
        var isDeleted = await IsCatalogIdDeleted(testItemId);
        isDeleted.Should().BeFalse();
    }

    [Fact]
    public async Task CatalogItems_Single_Delete_Forbidden()
    {
        // Arrange
        var itemName = Guid.NewGuid().ToString();
        testItemId = await InsertTestItemToDeleteLaterAsync(itemName);

        var nonAdminToken = await httpService.GetTestAuthTokenAsync();
        var deleteRequest = new HttpRequestMessage(HttpMethod.Delete, "catalog-items/" + testItemId);
        deleteRequest.Headers.Add("Authorization", "Bearer " + nonAdminToken);

        // Act
        var actualResponse = httpService.HttpClient.Send(deleteRequest);

        // Assert
        actualResponse.StatusCode.Should().Be(System.Net.HttpStatusCode.Forbidden);
        var isDeleted = await IsCatalogIdDeleted(testItemId);
        isDeleted.Should().BeFalse();
    }

    public void Dispose()
    {
        DeleteTestCatalogItem(testItemId);
    }
}
