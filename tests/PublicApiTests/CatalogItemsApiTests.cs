using System.Dynamic;
using FluentAssertions;
using Newtonsoft.Json;

namespace PublicApiTests;

public class CatalogItemsApiTests : IClassFixture<HttpServiceFixture>
{
    private readonly HttpServiceFixture httpService;

    public CatalogItemsApiTests(HttpServiceFixture httpService)
    {
        this.httpService = httpService;
    }

    [Theory]
    [InlineData(null, null, new [] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, "Expect all items")]
    [InlineData(2, 3, new[] { 10, 11 }, "Expect catalog brand 2 and catalog type 3 only")]
    [InlineData(null, 3, new[] { 5, 10, 11 }, "Expect catalog type 3 only")]
    public async Task CatalogItems_Multiple_Get_Ok(int? catalogBrandId, int? catalogTypeId, int[] expectedIds, string testDescription)
    {
        // Arrange
        var parts = new List<string> { "catalog-items?pageSize=10" };

        if (catalogBrandId.HasValue)
            parts.Add("catalogBrandId=2");

        if (catalogTypeId.HasValue)
            parts.Add("catalogTypeId=3");

        // Act
        var response = await httpService.HttpClient.GetAsync(string.Join("&", parts));

        // Assert
        response.IsSuccessStatusCode.Should().BeTrue();

        var contentString = await response.Content.ReadAsStringAsync();
        var contentDict = JsonConvert.DeserializeObject<ExpandoObject>(contentString) as IDictionary<string, object>;

        contentDict!.Keys.Should().HaveCount(2);
        contentDict!.First().Key.Should().Be("catalogItems");
        contentDict!.Last().Key.Should().Be("pageCount");

        var contentItemList = (List<object>)contentDict.First().Value;
        var contentItemDicts = contentItemList.Select(s => s as IDictionary<string, object>).ToList();
        contentItemDicts.Should().HaveCount(expectedIds.Length);

        if (catalogBrandId.HasValue)
            contentItemDicts.Should().AllSatisfy(x => x["catalogBrandId"].Should().Be(catalogBrandId));

        if (catalogTypeId.HasValue)
            contentItemDicts.Should().AllSatisfy(x => x["catalogTypeId"].Should().Be(catalogTypeId));

    }

    [Fact]
    public async Task CatalogItems_Single_Get_Ok()
    {
        // Arrange
        const int catalogId = 11;
        var expectedDict = new Dictionary<string, object>()
        {
            { "id", catalogId },
            { "name", "Cup<T> Sheet" },
            { "description", "Cup<T> Sheet" },
            { "price", 8.5 },
            { "pictureUri", "/images/products/11.png" },
            { "catalogTypeId", 3 },
            { "catalogBrandId", 2 }
        };

        // Act
        var response = await httpService.HttpClient.GetAsync(@$"catalog-items/{catalogId}");

        // Assert
        response.IsSuccessStatusCode.Should().BeTrue();

        var contentString = await response.Content.ReadAsStringAsync();
        var contentDict = JsonConvert.DeserializeObject<ExpandoObject>(contentString) as IDictionary<string, object>;

        contentDict!.Keys.Should().HaveCount(1);
        contentDict!.Single().Key.Should().Be("catalogItem");

        var contentItemDict = contentDict.First().Value as IDictionary<string, object>;

        contentItemDict.Should().BeEquivalentTo(expectedDict);
    }

    [Theory(Skip = "Test not written yet")]
    [InlineData(1, 3, "Expect to get one item on notional page 3")]
    [InlineData(3, 1, "Expect to get three items on notional page 1")]
    [InlineData(5, 2, "Expect to get upto five (actually one) item on notional page 2")]
    public async Task CatalogItems_Paging_Ok(int pageSize, int pageIndex, string description)
    {
        /* In the 'test database' we have six items with catalogBrandId = 2

           Use catalogBrandId 2 for all these tests.

           Using the inline data we can test the NUMBER of items returned on the page. 
           I think that's all we need to do here. */

    }
}
